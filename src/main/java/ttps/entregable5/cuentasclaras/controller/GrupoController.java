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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ttps.entregable5.cuentasclaras.dto.GastoDTO;
import ttps.entregable5.cuentasclaras.model.CategoriaGrupo;
import ttps.entregable5.cuentasclaras.model.Gasto;
import ttps.entregable5.cuentasclaras.model.Grupo;
import ttps.entregable5.cuentasclaras.repository.CategoriaGrupoRepository;
import ttps.entregable5.cuentasclaras.repository.GrupoRepository;

@Controller
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepo;

	@Autowired
	private CategoriaGrupoRepository catGRepo;

	@PostMapping("/create")
	public ResponseEntity<?> crearGrupo(@RequestBody Grupo grp) {
		// recibir el grupo y validar que no sea nulo
		if (grp == null || grp.getNombre() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El grupo no ha sido bien cargado");
		}
		// validar si el nombre ya esta en uso
		if (grupoRepo.findByNombre(grp.getNombre()) != null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Existe un grupo creado con ese nombre");
		}
		// insertar el grupo en la db
		grupoRepo.save(grp);
		return new ResponseEntity<Grupo>(grp, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerGrupo(@PathVariable("id") Long id) {
		Optional<Grupo> grp = grupoRepo.findById(id);
		if (grp.isPresent()) {
			Grupo grpEncontrado = grp.get();
			return new ResponseEntity<Grupo>(grpEncontrado, HttpStatus.OK);
		} else {
			System.out.println("Grupo no encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo no encontrado");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editarGrupo(@RequestBody Grupo grp, @PathVariable("id") Long id) {
		Optional<Grupo> grupoR = grupoRepo.findById(id);
		if (grupoR.isPresent()) {
			Grupo grupo = grupoR.get();
			grupo.setNombre(grp.getNombre());
			grupo.setCategoriaGrupo(grp.getCategoriaGrupo());
			Grupo gActualizado = grupoRepo.save(grupo);
			return new ResponseEntity<Grupo>(gActualizado, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El grupo no pudo modificarse");
	}

	/*
	 * @GetMapping("/all") public @ResponseBody Iterable<Grupo> getAllGrupos() { //
	 * This returns a JSON or XML with the groups return grupoRepo.findAll(); }
	 */

	@GetMapping("/all")
	public ResponseEntity<List<Grupo>> obtenerTodosLosGrupos() {
		List<Grupo> grupos = grupoRepo.findAll();
		return new ResponseEntity<List<Grupo>>(grupos, HttpStatus.OK);
	}

	@GetMapping("/gastosDelGrupo/{id}") 
	public ResponseEntity<?> obtenerGastosGrupo(@PathVariable("id") Long id) {
		Optional<Grupo> grupoR = grupoRepo.findById(id); 
		if (grupoR.isPresent()) { 
			Grupo grupo = grupoR.get();
			List<GastoDTO> gastosG = grupoRepo.obtenerGastos(grupo.getId());
			return new ResponseEntity<List<GastoDTO>>(gastosG, HttpStatus.OK);
		} 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo no encontrado");
	  }

	@PostMapping("/create/catGrupo")
	public ResponseEntity<?> crearCatGrupo(@RequestBody CategoriaGrupo grp) {
		// recibir la categoria de grupo y validar que no sea nulo
		if (grp == null || grp.getNombreGrupo() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La categoria del grupo no ha sido bien cargada");
		}
		// validar si el nombre ya esta en uso
		if (catGRepo.findByNombreGrupo(grp.getNombreGrupo()) != null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body("Existe una categoria de grupo creada con ese nombre");
		}
		// insertar el grupo en la db
		catGRepo.save(grp);
		return new ResponseEntity<CategoriaGrupo>(grp, HttpStatus.CREATED);
	}
}
