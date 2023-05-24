package integracion.Productos;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;
import negocio.Marcas.TMarca;
import negocio.Productos.*;
import negocio.Secciones.TSeccion;
import presentacion.Date;

public class DAOProductosTests {
	private static DAOProductosImp daoProductos;
	private static int id;
	private static  TMarca marca;
	private static TSeccion seccion;

	@BeforeClass
	public static void beforeClass() {
		daoProductos = new DAOProductosImp();
		marca = new TMarca("Nestle");
		marca.setId(312579);
		seccion = new TSeccion(3, "Carniceria");
		seccion.setId(12);
	}

	@Test
	public void altaProductoPerecedero() {
		Date date = new Date(2100, 01, 01);
		TProducto p = new TProdPerecedero("Pera de prueba", 1, 999.0, date, marca, seccion);
		id = daoProductos.crearProducto(p);
		assertNotEquals(-1, id);
	}
	
	@Test
	public void eliminarProducto(){
		id = daoProductos.eliminarProducto(id);
		assertNotEquals(-1, id);
	}
	
	@Test
	public void eliminarProductoInexistente(){
		id = daoProductos.eliminarProducto(Integer.MAX_VALUE);
		assertEquals(-1, id);
	}
	
	@Test 
	public void altaProductoNoPerecedero(){
		TProducto p = new TProdNoPerecedero("Dartz Kombat", 10, 500000.99,"Enlatados",marca, seccion);
		id = daoProductos.crearProducto(p);
		eliminarProducto();
		assertNotEquals(-1, id);
	}
	
	@Test
	public void vincularProveedor(){
		id = daoProductos.vincularProveedor(25, 85417872, 1.50);
		assertNotEquals(-1, id);
	}
	
	@Test
	public void desvincularProveedor(){
		id = daoProductos.desvincularProveedor(25, 85417872);
		assertNotEquals(-1, id);
	}	
	
	@Test
	public void vincularProveedorInexistente(){
		id = daoProductos.vincularProveedor(25, 00000000, 1.50);
		assertEquals(-1, id);
	}
	
	@Test
	public void desvincularSinVinculo() {
		id = daoProductos.desvincularProveedor(25, 85417872);
		assertNotEquals(-1, id); //La BD no sufre cambios
	}
	
	@Test
	public void modificarProducto() {
		TProducto p = new TProdNoPerecedero("Llama de combate", 5, 5098,"Encartonados",marca, seccion);
		p.setID(31);
		id = daoProductos.modificarProducto(p);
		assertEquals(-1, id);
	}
	
	@Test
	public void buscarTodos(){
		HashSet<TProducto> pHash = daoProductos.buscarTodos();
		assertNotNull(pHash);
	}

}
