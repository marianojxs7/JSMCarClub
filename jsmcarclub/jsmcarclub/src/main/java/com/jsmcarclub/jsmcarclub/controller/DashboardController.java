
package com.jsmcarclub.jsmcarclub.controller;

import com.jsmcarclub.jsmcarclub.domain.Vehiculo;
import com.jsmcarclub.jsmcarclub.service.VehiculoService;
import com.jsmcarclub.jsmcarclub.service.UsuarioService;
import com.jsmcarclub.jsmcarclub.service.TransaccionService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    private final VehiculoService vehiculoService;
    private final UsuarioService usuarioService;
    private final TransaccionService transaccionService;

    public DashboardController(
            VehiculoService vehiculoService,
            UsuarioService usuarioService,
            TransaccionService transaccionService) {

        this.vehiculoService = vehiculoService;
        this.usuarioService = usuarioService;
        this.transaccionService = transaccionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Vehiculo> vehiculos = vehiculoService.listar();

        long totalVehiculos = vehiculos.size();

        long vehiculosActivos = vehiculos.stream()
                .filter(v -> Boolean.TRUE.equals(v.getActivo()))
                .count();

        long vehiculosPremium = vehiculos.stream()
                .filter(v -> Boolean.TRUE.equals(v.getPremium()))
                .filter(v -> Boolean.TRUE.equals(v.getActivo()))
                .count();

        model.addAttribute("totalVehiculos", totalVehiculos);
        model.addAttribute("vehiculosActivos", vehiculosActivos);
        model.addAttribute("vehiculosPremium", vehiculosPremium);
        model.addAttribute("totalUsuarios", usuarioService.listar().size());
        model.addAttribute("totalTransacciones",
                transaccionService.listar().size());

        model.addAttribute("ultimosVehiculos",
                vehiculos.stream()
                        .sorted((a, b) -> b.getFechaPublicacion()
                                .compareTo(a.getFechaPublicacion()))
                        .limit(5)
                        .toList());

        return "admin/dashboard";
    }
}
