document.addEventListener("DOMContentLoaded", function () {
    // Obtén una referencia al elemento de entrada de búsqueda
    const buscarInput = document.getElementById("searchInput");

    // Agrega un evento de escucha para el evento de entrada en el campo de búsqueda
    buscarInput.addEventListener("input", function () {
        const filtro = buscarInput.value.toLowerCase();
        const filasMedicamento = document.querySelectorAll(".row-client");

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
    const cedulaError = document.getElementById("cedulaError");
    const nameError = document.getElementById("nameError");
    const surnameError = document.getElementById("surnameError");
    const secondSurnameError = document.getElementById("secondSurnameError");
    const addressError = document.getElementById("addressError");
    const phoneError = document.getElementById("phoneError");

    form.addEventListener("submit", function (event) {
        const cedulaInput = document.getElementById("cedulaInput");
        const nameInput = document.getElementById("nameInput");
        const surnameInput = document.getElementById("surnameInput");
        const secondSurnameInput = document.getElementById("secondSurnameInput");
        const addressInput = document.getElementById("addressInput");
        const phoneInput = document.getElementById("phoneInput");

        const cedulaValue = cedulaInput.value.trim();
        const nameValue = nameInput.value.trim();
        const surnameValue = surnameInput.value.trim();
        const secondSurnameValue = secondSurnameInput.value.trim();
        const addressValue = addressInput.value.trim();
        const phoneValue = phoneInput.value.trim();

        validateAndDisplayError(cedulaValue, cedulaError, "La cédula es obligatoria.");
        validateAndDisplayError(nameValue, nameError, "El nombre es obligatorio.");
        validateAndDisplayError(surnameValue, surnameError, "El primer apellido es obligatorio.");
        validateAndDisplayError(secondSurnameValue, secondSurnameError, "El segundo apellido es obligatorio.");
        validateAndDisplayError(addressValue, addressError, "La dirección es obligatoria.");
        validateAndDisplayError(phoneValue, phoneError, "El teléfono es obligatorio.");


        function validateAndDisplayError(inputValue, errorElement, errorMessage) {
            if (inputValue === "") {
                errorElement.textContent = decodeURIComponent(escape(errorMessage));
                errorElement.style.display = "block";
                event.preventDefault();
            } else {
                errorElement.style.display = "none";
            }
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    // Obtenemos referencias al input y al div del mensaje
    const cedulaInput = document.getElementById("cedulaInput");
    const nameInput = document.getElementById("nameInput");
    const surnameInput = document.getElementById("surnameInput");
    const secondSurnameInput = document.getElementById("secondSurnameInput");
    const addressInput = document.getElementById("addressInput");
    const phoneInput = document.getElementById("phoneInput");

    const cedulaError = document.getElementById("cedulaError");
    const nameError = document.getElementById("nameError");
    const surnameError = document.getElementById("surnameError");
    const secondSurnameError = document.getElementById("secondSurnameError");
    const addressError = document.getElementById("addressError");
    const phoneError = document.getElementById("phoneError");

    setupInputValidation(nameInput, nameError, "El nombre del medicamento es obligatorio.");
    setupInputValidation(cedulaInput, cedulaError, "La cédula es obligatoria.");
    setupInputValidation(surnameInput, surnameError, "El primer apellido es obligatorio.");
    setupInputValidation(secondSurnameInput, secondSurnameError, "El segundo apellido es obligatorio.");
    setupInputValidation(addressInput, addressError, "La dirección es obligatoria.");
    setupInputValidation(phoneInput, phoneError, "El teléfono es obligatorio.");


    function setupInputValidation(input, errorDiv, errorMessage) {
        input.addEventListener("input", function () {
            if (input.value.trim() !== "") {
                errorDiv.style.display = "none";
            } else {
                errorDiv.textContent = decodeURIComponent(escape(errorMessage));
                errorDiv.style.display = "block";
            }
        });
    }
});