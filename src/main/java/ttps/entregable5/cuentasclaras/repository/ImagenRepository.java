package ttps.entregable5.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.entregable5.cuentasclaras.model.Imagen;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {

	//find by distintos criterios
	Optional<Imagen> findById(Long id);
	Imagen findByNombre(String nombre);
	List<Imagen> findAll();
	boolean existsByNombre(String nombre); 
	
	//Guardar y actualizar imagen
	Imagen save(Imagen img);
	
	//Especifico de las Imagenes 
}
