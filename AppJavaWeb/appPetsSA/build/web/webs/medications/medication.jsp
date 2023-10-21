<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.Medication"%>
<%@page import="dao.MedicationDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Medicamentos</title>
        <link rel="shortcut icon" href="assets/petssa.png" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/9fc76cebfc.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/pets.css">
        <script src="js/script.js"></script>
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
                <div class="contenedor">
                    <div class="formulario">
                        <%
                            int edit = Integer.parseInt((String) request.getAttribute("flag"));
                            Medication objMedication = new Medication();
                            int id = 0;
                            if (edit != 0) {
                                MedicationDAO objMedicationDAO2 = new MedicationDAO();
                                id = Integer.parseInt((String) request.getAttribute("idMed"));
                                objMedication = (Medication) objMedicationDAO2.list(id);
                            }
                        %>
                        <h3><%= (edit == 0) ? "Añadir" : "Modificar"%> medicamento</h3>
                        <div class="formulario f2">
                            <form action="MedicationController">
                                <input hidden="" class="form-control" type="text" name="id" value="<%= (edit == 0) ? "" : objMedication.getIdentification()%>" autofocus>

                                <label class="label-control">Nombre
                                    <input id="nameInput" class="form-control" type="text" name="name" value="<%= (edit == 0) ? "" : objMedication.getName()%>" autofocus>
                                </label>
                                <div id="nameError" class="alert alert-danger" style="display: none; "role="alert">
                                    Este campo es obligatorio.
                                </div>

                                <label class="label-control">Descripción
                                    <input id="descriptionInput" class="form-control" type="text" name="description" value="<%= (edit == 0) ? "" : objMedication.getDescription()%>" autofocus>
                                </label>
                                <div id="descriptionError" class="alert alert-danger" style="display: none;" role="alert">
                                    Este campo es obligatorio.
                                </div>

                                <div hidden="<%= (edit == 0) ? true : false%>" class="alert alert-warning" role="alert">
                                    Las modificaciones realizadas se actualizan automáticamente en la lista, pero no se guardan hasta que haga clic en el botón 'Modificar'.
                                    <a class="alert-link">Click aquí para descartar</a>.
                                </div>
                                <div class="btn-acciones">
                                    <button class="btn btn-success btn-control" type="submit" name="action" value="<%= (edit == 0) ? "add" : "save"%>">
                                        <span><%= (edit == 0) ? "Guardar" : "Modificar"%></span>
                                    </button>
                                    <%if (edit != 0) {%>
                                    <button class="btn btn-danger btn-control" name="action" value="discard">
                                        <span>Descartar</span>
                                    </button>
                                    <%}%>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="tabla-medicamentos">
                        <h3>Lista de medicamentos</h3>
                        <div class="table-responsive">
                            <div class="custom-input">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                <input type="text" id="searchInput" class="form-control" placeholder="Buscar...">
                            </div>
                            <table class="table table-borderless">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Nombre</th>
                                        <th>Descripción</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        MedicationDAO objMedicationDAO = new MedicationDAO();
                                        List<Medication> objListMedication = objMedicationDAO.list();
                                        Iterator<Medication> iter = objListMedication.iterator();
                                        Medication medication = null;
                                        while (iter.hasNext()) {
                                            medication = iter.next();
                                    %>
                                    <tr class="row-medication">
                                        <td><%= medication.getIdentification()%></td>
                                        <td><%= medication.getName()%></td>
                                        <td><%= medication.getDescription()%></td>
                                        <td class="td-acciones">
                                            <a class="btn btn-primary" href="MedicationController?action=edit&id=<%= medication.getIdentification()%>"><i class="fa-solid fa-pen-to-square"></i></a>
                                            <a class="btn btn-danger" href="MedicationController?action=remove&id=<%= medication.getIdentification()%>"><i class="fa-solid fa-trash"></i></a>
                                        </td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
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
