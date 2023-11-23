package ttps.entregable5.cuentasclaras.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupos")
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "grupo_usuario", joinColumns = {
			@JoinColumn(name = "grupoId", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "usuarioId", referencedColumnName = "id") })
	private Set<Usuario> integrantes;

	@OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
	private List<Gasto> gastosGrupo;

	@ManyToOne()
	@JoinColumn(name = "categoriaGrupoId")
	private CategoriaGrupo categoriaGrupo;

	public Grupo() {
		
	}
	public Grupo(Long id, String nombre, Set<Usuario> integrantes, List<Gasto> gastosGrupo,
			CategoriaGrupo categoriaGrupo) {
		this.id = id;
		this.nombre = nombre;
		this.integrantes = integrantes;
		this.gastosGrupo = gastosGrupo;
		this.categoriaGrupo = categoriaGrupo;
	}

	public Grupo(String nombre, Usuario integrante, Gasto gastoGrupo,
			CategoriaGrupo categoriaGrupo) {
		this.nombre = nombre;
		this.integrantes.add(integrante);
		this.gastosGrupo.add(gastoGrupo);
		this.categoriaGrupo = categoriaGrupo;
	}
	
	public Grupo(String nombre, CategoriaGrupo categoriaGrupo) {
		this.nombre = nombre;
		this.categoriaGrupo = categoriaGrupo;
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

	public Set<Usuario> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(Set<Usuario> integrantes) {
		this.integrantes = integrantes;
	}

	public List<Gasto> getGastosGrupo() {
		return gastosGrupo;
	}

	public void setGastosGrupo(List<Gasto> gastosGrupo) {
		this.gastosGrupo = gastosGrupo;
	}

	public CategoriaGrupo getCategoriaGrupo() {
		return categoriaGrupo;
	}

	public void setCategoriaGrupo(CategoriaGrupo categoriaGrupo) {
		this.categoriaGrupo = categoriaGrupo;
	}

	public void agregarIntegrante(Usuario unIntegrante) {
	}
}
