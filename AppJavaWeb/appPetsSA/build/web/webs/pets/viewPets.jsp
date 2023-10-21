<%@page import="model.PetClient"%>
<%@page import="dao.DetailDAO"%>
<%@page import="model.Detail"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.Pet"%>
<%@page import="dao.PetDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mascotas</title>
        <link rel="shortcut icon" href="assets/petssa.png" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/9fc76cebfc.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/pets.css">
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
                    String id = "";
                    boolean flag = false;

                    PetDAO objPetDAO = new PetDAO();
                    List<PetClient> objListPet = null;
                    try {
                        id = (String) request.getParameter("ic");
                        if (!id.isEmpty() || !id.equals("")) {
                            objListPet = objPetDAO.listByOwner(id);
                            flag = true;
                        } else {
                            objListPet = objPetDAO.list();
                            flag = false;
                        }
                    } catch (Exception e) {
                        id = "";
                        objListPet = objPetDAO.list();
                    }
                %>

                <h1 class="text-center"><%= (flag) ? objListPet.get(0).getClient().getName() + " "
                        + objListPet.get(0).getClient().getSecondName() + " " + objListPet.get(0).getClient().getSurname()
                        + " " + objListPet.get(0).getClient().getSecondSurname() : "Todas las mascotas"%></h1>

                <%
                    if (flag) {
                %> 
                <div class="container d-flex justify-content-center">
                    <div class="nueva-mascota" >
                        <p class="text-center title">
                            <a class="btn btn-success" 
                               href="PetController?action=new-pet&id=<%= objListPet.get(0).getPet().getOwnerId()%>&ic=<%= objListPet.get(0).getPet().getOwnerCedula()%>">
                                <i class="fa-solid fa-plus" style="color: #ffffff; padding-inline-end: 8px;"></i>
                                Nueva mascota
                            </a>
                        </p>
                    </div>
                </div>
                <%
                    }
                %> 
                <%
                    Iterator<PetClient> iter = objListPet.iterator();
                    PetClient petClient = null;
                    DetailDAO objDetailDAO = new DetailDAO();
                    while (iter.hasNext()) {
                        petClient = iter.next();
                        List<Detail> obListDetails = objDetailDAO.list(petClient.getPet().getIdentification());
                        Iterator<Detail> iterDetail = obListDetails.iterator();
                        Detail detail = null;
                %>

                <div class="contenedor">
                    <div class="formulario">
                        <h3>Información de la mascota</h3>
                        <div class="formulario f2">
                            <p class="title">Nombre: <span class="text"><%= petClient.getPet().getName()%></span></p>
                            <p class="title">Raza: <span class="text"><%= petClient.getPet().getBreed()%></span></p>
                            <p class="title">Edad: <span class="text"><%= petClient.getPet().getAge()%></span></p>
                            <p class="title">Peso: <span class="text"><%= petClient.getPet().getWeight()%></span></p>

                            <% if (!flag) {%>
                            <p class="title">Propietario: 
                                <span class="text"><%= petClient.getClient().getName() + " "
                                        + petClient.getClient().getSecondName() + " " + petClient.getClient().getSurname()
                                        + " " + petClient.getClient().getSecondSurname()%>
                                </span>
                            </p>
                            <%
                                }
                                if (flag) {%>
                            <div class="text-center">
                                <a class="btn btn-secondary" href="PetController?action=edit-pet&id=<%= petClient.getPet().getOwnerId()%>&ic=<%= petClient.getPet().getOwnerCedula()%>&ip=<%= petClient.getPet().getIdentification()%>">
                                    <i class="fa-solid fa-pen-to-square" style="color: #ffffff;"></i>
                                    Modificar
                                </a>
                                <a class="btn btn-danger" href="PetController?action=pet-remove&id=<%= petClient.getPet().getIdentification()%>">
                                    <i class="fa-solid fa-pen-to-trash" style="color: #ffffff;"></i>
                                    Eliminar
                                </a>
                            </div>
                            <%}%>
                        </div>
                    </div>
                    <div class="tabla-medicamentos">
                        <h3>Lista de medicamentos</h3>
                        <div class="table-responsive">
                            <table class="table table-borderless">
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Dosis</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        while (iterDetail.hasNext()) {
                                            detail = iterDetail.next();
                                    %>
                                    <tr>
                                        <td><%= detail.getName()%></td>
                                        <td><%= detail.getDosage()%></td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <%}%>
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
