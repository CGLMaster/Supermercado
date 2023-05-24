
package integracion.Clientes;

import negocio.Clientes.TCliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


public class DAOClienteimp implements DAOCliente {
	Connection c;
	
	public DAOClienteimp(){
		try {
			c = DriverManager.getConnection("jdbc:mysql://34.105.156.61:3306/is2_bd", "ibmuser1", "JavaJavita69");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int crearCliente(TCliente cliente){
		int r;
		
		try{
			Statement s = c.createStatement();
			
			r = s.executeUpdate("INSERT INTO CLIENTES(nombre, dni, mail) values('" +cliente.getNombre()+"', '"+cliente.getDNI()+"', '"+cliente.getMail()+"');", Statement.RETURN_GENERATED_KEYS);
			
			ResultSet idInserted = s.getGeneratedKeys();
	        if (idInserted.next()){
	            r=idInserted.getInt(1);
	        }
			
		}catch (SQLException e) {
            r = -1;
            e.printStackTrace();
        }
		
		return r;
	}


	public int eliminarCliente(int id_cliente){
		int r;
		
		try{
			Statement s= c.createStatement();
			
			
			r=s.executeUpdate("DELETE FROM CLIENTES WHERE id_socio = " + id_cliente + ";");
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		return r;
	}

	public TCliente buscarCliente(int id) {
		TCliente cliente = null;
		
		try{
			Statement s= c.createStatement();
			
			ResultSet r = s.executeQuery("SELECT * FROM CLIENTES WHERE id_socio =" + id+";");
			
			r.next();
			cliente = new TCliente(r.getString("dni"),r.getString("mail"),r.getString("Nombre"));
			cliente.setId(r.getInt("id_socio"));
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return cliente;
	}

	public Set<TCliente> buscarTodos() {
		HashSet<TCliente> clientes = null;
		
		
		try {
			Statement s = c.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM CLIENTES");
			clientes = new HashSet<TCliente>();
			
			while(result.next()){
				TCliente cl = new TCliente(result.getString("dni"),result.getString("mail"),result.getString("nombre"));
				cl.setId(result.getInt("id_socio"));
				clientes.add(cl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return clientes;
	}

	public int modificarCliente(TCliente cliente) {
		int r;
		
		try{
			Statement s = c.createStatement();
			
			r = s.executeUpdate("UPDATE CLIENTES SET nombre = '" + cliente.getNombre() + "', dni = '" + cliente.getDNI() + "', mail = '" + cliente.getMail() + "' WHERE id_socio = " + cliente.getId() + ";");		
		}catch (SQLException e) {
            r = -1;
            e.printStackTrace();
        }
		
		return r;
	}
}