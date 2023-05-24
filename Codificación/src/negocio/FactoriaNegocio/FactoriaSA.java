
package negocio.FactoriaNegocio;

import negocio.Clientes.SACliente;
import negocio.Proveedores.SAProveedor;
import negocio.Trabajadores.SATrabajador;
import negocio.Productos.SAProducto;
import negocio.Secciones.SAPSeccion;
import negocio.Compras.SACompras;
import negocio.Marcas.SAMarca;

public abstract class FactoriaSA {

	private static FactoriaSAImp instancia;

	public abstract SACliente generarSAClientes();

	public abstract SAProveedor generarSAProveedores();

	public abstract SATrabajador generarSATrabajadores();

	public abstract SAProducto generarSAProductos();

	public abstract SAPSeccion generarSASecciones();

	public abstract SACompras generarSACompras();

	public abstract SAMarca generarSAMarcas();

	public static FactoriaSAImp obtenerInstancia() {
		if (instancia == null)
			instancia = new FactoriaSAImp();

		return instancia;
	}
}