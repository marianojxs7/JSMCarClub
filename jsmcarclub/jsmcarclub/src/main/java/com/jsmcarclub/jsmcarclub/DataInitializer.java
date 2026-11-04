
package com.jsmcarclub.jsmcarclub;

import com.jsmcarclub.jsmcarclub.domain.Rol;
import com.jsmcarclub.jsmcarclub.domain.Marca;
import com.jsmcarclub.jsmcarclub.domain.TipoVehiculo;
import com.jsmcarclub.jsmcarclub.domain.Combustible;
import com.jsmcarclub.jsmcarclub.domain.Transmision;
import com.jsmcarclub.jsmcarclub.domain.Usuario;

import com.jsmcarclub.jsmcarclub.repository.RolRepository;
import com.jsmcarclub.jsmcarclub.repository.MarcaRepository;
import com.jsmcarclub.jsmcarclub.repository.TipoVehiculoRepository;
import com.jsmcarclub.jsmcarclub.repository.CombustibleRepository;
import com.jsmcarclub.jsmcarclub.repository.TransmisionRepository;
import com.jsmcarclub.jsmcarclub.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final MarcaRepository marcaRepository;
    private final TipoVehiculoRepository tipoVehiculoRepository;
    private final CombustibleRepository combustibleRepository;
    private final TransmisionRepository transmisionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jsm.admin.password}")
    private String adminPassword;

    public DataInitializer(
            RolRepository rolRepository,
            MarcaRepository marcaRepository,
            TipoVehiculoRepository tipoVehiculoRepository,
            CombustibleRepository combustibleRepository,
            TransmisionRepository transmisionRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.rolRepository = rolRepository;
        this.marcaRepository = marcaRepository;
        this.tipoVehiculoRepository = tipoVehiculoRepository;
        this.combustibleRepository = combustibleRepository;
        this.transmisionRepository = transmisionRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        crearRol("ADMINISTRADOR", "Administración completa del sistema");
        crearRol("COLABORADOR", "Gestión de vehículos y publicaciones");
        crearRol("CLIENTE", "Usuario registrado de la plataforma");

        crearMarca("Toyota");
        crearMarca("Honda");
        crearMarca("Hyundai");
        crearMarca("Nissan");
        crearMarca("BMW");
        crearMarca("Mercedes-Benz");
        crearMarca("Audi");
        crearMarca("Ford");
        crearMarca("Kia");
        crearMarca("Suzuki");
        crearMarca("Mazda");
        crearMarca("Volkswagen");
        crearMarca("Chevrolet");
        crearMarca("Mitsubishi");
        crearMarca("Subaru");

        crearTipo("Sedán");
        crearTipo("SUV");
        crearTipo("Pickup");
        crearTipo("Hatchback");
        crearTipo("Coupé");
        crearTipo("Convertible");
        crearTipo("Camioneta");
        crearTipo("Deportivo");

        crearCombustible("Gasolina");
        crearCombustible("Diésel");
        crearCombustible("Híbrido");
        crearCombustible("Eléctrico");

        crearTransmision("Automática");
        crearTransmision("Manual");
        crearTransmision("CVT");

        crearAdministrador();
    }

    private void crearRol(String nombre, String descripcion) {
        if (rolRepository.findByNombre(nombre).isEmpty()) {
            Rol rol = new Rol();
            rol.setNombre(nombre);
            rol.setDescripcion(descripcion);
            rolRepository.save(rol);
        }
    }

    private void crearMarca(String nombre) {
        if (!marcaRepository.existsByNombre(nombre)) {
            Marca marca = new Marca();
            marca.setNombre(nombre);
            marcaRepository.save(marca);
        }
    }

    private void crearTipo(String nombre) {
        if (!tipoVehiculoRepository.existsByNombre(nombre)) {
            TipoVehiculo tipo = new TipoVehiculo();
            tipo.setNombre(nombre);
            tipoVehiculoRepository.save(tipo);
        }
    }

    private void crearCombustible(String nombre) {
        if (!combustibleRepository.existsByNombre(nombre)) {
            Combustible combustible = new Combustible();
            combustible.setNombre(nombre);
            combustibleRepository.save(combustible);
        }
    }

    private void crearTransmision(String nombre) {
        if (!transmisionRepository.existsByNombre(nombre)) {
            Transmision transmision = new Transmision();
            transmision.setNombre(nombre);
            transmisionRepository.save(transmision);
        }
    }

    private void crearAdministrador() {

        String correo = "admin@jsmcarclub.com";

        if (usuarioRepository.findByCorreo(correo).isEmpty()) {

            Rol rolAdmin = rolRepository.findByNombre("ADMINISTRADOR")
                    .orElseThrow(() -> new IllegalStateException(
                            "No se encontró el rol ADMINISTRADOR"));

            Usuario admin = new Usuario();

            admin.setNombre("admin");
            admin.setCorreo(correo);
            admin.setContrasena(passwordEncoder.encode(adminPassword));
            admin.setTelefono("00000000");
            admin.setActivo(true);
            admin.setRol(rolAdmin);

            usuarioRepository.save(admin);

            System.out.println("======================================");
            System.out.println("ADMINISTRADOR JSMCARCLUB CREADO");
            System.out.println("Correo: " + correo);
            System.out.println("======================================");
        }
    }
}
