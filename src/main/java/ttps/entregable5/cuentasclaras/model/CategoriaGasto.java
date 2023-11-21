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
@Table(name= "categoriasGasto")
public class CategoriaGasto {
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="nombre")
	private String nombreGasto;

	@ManyToOne()
    @JoinColumn(name = "imgId")
	private Imagen img;

	public CategoriaGasto(Long id, String nombreGasto, Imagen img) {
		this.id = id;
		this.nombreGasto = nombreGasto;
		this.img = img;
	}
	public CategoriaGasto() {
		
	}
	public CategoriaGasto(String nombreGasto) {
		this.nombreGasto = nombreGasto;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreGasto() {
		return nombreGasto;
	}

	public void setNombreGasto(String nombreGasto) {
		this.nombreGasto = nombreGasto;
	}

	public Imagen getImg() {
		return img;
	}

	public void setImg(Imagen img) {
		this.img = img;
	}
}
