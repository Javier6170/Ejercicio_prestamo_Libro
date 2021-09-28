package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import java.util.*
import javax.persistence.*


@Table(name="prestamo")
@Entity
data class Prestamo(
    @Id
    @Column(name="id")
    val id: Long,

    @Column(name="fecha_prestamo")
    val fecha_prestamo:Date,

    @ManyToOne
    @JoinColumn(name="id_usuario")
    val id_usuario:Usuario,

    @ManyToOne
    @JoinColumn(name="id_libro")
    val libro:Libro,
):Serializable
