package com.colegio.repository;
import com.colegio.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    List<Actividad> findByMateriaId(Long materiaId);
    List<Actividad> findByDocenteId(Long docenteId);
}
