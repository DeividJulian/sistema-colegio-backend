package com.colegio.repository;
import com.colegio.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    boolean existsByEstudianteIdAndActividadId(Long estudianteId, Long actividadId);
    List<Asignacion> findByEstudianteId(Long estudianteId);
    List<Asignacion> findByActividadId(Long actividadId);
}
