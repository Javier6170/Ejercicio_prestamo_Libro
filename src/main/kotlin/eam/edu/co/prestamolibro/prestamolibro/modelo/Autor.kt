package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Table(name="autor")
@Entity
data class Autor(
    @Id
    @Column(name="codigo_autor")
    val codigo_autor: Int,

    @Column(name="name")
    var name:String,

    @Column(name="lastname")
    var lastname:String,
):Serializable
