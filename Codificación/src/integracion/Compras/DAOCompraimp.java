
package integracion.Compras;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Set;

import negocio.Compras.TCompraTotal;
import negocio.Compras.TProductoCantidad;


public class DAOCompraimp implements DAOCompra {
	Connection c;

	public DAOCompraimp() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://34.105.156.61:3306/is2_bd", "ibmuser1", "JavaJavita69");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public int mostrarAyuda() {
		return 0;
	}

	
	public TProductoCantidad aniadirProductos(int ID_Producto, int cantidad) {		//buscar en bbdd el producto y lo devuelve
		TProductoCantidad producto=null;
		
		try{
			Statement s= c.createStatement();
			
			ResultSet r = s.executeQuery("SELECT * FROM (Producto P LEFT JOIN Perecedero Pe ON(P.Id_producto = Pe.Id_producto)) LEFT JOIN No_Perecedero NP ON(NP.Id_Producto = P.Id_producto) "
					+ "WHERE P.Id_producto =" + ID_Producto + ";");
			
			r.next();
			producto = new TProductoCantidad(r.getString("Nombre"),  cantidad, r.getDouble("precio"));
			
			if(cantidad > StockProducto(ID_Producto)){
				producto.setCantidad(-1);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return producto;
	}

	
	public int eliminarProducto(int ID_Producto, int cantidad) {		//reducir cantidad en bbdd
		int r;
		
		try{
			Statement s = c.createStatement();
			int stock_final = StockProducto(ID_Producto) - cantidad;
			r = s.executeUpdate("UPDATE Producto SET Stock = " + stock_final + " WHERE ID_Producto = " + ID_Producto);	
			
		}catch (Exception e) {
            r = -1;
            e.printStackTrace();
        }
		
		return r;
	}
	
	public int StockProducto(int id_producto) throws SQLException {
		int r = 0;
		try {
			Statement s= c.createStatement();
			ResultSet resultSet = s.executeQuery("SELECT Stock FROM Producto WHERE ID_Producto = " + id_producto + ";");
			
			resultSet.next();
			r = resultSet.getInt("Stock");
			
		} catch (SQLException e) {
            e.printStackTrace();
            r = -1;
            throw e;
		}	
		return r;
	}


	public int pagarCompra(TCompraTotal compra) {

		int r;

		try {
			Statement s = c.createStatement();
			
			Map<Integer,TProductoCantidad> productos = compra.getMapaProductos();
			Set<Integer> pr = productos.keySet();
			for (Integer key : pr) {
				eliminarProducto(key,productos.get(key).getCantidad());
			}

			r = s.executeUpdate(
					"INSERT INTO COMPRA(FECHA, PRECIO_TOTAL, PRECIO_PAGADO, ID_CLIENTE, ID_TRABAJADOR) values("
					+ "SYSDATE()" + ", "
					+ compra.getCompra().getPrecioFinal() + ", "
					+ compra.getCompra().getPrecioEfectivo() + ", " 
					+ compra.getCompra().getIdCliente() + ", " 
					+ compra.getCompra().getIdTrabajador() + ")");

		} catch (SQLException e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}
}