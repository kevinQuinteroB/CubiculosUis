
document.addEventListener("DOMContentLoaded", function() {
    const loginBtn = document.getElementById("loginBtn");
    const emailInput = document.getElementById("email");
    const contraseniaInput = document.getElementById("contrasenia");

    const apiBaseUrl = 'http://localhost:8080';  // Definimos la URL base de la API

    loginBtn.addEventListener("click", function(event) {
        event.preventDefault(); 

        const email = emailInput.value;
        const contrasenia = contraseniaInput.value;

        if (!email || !contrasenia) {
            alert("Por favor, ingresa todos los campos.");
            return;
        }

        const loginData = {
            email: email,
            contrasenia: contrasenia
        };

        fetch(`${apiBaseUrl}/auth/login`, {  // Usamos la URL completa con apiBaseUrl
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        })
        .then(response => response.json())
        .then(isAuthenticated => {
            if (isAuthenticated) {
                window.location.href = "../html/cubiculos.html";  // Redirige al home
            } else {
                alert("Email o contraseña incorrectos");
            }
        })
        .catch(error => {
            console.error("Error durante el login:", error);
            alert("Ocurrió un error durante el login. Por favor, inténtalo de nuevo.");
        });
    });
});
