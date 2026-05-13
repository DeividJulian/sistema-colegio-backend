package com.colegio.repository;
import com.colegio.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
    List<Estudiante> findByGradoId(Long gradoId);
}
