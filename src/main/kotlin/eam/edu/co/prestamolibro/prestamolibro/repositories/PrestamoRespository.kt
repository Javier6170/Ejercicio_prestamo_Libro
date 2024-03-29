package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.Libro
import eam.edu.co.prestamolibro.prestamolibro.modelo.Prestamo
import eam.edu.co.prestamolibro.prestamolibro.modelo.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component //anotacion que nos dice que esta es una clase manejada por springboot
@Transactional //para que las operaciones sobre la BD funcionen.
class PrestamoRespository {
    @Autowired //esta anotacion indica que springboot se encargara de instanciar esta clase.
    lateinit var em: EntityManager //clase que nos da JPA para manipular las entidades.

    fun create(prestamo: Prestamo){
        em.persist(prestamo) //inserta en la tabla que define la entidad.
    }

    //? quiere decir q algo puede ser null
    fun find(id:Int): Prestamo?{
        //se el envia la clase que quiero buscar y el valor de la llave primaria que quiero buscar.
        return em.find(Prestamo::class.java, id) //busca en la bd por llave primaria
    }

    fun update(prestamo: Prestamo) {
        em.merge(prestamo) //actualizar un registro sobre la BD
    }

    fun delete(id: Int) {
        //buscan por id la entidad que quiero borrar
        val prestamo = find(id)

        //solo puedo borrar una persona que exista...
        if (prestamo!=null) {
            //borra la entidad de la BD, recibe por parametro la entidad a borrrar
            em.remove(prestamo)
        }
}

    fun findByUsuario(id: String): List<Prestamo> {
        val query = em.createQuery("SELECT prest FROM Prestamo prest WHERE prest.id_usuario.user_identification =: user_identification")
        query.setParameter("user_identification", id)

        return query.resultList as List<Prestamo>
    }

    fun findByLibro(id: String): List<Prestamo> {
        val query = em.createQuery("SELECT prest FROM Prestamo prest WHERE prest.libro.codigo_libro =: codigo_libro")
        query.setParameter("codigo_libro", id)

        return query.resultList as List<Prestamo>
    }
}