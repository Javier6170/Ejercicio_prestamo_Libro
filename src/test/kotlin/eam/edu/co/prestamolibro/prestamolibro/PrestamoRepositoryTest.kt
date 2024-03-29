package eam.edu.co.prestamolibro.prestamolibro

import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.modelo.Libro
import eam.edu.co.prestamolibro.prestamolibro.modelo.Prestamo
import eam.edu.co.prestamolibro.prestamolibro.modelo.Usuario
import eam.edu.co.prestamolibro.prestamolibro.repositories.PrestamoRespository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityManager
import org.springframework.transaction.annotation.Transactional
import java.util.*

@SpringBootTest
@Transactional
class PrestamoRepositoryTest {

    @Autowired
    lateinit var prestamoRepository: PrestamoRespository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        prestamoRepository.create(Prestamo(1,Date(23,9,2021),Usuario("1","rodriguez","javier"),Libro("1","178643438","Matematica Vectorial",Editorial(2,"Castellana"))))

        val prestamo = entityManager.find(Prestamo::class.java,1)
        Assertions.assertNotNull(prestamo)
        Assertions.assertEquals("Matematica Vectorial", prestamo.libro.nombre_Libro)
        Assertions.assertEquals("178643438", prestamo.libro.isbn_libro)
        Assertions.assertEquals(Date(23,9,2021), prestamo.fecha_prestamo)
        Assertions.assertEquals("Castellana", prestamo.libro.id_editorial.nombre_editorial)
        Assertions.assertEquals("javier", prestamo.id_usuario.nombre_usuario)
        Assertions.assertEquals("rodriguez", prestamo.id_usuario.apellido_usuario)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        entityManager.persist(Prestamo(1,Date(2021,9,23),Usuario("1","rodriguez","javier"),Libro("1","178643438","Matematica Vectorial",Editorial(2,"Castellana"))))

        //ejecutando...
        val prestamo = entityManager.find(Prestamo::class.java, 1)
        prestamo.id_usuario.nombre_usuario = "gladys"
        prestamo.id_usuario.apellido_usuario = "martin"
        prestamo.libro.isbn_libro = "1020-4040-89871"
        prestamo.libro.nombre_Libro = "Castellano basico"
        prestamo.libro.id_editorial.nombre_editorial = "Nacho"

        prestamoRepository.update(prestamo)

        //assersiones
        val prestamoAssert = entityManager.find(Prestamo::class.java, 1)
        Assertions.assertEquals("gladys", prestamoAssert.id_usuario.nombre_usuario)
        Assertions.assertEquals("martin", prestamoAssert.id_usuario.apellido_usuario)
        Assertions.assertEquals("1020-4040-89871",  prestamo.libro.isbn_libro)
        Assertions.assertEquals("Castellano basico",  prestamo.libro.nombre_Libro)
        Assertions.assertEquals("Nacho",  prestamo.libro.id_editorial.nombre_editorial)
    }

    @Test
    fun findTest() {
        entityManager.persist(Prestamo(1,Date(2021,9,23),Usuario("1","rodriguez","javier"),Libro("1","178643438","Matematica Vectorial",Editorial(2,"Castellana"))))

        val prestamo = prestamoRepository.find(1)

        Assertions.assertNotNull(prestamo)
        Assertions.assertEquals("javier", prestamo?.id_usuario?.nombre_usuario)
        Assertions.assertEquals("rodriguez", prestamo?.id_usuario?.apellido_usuario)
        Assertions.assertEquals("Matematica Vectorial", prestamo?.libro?.nombre_Libro)
    }

    @Test
    fun testDelete() {
        entityManager.persist(Prestamo(1,Date(2021,9,23),Usuario("1","rodriguez","javier"),Libro("1","178643438","Matematica Vectorial",Editorial(2,"Castellana"))))

        //ejecucion de la preuba
        prestamoRepository.delete(1)

        //assersiones
        val prestamo = entityManager.find(Prestamo::class.java, 1)
        Assertions.assertNull(prestamo)
    }

    @Test
    fun testFindByUsuario(){
        val usuario = Usuario("1", "Rodriguez", "Javier")
        entityManager.persist(usuario)
        val editorial = Editorial(2,"Castellana")
        entityManager.persist(editorial)
        val libro = Libro("1","178643438","Matematica Vectorial",editorial)
        entityManager.persist(libro)
        entityManager.persist(Prestamo(1,Date(2021,9,25),usuario,libro))
        entityManager.persist(Prestamo(2,Date(2021,9,26),usuario,libro))
        entityManager.persist(Prestamo(3,Date(2021,9,27),usuario,libro))

        //ejecutando pruebas
        val usuarios = prestamoRepository.findByUsuario("1")

        //assertions
        Assertions.assertEquals(3,usuarios.size)
    }

    @Test
    fun testFindByLibro(){
        val usuario = Usuario("1", "Rodriguez", "Javier")
        entityManager.persist(usuario)
        val editorial = Editorial(2,"Castellana")
        entityManager.persist(editorial)
        val libro = Libro("1","178643438","Matematica Vectorial",editorial)
        entityManager.persist(libro)
        entityManager.persist(Prestamo(1,Date(2021,9,25),usuario,libro))
        entityManager.persist(Prestamo(2,Date(2021,9,26),usuario,libro))


        //ejecutando pruebas
        val libros = prestamoRepository.findByLibro("1")

        //assertions
        Assertions.assertEquals(2,libros.size)
    }




}