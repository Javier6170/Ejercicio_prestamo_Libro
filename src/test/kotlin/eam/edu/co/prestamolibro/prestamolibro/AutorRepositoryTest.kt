package eam.edu.co.prestamolibro.prestamolibro

import eam.edu.co.prestamolibro.prestamolibro.modelo.Autor
import eam.edu.co.prestamolibro.prestamolibro.repositories.AutorRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityManager
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class AutorRepositoryTest {

    @Autowired
    lateinit var autorRepository:AutorRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        autorRepository.create(Autor(2, "juan", "marin"))

        val autor = entityManager.find(Autor::class.java,2)
        Assertions.assertNotNull(autor)
        Assertions.assertEquals("juan", autor.name)
        Assertions.assertEquals("marin", autor.lastname)
        Assertions.assertEquals(2, autor.codigo_autor)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        entityManager.persist(Autor(2,"juan","marin"))

        //ejecutando...
        val autor = entityManager.find(Autor::class.java, 2)
        autor.name = "gladys"
        autor.lastname = "pepo"

        autorRepository.update(autor)

        //assersiones
        val autorAssert = entityManager.find(Autor::class.java, 2)
        Assertions.assertEquals("gladys", autorAssert.name)
        Assertions.assertEquals("pepo", autorAssert.lastname)
    }

    @Test
    fun findTest() {
        entityManager.persist(Autor(2,"juan","marin"))

        val autor = autorRepository.find(2)

        Assertions.assertNotNull(autor)
        Assertions.assertEquals("juan", autor?.name)
        Assertions.assertEquals("marin", autor?.lastname)

    }

    @Test
    fun testDelete() {
        entityManager.persist(Autor(2,"juan","marin"))
        //ejecucion de la preuba
        autorRepository.delete(2)

        //assersiones
        val autor = entityManager.find(Autor::class.java, 2)
        Assertions.assertNull(autor)
    }
}