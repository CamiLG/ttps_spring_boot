package ttps.entregable5.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.entregable5.cuentasclaras.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	//find by distintos criterios
	Optional<Usuario> findById(Long id);
	//Usuario getUsuario(Long id);
	Usuario findByUsuario(String usuario);
	Usuario findByEmail(String email);
	Usuario findByUsuarioAndPassword(String usuario, String password);
	List <Usuario> findByNombre(String nombre);
	boolean existsByEmail(String email);
	boolean existsByUsuario(String usuario);
	
	//Guardar/actualizar y borrar usuarios
	Usuario save(Usuario usr);
	void delete(Usuario usr);
	
	//Especifico del usuario
}
