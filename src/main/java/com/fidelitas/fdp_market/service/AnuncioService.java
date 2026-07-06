package com.fidelitas.fdp_market.service;

import com.fidelitas.fdp_market.domain.Anuncio;
import com.fidelitas.fdp_market.domain.ImagenAnuncio;
import com.fidelitas.fdp_market.repository.AnuncioRepository;
import com.fidelitas.fdp_market.repository.ImagenAnuncioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    private final ImagenAnuncioRepository imagenAnuncioRepository;

    public AnuncioService(AnuncioRepository anuncioRepository, ImagenAnuncioRepository imagenAnuncioRepository) {
        this.anuncioRepository = anuncioRepository;
        this.imagenAnuncioRepository = imagenAnuncioRepository;
    }

    // hu7 - guardar anuncio
    @Transactional
    public Anuncio guardarAnuncio(Anuncio anuncio) {
        return anuncioRepository.save(anuncio);
    }

    // hu7 - anuncio + imagenes firebase
    @Transactional
    public void registrarImagen(Anuncio anuncio, String url) {
        ImagenAnuncio img = new ImagenAnuncio();
        img.setAnuncio(anuncio);
        img.setUrl(url);
        imagenAnuncioRepository.save(img);
    }

    // hu8 - anuncio gestor (visibilidad)
    @Transactional
    public void cambiarEstadoVisibilidad(Long idAnuncio, boolean activo) {
        Anuncio anuncio = anuncioRepository.findById(idAnuncio)
                .orElseThrow(() -> new IllegalArgumentException("Anuncio no encontrado"));
        anuncio.setActivo(activo);
        anuncioRepository.save(anuncio);
    }

    @Transactional(readOnly = true)
    public List<Anuncio> listarPorCategoria(Long categoriaId) {
        return anuncioRepository.findByActivoTrueAndCategoriaId(categoriaId);
    }

    @Transactional(readOnly = true)
    public Optional<Anuncio> buscarPorId(Long id) {
        return anuncioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Anuncio> listarPorCategoriaCompleta(Long categoriaId) {
        return anuncioRepository.findByCategoriaId(categoriaId);
    }

}
