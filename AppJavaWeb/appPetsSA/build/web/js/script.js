document.addEventListener("DOMContentLoaded", function () {
    // Obtén una referencia al elemento de entrada de búsqueda
    const buscarInput = document.getElementById("searchInput");

    // Agrega un evento de escucha para el evento de entrada en el campo de búsqueda
    buscarInput.addEventListener("input", function () {
        const filtro = buscarInput.value.toLowerCase();
        const filasMedicamento = document.querySelectorAll(".row-medication");

        filasMedicamento.forEach(function (fila) {
            const contenidoFila = fila.textContent.toLowerCase();
            if (contenidoFila.includes(filtro)) {
                fila.style.display = "table-row"; // Muestra la fila
            } else {
                fila.style.display = "none"; // Oculta la fila que no coincide
            }
        });
    });

    const form = document.querySelector("form");
    const nameError = document.getElementById("nameError");
    const descriptionError = document.getElementById("descriptionError");

    form.addEventListener("submit", function (event) {
        const nameInput = document.getElementById("nameInput");
        const descriptionInput = document.getElementById("descriptionInput");
        const nameValue = nameInput.value.trim();
        const descriptionValue = descriptionInput.value.trim();

        if (nameValue === "") {
            // Muestra el mensaje de validación en el div personalizado
            nameError.textContent = decodeURIComponent(escape("El nombre del medicamento es obligatorio."));
            nameError.style.display = "block";
            event.preventDefault();
        } else {
            nameError.style.display = "none";
        }
        if (descriptionValue === "") {
            // Muestra el mensaje de validación en el div personalizado
            descriptionError.textContent = decodeURIComponent(escape("La descripción no puede estar vacía."));
            descriptionError.style.display = "block"; // Muestra el div personalizado
            event.preventDefault(); // Evita que el formulario se envíe si hay campos vacíos
        } else {
            descriptionError.style.display = "none"; // Oculta el div personalizado si no hay errores
        }
    });
});



document.addEventListener("DOMContentLoaded", function () {
    // Obtenemos referencias al input y al div del mensaje
    const nameInput = document.getElementById("nameInput");
    const nameError = document.getElementById("nameError");

    const descriptionInput = document.getElementById("descriptionInput");
    const descriptionError = document.getElementById("descriptionError");

// Agregamos un evento de escucha al input
    nameInput.addEventListener("input", function () {
        // Verificamos si el valor del input no está vacío
        if (nameInput.value.trim() !== "") {
            // Si no está vacío, ocultamos el div del mensaje
            nameError.style.display = "none";
        } else {
            // Si está vacío, mostramos el div del mensaje
            nameError.style.display = "block";
        }
    });
    descriptionInput.addEventListener("input", function () {
        // Verificamos si el valor del input no está vacío
        if (descriptionInput.value.trim() !== "") {
            // Si no está vacío, ocultamos el div del mensaje
            descriptionError.style.display = "none";
        } else {
            // Si está vacío, mostramos el div del mensaje
            descriptionError.style.display = "block";
        }
    });
});