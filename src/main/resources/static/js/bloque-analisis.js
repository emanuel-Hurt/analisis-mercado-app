const addBlockBtn = document.getElementById("addBlockBtn");

const blocksContainer = document.getElementById("notebookContainer");

addBlockBtn.addEventListener("click", crearBloque);

function crearBloque() {

    const template = document.getElementById("bloque-template");
    const clone = template.content.cloneNode(true);

    const deleteBtn = clone.querySelector('.btn-delete');
    deleteBtn.addEventListener("click", (e) => {
        e.target.closest(".notebook-card").remove();
    });

    configurarBloque(clone)

    blocksContainer.appendChild(clone);

}

function configurarBloque(card) {

    const opSelect = card.querySelector(".op-select");
    const colIndexInput = card.querySelector(".col-index-input");
    const executeBtn = card.querySelector(".execute-btn");
    const resultArea = card.querySelector(".result-area");

    executeBtn.addEventListener('click', () => {
        const operacion = opSelect.value;
        const colIndex = colIndexInput.value;
        consultaResultado(operacion, colIndex, resultArea)
    });

}

function consultaResultado(operacion, colIndex, resultArea) {

    // Validacion basica
    if (colIndex === '' || colIndex < 0) {
        alert('Por favor ingrese un índice de columna válido (número mayor o igual a cero)');
        return;
    }

    // Construccion de la URL con los parametros
    const url = `/calcular?operacion=${encodeURIComponent(operacion)}&colIndex=${encodeURIComponent(colIndex)}`;

    // Mostrar estado de carga
    resultArea.style.display = 'block';
    resultArea.innerHTML = '<p style="color: #718096; font-style: italic;">Procesando operación en el servidor...</p>';

    // Peticion asincrona mediante Fetch
    fetch(url)
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text || "Error en el servidor");});
            }
            return response.json();
        })
        .then(data => {
            renderizarResultado(resultArea, data);
        })
        .catch(error => {
            resultArea.innerHTML = `<p style="color: #e53e3e; font-weight: 500;">Error: ${error.message}</p>`;
        });
}