package integracion.Seccion;


import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import integracion.Seccion.DAOSeccionimp;
import negocio.Secciones.TSeccion;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DAOSeccionTests {
	static DAOSeccionimp daoSeccion;
	static int id;
	
	@BeforeClass
	public static void beforeClass(){
		daoSeccion = new DAOSeccionimp();
	}
	@Test
	public void aCrearNuevaSeccion(){
		TSeccion seccion = new TSeccion();
		seccion.setZona("PruebaCrear");
		seccion.setPasillo(1);
		id = daoSeccion.crearSeccion(seccion);
		
		assertNotEquals(-1,id);
	}
	@Test
	public void bCrearSeccionYaExistente(){
		TSeccion seccion = new TSeccion();
		seccion.setZona("Carniceria");
		seccion.setPasillo(1);
		int res = daoSeccion.crearSeccion(seccion);
		
		assertEquals(-1,res);
	}
	@Test
	public void cModificarSeccionError(){
		TSeccion seccion = daoSeccion.buscarSeccion(13);
		seccion.setZona("Carniceria"); //Nombre ya existente
		seccion.setPasillo(1);
		int res = daoSeccion.crearSeccion(seccion);
		
		assertEquals(-1,res);
	}
	@Test
	public void dModificarSeccionCorrecto(){
		TSeccion seccion = daoSeccion.buscarSeccion(id);
		seccion.setZona("PruebaModificar"); //Nombre no existente
		seccion.setPasillo(2);
		int res = daoSeccion.editarSeccion(seccion);
		
		assertEquals(1,res);
	}
	@Test
	public void eBuscarMarcaExistente(){
		
		TSeccion res = daoSeccion.buscarSeccion(id);
		
		assertNotNull(res);
	}
	@Test
	public void fBuscarSeccionNoExistente(){
		int id = -2001;
		
		TSeccion res = daoSeccion.buscarSeccion(id);
		
		assertNull(res);
	}
	@Test
	public void gEliminarSeccionSinProductos(){
		
		int res = daoSeccion.eliminarSeccion(id);
		
		assertEquals(1,res);
	}
	@Test
	public void hEliminarSeccionConProductos(){
		int id = 13;
		
		int res = daoSeccion.eliminarSeccion(id);
		
		assertEquals(-1,res);
	}
	@Test
	public void iBuscarTodasLasSecciones(){
		
		Set<TSeccion> res = daoSeccion.buscarTodos();
		
		assertNotNull(res);
	}

}
