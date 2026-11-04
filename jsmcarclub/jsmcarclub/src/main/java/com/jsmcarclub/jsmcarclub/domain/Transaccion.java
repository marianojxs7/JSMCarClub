
package com.jsmcarclub.jsmcarclub.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false, length = 30)
    private String metodoPago;

    @Column(nullable = false, length = 30)
    private String estado;

    @Column(nullable = false, length = 50)
    private String tipoServicio = "PUBLICACION_PREMIUM";

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario registradoPor;

    public Transaccion() {
    }

    @PrePersist
    public void antesDeGuardar() {
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Usuario getRegistradoPor() { return registradoPor; }
    public void setRegistradoPor(Usuario registradoPor) { this.registradoPor = registradoPor; }
}
