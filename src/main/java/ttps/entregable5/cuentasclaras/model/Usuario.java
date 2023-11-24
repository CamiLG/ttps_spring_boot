package ttps.entregable5.cuentasclaras.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;


@Entity // This tells Hibernate to make a table out of this class
@Table(name= "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="usuario", unique=true)
	private String usuario;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="password")
	private String password;

	@JsonIgnore
	 @ManyToMany
	 @JoinTable(name = "amigos",
	        joinColumns = @JoinColumn(name = "usuarioId"),
	        inverseJoinColumns = @JoinColumn(name = "amigoId"))
	private List<Usuario> amigos;

	@JsonIgnore
	@ManyToMany(mappedBy = "integrantes")
	private List<Grupo> grupos;

	@JsonIgnore
	@OneToMany(mappedBy = "usuarioGasto")
	private List<Gasto> gastos;

	public Usuario() {
		
	}
	
	public Usuario(Long id, String nombre, String apellido, String usuario, String email, String password, List<Usuario> amigos, List<Grupo> grupos, List<Gasto> gastos) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.email = email;
		this.password = password;
		this.amigos = amigos;
		this.grupos = grupos;
		this.gastos = gastos;
	}
	
	public Usuario(String nombre, String apellido, String usuario, String email, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

	public void crearGrupo(Grupo unGrupo){}

	public void crearGasto(Gasto unGasto){}

	public void enviarInvitacion(String unEmail){}

	public double calcularSaldoTotal(SaldoPorPersona unSaldo){
		return 0.00;
	}
}
