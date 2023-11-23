package ttps.entregable5.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ttps.entregable5.cuentasclaras.model.FormaDivision;



public interface FormaDivisionRepository extends JpaRepository<FormaDivision, Long> {

	//find by distintos criterios
	Optional<FormaDivision> findById(Long id);
	FormaDivision findByDescripcion(String desc);
	List<FormaDivision> findAll();
	boolean existsByDescripcion(String desc);
	
	//Guardar y actualizar formas de division
	FormaDivision save(FormaDivision formaDivision);
	
	//Especifico de las formas de division
}
