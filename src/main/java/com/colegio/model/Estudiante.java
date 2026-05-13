package com.colegio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código estudiantil es requerido")
    @Pattern(regexp = "\\d+", message = "El código debe contener solo dígitos")
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El correo es requerido")
    @Email(message = "El correo debe tener formato válido")
    @Column(nullable = false)
    private String correo;

    @NotNull(message = "El grado es requerido")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grado_id", nullable = false)
    private Grado grado;

    @JsonIgnore
    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<Asignacion> asignaciones = new ArrayList<>();

    public Estudiante() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Grado getGrado() { return grado; }
    public void setGrado(Grado grado) { this.grado = grado; }
    public List<Asignacion> getAsignaciones() { return asignaciones; }
    public void setAsignaciones(List<Asignacion> asignaciones) { this.asignaciones = asignaciones; }
}
