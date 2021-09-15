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
    private val codigo_autor: Long,

    @Column(name="name")
    private var name:String,

    @Column(name="lastname")
    private var lastname:String,
):Serializable
