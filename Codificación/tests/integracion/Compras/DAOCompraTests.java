package integracion.Compras;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import integracion.FactoríaIntegración.FactoriaIntegracion;
import negocio.Compras.TCompraTotal;
import negocio.Compras.TCompras;
import negocio.Compras.TProductoCantidad;
import negocio.Productos.TProducto;

public class DAOCompraTests {
	
	static DAOCompraimp daoCompra;
	
	@BeforeClass
	public static void beforeClass(){
		daoCompra = new DAOCompraimp();
	}

	@Test
	public void aniadirProductos() {
		
		TProductoCantidad producto = daoCompra.aniadirProductos(23, 3);
		
		assertEquals("Leche 1L", producto.getProducto());
		assertEquals(3, producto.getCantidad());
		
	}
	
	@Test
	public void aniadirProductosNoExistente() {
		
		TProductoCantidad producto = daoCompra.aniadirProductos(1, 3);
		
		assertNull(producto);
		
	}
	
	@Test
	public void aniadirProductosCantidadMayorQueStock() {
		
		TProductoCantidad producto = daoCompra.aniadirProductos(23, 1000000);
		
		assertEquals(-1, producto.getCantidad());
	}
	
	@Test
	public void eliminarProducto() {
		
		TProducto producto = FactoriaIntegracion.obtenerInstancia().generarDAOProductos().buscarProducto(23);
		int stockInicial = producto.getStock();
		
		daoCompra.eliminarProducto(23, 1);
		
		producto =FactoriaIntegracion.obtenerInstancia().generarDAOProductos().buscarProducto(23);
		int stockFinal = producto.getStock();
		
		
		assertTrue(stockFinal == (stockInicial - 1));
	}
	
	@Test
	public void eliminarProductoNoExistente() {
		
		int respuesta = daoCompra.eliminarProducto(-1, 1);
		
		assertEquals(-1, respuesta);
	}

	
	@Test
	public void stockProducto() {
		
		TProducto producto = FactoriaIntegracion.obtenerInstancia().generarDAOProductos().buscarProducto(23);
		int stockEsperado = producto.getStock();
		
		int stockProductoRespuesta = -1;
		
		try{
			stockProductoRespuesta = daoCompra.StockProducto(23);
		}
		catch(Exception e){
			fail("Producto no existe");
		}
		
		assertEquals(stockEsperado, stockProductoRespuesta);
	}
	
	
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void stockProductoNoExistente() throws SQLException {
		
		thrown.expect(SQLException.class);
		daoCompra.StockProducto(1);

	}
	
	
	@Test
	public void pagarCompra() {
		
		int ID_Cliente = 1;
		int ID_Trabajador = 2;
		int cantidadPagada = 7;
		int precioFinal = 4;
		
		TreeMap<Integer, TProductoCantidad> productosEnCarrito = new TreeMap<Integer, TProductoCantidad>();
		
		int ID_Producto = 23;
		int cantidad_producto = 2;
		productosEnCarrito.put(ID_Producto, new TProductoCantidad("Leche 1L", cantidad_producto, 2));
		
		TProducto producto = FactoriaIntegracion.obtenerInstancia().generarDAOProductos().buscarProducto(ID_Producto);
		int stockInicial = producto.getStock();
		
		TCompras compraInfo = new TCompras();
		compraInfo.setIdCliente(ID_Cliente);
		compraInfo.setIdTrabajador(ID_Trabajador);
		compraInfo.setPrecioEfectivo(cantidadPagada);
		compraInfo.setPrecioFinal(precioFinal);
		
		TCompraTotal compraTotal = new TCompraTotal(compraInfo, productosEnCarrito);
		
		// LLAMADA AL DAO
		int res = daoCompra.pagarCompra(compraTotal);
		
		
		// -----------------------
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://34.105.156.61:3306/is2_bd", "ibmuser1", "JavaJavita69");
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM COMPRA ORDER BY ID_COMPRA DESC LIMIT 1");
			
			resultSet.next();
			int ID_Cliente_db = resultSet.getInt("ID_CLIENTE");
			int ID_Trabajador_db = resultSet.getInt("ID_TRABAJADOR");
			int cantidadPagada_db = resultSet.getInt("PRECIO_PAGADO");
			int precioFinal_db = resultSet.getInt("PRECIO_TOTAL");
			
			// Comprobar que todos los atributos sean correctos
			assertEquals(ID_Cliente, ID_Cliente_db);
			assertEquals(ID_Trabajador, ID_Trabajador_db);
			assertEquals(cantidadPagada, cantidadPagada_db);
			assertEquals(precioFinal, precioFinal_db);
			
			// Comprobar que se redujo el stock del producto
			producto = FactoriaIntegracion.obtenerInstancia().generarDAOProductos().buscarProducto(ID_Producto);
			int stockFinal = producto.getStock();
			assertTrue(stockFinal == (stockInicial - cantidad_producto));
			
			// Comprobar el valor retornado
			assertEquals(0, res);
			
			
			// Eliminar compra creada
			Statement statement1 = connection.createStatement();
			statement1.executeUpdate("DELETE FROM COMPRA WHERE ID_COMPRA = (SELECT ID_COMPRA FROM (SELECT * FROM COMPRA) AS aux ORDER BY ID_COMPRA DESC LIMIT 1)");
			
		} catch (SQLException e) {
			fail("Error de SQL");
			System.out.println("HERE");
			e.printStackTrace();
		}
	
		
	}

}
