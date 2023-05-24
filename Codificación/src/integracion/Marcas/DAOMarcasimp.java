package integracion.Marcas;

import negocio.Marcas.TMarca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class DAOMarcasimp implements DAOMarcas {
	Connection c;

	public DAOMarcasimp() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://34.105.156.61:3306/is2_bd", "ibmuser1", "JavaJavita69");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int crearMarca(TMarca marca) {
		int r;

		try {
			if (marca.getNombre() == "" || marca.getNombre() == null) throw new IllegalArgumentException();
			Statement s = c.createStatement();
			
			if( marca.getWeb() != null){
				r = s.executeUpdate(
						"INSERT INTO marca (nombre, web) values('" + marca.getNombre() + "', '" + marca.getWeb() + "');", Statement.RETURN_GENERATED_KEYS);
			}else{
				r = s.executeUpdate(
						"INSERT INTO marca (nombre) values('" + marca.getNombre() + "');",Statement.RETURN_GENERATED_KEYS);
			}
			
			ResultSet id = s.getGeneratedKeys();
			id.next();
			r = id.getInt(1);

		} catch (Exception e) {
			r = -1;
			e.printStackTrace();
		}
		
		return r;

	}

	public TMarca buscarMarca(int ID_Marca) {
		TMarca marca = null;

		try {
			Statement s = c.createStatement();

			ResultSet r = s.executeQuery("SELECT * FROM marca WHERE ID_Marca = " + ID_Marca + ";");

			while (r.next()) {
				marca = new TMarca(r.getString("Nombre"));
				marca.setId(r.getInt("ID_Marca"));
				marca.setWeb(r.getString("web"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return marca;

	}

	public Set<TMarca> buscarTodas() {
		HashSet<TMarca> marcas = null;

		try {
			Statement s = c.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM marca");
			marcas = new HashSet<TMarca>();

			while (result.next()) {
				TMarca m = new TMarca(result.getString("Nombre"));
				m.setId(result.getInt("ID_Marca"));
				m.setWeb("web");
				marcas.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return marcas;
	}

	public int modificarMarca(TMarca marca) {
		int r;

		try {
			Statement s = c.createStatement();

			r = s.executeUpdate("UPDATE marca SET nombre = '" + marca.getNombre() + "', web = '" + marca.getWeb()
					+ "' WHERE ID_Marca = " + marca.getId() + ";");
		} catch (SQLException e) {
			r = -1;
			e.printStackTrace();
		}
		System.out.println(r);
		return r;
	}

	public int eliminarMarca(int ID_Marca) {
		int r;

		try {
			Statement s = c.createStatement();

			r = s.executeUpdate("DELETE FROM marca WHERE ID_Marca = " + ID_Marca + ";");

		} catch (SQLException e) {
			return -1;
		}

		return r;
	}
}