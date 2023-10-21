document.addEventListener("DOMContentLoaded", function () {

// Obtén el elemento con el id "isEdit"
    var isEditElement = document.getElementById("isEdit");
    // Comprueba si el elemento existe
    if (isEditElement) {
// Obtén el valor de "flag" del elemento
        var editValue = parseInt(isEditElement.value);
        // Obtén el elemento al que deseas aplicar la clase condicional
        var contenedorElement = document.querySelector(".contenedor");
        // Comprueba el valor de "edit" y aplica la clase condicional
        if (editValue !== 0) {
            contenedorElement.classList.add("add-pets");
        } else {
            contenedorElement.classList.remove("add-pets");
        }
    }
});
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("form");
    const nameError = document.getElementById("nameError");
    const breedError = document.getElementById("breedError");
    const ageError = document.getElementById("ageError");
    const weightError = document.getElementById("weightError");
    form.addEventListener("submit", function (event) {
        const nameInput = document.getElementById("nameInput");
        const breedInput = document.getElementById("breedInput");
        const ageInput = document.getElementById("ageInput");
        const weightInput = document.getElementById("weightInput");
        const nameValue = nameInput.value.trim();
        const breedValue = breedInput.value.trim();
        const ageValue = ageInput.value.trim();
        const weightValue = weightInput.value.trim();
        validateAndDisplayError(nameValue, nameError, "El nombre es obligatorio.");
        validateAndDisplayError(breedValue, breedError, "La raza es obligatoria.");
        validateAndDisplayError(ageValue, ageError, "La edad es obligatoria.");
        validateAndDisplayError(weightValue, weightError, "El es peso es obligatorio.");
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
    const nameInput = document.getElementById("nameInput");
    const breedInput = document.getElementById("breedInput");
    const ageInput = document.getElementById("ageInput");
    const weightInput = document.getElementById("weightInput");
    const nameError = document.getElementById("nameError");
    const breedError = document.getElementById("breedError");
    const ageError = document.getElementById("ageError");
    const weightError = document.getElementById("weightError");
    setupInputValidation(nameInput, nameError, "El nombre es obligatorio.");
    setupInputValidation(breedInput, breedError, "La raza es obligatoria.");
    setupInputValidation(ageInput, ageError, "La edad es obligatoria.");
    setupInputValidation(weightInput, weightError, "El es peso es obligatorio.");
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

document.addEventListener("DOMContentLoaded", function () {
    // Obtén una referencia al botón de validación
    const validarDosisButton = document.getElementById("submitDosage");
    // Obtén referencias a los elementos del formulario
    const medicamentosSelect = document.getElementById("medicamentos");
    const dosisInput = document.getElementById("dosageInput");
    const dosisError = document.getElementById("dosageError");
    // Agrega un evento de clic al botón de validación
    validarDosisButton.addEventListener("click", function (event) {
        // Verifica si se ha seleccionado un medicamento y la dosis está en blanco
        if (medicamentosSelect.value === "" && dosisInput.value.trim() === "") {
            event.preventDefault();
        }
        if (medicamentosSelect.value !== "" && dosisInput.value.trim() === "") {
            // Mostrar el mensaje de error
            dosisError.style.display = "block";
            event.preventDefault();
        } else {
            // Si la validación pasa, ocultar el mensaje de error
            dosisError.style.display = "none";
        }

        // Prevenir el comportamiento predeterminado de la etiqueta <a>
    });
});

