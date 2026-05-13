package com.colegio.service;
import com.colegio.model.*;
import com.colegio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ActividadService {
    @Autowired private ActividadRepository repo;
    @Autowired private MateriaRepository materiaRepo;
    @Autowired private DocenteRepository docenteRepo;
    public List<Actividad> listar(){return repo.findAll();}
    public Optional<Actividad> buscarPorId(Long id){return repo.findById(id);}
    public Actividad guardar(Actividad a){
        if(a.getMateria()!=null && a.getMateria().getId()!=null)
            a.setMateria(materiaRepo.findById(a.getMateria().getId()).orElseThrow(()->new RuntimeException("Materia no encontrada")));
        if(a.getDocente()!=null && a.getDocente().getId()!=null)
            a.setDocente(docenteRepo.findById(a.getDocente().getId()).orElseThrow(()->new RuntimeException("Docente no encontrado")));
        return repo.save(a);
    }
    public Actividad actualizar(Long id,Actividad datos){
        return repo.findById(id).map(a->{
            a.setTitulo(datos.getTitulo()); a.setTipo(datos.getTipo());
            a.setValorMaximo(datos.getValorMaximo()); a.setFechaLimite(datos.getFechaLimite());
            if(datos.getMateria()!=null && datos.getMateria().getId()!=null)
                a.setMateria(materiaRepo.findById(datos.getMateria().getId()).orElseThrow(()->new RuntimeException("Materia no encontrada")));
            if(datos.getDocente()!=null && datos.getDocente().getId()!=null)
                a.setDocente(docenteRepo.findById(datos.getDocente().getId()).orElseThrow(()->new RuntimeException("Docente no encontrado")));
            return repo.save(a);
        }).orElseThrow(()->new RuntimeException("Actividad no encontrada"));
    }
    public void eliminar(Long id){repo.deleteById(id);}
}
