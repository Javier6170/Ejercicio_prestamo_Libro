package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.Autor
import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.modelo.Prestamo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component //anotacion que nos dice que esta es una clase manejada por springboot
@Transactional //para que las operaciones sobre la BD funcionen.
class EditorialRepository {

    //inyeccion de depencia...... el framework se encarga de asignarle valor a la depencia
    @Autowired //esta anotacion indica que springboot se encargara de instanciar esta clase.
    lateinit var em: EntityManager //clase que nos da JPA para manipular las entidades.


    fun create(editorial: Editorial){
        em.persist(editorial) //inserta en la tabla que define la entidad.
    }

    //? quiere decir q algo puede ser null
    fun find(id:Int): Editorial?{
        //se el envia la clase que quiero buscar y el valor de la llave primaria que quiero buscar.
        return em.find(Editorial::class.java, id) //busca en la bd por llave primaria
    }

    fun update(editorial: Editorial) {
        em.merge(editorial) //actualizar un registro sobre la BD
    }

    fun delete(id: Int) {
        //buscan por id la entidad que quiero borrar
        val editorial = find(id)

        //solo puedo borrar una persona que exista...
        if (editorial!=null) {
            //borra la entidad de la BD, recibe por parametro la entidad a borrrar
            em.remove(editorial)
        }
    }



}