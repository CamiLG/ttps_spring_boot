package ttps.entregable5.cuentasclaras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ttps.entregable5.cuentasclaras.model.Usuario;
import ttps.entregable5.cuentasclaras.repository.UsuarioRepository;

@Controller
@RequestMapping(path="/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UsuarioRepository userRepo;
	
	  @PostMapping(path="/add") // Map ONLY POST Requests
		public ResponseEntity<String> createUser(@RequestBody Usuario user){
			
			//recibir el usuario y validar que no sea nulo
	        if (user == null || user.getEmail() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no ha sido bien cargado");
	        }
			//validar si el email ya esta en uso
			if(userRepo.findByEmail(user.getEmail())!= null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existe una cuenta creada para ese email");
			}
			//insertar el usuario en la db
			userRepo.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito");
		}

		@GetMapping("{id}")
		public ResponseEntity<Usuario> getUser(@PathVariable("id")long id, @RequestHeader(name = "token") String token){
		/**En el header enviar token: {idUsuario}+’123456’
			Retorna #200 con los datos del usuario
			especificado, #404 si no se encuentra el usuario y
			#401 en caso de token inválido.**/	
			
			String tokenEsperado = id + "123456";
	        if (tokenEsperado != token) {
	            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
	        }
			
	       System.out.println("Obteniendo un usuario con id" + id);
			
			if(userRepo.findById(id) == null) {
				System.out.println("Usuario no encontrado");
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			
			}
		    Usuario usuario = userRepo.getReferenceById(id); //ver que onda esto
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
	  
	  
	  
	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Usuario> getAllUsers() {
	    // This returns a JSON or XML with the users
	    return userRepo.findAll();
	  }
}
