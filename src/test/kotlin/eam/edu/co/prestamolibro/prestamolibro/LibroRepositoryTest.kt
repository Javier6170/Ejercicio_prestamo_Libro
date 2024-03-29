package eam.edu.co.prestamolibro.prestamolibro


import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.modelo.Libro
import eam.edu.co.prestamolibro.prestamolibro.repositories.LibroRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityManager
import org.springframework.transaction.annotation.Transactional


@SpringBootTest
@Transactional
class LibroRepositoryTest {

    @Autowired
    lateinit var libroRepository: LibroRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        libroRepository.create(Libro("1","178643438","Matematica Vectorial",Editorial(2,"Castellana")))

        val libro = entityManager.find(Libro::class.java,"1")
        Assertions.assertNotNull(libro)
        Assertions.assertEquals("178643438", libro.isbn_libro)
        Assertions.assertEquals("Matematica Vectorial", libro.nombre_Libro)
        Assertions.assertEquals("Castellana", libro.id_editorial.nombre_editorial)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        entityManager.persist(Libro("1","178643438","Matematica Vectorial",Editorial(2,"Castellana")))

        //ejecutando...
        val libro = entityManager.find(Libro::class.java, "1")
        libro.isbn_libro = "1020-4040-89871"
        libro.nombre_Libro = "Castellano basico"
        libro.id_editorial.nombre_editorial = "Babel"

        libroRepository.update(libro)

        //assersiones
        val libroAssert = entityManager.find(Libro::class.java, "1")
        Assertions.assertEquals("1020-4040-89871", libroAssert.isbn_libro)
        Assertions.assertEquals("Castellano basico", libroAssert.nombre_Libro)
        Assertions.assertEquals("Babel", libroAssert.id_editorial.nombre_editorial)
    }

    @Test
    fun findTest() {
        entityManager.persist(Libro("1","178643438","Matematica Vectorial",Editorial(2,"Castellana")))

        val libro = libroRepository.find("1")

        Assertions.assertNotNull(libro)
        Assertions.assertEquals("Matematica Vectorial", libro?.nombre_Libro)
        Assertions.assertEquals("Castellana", libro?.id_editorial?.nombre_editorial)
        Assertions.assertEquals("178643438", libro?.isbn_libro)
    }


    @Test
    fun testDelete() {
        entityManager.persist(Libro("1","178643438","Matematica Vectorial",Editorial(2,"Castellana")))

        //ejecucion de la preuba
        libroRepository.delete("1")

        //assersiones
        val libro = entityManager.find(Libro::class.java, "1")
        Assertions.assertNull(libro)
    }

    @Test
    fun testFindByEditorial(){
        val editorial = Editorial(1,"Castellana")
        entityManager.persist(editorial)
        entityManager.persist(Libro("1","654646464","Matematica vectorial",editorial))
        entityManager.persist(Libro("2","9151651165","Castellano basico",editorial))


        //ejecutando pruebas
        val editoriales = libroRepository.findByEditorial(1)

        //assertions
        Assertions.assertEquals(2,editoriales.size)
    }
}