package ttps.entregable5.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.entregable5.cuentasclaras.model.Gasto;


public interface GastoRepository extends JpaRepository<Gasto, Long> {

	//find by distintos criterios
	Optional<Gasto> findById(Long id);
	Gasto findByNombre(String nombre);
	List<Gasto> findAll();
	boolean existsByNombre(String nombre);
	
	//Guardar/actualizar y borrar grupos
	Gasto save(Gasto gasto);
	void delete(Gasto gasto);
	
	//Especifico del Gasto
}
