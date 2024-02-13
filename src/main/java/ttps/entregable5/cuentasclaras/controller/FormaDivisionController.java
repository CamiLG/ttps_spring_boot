package ttps.entregable5.cuentasclaras.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ttps.entregable5.cuentasclaras.model.CategoriaGrupo;
import ttps.entregable5.cuentasclaras.model.FormaDivision;
import ttps.entregable5.cuentasclaras.repository.FormaDivisionRepository;

@Controller
@RequestMapping(path = "/division", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaDivisionController {

	@Autowired
	private FormaDivisionRepository fdRepo;
	
	@PostMapping
	public ResponseEntity<?> crearFormaDivision(@RequestBody FormaDivision fd) {
		// recibir la forma de division y validar que no sea nulo
		if (fd == null || fd.getDescripcion() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La forma de division no ha sido bien cargada");
		}
		// validar si la descripcion ya esta en uso
		if (fdRepo.findByDescripcion(fd.getDescripcion()) != null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Existe una forma de division creada con ese nombre");
		}
		// insertarla forma de division en la db
		fdRepo.save(fd);
		return new ResponseEntity<FormaDivision>(fd, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getFormaDivision(@PathVariable(name = "id") Long id) {
		Optional<FormaDivision> fd = fdRepo.findById(id);
		if (fd.isPresent()) {
			FormaDivision fdEncontrado = fd.get();
			return new ResponseEntity<FormaDivision>(fdEncontrado, HttpStatus.OK);
		}
		System.out.println("Forma de division no encontrada");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Forma de division no encontrada");

	}

	//@GetMapping("/all")
	//public @ResponseBody Iterable<FormaDivision> getAllFormaDivision() {
	//	return fdRepo.findAll();
	//}

	@GetMapping("/all")
	public ResponseEntity<List<FormaDivision>> getAllFormaDivision() {
		List<FormaDivision> fd = fdRepo.findAll();
		return new ResponseEntity<List<FormaDivision>>(fd, HttpStatus.OK);
	}

}
