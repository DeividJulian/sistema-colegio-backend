package com.colegio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "actividades")
public class Actividad {

    public enum Tipo { QUIZ, TALLER, EXAMEN, PROYECTO, TAREA }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es requerido")
    @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s\\-\\.]+$", message = "El título solo puede contener letras y espacios, no números")
    @Column(nullable = false)
    private String titulo;

    @NotNull(message = "El tipo es requerido")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @NotNull(message = "El valor máximo es requerido")
    @Min(value = 1, message = "El valor debe ser al menos 1")
    @Max(value = 100, message = "El valor no puede superar 100")
    @Column(nullable = false)
    private Integer valorMaximo;

    @NotNull(message = "La fecha límite es requerida")
    @Column(nullable = false)
    private LocalDate fechaLimite;

    @NotNull(message = "La materia es requerida")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    @NotNull(message = "El docente es requerido")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "docente_id", nullable = false)
    private Docente docente;

    @JsonIgnore
    @OneToMany(mappedBy = "actividad", fetch = FetchType.LAZY)
    private List<Asignacion> asignaciones = new ArrayList<>();

    public Actividad() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }
    public Integer getValorMaximo() { return valorMaximo; }
    public void setValorMaximo(Integer valorMaximo) { this.valorMaximo = valorMaximo; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }
    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }
    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }
    public List<Asignacion> getAsignaciones() { return asignaciones; }
    public void setAsignaciones(List<Asignacion> asignaciones) { this.asignaciones = asignaciones; }
}