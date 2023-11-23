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
@Table(name= "saldosPersona")
public class SaldoPorPersona {
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="monto")
	private double monto;
	
	@ManyToOne
    @JoinColumn(name = "usuarioId")
	private Usuario saldoUsuario;
	
	@ManyToOne
	@JoinColumn(name="gasto_id", nullable=false)
	private Gasto gasto;

	public SaldoPorPersona() {
		
	}
	public SaldoPorPersona(Long id, double monto, Usuario saldoUsuario) {
		this.id = id;
		this.monto = monto;
		this.saldoUsuario = saldoUsuario;
	}
	
	public SaldoPorPersona(double monto, Usuario saldoUsuario) {
		this.monto = monto;
		this.saldoUsuario = saldoUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Usuario getSaldoUsuario() {
		return saldoUsuario;
	}

	public void setSaldoUsuario(Usuario saldoUsuario) {
		this.saldoUsuario = saldoUsuario;
	}
}