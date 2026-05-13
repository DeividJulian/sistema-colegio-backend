package com.colegio.service;
import com.colegio.model.Estudiante;
import com.colegio.model.Grado;
import com.colegio.repository.EstudianteRepository;
import com.colegio.repository.GradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class EstudianteService {
    @Autowired private EstudianteRepository repo;
    @Autowired private GradoRepository gradoRepo;
    public List<Estudiante> listar(){return repo.findAll();}
    public Optional<Estudiante> buscarPorId(Long id){return repo.findById(id);}
    public List<Estudiante> listarPorGrado(Long gradoId){return repo.findByGradoId(gradoId);}
    public Estudiante guardar(Estudiante e){
        if(repo.existsByCodigo(e.getCodigo())) throw new RuntimeException("Código estudiantil ya existe");
        if(e.getGrado()!=null && e.getGrado().getId()!=null){
            Grado g=gradoRepo.findById(e.getGrado().getId()).orElseThrow(()->new RuntimeException("Grado no encontrado"));
            e.setGrado(g);
        }
        return repo.save(e);
    }
    public Estudiante actualizar(Long id,Estudiante datos){
        return repo.findById(id).map(e->{
            e.setNombre(datos.getNombre()); e.setCorreo(datos.getCorreo());
            if(datos.getGrado()!=null && datos.getGrado().getId()!=null){
                Grado g=gradoRepo.findById(datos.getGrado().getId()).orElseThrow(()->new RuntimeException("Grado no encontrado"));
                e.setGrado(g);
            }
            return repo.save(e);
        }).orElseThrow(()->new RuntimeException("Estudiante no encontrado"));
    }
    public void eliminar(Long id){repo.deleteById(id);}
}
