package com.colegio.service;
import com.colegio.model.Docente;
import com.colegio.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class DocenteService {
    @Autowired private DocenteRepository repo;
    public List<Docente> listar(){return repo.findAll();}
    public Optional<Docente> buscarPorId(Long id){return repo.findById(id);}
    public Docente guardar(Docente d){
        if(repo.existsByCedula(d.getCedula())) throw new RuntimeException("Cédula ya registrada");
        if(repo.existsByCorreo(d.getCorreo())) throw new RuntimeException("Correo ya registrado");
        return repo.save(d);
    }
    public Docente actualizar(Long id,Docente datos){
        return repo.findById(id).map(d->{
            d.setNombre(datos.getNombre()); d.setCorreo(datos.getCorreo()); d.setTelefono(datos.getTelefono());
            return repo.save(d);
        }).orElseThrow(()->new RuntimeException("Docente no encontrado"));
    }
    public void eliminar(Long id){repo.deleteById(id);}
}
