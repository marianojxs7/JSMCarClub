
package com.jsmcarclub.jsmcarclub.controller;

import com.jsmcarclub.jsmcarclub.domain.Vehiculo;
import com.jsmcarclub.jsmcarclub.domain.Fotografia;
import com.jsmcarclub.jsmcarclub.service.VehiculoService;
import com.jsmcarclub.jsmcarclub.service.MarcaService;
import com.jsmcarclub.jsmcarclub.service.TipoVehiculoService;
import com.jsmcarclub.jsmcarclub.service.FotografiaService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final VehiculoService vehiculoService;
    private final MarcaService marcaService;
    private final TipoVehiculoService tipoVehiculoService;
    private final FotografiaService fotografiaService;

    public HomeController(
            VehiculoService vehiculoService,
            MarcaService marcaService,
            TipoVehiculoService tipoVehiculoService,
            FotografiaService fotografiaService) {

        this.vehiculoService = vehiculoService;
        this.marcaService = marcaService;
        this.tipoVehiculoService = tipoVehiculoService;
        this.fotografiaService = fotografiaService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("vehiculos",
                vehiculoService.listarActivos());
        model.addAttribute("vehiculosPremium",
                vehiculoService.listarPremium());
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo(
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) Long marcaId,
            @RequestParam(required = false) Long tipoId,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            Model model) {

        List<Vehiculo> vehiculos = vehiculoService.listarActivos();

        if (buscar != null && !buscar.isBlank()) {
            String texto = buscar.toLowerCase().trim();

            vehiculos = vehiculos.stream()
                    .filter(v ->
                            (v.getModelo() != null &&
                             v.getModelo().toLowerCase().contains(texto))
                            ||
                            (v.getMarca() != null &&
                             v.getMarca().getNombre().toLowerCase().contains(texto)))
                    .collect(Collectors.toList());
        }

        if (marcaId != null) {
            vehiculos = vehiculos.stream()
                    .filter(v -> v.getMarca() != null
                            && v.getMarca().getId().equals(marcaId))
                    .collect(Collectors.toList());
        }

        if (tipoId != null) {
            vehiculos = vehiculos.stream()
                    .filter(v -> v.getTipoVehiculo() != null
                            && v.getTipoVehiculo().getId().equals(tipoId))
                    .collect(Collectors.toList());
        }

        if (precioMin != null) {
            vehiculos = vehiculos.stream()
                    .filter(v -> v.getPrecio() != null
                            && v.getPrecio() >= precioMin)
                    .collect(Collectors.toList());
        }

        if (precioMax != null) {
            vehiculos = vehiculos.stream()
                    .filter(v -> v.getPrecio() != null
                            && v.getPrecio() <= precioMax)
                    .collect(Collectors.toList());
        }

        Map<Long, String> portadas = new HashMap<>();

        for (Vehiculo vehiculo : vehiculos) {
            List<Fotografia> fotos =
                    fotografiaService.listarPorVehiculo(vehiculo.getId());

            if (!fotos.isEmpty()) {
                portadas.put(vehiculo.getId(), fotos.get(0).getUrl());
            }
        }

        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("portadas", portadas);
        model.addAttribute("marcas", marcaService.listar());
        model.addAttribute("tipos", tipoVehiculoService.listar());

        model.addAttribute("buscar", buscar);
        model.addAttribute("marcaId", marcaId);
        model.addAttribute("tipoId", tipoId);
        model.addAttribute("precioMin", precioMin);
        model.addAttribute("precioMax", precioMax);

        return "catalogo/listado";
    }

    @GetMapping("/vehiculo/{id}")
    public String detalle(@PathVariable Long id, Model model) {

        Vehiculo vehiculo = vehiculoService.buscarPorId(id);

        if (vehiculo == null ||
                !Boolean.TRUE.equals(vehiculo.getActivo())) {
            return "redirect:/catalogo";
        }

        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("fotografias",
                fotografiaService.listarPorVehiculo(id));

        return "catalogo/detalle";
    }
}
