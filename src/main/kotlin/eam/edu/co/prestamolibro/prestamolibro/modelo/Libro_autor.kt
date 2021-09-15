package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.*


@Table(name="libro_autor")
@Entity
data class Libro_autor(
    @Id
    @Column(name="id")
    private val id:Long,

    @ManyToOne
    @JoinColumn(name="id_autor")
    private val autor:Autor,

    @ManyToOne
    @JoinColumn(name="id_libro")
    private val libro:Libro,
):Serializable
