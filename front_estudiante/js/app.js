const apiBaseUrl = 'http://localhost:8080/estudiante';

// Leer y mostrar la lista de estudiantes
function fetchStudents() {
    fetch(`${apiBaseUrl}/listar`)
        .then(response => response.json())
        .then(data => {
            const studentsTableBody = document.querySelector('#students-table tbody');
            studentsTableBody.innerHTML = '';

            data.forEach(student => {
                const row = `
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.nombre}</td>
                        <td>${student.codigo}</td>
                        <td>${student.idEstado}</td>
                        <td class="actions">
                            <button onclick="editStudent(${student.id})">Editar</button>
                            <button onclick="deleteStudent(${student.id})">Eliminar</button>
                        </td>
                    </tr>
                `;
                studentsTableBody.innerHTML += row;
            });
        });
}

// Insertar o actualizar estudiante
document.getElementById('student-form').addEventListener('submit', function (e) {
    e.preventDefault();

    const studentId = document.getElementById('student-id').value;
    const studentName = document.getElementById('student-name').value;
    const studentCode = document.getElementById('student-code').value;
    const studentStatus = document.getElementById('student-status').value;

    const studentData = {
        nombre: studentName,
        codigo: parseInt(studentCode),
        idEstado: parseInt(studentStatus)
    };

    if (studentId) {
        // Actualizar estudiante
        fetch(`${apiBaseUrl}/actualizar`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id: studentId, ...studentData })
        }).then(() => {
            clearForm();
            fetchStudents();
        });
    } else {
        // Crear estudiante
        fetch(`${apiBaseUrl}/crear`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(studentData)
        }).then(() => {
            clearForm();
            fetchStudents();
        });
    }
});

// Editar estudiante
function editStudent(id) {
    fetch(`${apiBaseUrl}/listar`)
        .then(response => response.json())
        .then(data => {
            const student = data.find(student => student.id === id);
            document.getElementById('student-id').value = student.id;
            document.getElementById('student-name').value = student.nombre;
            document.getElementById('student-code').value = student.codigo;
            document.getElementById('student-status').value = student.idEstado;
        });
}

// Borrar estudiante
function deleteStudent(id) {
    fetch(`${apiBaseUrl}/borrar?id=${id}`, {
        method: 'DELETE'
    }).then(() => {
        fetchStudents();
    });
}

// Limpiar formulario
function clearForm() {
    document.getElementById('student-id').value = '';
    document.getElementById('student-name').value = '';
    document.getElementById('student-code').value = '';
    document.getElementById('student-status').value = '';
}

// Cargar la lista de estudiantes al cargar la página
fetchStudents();
