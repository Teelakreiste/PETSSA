<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pets S.A.</title>
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
                <h1 class="text-center">Bienvenido a Pets S.A</h1>
                <div class="formulario f2">
                    <p>
                        Bienvenido a PETS S.A., el centro veterinario líder en la ciudad de Cartagena, donde nos dedicamos a cuidar y proteger a tus queridas mascotas. En nuestro constante esfuerzo por brindar el mejor servicio, hemos dado un paso adelante para mejorar tu experiencia como cliente y garantizar la salud y bienestar de tus compañeros peludos.
                        <br>
                        Hemos modernizado y optimizado nuestros procesos de registro y gestión de datos, dejando atrás los métodos manuales y los archivos de MS Excel. Ahora, ponemos a tu disposición una poderosa aplicación web que simplifica y centraliza la administración de información, permitiéndonos brindarte un servicio más eficiente y personalizado.
                        <br><br>
                        En PETS S.A., comprendemos la importancia de mantener un registro detallado de cada mascota, por lo que nuestro sistema almacena datos esenciales como su identificación, nombre, raza, edad y peso. Además, hemos ampliado nuestro alcance para incluir información sobre medicamentos, lo que nos permite llevar un control preciso de las prescripciones y tratamientos de tus mascotas.
                        <br><br>
                        No nos olvidamos de ti, nuestro valioso cliente. Con esta nueva plataforma, puedes acceder y actualizar tus datos de manera rápida y sencilla. Registra tu cédula, nombres, apellidos, dirección y número de teléfono, para que podamos brindarte un servicio aún más personalizado.
                        <br><br>
                        Nuestro catálogo de medicamentos también está disponible en línea, proporcionándote información detallada sobre cada producto, incluyendo su nombre, descripción y dosis recomendada. Así, estarás informado sobre los tratamientos que tu mascota recibe y podrás tomar decisiones más informadas.
                        <br><br>
                        En PETS S.A., la salud y el bienestar de tus mascotas son nuestra prioridad. Con nuestra nueva aplicación web, estamos seguros de que experimentarás una atención de mayor calidad y una gestión de información más efectiva. Gracias por confiar en nosotros para el cuidado de tus seres queridos de cuatro patas. Estamos aquí para servirte mejor que nunca.
                    </p>
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