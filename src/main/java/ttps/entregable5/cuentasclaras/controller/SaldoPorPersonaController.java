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

import ttps.entregable5.cuentasclaras.model.SaldoPorPersona;
import ttps.entregable5.cuentasclaras.repository.SaldoPorPersonaRepository;

@Controller
@RequestMapping(path = "/saldos", produces = MediaType.APPLICATION_JSON_VALUE)
public class SaldoPorPersonaController {

	@Autowired
	private SaldoPorPersonaRepository sppRepo;
	

	@PostMapping("/create")
	public ResponseEntity<String> crearSaldosPersona(@RequestBody SaldoPorPersona spp) {
		// recibir el saldo por persona y validar que el usuario no sea nulo
		if (spp == null || spp.getSaldoUsuario() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El saldo por persona no ha sido bien cargado");
		}
		// validar si el usuario ya tiene un saldo
		if (sppRepo.findBySaldoUsuario(spp.getSaldoUsuario()) != null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Existe un saldo creado para esa persona");
		}
		// insertar el saldo por persona en la db
		sppRepo.save(spp);
		return ResponseEntity.status(HttpStatus.CREATED).body("Saldo para persona creado con éxito");
	}

	@GetMapping("/{id}")
	public ResponseEntity<SaldoPorPersona> getSaldosPorPersona(@PathVariable(name = "id") Long id) {
		Optional<SaldoPorPersona> spp = sppRepo.findById(id);
		if (spp.isPresent()) {
			SaldoPorPersona sppEncontrado = spp.get();
			return new ResponseEntity<SaldoPorPersona>(sppEncontrado, HttpStatus.OK);
		}
		System.out.println("Saldo de persona no encontrado");
		return new ResponseEntity<SaldoPorPersona>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/edit")
	public ResponseEntity<SaldoPorPersona> editarSaldosPorPersona(SaldoPorPersona spp) {
		// chequear si el saldo por persona existe y modificarlo
		if (sppRepo.existsBySaldoUsuario(spp.getSaldoUsuario())) {
			SaldoPorPersona saldo = sppRepo.findBySaldoUsuario(spp.getSaldoUsuario());
			return new ResponseEntity<SaldoPorPersona>(saldo, HttpStatus.OK);
		}
		return new ResponseEntity<SaldoPorPersona>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<SaldoPorPersona> getAllSaldosPorPersona() {
		return sppRepo.findAll();
	}
}
