package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Table(name="usuario")
@Entity
data class Usuario(
    @Id
    @Column(name="user_identification")
    val user_identification:String,

    @Column(name="apellido_usuario")
    var apellido_usuario:String,

    @Column(name="nombre_usuario")
    var nombre_usuario:String,
):Serializable
