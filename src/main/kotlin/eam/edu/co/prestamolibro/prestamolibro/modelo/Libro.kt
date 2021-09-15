package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.*


@Table(name="libro")
@Entity
data class Libro(
    @Id
    @Column(name="codigo_libro")
    private val codigo_libro:String,

    @Column(name="isbn_libro")
    private var isbn_libro:String,

    @Column(name="nombre_Libro")
    private var nombre_Libro:String,


    @ManyToOne
    @JoinColumn(name="id_editorial")
    private val id_editorial:Editorial,



):Serializable
