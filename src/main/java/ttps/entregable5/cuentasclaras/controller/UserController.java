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

import ttps.entregable5.cuentasclaras.model.Credentials;
import ttps.entregable5.cuentasclaras.model.Usuario;
import ttps.entregable5.cuentasclaras.repository.UsuarioRepository;
import ttps.entregable5.cuentasclaras.service.TokenService;

@Controller
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UsuarioRepository userRepo;

	@Autowired
	private TokenService tokenService;

	// un dia
	private final int EXPIRATION_IN_SEC = 100;


	@PostMapping("/create") // Map ONLY POST Requests
	public ResponseEntity<?> createUser(@RequestBody Usuario user) {

		// recibir el usuario y validar que no sea nulo
		if (user == null || user.getEmail() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no ha sido bien cargado");
		}
		// validar si el email ya esta en uso
		if (userRepo.findByEmail(user.getEmail()) != null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body("Existe una cuenta creada para ese email");
		}
		// insertar el usuario en la db
		userRepo.save(user);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable(name = "id") Long id) {
		Optional<Usuario> usr = userRepo.findById(id);
		if (usr.isPresent()) {
			Usuario usrEncontrado = usr.get();
			return new ResponseEntity<Usuario>(usrEncontrado, HttpStatus.OK);
		}
		System.out.println("Usuario no encontrado");
		return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);

	}

	/*
	@PostMapping("/login")
	public ResponseEntity<?> loginUsuario(@RequestBody Usuario usuario) {
		// chequear si el usuario existe y ver si la password coincide
		Optional<Usuario> user = userRepo.findByUsuario(usuario.getUsuario());
		if (user.isPresent()) {
			Usuario usrEncontrado = user.get();
			if (usrEncontrado.getPassword().equals(usuario.getPassword())) {
				return new ResponseEntity<Usuario>(usrEncontrado, HttpStatus.OK);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario y/o contraseña incorrecta");
	}*/

	@PostMapping(path = "/login")
	public ResponseEntity<?> authenticate(@RequestBody Usuario usr) {

		if(isLoginSuccess(usr.getUsuario(), usr.getPassword())) {
			String token = tokenService.generateToken(usr.getUsuario(), EXPIRATION_IN_SEC);
			Usuario user = userRepo.findByUsuario(usr.getUsuario());
			String userId = Long.toString(user.getId());
			System.out.println(userId);

			return ResponseEntity.ok(new Credentials(token, EXPIRATION_IN_SEC, usr.getUsuario(), userId));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o password incorrecto");
		}
	}
	private boolean isLoginSuccess(String username, String password) {
		// recupero el usuario de la base de usuarios
		Usuario u = userRepo.findByUsuarioAndPassword(username,password);

		// chequeo que el usuario exista y el password sea correcto
		return (u != null && u.getPassword().equals(password));
	}

	/**
	 * En el header enviar token: {idUsuario}+’123456’ Retorna #200 con los datos
	 * del usuario especificado, #404 si no se encuentra el usuario y #401 en caso
	 * de token inválido.
	 **/
	/**
	 * @GetMapping("{id}") public ResponseEntity<Usuario>
	 * getUser(@PathVariable("id")long id, @RequestHeader(name = "token") String
	 * token){
	 * 
	 * String tokenEsperado = id + "123456"; if (tokenEsperado != token) { return
	 * new ResponseEntity(HttpStatus.UNAUTHORIZED); }
	 * 
	 * System.out.println("Obteniendo un usuario con id" + id);
	 * 
	 * if(userRepo.findById(id) == null) { System.out.println("Usuario no
	 * encontrado"); return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
	 * 
	 * } Usuario usuario = userRepo.getReferenceById(id); //ver que onda esto return
	 * new ResponseEntity<Usuario>(usuario, HttpStatus.OK); }
	 **/
	@PutMapping("/{id}")
	public ResponseEntity<?> editarUsuario(@RequestBody Usuario usr, @PathVariable("id") Long id) {
		Optional<Usuario> usrDB = userRepo.findById(id);
		if (usrDB.isPresent()) {
			Usuario usuario = usrDB.get();
			usuario.setNombre(usr.getNombre());
			usuario.setApellido(usr.getApellido());
			usuario.setUsuario(usr.getUsuario());
			usuario.setEmail(usr.getEmail());
			usuario.setPassword(usr.getPassword());
			userRepo.save(usuario);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado, no es posible editar");
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<Usuario> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepo.findAll();
	}
}
