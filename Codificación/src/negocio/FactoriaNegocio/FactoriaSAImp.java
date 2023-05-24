package negocio.FactoriaNegocio;

import negocio.Clientes.SACliente;
import negocio.Clientes.SAClienteImp;
import negocio.Proveedores.SAProveedor;
import negocio.Proveedores.SAProveedorimp;
import negocio.Trabajadores.SATrabajador;
import negocio.Trabajadores.SATrabajadorimp;
import negocio.Productos.SAProducto;
import negocio.Productos.SAProductoImp;
import negocio.Secciones.SAPSeccion;
import negocio.Secciones.SASeccionImp;
import negocio.Compras.SACompras;
import negocio.Compras.SAComprasImp;
import negocio.Marcas.SAMarca;
import negocio.Marcas.SAMarcaImp;

public class FactoriaSAImp extends FactoriaSA {

	public SACliente generarSAClientes() {
		return new SAClienteImp();
	}

	public SAProveedor generarSAProveedores() {
		return new SAProveedorimp();
	}

	public SATrabajador generarSATrabajadores() {
		return new SATrabajadorimp();
	}

	public SAProducto generarSAProductos() {
		return new SAProductoImp();
	}

	public SAPSeccion generarSASecciones() {
		return new SASeccionImp();
	}

	public SACompras generarSACompras() {
		return new SAComprasImp();
	}

	public SAMarca generarSAMarcas() {
		return new SAMarcaImp();
	}
}