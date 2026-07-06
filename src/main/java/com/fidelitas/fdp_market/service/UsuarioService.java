package com.fidelitas.fdp_market.service;

import com.fidelitas.fdp_market.domain.Rol;
import com.fidelitas.fdp_market.domain.Usuario;
import com.fidelitas.fdp_market.repository.RolRepository;
import com.fidelitas.fdp_market.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // hu1 - registro
    @Transactional
    public Usuario registrarUsuario(String correo, String password, String nombreRol) {
        if (usuarioRepository.existsByCorreo(correo)) {
            throw new IllegalArgumentException("Correo ya registrado.");
        }
        Rol rol = rolRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new IllegalArgumentException("Rol no existe."));

        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setClaveHash(passwordEncoder.encode(password));
        usuario.setRol(rol);
        usuario.setSaldoBilletera(BigDecimal.ZERO);

        return usuarioRepository.save(usuario);
    }
    
    // hu3 - recargar saldo billetera
    @Transactional
    public void recargarSaldo(Long usuarioId, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monto no puede ser menor a cero.");
        }
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        
        usuario.setSaldoBilletera(usuario.getSaldoBilletera().add(monto));
        usuarioRepository.save(usuario);
    }
    
    // hu4 - sub-vendedores
    @Transactional
    public Usuario crearSubVendedor(Long padreId, String correo, String password, BigDecimal comision) {
        Usuario padre = usuarioRepository.findById(padreId)
                .orElseThrow(() -> new IllegalArgumentException("Vendedor padre no existe."));
        
        Rol rolSub = rolRepository.findByNombre("Sub-vendedor")
                .orElseThrow(() -> new IllegalArgumentException("Rol no inicializado."));

        if (usuarioRepository.existsByCorreo(correo)) {
            throw new IllegalArgumentException("Correo en uso.");
        }

        Usuario sub = new Usuario();
        sub.setCorreo(correo);
        sub.setClaveHash(passwordEncoder.encode(password));
        sub.setRol(rolSub);
        sub.setVendedorPadre(padre);
        sub.setComisionSubvendedorPct(comision);
        
        return usuarioRepository.save(sub);
    }
    
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

}
