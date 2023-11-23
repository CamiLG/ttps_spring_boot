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

import ttps.entregable5.cuentasclaras.model.CategoriaGasto;
import ttps.entregable5.cuentasclaras.model.Gasto;
import ttps.entregable5.cuentasclaras.repository.CategoriaGastoRepository;
import ttps.entregable5.cuentasclaras.repository.GastoRepository;

@Controller
@RequestMapping(path = "/gastos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GastoController {

	@Autowired
	private GastoRepository gastoRepo;
	
	@Autowired
	private CategoriaGastoRepository catGastoRepo;

	@PostMapping("/create")
	public ResponseEntity<String> crearGasto(@RequestBody Gasto gst) {
		// recibir el gasto y validar que no sea nulo
		if (gst == null || gst.getNombre() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Gasto no ha sido bien cargado");
		}
		// validar si el nombre ya esta en uso
		if (gastoRepo.findByNombre(gst.getNombre()) != null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Existe un Gasto creado con ese nombre");
		}
		// insertar el gasto en la db
		gastoRepo.save(gst);
		return ResponseEntity.status(HttpStatus.CREATED).body("Gasto creado con éxito");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Gasto> getGasto(@PathVariable(name = "id") Long id) {
		Optional<Gasto> gst = gastoRepo.findById(id);
		if (gst.isPresent()) {
			Gasto gstEncontrado = gst.get();
			return new ResponseEntity<Gasto>(gstEncontrado, HttpStatus.OK);
		}
		System.out.println("Gasto no encontrado");
		return new ResponseEntity<Gasto>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Gasto> editarGasto(@RequestBody Gasto gst, @PathVariable("id") Long id) {
		Optional<Gasto> gastoR = gastoRepo.findById(id);
		if (gastoR.isPresent()) {
			Gasto gasto = gastoR.get();
			gasto.setNombre(gst.getNombre());
			gasto.setMonto(gst.getMonto());
			gasto.setFormaDivision(gst.getFormaDivision());
			gasto.setImg(gst.getImg());
			gasto.setCategoriaGasto(gst.getCategoriaGasto());
			gastoRepo.save(gasto);
			return new ResponseEntity<Gasto>(gasto, HttpStatus.OK);
		}
		return new ResponseEntity<Gasto>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<Gasto> getAllGastos() {
		return gastoRepo.findAll();
	}

	@PostMapping("/create/catGrupo")
	public ResponseEntity<String> crearCatGasto(@RequestBody CategoriaGasto gst) {
		// recibir la categoria de grupo y validar que no sea nulo
		if (gst == null || gst.getNombreGasto() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La categoria del gasto no ha sido bien cargada");
		}
		// validar si el nombre ya esta en uso
		if (catGastoRepo.findByNombreGasto(gst.getNombreGasto()) != null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body("Existe una categoria de gasto creada con ese nombre");
		}
		// insertar el grupo en la db
		catGastoRepo.save(gst);
		return ResponseEntity.status(HttpStatus.CREATED).body("Categoria de gasto creada con éxito");
	}
}
