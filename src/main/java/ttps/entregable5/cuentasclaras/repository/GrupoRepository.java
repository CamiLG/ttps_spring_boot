package ttps.entregable5.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;
import ttps.entregable5.cuentasclaras.dto.GastoDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ttps.entregable5.cuentasclaras.dto.GastoDTO;
import ttps.entregable5.cuentasclaras.model.Gasto;
import ttps.entregable5.cuentasclaras.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

	//find by distintos criterios
	Optional<Grupo> findById(Long id);
	Grupo findByNombre(String nombre);
	List<Grupo> findAll();
	boolean existsByNombre(String nombre);


	@Query("Select new ttps.entregable5.cuentasclaras.dto.GastoDTO(gas.id,gas.nombre,gas.monto, gas.fechaGasto, gas.formaDivision, gas.img, gas.categoriaGasto)  from Gasto gas join Grupo gr on (gas.grupo.id = gr.id) where gas.grupo.id = ?1")
	List<GastoDTO> obtenerGastos(Long id);
	
	//Guardar/actualizar y borrar grupos
	Grupo save(Grupo grupo);
	void delete(Grupo grupo);
	
	//Especifico del Grupo
}
