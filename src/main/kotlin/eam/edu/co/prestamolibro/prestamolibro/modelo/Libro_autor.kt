package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.*


@Table(name="libro_autor")
@Entity
data class Libro_autor(
    @Id
    @Column(name="id")
    val id:Int,

    @ManyToOne
    @JoinColumn(name="id_autor")
    val autor:Autor,

    @ManyToOne
    @JoinColumn(name="id_libro")
    val libro:Libro,
):Serializable
