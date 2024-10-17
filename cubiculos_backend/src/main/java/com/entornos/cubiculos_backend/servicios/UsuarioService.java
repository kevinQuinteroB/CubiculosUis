package com.entornos.cubiculos_backend.servicios;

import com.entornos.cubiculos_backend.modelos.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
    Usuario CrearUsuario(Usuario usuario);
    boolean login(String email, String contrasenia);
}
