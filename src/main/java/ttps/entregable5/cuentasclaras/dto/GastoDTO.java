package ttps.entregable5.cuentasclaras.dto;

import java.time.LocalDate;
import java.util.List;

import ttps.entregable5.cuentasclaras.model.*;

public class GastoDTO {

    private Long id;
    private String nombre;
    private double monto;

    private LocalDate fechaGasto;


    private FormaDivision formaDivision;

    private Imagen img;

    private CategoriaGasto categoriaGasto;

    public GastoDTO () {

    }
    public GastoDTO(Long id, String nombre, double monto) {
        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
    }

    public GastoDTO( String nombre, double monto, LocalDate fechaGasto, FormaDivision formaDivision, Imagen img, CategoriaGasto categoriaGasto) {
        this.nombre = nombre;
        this.monto = monto;
        this.fechaGasto = fechaGasto;
        this.formaDivision = formaDivision;
        this.img = img;
        this.categoriaGasto = categoriaGasto;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(LocalDate fechaGasto) {
        this.fechaGasto = fechaGasto;
    }


    public FormaDivision getFormaDivision() {
        return formaDivision;
    }

    public void setFormaDivision(FormaDivision formaDivision) {
        this.formaDivision = formaDivision;
    }


    public Imagen getImg() {
        return img;
    }

    public void setImg(Imagen img) {
        this.img = img;
    }


    public CategoriaGasto getCategoriaGasto() {
        return categoriaGasto;
    }

    public void setCategoriaGasto(CategoriaGasto categoriaGasto) {
        this.categoriaGasto = categoriaGasto;
    }

}
