package com.colegio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "grados")
public class Grado {

    public enum Nivel { PRIMARIA, SECUNDARIA }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del grado es requerido")
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s0-9\\-]+$", message = "El nombre solo puede contener letras, números y guiones")
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotNull(message = "El nivel es requerido")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;

    @JsonIgnore
    @OneToMany(mappedBy = "grado", fetch = FetchType.LAZY)
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Grado() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }
    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(List<Estudiante> estudiantes) { this.estudiantes = estudiantes; }
}