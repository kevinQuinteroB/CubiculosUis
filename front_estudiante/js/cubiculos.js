const apiBaseUrl = 'http://localhost:8080/cubiculo';

// Leer y mostrar la lista de cubiculos
function fetchCubiculos() {
    fetch(`${apiBaseUrl}/listar`)
        .then(response => response.json())
        .then(data => {
            const cubiculosTableBody = document.querySelector('#cubiculos-table tbody');
            cubiculosTableBody.innerHTML = '';

            data.forEach(cubiculo => {
                const row = `
                    <tr>
                        <td>${cubiculo.id}</td>
                        <td>${cubiculo.numeroCubiculo}</td>
                        <td>${cubiculo.capacidad}</td>
                        <td>${cubiculo.idPiso}</td>
                        <td>${cubiculo.descripcion}</td>
                        <td>${cubiculo.idEstadoCubiculo == 1 ? "Diponible" : "No disponible" }</td>
                        <td class="actions">
                            <button onclick="editcubiculo(${cubiculo.id})">Editar</button>
                            <button onclick="deletecubiculo(${cubiculo.id})">Eliminar</button>
                        </td>
                    </tr>
                `;
                cubiculosTableBody.innerHTML += row;
            });
        });
}

// Insertar o actualizar cubiculo
document.getElementById('cubiculo-form').addEventListener('submit', function (e) {
    e.preventDefault();

    const cubiculoId = document.getElementById('cubiculo-id').value;
    const numeroCubiculo = document.getElementById('numero_cubiculo').value;
    const capacidadCubiculo = document.getElementById('capacidad').value;
    const pisoCubiculo = document.getElementById('piso').value;
    const descripcionCubiculo = document.getElementById('descripcion').value;
    const estadoCubiculo = document.getElementById('estado').value;



    const cubiculoData = {
        numeroCubiculo: numeroCubiculo,
        capacidad: parseInt(capacidadCubiculo),
        idPiso: Number(pisoCubiculo),
        descripcion: descripcionCubiculo,
        idEstadoCubiculo: Number(estadoCubiculo)
    };

    if (cubiculoId) {
        // Actualizar cubiculo
        fetch(`${apiBaseUrl}/actualizar`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id: cubiculoId, ...cubiculoData})
        }).then(() => {
            clearForm();
            alert('Cubículo guardado correctamente');
            fetchCubiculos();
        }).catch(error => {
            // Muestra el mensaje de error en un alert
            alert(error.message); // Muestra el mensaje de error
        });
    } else {
        // Crear cubiculo
        fetch(`${apiBaseUrl}/crear`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(cubiculoData)
        }).then(response => {
            if (!response.ok) {
                return response.json().then(err => { throw new Error(err.message); });
            }
            clearForm();
            alert('Cubículo guardado correctamente');
            fetchCubiculos();
        }).catch(error => {
            // Muestra el mensaje de error en un alert
            alert(error.message); // Muestra el mensaje de error
        });;
    }
});

// Editar cubiculo
function editcubiculo(id) {
    fetch(`${apiBaseUrl}/listar`)
        .then(response => response.json())
        .then(data => {
            const cubiculo = data.find(cubiculo => cubiculo.id === id);
            document.getElementById('cubiculo-id').value = cubiculo.id;
            document.getElementById('numero_cubiculo').value = cubiculo.numeroCubiculo;
            document.getElementById('capacidad').value = cubiculo.capacidad;
            document.getElementById('piso').value = cubiculo.idPiso;
            document.getElementById('descripcion').value = cubiculo.descripcion;
            document.getElementById('estado').value = cubiculo.idEstadoCubiculo;

        });
}

// Borrar cubiculo
function deletecubiculo(id) {
    fetch(`${apiBaseUrl}/eliminar?id=${id}`, {
        method: 'DELETE'
    }).then(() => {
        fetchCubiculos();
    });
}

// Limpiar formulario
function clearForm() {
    document.getElementById('cubiculo-id').value = '';
    document.getElementById('numero_cubiculo').value = '';
    document.getElementById('capacidad').value = '';
    document.getElementById('piso').value = '';
    document.getElementById('descripcion').value = '';
}

// Cargar la lista de cubiculos al cargar la página
fetchCubiculos();
