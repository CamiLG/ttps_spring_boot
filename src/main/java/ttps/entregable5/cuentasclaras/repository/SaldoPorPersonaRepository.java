package ttps.entregable5.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ttps.entregable5.cuentasclaras.model.SaldoPorPersona;
import ttps.entregable5.cuentasclaras.model.Usuario;

public interface SaldoPorPersonaRepository extends JpaRepository<SaldoPorPersona, Long> {

	//find by distintos criterios
	Optional<SaldoPorPersona> findById(Long id);
	SaldoPorPersona findBySaldoUsuario(Usuario usr);
	List<SaldoPorPersona> findAll();
	boolean existsBySaldoUsuario(Usuario usr); 
	
	//Guardar y actualizar los saldos por persona
	SaldoPorPersona save(SaldoPorPersona spp);
	
	//Especifico de los saldos por persona  
}
