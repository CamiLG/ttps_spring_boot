package ttps.entregable5.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.entregable5.cuentasclaras.model.CategoriaGasto;

public interface CategoriaGastoRepository extends JpaRepository<CategoriaGasto, Long> {

	//find by distintos criterios
	Optional<CategoriaGasto> findById(Long id);
	CategoriaGasto findByNombreGasto(String nombre);
	List<CategoriaGasto> findAll();
	boolean existsByNombreGasto(String nombre); 
	
	//Guardar y actualizar categorias de gastos
	CategoriaGasto save(CategoriaGasto catGasto);
	
	//Especifico de las Categorias de Gastos
}
