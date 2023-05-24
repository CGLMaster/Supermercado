package integracion.Marcas;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import negocio.Marcas.TMarca;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DAOMarcasTest {
	static DAOMarcasimp daoMarcas;
	static int id;
	
	@BeforeClass
	public static void beforeClass(){
		daoMarcas = new DAOMarcasimp();
	}
	
	@Test
	public void aCrearNuevaMarca(){
		TMarca marca = new TMarca("PruebaCrear");
		
		id = daoMarcas.crearMarca(marca);
		
		assertNotEquals(-1,id);
	}
	
	@Test
	public void bCrearMarcaYaExistente(){
		TMarca marca = new TMarca("Colacao");
		
		int res = daoMarcas.crearMarca(marca);
		
		assertEquals(-1,res);
	}
	
	@Test
	public void cModificarMarcaError(){
		TMarca marca = daoMarcas.buscarMarca(809010);
		marca.setNombre("Colacao"); //Nombre ya existente
		
		int res = daoMarcas.modificarMarca(marca);
		
		assertEquals(-1,res);
	}
	
	@Test
	public void dModificarMarcaCorrecto(){
		TMarca marca = daoMarcas.buscarMarca(id);
		marca.setNombre("PruebaModificar"); //Nombre no existente
		
		int res = daoMarcas.modificarMarca(marca);
		
		assertEquals(1,res);
	}
	
	@Test
	public void eBuscarMarcaExistente(){
		
		TMarca res = daoMarcas.buscarMarca(id);
		
		assertNotNull(res);
	}
	
	@Test
	public void fBuscarMarcaNoExistente(){
		int id = -2001;
		
		TMarca res = daoMarcas.buscarMarca(id);
		
		assertNull(res);
	}
	
	@Test
	public void gEliminarMarcaSinProductos(){
		
		int res = daoMarcas.eliminarMarca(id);
		
		assertEquals(1,res);
	}
	
	@Test
	public void hEliminarMarcaConProductos(){
		int id = 809010;
		
		int res = daoMarcas.eliminarMarca(id);
		
		assertEquals(-1,res);
	}
	
	@Test
	public void iBuscarTodasLasMarcas(){
		
		Set<TMarca> res = daoMarcas.buscarTodas();
		
		assertNotNull(res);
	}
	
}
