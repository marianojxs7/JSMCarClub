
package com.jsmcarclub.jsmcarclub.controller;

import com.jsmcarclub.jsmcarclub.domain.Vehiculo;
import com.jsmcarclub.jsmcarclub.service.VehiculoService;
import com.jsmcarclub.jsmcarclub.service.MarcaService;
import com.jsmcarclub.jsmcarclub.service.TipoVehiculoService;
import com.jsmcarclub.jsmcarclub.service.CombustibleService;
import com.jsmcarclub.jsmcarclub.service.TransmisionService;
import com.jsmcarclub.jsmcarclub.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;
    private final MarcaService marcaService;
    private final TipoVehiculoService tipoVehiculoService;
    private final CombustibleService combustibleService;
    private final TransmisionService transmisionService;
    private final UsuarioService usuarioService;

    public VehiculoController(
            VehiculoService vehiculoService,
            MarcaService marcaService,
            TipoVehiculoService tipoVehiculoService,
            CombustibleService combustibleService,
            TransmisionService transmisionService,
            UsuarioService usuarioService) {

        this.vehiculoService = vehiculoService;
        this.marcaService = marcaService;
        this.tipoVehiculoService = tipoVehiculoService;
        this.combustibleService = combustibleService;
        this.transmisionService = transmisionService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("vehiculos", vehiculoService.listar());
        return "admin/vehiculos/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        cargarCatalogos(model);
        return "admin/vehiculos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Vehiculo vehiculo,
            @RequestParam Long marcaId,
            @RequestParam Long tipoVehiculoId,
            @RequestParam Long combustibleId,
            @RequestParam Long transmisionId,
            @RequestParam Long usuarioId,
            RedirectAttributes redirectAttributes) {

        vehiculo.setMarca(marcaService.buscarPorId(marcaId));
        vehiculo.setTipoVehiculo(
                tipoVehiculoService.buscarPorId(tipoVehiculoId));
        vehiculo.setCombustible(
                combustibleService.buscarPorId(combustibleId));
        vehiculo.setTransmision(
                transmisionService.buscarPorId(transmisionId));
        vehiculo.setRegistradoPor(usuarioService.buscarPorId(usuarioId));

        vehiculoService.guardar(vehiculo);

        redirectAttributes.addFlashAttribute(
                "mensaje", "Vehículo guardado correctamente.");

        return "redirect:/admin/vehiculos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Vehiculo vehiculo = vehiculoService.buscarPorId(id);

        if (vehiculo == null) {
            return "redirect:/admin/vehiculos";
        }

        model.addAttribute("vehiculo", vehiculo);
        cargarCatalogos(model);

        return "admin/vehiculos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        vehiculoService.eliminar(id);

        redirectAttributes.addFlashAttribute(
                "mensaje", "Vehículo eliminado correctamente.");

        return "redirect:/admin/vehiculos";
    }

    private void cargarCatalogos(Model model) {
        model.addAttribute("marcas", marcaService.listar());
        model.addAttribute("tipos", tipoVehiculoService.listar());
        model.addAttribute("combustibles", combustibleService.listar());
        model.addAttribute("transmisiones", transmisionService.listar());
    }
}

