package ttps.entregable5.cuentasclaras.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "categoriasGrupo")
public class CategoriaGrupo {
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="nombre")
	private String nombreGrupo;

	@ManyToOne()
    @JoinColumn(name = "imgId")
	private Imagen img;

	public CategoriaGrupo() {
		
	}

	public CategoriaGrupo(Long id, String nombreGrupo, Imagen img) {
		this.id = id;
		this.nombreGrupo = nombreGrupo;
		this.img = img;
	}
	
	public CategoriaGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public Imagen getImg() {
		return img;
	}

	public void setImg(Imagen img) {
		this.img = img;
	}
}
