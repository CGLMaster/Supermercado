package integracion.FactoríaIntegración;

import integracion.Clientes.DAOCliente;
import integracion.Clientes.DAOClienteimp;
import integracion.Compras.DAOCompra;
import integracion.Compras.DAOCompraimp;
import integracion.Marcas.DAOMarcas;
import integracion.Marcas.DAOMarcasimp;
import integracion.Productos.DAOProductos;
import integracion.Productos.DAOProductosImp;
import integracion.Proveedores.DAOProveedores;
import integracion.Proveedores.DAOProveedorimp;
import integracion.Seccion.DAOSecciones;
import integracion.Seccion.DAOSeccionimp;
import integracion.Trabajadores.DAOTrabajadores;
import integracion.Trabajadores.DAOTrabajadorimp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {

	public DAOCliente generaDAOClientes() {
		return new DAOClienteimp();
	}

	public DAOCompra generarDAOCompras() {
		return new DAOCompraimp();
	}

	public DAOMarcas generarDAOMarcas() {
		return new DAOMarcasimp();
	}

	public DAOProductos generarDAOProductos() {
		return new DAOProductosImp();
	}

	public DAOProveedores generarDAOProveedores() {
		return new DAOProveedorimp();
	}

	public DAOSecciones generarDAOSecciones() {
		return new DAOSeccionimp();
	}

	public DAOTrabajadores generarDAOTrabajadores() {
		return new DAOTrabajadorimp();
	}
}