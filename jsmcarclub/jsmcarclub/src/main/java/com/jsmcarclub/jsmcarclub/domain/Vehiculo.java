
package com.jsmcarclub.jsmcarclub.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String placa;

    @Column(nullable = false, length = 100)
    private String modelo;

    @Column(nullable = false)
    private Integer anio;

    @Column(nullable = false)
    private Integer kilometraje;

    @Column(nullable = false)
    private Double precio;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false, length = 20)
    private String estado = "DISPONIBLE";

    @Column(nullable = false)
    private Boolean premium = false;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false, length = 100)
    private String nombrePropietario;

    @Column(nullable = false, length = 20)
    private String telefonoPropietario;

    @Column(nullable = false)
    private LocalDateTime fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id", nullable = false)
    private TipoVehiculo tipoVehiculo;

    @ManyToOne
    @JoinColumn(name = "combustible_id", nullable = false)
    private Combustible combustible;

    @ManyToOne
    @JoinColumn(name = "transmision_id", nullable = false)
    private Transmision transmision;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario registradoPor;

    public Vehiculo() {
    }

    @PrePersist
    public void antesDeGuardar() {
        if (fechaPublicacion == null) {
            fechaPublicacion = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public Integer getKilometraje() { return kilometraje; }
    public void setKilometraje(Integer kilometraje) { this.kilometraje = kilometraje; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Boolean getPremium() { return premium; }
    public void setPremium(Boolean premium) { this.premium = premium; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public String getNombrePropietario() { return nombrePropietario; }
    public void setNombrePropietario(String nombrePropietario) { this.nombrePropietario = nombrePropietario; }

    public String getTelefonoPropietario() { return telefonoPropietario; }
    public void setTelefonoPropietario(String telefonoPropietario) { this.telefonoPropietario = telefonoPropietario; }

    public LocalDateTime getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDateTime fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }

    public TipoVehiculo getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }

    public Combustible getCombustible() { return combustible; }
    public void setCombustible(Combustible combustible) { this.combustible = combustible; }

    public Transmision getTransmision() { return transmision; }
    public void setTransmision(Transmision transmision) { this.transmision = transmision; }

    public Usuario getRegistradoPor() { return registradoPor; }
    public void setRegistradoPor(Usuario registradoPor) { this.registradoPor = registradoPor; }
}
