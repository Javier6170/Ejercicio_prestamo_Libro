package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import java.util.*
import javax.persistence.*


@Table(name="prestamo")
@Entity
data class Prestamo(
    @Id
    @Column(name="id")
    private val id:Long,

    @Column(name="fecha_prestamo")
    private val fecha_prestamo:Date,

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private val id_usuario:Usuario,

    @ManyToOne
    @JoinColumn(name="id_libro")
    private val libro:Libro,
):Serializable
