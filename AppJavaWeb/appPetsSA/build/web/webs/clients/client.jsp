<%@page import="java.util.Iterator"%>
<%@page import="model.Client"%>
<%@page import="java.util.List"%>
<%@page import="dao.ClientDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Clientes</title>
        <link rel="shortcut icon" href="assets/petssa.png" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/9fc76cebfc.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/pets.css">
        <script src="js/client-script.js"></script>
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
            <div class="clientes">
                <div class="contenedor">
                    <div class="formulario">
                        <%
                            int edit = Integer.parseInt((String) request.getAttribute("flag"));
                            Client objClient = new Client();
                            ClientDAO objClientDAO = new ClientDAO();
                            String id = "";
                            if (edit != 0) {
                                id = (String) request.getAttribute("idCli");
                                objClient = (Client) objClientDAO.list(id);
                            }
                        %>
                        <h3>Añadir clientes</h3>
                        <div class="formulario f2">
                            <form action="ClientController">
                                <input hidden="" class="form-control" type="text" name="id" value="<%= (edit == 0) ? "" : objClient.getIdentification()%>">

                                <label class="label-control"> Cédula
                                    <input id="cedulaInput" type="text" name="cedula" class="form-control <%=(edit != 0) ? "readonly" : ""%>" value="<%= (edit == 0) ? "" : objClient.getCedula()%>" <%= (edit == 0) ? "autofocus" : "readonly"%> >
                                </label>
                                <div id="cedulaError" class="alert alert-danger" style="display: none;" role="alert">
                                    Este campo es obligatorio.
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <label class="label-control"> Primer nombre
                                            <input id="nameInput" type="text" name="name" class="form-control" value="<%= (edit == 0) ? "" : objClient.getName()%>" autofocus>
                                        </label>
                                        <div id="nameError" class="alert alert-danger" style="display: none;" role="alert">
                                            Este campo es obligatorio.
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <label id="secondNameError" class="label-control"> Segundo nombre
                                            <input id="secondNameInput" type="text" name="second_name" class="form-control" value="<%= (edit == 0) ? "" : objClient.getSecondName()%>" autofocus>
                                        </label>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <label class="label-control"> Primer apellido
                                            <input id="surnameInput" type="text" name="surname" class="form-control" value="<%= (edit == 0) ? "" : objClient.getSurname()%>" autofocus>
                                        </label>
                                        <div id="surnameError" class="alert alert-danger" style="display: none;" role="alert">
                                            Este campo es obligatorio.
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="label-control"> Segundo apellido
                                            <input id="secondSurnameInput" type="text" name="second_surname" class="form-control" value="<%= (edit == 0) ? "" : objClient.getSecondSurname()%>" autofocus>
                                        </label>
                                        <div id="secondSurnameError" class="alert alert-danger" style="display: none;" role="alert">
                                            Este campo es obligatorio.
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-8">
                                        <label class="label-control"> Dirección
                                            <input id="addressInput" type="text" name="address" class="form-control" value="<%= (edit == 0) ? "" : objClient.getAddress()%>" autofocus>
                                        </label>
                                        <div id="addressError" class="alert alert-danger" style="display: none;" role="alert">
                                            Este campo es obligatorio.
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <label class="label-control"> Teléfono
                                            <input id="phoneInput" type="text" name="phone" class="form-control" value="<%= (edit == 0) ? "" : objClient.getPhone()%>" autofocus>
                                        </label>
                                        <div id="phoneError" class="alert alert-danger" style="display: none;" role="alert">
                                            Este campo es obligatorio.
                                        </div>
                                    </div>
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
                        <h3>Lista de clientes</h3>
                        <div class="table-responsive">
                            <div class="custom-input">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                <input type="text" id="searchInput" class="form-control" placeholder="Buscar...">
                            </div>
                            <table class="table table-borderless">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Cédula</th>
                                        <th>Nombres</th>
                                        <th>Apellidos</th>
                                        <th>Dirección</th>
                                        <th>Teléfono</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        //ClientDAO objClientDAO = new ClientDAO();
                                        List<Client> objListClient = objClientDAO.list();
                                        Iterator<Client> iter = objListClient.iterator();
                                        Client client = null;
                                        while (iter.hasNext()) {
                                            client = iter.next();
                                    %>
                                    <tr class="row-client">
                                        <td><%= client.getIdentification()%></td>
                                        <td><%= client.getCedula()%></td>
                                        <td><%= client.getName() + " " + client.getSecondName()%></td>
                                        <td><%= client.getSurname() + " " + client.getSecondSurname()%></td>
                                        <td><%= client.getAddress()%></td>
                                        <td><%= client.getPhone()%></td>
                                        <td class="td-acciones">
                                            <a class="btn btn-primary" href="ClientController?action=edit&id=<%= client.getCedula()%>"><i
                                                    class="fa-solid fa-pen-to-square"></i></a>
                                            <a class="btn btn-secondary" href="PetController?action=viewPets&id=<%= client.getIdentification()%>&ic=<%= client.getCedula()%>"><i
                                                    class="fa-solid fa-paw"></i></a>
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
