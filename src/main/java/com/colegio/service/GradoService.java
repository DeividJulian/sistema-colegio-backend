package com.colegio.service;

import com.colegio.model.Grado;
import com.colegio.repository.GradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GradoService {
    @Autowired private GradoRepository repo;

    public List<Grado> listar() { return repo.findAll(); }

    public Optional<Grado> buscarPorId(Long id) { return repo.findById(id); }

    public Grado guardar(Grado grado) {
        if (repo.existsByNombre(grado.getNombre()))
            throw new RuntimeException("Ya existe un grado con ese nombre");
        return repo.save(grado);
    }

    public Grado actualizar(Long id, Grado datos) {
        return repo.findById(id).map(g -> {
            g.setNombre(datos.getNombre());
            g.setNivel(datos.getNivel());
            return repo.save(g);
        }).orElseThrow(() -> new RuntimeException("Grado no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Grado no encontrado con id: " + id);
        repo.deleteById(id);
    }
}
