package com.colegio.service;
import com.colegio.model.*;
import com.colegio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class AsignacionService {
    @Autowired private AsignacionRepository repo;
    @Autowired private EstudianteRepository estudianteRepo;
    @Autowired private ActividadRepository actividadRepo;
    public List<Asignacion> listar(){return repo.findAll();}
    public Optional<Asignacion> buscarPorId(Long id){return repo.findById(id);}
    public Asignacion guardar(Asignacion a){
        Estudiante est=estudianteRepo.findById(a.getEstudiante().getId()).orElseThrow(()->new RuntimeException("Estudiante no encontrado"));
        Actividad act=actividadRepo.findById(a.getActividad().getId()).orElseThrow(()->new RuntimeException("Actividad no encontrada"));
        if(repo.existsByEstudianteIdAndActividadId(est.getId(),act.getId()))
            throw new RuntimeException("Esta actividad ya fue asignada a este estudiante");
        a.setEstudiante(est); a.setActividad(act);
        return repo.save(a);
    }
    public Asignacion actualizar(Long id,Asignacion datos){
        return repo.findById(id).map(a->{
            a.setEstado(datos.getEstado());
            if(datos.getEstado()==Asignacion.Estado.CALIFICADO) a.setNota(datos.getNota());
            if(datos.getFechaEntrega()!=null) a.setFechaEntrega(datos.getFechaEntrega());
            return repo.save(a);
        }).orElseThrow(()->new RuntimeException("Asignación no encontrada"));
    }
    public void eliminar(Long id){repo.deleteById(id);}
}
