package com.entornos.cubiculos_backend.serviciosImpl;

import com.entornos.cubiculos_backend.modelos.Usuario;
import com.entornos.cubiculos_backend.repositorios.UsuarioRepository;
import com.entornos.cubiculos_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario CrearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean login(String email, String contrasenia) {
        Integer count = this.usuarioRepository.findByEmailAndContrasenia(email, contrasenia);

        // Si el conteo es mayor que 0, las credenciales son válidas
        return count > 0;
    }

}
