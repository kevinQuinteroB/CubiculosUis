package com.entornos.cubiculos_backend.controladores;

import com.entornos.cubiculos_backend.modelos.LoginRequest;
import com.entornos.cubiculos_backend.modelos.Usuario;
import com.entornos.cubiculos_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(this.usuarioService.CrearUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = usuarioService.login(loginRequest.getEmail(), loginRequest.getContrasenia());

        //true si las credenciales son correctas, false si no lo son
        return ResponseEntity.ok(isAuthenticated);
    }

}
