package eam.edu.co.prestamolibro.prestamolibro.modelo

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Table(name="editorial")
@Entity
data class Editorial(
    @Id
    @Column(name="codigo_editorial")
    private val codigo_editorial: Long,

    @Column(name="nombre_editorial")
    private var nombre_editorial:String,
):Serializable