package integracion.Proveedores;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import negocio.Proveedores.TProveedor;

import java.util.HashSet;
import java.util.Set;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOProveedorimp implements DAOProveedores {
	
	Connection c;
	
	public DAOProveedorimp(){
		try {
			c = DriverManager.getConnection("jdbc:mysql://34.105.156.61:3306/is2_bd", "ibmuser1", "JavaJavita69");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int crearProveedor(TProveedor proveedor) {

		int r;
		try {
			Statement s = c.createStatement();
			r = s.executeUpdate("INSERT INTO proveedor(nombre) VALUES('" + proveedor.getNombre() + "');",Statement.RETURN_GENERATED_KEYS);
			ResultSet idInserted = s.getGeneratedKeys();
	        if (idInserted.next()){
	            r=idInserted.getInt(1);
	        }
		} catch (SQLException e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}
	
	public TProveedor buscarProveedor(int ID_Proveedor) {
		// begin-user-code
		TProveedor proveedor = null;
		try {
			Statement s = c.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM proveedor WHERE ID_Proveedor = " + ID_Proveedor + ";");
			
			while (result.next()) {
				proveedor = new TProveedor(result.getString("Nombre"));
				proveedor.setId(result.getInt("ID_Proveedor"));
		    }
			
			
		} catch (SQLException e) {
			proveedor = null;
			e.printStackTrace();
		}
		return proveedor;
		// end-user-code
	}

	public Set<TProveedor> buscarTodos() {
		HashSet<TProveedor> proveedores = null;
		try {
			Statement s = c.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM proveedor");
			
			proveedores = new HashSet<TProveedor>();
			while (result.next()) {
				TProveedor proveedor = new TProveedor(result.getString("Nombre"), result.getInt("ID_Proveedor"));
				proveedores.add(proveedor);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proveedores;
	}
	
	public int eliminarProveedor(int ID_proveedor) {
		int r;
        try {
            Statement s = c.createStatement();
            r = s.executeUpdate("DELETE FROM proveedor WHERE ID_proveedor = " + ID_proveedor + ";");
        } catch (SQLException e) {
            r = -1;
            e.printStackTrace();
        }
        return r;
	}

	public int editarProveedor(TProveedor proveedor) {
		
		int r;
		try {
			Statement s = c.createStatement();
			r = s.executeUpdate("UPDATE proveedor SET nombre='"+proveedor.getNombre()+"' WHERE ID_Proveedor='"+proveedor.getId()+"'");
		} catch (SQLException e) {
			r = -1;
			e.printStackTrace();
		}

		return r;

	}
}