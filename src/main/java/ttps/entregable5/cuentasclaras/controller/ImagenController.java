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

import ttps.entregable5.cuentasclaras.model.Imagen;
import ttps.entregable5.cuentasclaras.repository.ImagenRepository;

@Controller
@RequestMapping(path = "/imagenes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImagenController {

	@Autowired
	private ImagenRepository imgRepo;

	@PostMapping
	public ResponseEntity<?> crearImagen(@RequestBody Imagen img) {
		// recibir la imagen y validar que no sea nula
		if (img == null || img.getNombre() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La imagen no ha sido bien cargada");
		}
		// validar si el nombre ya esta en uso
		if (imgRepo.findByNombre(img.getNombre()) != null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Existe una imagen creada con ese nombre");
		}
		// insertar la imagen en la db
		imgRepo.save(img);
		return new ResponseEntity<Imagen>(HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getImagen(@PathVariable(name = "id") Long id) {
		Optional<Imagen> img = imgRepo.findById(id);
		if (img.isPresent()) {
			Imagen imgEncontrada = img.get();
			return new ResponseEntity<Imagen>(imgEncontrada, HttpStatus.OK);
		}
		System.out.println("Imagen no encontrada");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagen no encontrada");

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editarImagen(@RequestBody Imagen img, @PathVariable("id") Long id) {
		Optional<Imagen> imgR = imgRepo.findById(id);
		if (imgR.isPresent()) {
			Imagen imagen = imgR.get();
			imagen.setNombre(img.getNombre());
			imagen.setPath(img.getPath());
			imgRepo.save(imagen);
			return new ResponseEntity<Imagen>(imagen, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<Imagen> getAllImagenes() {
		return imgRepo.findAll();
	}

}
