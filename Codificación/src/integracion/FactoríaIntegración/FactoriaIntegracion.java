package integracion.FactoríaIntegración;

import integracion.Clientes.DAOCliente;
import integracion.Compras.DAOCompra;
import integracion.Marcas.DAOMarcas;
import integracion.Productos.DAOProductos;
import integracion.Proveedores.DAOProveedores;
import integracion.Seccion.DAOSecciones;
import integracion.Trabajadores.DAOTrabajadores;

public abstract class FactoriaIntegracion {

	private static FactoriaIntegracion instancia;

	public static FactoriaIntegracion obtenerInstancia() {
		if (instancia == null)
			instancia = new FactoriaIntegracionImp();

		return instancia;
	}

	public abstract DAOCliente generaDAOClientes();

	public abstract DAOCompra generarDAOCompras();

	public abstract DAOMarcas generarDAOMarcas();

	public abstract DAOProductos generarDAOProductos();

	public abstract DAOProveedores generarDAOProveedores();

	public abstract DAOSecciones generarDAOSecciones();

	public abstract DAOTrabajadores generarDAOTrabajadores();
}