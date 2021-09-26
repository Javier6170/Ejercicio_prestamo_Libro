package eam.edu.co.prestamolibro.prestamolibro

import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.modelo.Prestamo
import eam.edu.co.prestamolibro.prestamolibro.repositories.EditorialRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityManager
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class EditorialRepositoryTest {

    @Autowired
    lateinit var editorialRepository:EditorialRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        editorialRepository.create(Editorial(1, "castellana"))

        val editorial = entityManager.find(Editorial::class.java,1)
        Assertions.assertNotNull(editorial)
        Assertions.assertEquals("castellana", editorial.nombre_editorial)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        entityManager.persist(Editorial(1,"castellana"))

        //ejecutando...
        val editorial = entityManager.find(Editorial::class.java, 1)
        editorial.nombre_editorial = "babel"

        editorialRepository.update(editorial)

        //assersiones
        val editorialAssert = entityManager.find(Editorial::class.java, 1)
        Assertions.assertEquals("babel", editorialAssert.nombre_editorial)
    }

    @Test
    fun findTest() {
        entityManager.persist(Editorial(1,"castellana"))

        val editorial = editorialRepository.find(1)

        Assertions.assertNotNull(editorial)
        Assertions.assertEquals("castellana", editorial?.nombre_editorial)

    }

    @Test
    fun testDelete() {
        entityManager.persist(Editorial(1,"castellana"))
        //ejecucion de la preuba
        editorialRepository.delete(1)

        //assersiones
        val editorial = entityManager.find(Editorial::class.java, 1)
        Assertions.assertNull(editorial)
    }


}