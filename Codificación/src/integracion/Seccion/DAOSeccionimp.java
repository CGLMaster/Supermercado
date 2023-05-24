package integracion.Seccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import negocio.Secciones.TSeccion;


public class DAOSeccionimp implements DAOSecciones {
	Connection c;

	public DAOSeccionimp() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://34.105.156.61:3306/is2_bd", "ibmuser1", "JavaJavita69");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public int crearSeccion(TSeccion seccion) {
		int r;

		try {
			Statement s = c.createStatement();
			
			r = s.executeUpdate("INSERT INTO Seccion (Zona, Pasillo) values('" + seccion.getZona() + "', " + seccion.getPasillo() + ")",Statement.RETURN_GENERATED_KEYS);
			ResultSet idInserted = s.getGeneratedKeys();
	        if (idInserted.next()){
	            r=idInserted.getInt(1);
	        }
			
		} catch (SQLException e) {
			r=-1;
			e.printStackTrace();
		}
		
		return r;
	}

	public int eliminarSeccion(int id) {
		int r=-1;
		try {
			Statement s = c.createStatement();
			
			r = s.executeUpdate("DELETE FROM Seccion WHERE ID_Seccion = " + id + ";");
			
			
		} catch (SQLException e) {
			r = -1;
			e.printStackTrace();
		}
		return r;
	}

	
	public Set<TSeccion> buscarTodos() {
		HashSet<TSeccion> secciones = null;

		try {
			Statement s = c.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM Seccion");
			secciones = new HashSet<TSeccion>();

			while (result.next()) {
				TSeccion seccion = new TSeccion(result.getInt("Pasillo"), result.getString("Zona"));
				seccion.setId(result.getInt("ID_Seccion"));
				secciones.add(seccion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return secciones;
	}


	public TSeccion buscarSeccion(int ID_Seccion) {
		TSeccion seccion = null;
		
		try {
			Statement s = c.createStatement();
			
			ResultSet r = s.executeQuery("SELECT * FROM Seccion WHERE ID_Seccion = "+ ID_Seccion +";");


			while (r.next()) {
				seccion = new TSeccion(r.getInt("Pasillo"), r.getString("Zona"));
				seccion.setId(r.getInt("ID_Seccion"));
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return seccion;
	}

	public int editarSeccion(TSeccion seccion) {
		int r;
		try {
			Statement s = c.createStatement();
			r = s.executeUpdate("UPDATE Seccion SET Zona='"+seccion.getZona()+"', Pasillo='" + seccion.getPasillo() + "' WHERE ID_Seccion=" + seccion.getId());
		} catch (SQLException e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}
}