package com.pavii.uth.data;



import java.sql.Date;
import java.time.LocalDate;


public class Clientes extends AbstractEntity {

    private Long idcliente;
    private String nombre;
    private Integer telefono;
 
    private String correo;
    private String direccion;
    private Date fecha_nacimiento;

    public Long getidcliente() {
        return idcliente;
    }
    public void setidcliente(Long idCliente) {
        this.idcliente = idCliente;
    }
    public String getnombre() {
        return nombre;
    }
    public void setnombre(String nombreCliente) {
        this.nombre = nombreCliente;
    }
    public Integer gettelefono() {
        return telefono;
    }
    public void settelefono(Integer telefonoCliente) {
        this.telefono = telefonoCliente;
    }
    public String getcorreo() {
        return correo;
    }
    public void setcorreo(String emailCliente) {
        this.correo = emailCliente;
    }
    public String getdireccion() {
        return direccion;
    }
    public void setdireccion(String direccionCliente) {
        this.direccion = direccionCliente;
    }
    public Date getfecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setfecha_nacimiento(LocalDate fechaNacimientoCliente) {
        this.fecha_nacimiento = convertirFecha(fechaNacimientoCliente);
    }
    
    private Date convertirFecha(LocalDate fecha) {
		return Date.valueOf(fecha);
	}

}
