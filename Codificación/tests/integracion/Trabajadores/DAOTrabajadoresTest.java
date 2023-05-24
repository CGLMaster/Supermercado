package integracion.Trabajadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.Trabajadores.TTrabajador;

public class DAOTrabajadoresTest {
	static DAOTrabajadorimp trabajadores;
	
	@BeforeClass
	public static void BeforeClass(){
		trabajadores=new DAOTrabajadorimp();
	}
	
	@Test
	public void pruebaCreaBien(){
		TTrabajador t_param=new TTrabajador("PruebaTestMario", "testmario@testmario", "11223344A");	//este va antes de crear mal y de cualquier eliminar
		
		assertEquals(1,trabajadores.crearTrabajador(t_param));
	}
	
	@Test
	public void pruebaCreaMal(){
		TTrabajador t_param =new TTrabajador("PruebaTestMario", "testmario@testmario", "11223344A");
		
		assertEquals(0,trabajadores.crearTrabajador(t_param));
	}
	
	@Test
	public void pruebaeliminaBien(){
		
		assertEquals(1,trabajadores.eliminarTrabajador(4));
	}
	
	@Test
	public void pruebaeliminaMal(){
		assertEquals(0,trabajadores.eliminarTrabajador(-5));
	}
	
	@Test
	public void pruebabuscaBien(){
		TTrabajador elem=trabajadores.buscarTrabajador(2);
		assertNotNull(elem);
		assertEquals(elem.getDNI(), "12345");
		assertEquals(elem.getID_Trabajador(), 2);
		assertEquals(elem.getMail(), "willyrex@gmail.com");
		assertEquals(elem.getNombre(), "Willyrex");
	}
	
	@Test
	public void pruebabuscaMal(){
		assertNull(trabajadores.buscarTrabajador(17));
	}
	
	@Test
	public void pruebaModificarBien(){
		TTrabajador t_param=new TTrabajador("PruebaTestMarioCambiado", "testmario@testmario", "11223344A");
		t_param.setId(4);
		assertEquals(1, trabajadores.modificarTrabajador(t_param));
	}
	
	@Test
	public void pruebaModificarMal(){
		TTrabajador t_param=new TTrabajador("PruebaTestMarioCambiadoMal", "testmario@testmario", "11223344A");
		t_param.setId(15);
		assertEquals(1, trabajadores.modificarTrabajador(t_param));
	}
	
}
