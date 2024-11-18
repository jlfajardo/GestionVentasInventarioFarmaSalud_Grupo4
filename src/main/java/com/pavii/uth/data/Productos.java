package com.pavii.uth.data;



public class Productos extends AbstractEntity {

    private Long idproducto;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private Integer stock;
    private String categoria;
    private String laboratorio;

    public Long getidproducto() {
        return idproducto;
    }
    public void setidproducto(Long idProducto) {
        this.idproducto = idProducto;
    }
    public String getnombre() {
        return nombre;
    }
    public void setnombre(String nombreProducto) {
        this.nombre = nombreProducto;
    }
    public String getdescripcion() {
        return descripcion;
    }
    public void setdescripcion(String descripcionProducto) {
        this.descripcion = descripcionProducto;
    }
    public Integer getprecio() {
        return precio;
    }
    public void setprecio(Integer precioProducto) {
        this.precio = precioProducto;
    }
    public Integer getstock() {
        return stock;
    }
    public void setstock(Integer cantidadDisponible) {
        this.stock = cantidadDisponible;
    }
    public String getcategoria() {
        return categoria;
    }
    public void setcategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getlaboratorio() {
        return laboratorio;
    }
    public void setlaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

}
