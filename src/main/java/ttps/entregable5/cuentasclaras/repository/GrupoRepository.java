package ttps.entregable5.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.entregable5.cuentasclaras.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

	//find by distintos criterios
	Optional<Grupo> findById(Long id);
	Grupo findByNombre(String nombre);
	List<Grupo> findAll();
	boolean existsByNombre(String nombre);
	
	//Guardar/actualizar y borrar grupos
	Grupo save(Grupo grupo);
	void delete(Grupo grupo);
	
	//Especifico del Grupo
}
