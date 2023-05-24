package integracion.Proveedores;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.Proveedores.TProveedor;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DAOProveedoresTest {
	static DAOProveedorimp daoProveedores;
	static int id;
	
	@BeforeClass
	public static void beforeClass(){
		daoProveedores = new DAOProveedorimp();
	}
	
	@Test
	public void aCrearNuevoProveedor(){
		TProveedor proveedor = new TProveedor("Granja San Francisco");
		
		id = daoProveedores.crearProveedor(proveedor);
		
		assertNotEquals(-1,id);
	}
	
	@Test
	public void bCrearProveedorExistente(){
		TProveedor proveedor = new TProveedor("Coca-Cola");//20149558
		
		id = daoProveedores.crearProveedor(proveedor);
		assertEquals(-1,id);
	}
	
	@Test
	public void cModificarProveedorExistente(){
		TProveedor proveedor = daoProveedores.buscarProveedor(51407067);//RopaporMayor
		proveedor.setNombre("Coca-Cola");
		
		int result = daoProveedores.editarProveedor(proveedor);
		
		assertEquals(-1,result);
	}
	
	@Test
	public void dModificarProveedorCorrecto(){
		TProveedor proveedor = daoProveedores.buscarProveedor(51407067);
		proveedor.setNombre("PruebaModificar");
		
		int result = daoProveedores.editarProveedor(proveedor);
		
		assertEquals(1,result);
	}
	
	@Test
	public void eBuscarProveedorExistente(){
		TProveedor proveedor = daoProveedores.buscarProveedor(51407067);
		assertNotNull(proveedor);
	}
	
	@Test
	public void fBuscarProveedorInexistente(){
		TProveedor proveedor = daoProveedores.buscarProveedor(-2020);
		assertNull(proveedor);
	}
	
	@Test
	public void gEliminarProveedorSinProductos(){
		int result = daoProveedores.eliminarProveedor(id);
		assertEquals(0,result);
	}
	
	@Test
	public void hEliminarProveedorConProductos(){
		int result = daoProveedores.eliminarProveedor(51407067);
		assertEquals(-1,result);
	}
	
	@Test
	public void iBuscarTodosLosProveedores(){
		Set<TProveedor> lista = daoProveedores.buscarTodos();
		assertNotNull(lista);
	}
}
