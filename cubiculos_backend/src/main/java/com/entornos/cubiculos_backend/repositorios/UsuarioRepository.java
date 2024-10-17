package com.entornos.cubiculos_backend.repositorios;

import com.entornos.cubiculos_backend.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select count(*) from Usuario as p where p.email = :email and p.contrasenia = :contrasenia")
    Integer findByEmailAndContrasenia(@Param("email") String email,
                                      @Param("contrasenia") String contrasenia);

}
