package ttps.entregable5.cuentasclaras.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import ttps.entregable5.cuentasclaras.model.CategoriaGrupo;
import ttps.entregable5.cuentasclaras.model.Grupo;
import ttps.entregable5.cuentasclaras.repository.CategoriaGrupoRepository;
import ttps.entregable5.cuentasclaras.repository.GrupoRepository;

@Controller
@RequestMapping(path="/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepo;
	
	@Autowired
	private CategoriaGrupoRepository catGRepo;
	
	  @PostMapping("/create")
		public ResponseEntity<String> crearGrupo(@RequestBody Grupo grp){
			//recibir el grupo y validar que no sea nulo
	        if (grp == null || grp.getNombre() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El grupo no ha sido bien cargado");
	        }
			//validar si el nombre ya esta en uso
			if(grupoRepo.findByNombre(grp.getNombre())!= null) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Existe un grupo creado con ese nombre");
			}
			//insertar el grupo en la db
			grupoRepo.save(grp);
			return ResponseEntity.status(HttpStatus.CREATED).body("Grupo creado con éxito");
		}
	 
	  @GetMapping("/{id}")
	  public ResponseEntity<Grupo> getGrupo(@PathVariable(name = "id") Long id) {
		  Optional<Grupo> grp = grupoRepo.findById(id);
		  if (grp.isPresent()) {
			Grupo grpEncontrado = grp.get();
			return new ResponseEntity<Grupo>(grpEncontrado, HttpStatus.OK);
			}
			System.out.println("Grupo no encontrado");
			return new ResponseEntity<Grupo>(HttpStatus.NOT_FOUND);
		
		}
	  
	  @PutMapping("/edit")
	  public ResponseEntity<Grupo> editarGrupo(Grupo grp) {
		  //chequear si el grupo existe y modificarlo
		  if(grupoRepo.existsByNombre(grp.getNombre())){
			 Grupo grupo = grupoRepo.findByNombre(grp.getNombre());
			 return new ResponseEntity<Grupo>(grupo,HttpStatus.OK);
		  }
		  return new ResponseEntity<Grupo>(HttpStatus.NOT_FOUND);
	  }
	  
	  
	  @GetMapping("/all")
	  public @ResponseBody Iterable<Grupo> getAllGrupos() {
	    // This returns a JSON or XML with the groups
	    return grupoRepo.findAll();
	  }
	  
		@PostMapping("/create/catGrupo")
		public ResponseEntity<String> crearCatGrupo(@RequestBody CategoriaGrupo grp) {
			// recibir la categoria de grupo y validar que no sea nulo
			if (grp == null || grp.getNombreGrupo() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("La categoria del grupo no ha sido bien cargada");
			}
			// validar si el nombre ya esta en uso
			if (catGRepo.findByNombreGrupo(grp.getNombreGrupo()) != null) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body("Existe una categoria de grupo creada con ese nombre");
			}
			// insertar el grupo en la db
			catGRepo.save(grp);
			return ResponseEntity.status(HttpStatus.CREATED).body("Categoria de Grupo creada con éxito");
		}
}
