package com.colegio.service;
import com.colegio.model.Materia;
import com.colegio.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class MateriaService {
    @Autowired private MateriaRepository repo;
    public List<Materia> listar(){return repo.findAll();}
    public Optional<Materia> buscarPorId(Long id){return repo.findById(id);}
    public Materia guardar(Materia m){
        if(repo.findByCodigo(m.getCodigo()).isPresent()) throw new RuntimeException("Código ya existe");
        return repo.save(m);
    }
    public Materia actualizar(Long id,Materia datos){
        return repo.findById(id).map(m->{
            m.setCodigo(datos.getCodigo()); m.setNombre(datos.getNombre()); m.setDescripcion(datos.getDescripcion());
            return repo.save(m);
        }).orElseThrow(()->new RuntimeException("Materia no encontrada"));
    }
    public void eliminar(Long id){repo.deleteById(id);}
}
