package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.*


@Table(name="libro")
@Entity
data class Libro(
    @Id
    @Column(name="codigo_libro")
    val id:String,

    @Column(name="isbn_libro")
    var isbn_libro:String,

    @Column(name="nombre_Libro")
    var nombre_Libro:String,


    @ManyToOne
    @JoinColumn(name="id_editorial")
    val id_editorial:Editorial,



    ):Serializable
