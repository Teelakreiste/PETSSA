<%@page import="dao.DetailDAO"%>
<%@page import="model.Detail"%>
<%@page import="model.PetClient"%>
<%@page import="dao.PetDAO"%>
<%@page import="model.Pet"%>
<%@page import="model.Medication"%>
<%@page import="dao.MedicationDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Mascotas</title>
        <link rel="shortcut icon" href="assets/petssa.png" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/9fc76cebfc.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/pets.css">
        <script src="js/pet-script.js"></script>
    </head>
    <body>
        <header>
            <nav class="nav">
                <ul class="nav-list">
                    <li class="nav-item">
                        <a href="/appPetsSA" class="btn btn-header text-logo" routerLink="/">
                            <img src="assets/petssa.png" alt="" class="img-logo">PETS S.A.
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-header" href="ClientController?action=clients">Clientes
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-header" href="PetController?action=viewPets" >Mascotas
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-header" href="MedicationController?action=medication" >Medicamentos
                        </a>
                    </li>
                </ul>
            </nav>
        </header>

        <main>
            <div class="medicamentos">
                <%
                    int edit = Integer.parseInt((String) request.getAttribute("flag"));
                    int id = -1;
                    String ic = "";
                    int ip = -1;
                    Pet objPet = new Pet();
                    try {
                        id = Integer.parseInt((String) request.getAttribute("id"));
                        ic = (String) request.getAttribute("ic");
                        ip = Integer.parseInt((String) request.getAttribute("ip"));
                    } catch (Exception e) {
                    }
                    if (edit != 0 && !ic.isEmpty() && ip != -1) {
                        PetDAO objPetDAO = new PetDAO();
                        objPet = (Pet) objPetDAO.list(ic, ip);
                    }
                %>
                <div class="contenedor <%= (edit == 0) ? "d-flex justify-content-center" : ""%>">
                    <div class="formulario">
                        <h3><%= (edit == 0) ? "Añadir" : "Modificar"%> mascota</h3>
                        <div class="formulario f2">
                            <form id="form" action="PetController">
                                <input hidden="" class="form-control" type="text" name="id" value="<%= id%>" autofocus>
                                <input hidden="" class="form-control" type="text" name="ic" value="<%= ic%>" autofocus>
                                <input hidden="" class="form-control" type="text" name="ip" value="<%= ip%>" autofocus>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label class="label-control"> Nombre
                                            <input id="nameInput" type="text" name="name" class="form-control" value="<%= (ip == -1) ? "" : objPet.getName()%>" autofocus>
                                        </label>
                                        <div id="nameError" class="alert alert-danger" style="display: none;" role="alert">
                                            Este campo es obligatorio.
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="label-control"> Raza
                                            <input id="breedInput" type="text" name="breed" class="form-control" value="<%= (ip == -1) ? "" : objPet.getBreed()%>" autofocus>
                                        </label>
                                        <div id="breedError" class="alert alert-danger" style="display: none;" role="alert">
                                            Este campo es obligatorio.
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label class="label-control"> Edad
                                            <input id="ageInput" type="number" min="0" max="999" name="age" class="form-control" value="<%= (ip == -1) ? "" : objPet.getAge()%>" autofocus>
                                        </label>
                                        <div id="ageError" class="alert alert-danger" style="display: none;" role="alert">
                                            Este campo es obligatorio.
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="label-control"> Peso
                                            <input id="weightInput" type="number" step="0.01" min="0" max="999" name="weight" class="form-control" value="<%= (ip == -1) ? "" : objPet.getWeight()%>" autofocus>
                                        </label>
                                        <div id="weightError" class="alert alert-danger" style="display: none;" role="alert">
                                            Este campo es obligatorio.
                                        </div>
                                    </div>
                                </div>
                                <div class="btn-acciones">
                                    <button class="btn btn-success btn-control" type="submit" name="action" value="<%= (edit == 0) ? "pet-add" : "pet-save"%>">
                                        <span><%= (edit == 0) ? "Guardar" : "Modificar"%></span>
                                    </button>
                                    <%if (edit != 0) {%>
                                    <a style="text-decoration:none; color: #fff;" href="PetController?action=viewPets&id=<%= id%>&ic=<%= ic%>">
                                        <button class="btn btn-danger btn-control" type="button">
                                            Descartar
                                        </button>
                                    </a>
                                    <%}%>
                                </div>

                            </form>
                        </div>
                        <%
                            if (edit != 0) {
                        %>
                        <h3>Asignar medicamento</h3>
                        <div class="formulario f2">
                            <form id="medications-form" action="PetController">
                                <input hidden="" class="form-control" type="text" name="ipD" value="<%= ip%>" autofocus>
                                <input hidden="" class="form-control" type="text" name="idD" value="<%= id%>" autofocus>
                                <input hidden="" class="form-control" type="text" name="icD" value="<%= ic%>" autofocus>
                                <div class="row">
                                    <div class="col-md-5">
                                        <label class="label-control"> Medicamento
                                            <select id="medicamentos" name="medicamentos" class="form-control">
                                                <option value="" selected>Selecciona un medicamento</option>
                                                <%
                                                    MedicationDAO objMedicationDAO = new MedicationDAO();
                                                    List<Medication> obListMedications = objMedicationDAO.list();
                                                    Iterator<Medication> iterDetail = obListMedications.iterator();
                                                    Medication detail = null;
                                                    while (iterDetail.hasNext()) {
                                                        detail = iterDetail.next();
                                                %>
                                                <option value="<%= detail.getIdentification()%>">
                                                    <%= detail.getName()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </label>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="label-control">Dosis
                                            <input id="dosageInput" type="text" name="dosis" class="form-control" autofocus>
                                        </label>
                                        <div id="dosageError" class="alert alert-danger" style="display: none;" role="alert">
                                            Este campo es obligatorio.
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                        <br>
                                        <button id="submitDosage" class="btn btn-primary"  type="submit" name="action" value="add-dosage">
                                            <i class="fa-solid fa-check" style="color: #fff;"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <%
                        if (edit != 0) {
                    %>
                    <div class="tabla-medicamentos">
                        <h3>Medicamentos asignados</h3>
                        <div class="table-responsive">
                            <table class="table table-borderless">
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Dosis</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        DetailDAO objDetailDAO = new DetailDAO();
                                        List<Detail> obListDetails = objDetailDAO.list(ip);
                                        Iterator<Detail> iterDetail = obListDetails.iterator();
                                        Detail detail = null;
                                        while (iterDetail.hasNext()) {
                                            detail = iterDetail.next();
                                    %>
                                    <tr>
                                        <td><%= detail.getName()%></td>
                                        <td><%= detail.getDosage()%></td>
                                        <% if (ip != -1) {%>
                                        <td class="td-acciones">
                                            <a class="btn btn-danger" href="PetController?action=medication-remove&petId=<%= ip%>&medId=<%= detail.getIdentification()%>&id=<%= id%>&ic=<%= ic%>">
                                                <i class="fa-solid fa-trash"></i>
                                            </a>
                                        </td>
                                        <%}%>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </main>

        <footer>
            <!-- <hr> -->
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <p class="text-footer">
                            <img class="img-logo-footer" src="assets/petssa.png" alt="">
                            <strong>PETS S.A.</strong> © 2023 - Todos los derechos reservados.
                        </p>
                        <p class="text-footer">
                            Desarrollado por: Teelakreiste | <a href="https://github.com/Teelakreiste" target="_blank"><i
                                    class="fa-brands fa-github"></i></a>
                        </p>
                    </div>
                </div>
            </div>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    </body>
</html>
