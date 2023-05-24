package integracion.Productos;

import negocio.Marcas.TMarca;
import negocio.Productos.TProdNoPerecedero;
import negocio.Productos.TProdPerecedero;
import negocio.Productos.TProducto;
import negocio.Proveedores.TProveedor;
import negocio.Secciones.TSeccion;
import presentacion.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class DAOProductosImp implements DAOProductos {
	Connection c;

	public DAOProductosImp() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://34.105.156.61:3306/is2_bd", "ibmuser1", "JavaJavita69");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int crearProducto(TProducto producto) {
		int r;
		try {

			c.setAutoCommit(false);

			Statement s = c.createStatement();

			r = s.executeUpdate("INSERT INTO Producto(precio, Stock, Nombre, ID_Marca, ID_Seccion) VALUES ("
					+ producto.getPrecio() + ", " + producto.getStock() + ", '" + producto.getNombre() + "', "
					+ producto.getMarca().getId() + ", " + producto.getSeccion().getId() + ");",Statement.RETURN_GENERATED_KEYS);
			
			ResultSet idInserted = s.getGeneratedKeys();
	        if (idInserted.next()){
	            r=idInserted.getInt(1);
	        }
	        producto.setID(r);

			if (producto.getTipo() == null) {
				Statement s2 = c.createStatement();
				r = s2.executeUpdate("INSERT INTO Perecedero(ID_Producto, Fecha_de_caducidad) VALUES ("
						+ producto.getID() + ", '" + producto.getFecha().toString() + "');");
			} else {
				Statement s3 = c.createStatement();
				r = s3.executeUpdate("INSERT INTO No_Perecedero(ID_Producto, Tipo) VALUES (" + producto.getID() + ", '"
						+ producto.getTipo() + "');");
			}
			 r=idInserted.getInt(1);
			c.commit();
		
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			r = -1;
			e.printStackTrace();
		}

		try {
			c.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
		
	}

	public int vincularProveedor(int id_producto, int id_proveedor, double precio) {
		int r;

		try {
			Statement s = c.createStatement();
			r = s.executeUpdate(
					"UPDATE Producto SET id_proveedor = " + id_proveedor + " WHERE id_producto = " + id_producto + ";");
			r = s.executeUpdate(
					"INSERT INTO Distribuye(precio_suministro, ID_Producto, ID_Proveedor) VALUES (" + precio + "," + id_producto + ","+ id_proveedor + ");");
			
		} catch (SQLException e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}

	public HashSet<TProducto> buscarTodos() {
		HashSet<TProducto> productos = null;

		try {
			Statement s = c.createStatement();
			ResultSet result = s.executeQuery("SELECT ID_Producto FROM Producto");
			productos = new HashSet<TProducto>();

			while (result.next()) {
				TProducto prod = buscarProducto(result.getInt("ID_Producto"));
				productos.add(prod);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productos;
	}

	public TProducto buscarProducto(int id_producto) {
		TProducto producto = null;
		TMarca marca = null;
		TProveedor proveedor = null;
		TSeccion seccion = null;

		try {
			Statement s = c.createStatement();

			ResultSet r = s.executeQuery(
					"SELECT * FROM (Producto P LEFT JOIN Perecedero Pe ON(P.Id_producto = Pe.Id_producto)) LEFT JOIN No_Perecedero NP ON(NP.Id_Producto = P.Id_producto) "
							+ "WHERE P.Id_producto =" + id_producto + ";");

			r.next();

			if (r.getString("Tipo") == null) {
				String[] fecha = r.getString("Fecha_de_caducidad").split("-");
				Date date = new Date(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]),
						Integer.parseInt(fecha[2]));
				producto = new TProdPerecedero(r.getString("Nombre"), r.getInt("Stock"), r.getDouble("precio"), date);
			} else
				producto = new TProdNoPerecedero(r.getString("Nombre"), r.getInt("Stock"), r.getDouble("precio"),
						r.getString("Tipo"));

			if (r.getInt("ID_Marca") != 0)
				marca = buscarMarcaAux(r.getInt("ID_Marca"));
			if (r.getInt("ID_Proveedor") != 0)
				proveedor = buscarProveedoresAux(r.getInt("ID_Proveedor"));
			if (r.getInt("ID_Seccion") != 0)
				seccion = buscarSeccionAux(r.getInt("ID_Seccion"));

			producto.setMarca(marca);
			producto.setProveedor(proveedor);
			producto.setSeccion(seccion);
			producto.setID(id_producto);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;

	}

	private TMarca buscarMarcaAux(int id) {
		TMarca marca = null;

		try {
			Statement s = c.createStatement();

			ResultSet r = s.executeQuery("SELECT * FROM marca WHERE ID_Marca =" + id + ";");

			r.next();
			marca = new TMarca(r.getString("Nombre"));
			marca.setId(id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return marca;
	}

	private TProveedor buscarProveedoresAux(int id) {
		TProveedor proveedor = null;

		try {
			Statement s = c.createStatement();

			ResultSet r = s.executeQuery("SELECT * FROM proveedor WHERE ID_Proveedor =" + id + ";");

			r.next();
			proveedor = new TProveedor(r.getString("Nombre"));
			proveedor.setId(id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proveedor;
	}

	private TSeccion buscarSeccionAux(int id) {
		TSeccion seccion = null;

		try {
			Statement s = c.createStatement();

			ResultSet r = s.executeQuery("SELECT * FROM Seccion WHERE ID_Seccion =" + id + ";");

			r.next();
			seccion = new TSeccion(r.getInt("Pasillo"), r.getString("Zona"));
			seccion.setId(id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seccion;
	}

	public int modificarProducto(TProducto producto) {
		int r;

		try {
			Statement s = c.createStatement();

			r = s.executeUpdate("UPDATE Producto SET nombre = '" + producto.getNombre() + "', precio = "
					+ producto.getPrecio() + ", Stock = " + producto.getStock() + ", ID_Marca = "
					+ producto.getMarca().getId() + ", ID_Seccion = " + producto.getSeccion().getId()
					+ " WHERE ID_Producto = " + producto.getID());

			// Falta modificar tablas de Perecedero/No Perecedero.
			if (producto.getTipo() == null) {
				Statement s2 = c.createStatement();
				r = s2.executeUpdate("UPDATE Perecedero SET Fecha_de_caducidad = '" + producto.getFecha().toString() + "' WHERE ID_Producto = " + producto.getID()+";");
			} else {
				Statement s3 = c.createStatement();
				r = s3.executeUpdate("UPDATE No_Perecedero SET Tipo = '" + producto.getTipo() + "' WHERE ID_Producto = " + producto.getID()+ ";");
			}

		} catch (SQLException e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}

	public int eliminarProducto(int id_producto) {
		int r = -1;
		try {
			Statement s = c.createStatement();

			TProducto elem = buscarProducto(id_producto);

			if (elem.getTipo() != null)
				r = s.executeUpdate("DELETE FROM No_Perecedero  WHERE id_producto = " + id_producto + ";");
			else
				r = s.executeUpdate("DELETE FROM Perecedero  WHERE id_producto = " + id_producto + ";");
				
			r = s.executeUpdate("DELETE FROM Producto  WHERE id_producto = " + id_producto + ";");

		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
			r = -1;
		}
		return r;
	}

	public int desvincularProveedor(int id_producto, int id_proveedor) {
		int r;

		try {
			Statement s = c.createStatement();

			r = s.executeUpdate("UPDATE Producto SET id_proveedor = NULL WHERE id_producto = " + id_producto + ";");
			r=s.executeUpdate("DELETE FROM Distribuye WHERE id_producto = " + id_producto + ";");
		} catch (SQLException e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}
}