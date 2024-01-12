package com.analistas.peluqueria.model.repository;

import com.analistas.peluqueria.model.entity.Rol;
import com.analistas.peluqueria.model.entity.Usuario;
import java.util.List;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

/**
 *
 * @author RVRN2
 */

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {
    
    //@Query(value = "SELECT u FROM usuario u WHERE u.id_rol = 2")
    //List<Usuario> buscarEstilistas();
    List<Usuario> findByIdRol(Rol idRol);
    
    //@Query(value = "SELECT u FROM usuario u WHERE u.id_rol = 1")
    //List<Usuario> buscarClientes();

    // buscar usuarios por su email:
    //@Query(value = "SELECT u FROM usuario u WHERE u.email = :email")
    Usuario findByEmail(String email);
}
