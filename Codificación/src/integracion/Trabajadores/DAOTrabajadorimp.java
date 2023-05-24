
package integracion.Trabajadores;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import negocio.Trabajadores.TTrabajador;

import java.util.HashSet;
import java.util.Set;
import java.sql.DriverManager;

@SuppressWarnings("serial")
public class DAOTrabajadorimp extends SQLException implements DAOTrabajadores {
	Connection c;

	public DAOTrabajadorimp() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://34.105.156.61:3306/is2_bd", "ibmuser1", "JavaJavita69");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int crearTrabajador(TTrabajador trabajador) {
		int r;

		try {
			Statement s = c.createStatement();
			r = s.executeUpdate("INSERT INTO TRABAJADORES(nombre, dni, mail) values('" + trabajador.getNombre() + "', '"
					+ trabajador.getDNI() + "', '" + trabajador.getMail() + "')",Statement.RETURN_GENERATED_KEYS);
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

	public TTrabajador buscarTrabajador(int ID_Trabajador) {
		TTrabajador trabajador = null;
		try {
			Statement s = c.createStatement();

			ResultSet r = s.executeQuery("SELECT * FROM TRABAJADORES WHERE id_trabajador =" + ID_Trabajador + ";");

			r.next();
			trabajador = new TTrabajador(r.getString("nombre"), r.getString("mail"), r.getString("dni"));
			trabajador.setId(r.getInt("id_trabajador"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trabajador;
	}

	public Set<TTrabajador> buscarTodos() {
		HashSet<TTrabajador> trabajadores = null;

		try {
			Statement s = c.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM TRABAJADORES");
			trabajadores = new HashSet<TTrabajador>();

			while (result.next()) {
				TTrabajador t = new TTrabajador(result.getString("nombre"), result.getString("mail"),
						result.getString("dni"));
				t.setId(result.getInt("id_trabajador"));
				trabajadores.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trabajadores;

	}

	public int eliminarTrabajador(int ID_Trabajador) {
		int r;

		try {
			Statement s = c.createStatement();
			r = s.executeUpdate("DELETE FROM TRABAJADORES WHERE ID_trabajador = " + ID_Trabajador + ";");
		} catch (SQLException e) {
			return -1;
		}
		return r;
	}

	public int modificarTrabajador(TTrabajador Trabajador) {
		int r;

		try {
			Statement s = c.createStatement();
			r = s.executeUpdate("UPDATE TRABAJADORES SET nombre = '" + Trabajador.getNombre() + "', dni = '"
					+ Trabajador.getDNI() + "', mail ='" + Trabajador.getMail() + "' WHERE id_trabajador = "
					+ Trabajador.getID_Trabajador() + ";");
		} catch (SQLException e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}

	public TTrabajador identificarTrabajador(int ID_Trabajador) {
		return buscarTrabajador(ID_Trabajador);
	}
}