package ttps.entregable5.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.entregable5.cuentasclaras.model.CategoriaGrupo;



public interface CategoriaGrupoRepository extends JpaRepository<CategoriaGrupo, Long> {

	//find by distintos criterios
	Optional<CategoriaGrupo> findById(Long id);
	CategoriaGrupo findByNombreGrupo(String nombre);
	List<CategoriaGrupo> findAll();
	boolean existsByNombreGrupo(String nombre);
	
	//Guardar y actualizar categorias de grupos
	CategoriaGrupo save(CategoriaGrupo catGrupo);
	
	//Especifico de categorias de Grupos
}
