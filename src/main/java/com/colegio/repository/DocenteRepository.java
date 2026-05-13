package com.colegio.repository;
import com.colegio.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Optional<Docente> findByCedula(String cedula);
    boolean existsByCedula(String cedula);
    boolean existsByCorreo(String correo);
}
