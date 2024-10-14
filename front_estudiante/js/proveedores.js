const apiBaseUrl = 'http://localhost:8080/operario';

function fetchOperario() {
    fetch(`${apiBaseUrl}/listar`)
        .then(response => response.json())
        .then(data => {
            const operariosTableBody = document.querySelector('#operario-table tbody');
            operariosTableBody.innerHTML = '';

            data.forEach(operario => {
                const row = `
                    <tr>
                        <td>${operario.id}</td>
                        <td>${operario.nombre}</td>
                        <td>${operario.documento}</td>
                        <td class="actions">
                            <button onclick="editOperario(${operario.id})">Editar</button>
                            <button onclick="deleteOperario(${operario.id})">Eliminar</button>
                        </td>
                    </tr>
                `;
                operariosTableBody.innerHTML += row;
            });
        });
}

document.getElementById('operario-form').addEventListener('submit', function (e) {
    e.preventDefault();

    const operarioId = document.getElementById('operario-id').value;
    const operarioName = document.getElementById('operario-name').value;
    const operarioDocumento = document.getElementById('operario-document').value;

    const operarioData = {
        nombre: operarioName, 
        documento: parseInt(operarioDocumento)  
    };

    if (operarioId) {
        fetch(`${apiBaseUrl}/actualizar`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id: operarioId, ...operarioData })
        }).then(() => {
            clearForm();
            fetchOperario();
        });
    } else {
        // Crear Operario
        fetch(`${apiBaseUrl}/crear`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(operarioData)
        }).then(() => {
            clearForm();
            fetchOperario();
        });
    }
});





// Editar Operario
function editOperario(id) {
    fetch(`${apiBaseUrl}/listar`)
        .then(response => response.json())
        .then(data => {
            const operario = data.find(operario => operario.id === id);
            document.getElementById('operario-id').value = operario.id;
            document.getElementById('operario-name').value = operario.nombre;
            document.getElementById('operario-document').value = operario.documento;
        });
}

// Borrar Operario
function deleteOperario(id) {
    fetch(`${apiBaseUrl}/borrar?id=${id}`, {
        method: 'DELETE'
    }).then(() => {
        fetchOperario();
    });
}

// Limpiar formulario
function clearForm() {
    document.getElementById('operario-id').value = '';
    document.getElementById('operario-name').value = '';
    document.getElementById('operario-document').value = '';
}

// Cargar la lista Operarios al cargar la página
fetchOperario();