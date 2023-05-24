package presentacion.Controlador;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;

import negocio.Clientes.TCliente;
import negocio.Compras.TProductoCantidad;
import negocio.FactoriaNegocio.FactoriaSA;
import negocio.Marcas.TMarca;
import negocio.Productos.TProducto;
import negocio.Proveedores.*;
import negocio.Secciones.TSeccion;
import negocio.Trabajadores.TTrabajador;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.FactoriaPresentacion.FactoriaVistas;
import presentacion.Marcas.VistasCasos_de_Uso.VBuscarMarca;
import presentacion.Marcas.VistasCasos_de_Uso.VModificarMarca;
import presentacion.Secciones.VistasCasos_de_Uso.VBuscarSeccion;

public class ControladorImp extends Controlador {

	private JFrame jframe;
	private IGUI currentIGUI;

	public ControladorImp() {
		jframe = new JFrame();
	}

	
	@SuppressWarnings("unchecked")
	public void accion(int evento, Object datos) {
		int res;
		switch (evento) {

		case Evento.CREAR_VPRINCIPAL:
			
			TTrabajador loggedTrabajador = (TTrabajador)datos;

			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.CREAR_VPRINCIPAL, jframe, loggedTrabajador);
	
			break;

		// CLIENTES
		case Evento.CREAR_VCLIENTE:
			Set<TCliente> set = FactoriaSA.obtenerInstancia().generarSAClientes().buscarTodos();
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.CREAR_VCLIENTE, jframe, set);

			break;
		case Evento.ALTA_CLIENTE:

			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.ALTA_CLIENTE, jframe, null);

			break;
		case Evento.GUARDAR_CLIENTE:

			res = FactoriaSA.obtenerInstancia().generarSAClientes().crearCliente((TCliente) datos);
			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_ALTA_CLIENTE_OK, res);
			}else {
				currentIGUI.actualizar(Evento.RES_ALTA_CLIENTE_KO, res);
			}

			break;
		case Evento.BAJA_CLIENTE:

			res = FactoriaSA.obtenerInstancia().generarSAClientes().eliminarCliente((int) datos);
			
			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_BAJA_CLIENTE_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_BAJA_CLIENTE_KO, res);
			}

			break;
		case Evento.BUSCAR_CLIENTE:

			TCliente cliente = FactoriaSA.obtenerInstancia().generarSAClientes().buscarCliente((int) datos);
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.BUSCAR_CLIENTE, jframe, cliente);

			break;

		case Evento.MODIFICAR_CLIENTE:

			TCliente cl = FactoriaSA.obtenerInstancia().generarSAClientes().buscarCliente((int) datos);
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.MODIFICAR_CLIENTE, jframe, cl);

			break;

		case Evento.UPDATE_CLIENTE:
			res = FactoriaSA.obtenerInstancia().generarSAClientes().modificarCliente((TCliente) datos);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_CLIENTE_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_CLIENTE_KO, res);
			}

			break;

		// PROVEEDORES
		case Evento.CREAR_VPROVEEDOR:

			Set<TProveedor> proveedores = FactoriaSA.obtenerInstancia().generarSAProveedores().buscarTodosProveedores();
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.CREAR_VPROVEEDOR, jframe, proveedores);

			break;

		case Evento.ALTA_PROVEEDOR:

			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.ALTA_PROVEEDOR, jframe, null);

			break;

		case Evento.GUARDAR_PROVEEDOR:

			res = FactoriaSA.obtenerInstancia().generarSAProveedores().crearProveedor((TProveedor) datos);
			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_ALTA_PROVEEDOR_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_ALTA_PROVEEDOR_KO, res);
			}
			break;

		case Evento.MODIFICAR_PROVEEDOR:

			TProveedor proveedor = FactoriaSA.obtenerInstancia().generarSAProveedores().buscarProveedor((int) datos);
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.MODIFICAR_PROVEEDOR, jframe, proveedor);

			break;

		case Evento.UPDATE_PROVEEDOR:

			res = FactoriaSA.obtenerInstancia().generarSAProveedores().modificarProveedor((TProveedor) datos);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_PROVEEDOR_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_PROVEEDOR_KO, res);
			}

			break;

		case Evento.BAJA_PROVEEDOR:

			res = FactoriaSA.obtenerInstancia().generarSAProveedores().eliminarProveedor((int) datos);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_BAJA_PROVEEDOR_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_BAJA_PROVEEDOR_KO, res);
			}

			break;

		case Evento.BUSCAR_PROVEEDOR:

			TProveedor proveedor1 = FactoriaSA.obtenerInstancia().generarSAProveedores().buscarProveedor((int) datos);
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.BUSCAR_PROVEEDOR, jframe, proveedor1);

			break;

		// ----- MARCAS -----

		case Evento.CREAR_VMARCA:
			Set<TMarca> setMarcas = FactoriaSA.obtenerInstancia().generarSAMarcas().buscarTodosMarca();

			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.CREAR_VMARCA, jframe, setMarcas);
			break;
		case Evento.ALTA_MARCA:
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.ALTA_MARCA, jframe, null);
			break;
		case Evento.GUARDAR_MARCA:
			res = FactoriaSA.obtenerInstancia().generarSAMarcas().crearMarca((TMarca) datos);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_ALTA_MARCA_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_ALTA_MARCA_KO, res);
			}

			break;
		case Evento.BAJA_MARCA:
			res = FactoriaSA.obtenerInstancia().generarSAMarcas().eliminarMarca((int) datos);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_BAJA_MARCA_OK, null);
			} else {
				currentIGUI.actualizar(Evento.RES_BAJA_MARCA_KO, null);
			}

			break;
		case Evento.BUSCAR_MARCA:
			TMarca marca = FactoriaSA.obtenerInstancia().generarSAMarcas().buscarMarca((int) datos);
			currentIGUI = new VBuscarMarca(jframe, marca);
			break;
		case Evento.MODIFICAR_MARCA:
			TMarca marca1 = FactoriaSA.obtenerInstancia().generarSAMarcas().buscarMarca((int) datos);
			currentIGUI = new VModificarMarca(marca1, jframe);
			break;
		case Evento.UPDATE_MARCA:
			res = FactoriaSA.obtenerInstancia().generarSAMarcas().modificarMarca((TMarca) datos);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_MARCA_OK, null);
			} else {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_MARCA_KO, null);
			}
			break;

		// ----- TRABAJADORES -----

		case Evento.CREAR_VTRABAJADOR:
			Set<TTrabajador> setTr = FactoriaSA.obtenerInstancia().generarSATrabajadores().buscarTodos();
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.CREAR_VTRABAJADOR, jframe, setTr);

			break;
		case Evento.ALTA_TRABAJADOR:

			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.ALTA_TRABAJADOR, jframe, null);

			break;

		case Evento.GUARDAR_TRABAJADOR:

			res = FactoriaSA.obtenerInstancia().generarSATrabajadores().crearTrabajador((TTrabajador) datos);
			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_ALTA_TRABAJADOR_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_ALTA_TRABAJADOR_KO, res);
			}

			break;

		case Evento.BAJA_TRABAJADOR:
			
			res = FactoriaSA.obtenerInstancia().generarSATrabajadores().eliminarTrabajador((int) datos);
			
			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_BAJA_TRABAJADOR_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_BAJA_TRABAJADOR_KO, res);
			}

			break;

		case Evento.MODIFICAR_TRABAJADOR:

			TTrabajador trabajador = FactoriaSA.obtenerInstancia().generarSATrabajadores()
					.buscarTrabajador((int) datos);
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.MODIFICAR_TRABAJADOR, jframe,
					trabajador);

			break;

		case Evento.UPDATE_TRABAJADOR:

			res = FactoriaSA.obtenerInstancia().generarSATrabajadores().modificarTrabajador((TTrabajador) datos);
			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_TRABAJADOR_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_TRABAJADOR_KO, res);
			}

			break;

		case Evento.BUSCAR_TRABAJADOR:

			TTrabajador tr = FactoriaSA.obtenerInstancia().generarSATrabajadores().buscarTrabajador((int) datos);
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.BUSCAR_TRABAJADOR, jframe, tr);

			break;
			
		case Evento.IDENTIFICAR_TRABAJADOR:
			
			TTrabajador loggedTr = FactoriaSA.obtenerInstancia().generarSATrabajadores().identificarTrabajador((int) datos);
			
			if(loggedTr != null){
				currentIGUI.actualizar(Evento.RES_IDENTIFICAR_TRABAJADOR_OK, loggedTr);
			}else{
				currentIGUI.actualizar(Evento.RES_IDENTIFICAR_TRABAJADOR_KO, loggedTr);
			}
			
			this.accion(Evento.CREAR_VPRINCIPAL, loggedTr);

			break;

		// ----- SECCIONES -----

		case Evento.CREAR_VSECCION:
			Set<TSeccion> setSecciones = FactoriaSA.obtenerInstancia().generarSASecciones().buscarTodas();
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.CREAR_VSECCION, jframe, setSecciones);
			
			break;
		case Evento.ALTA_SECCION:
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.ALTA_SECCION, jframe, null);
			break;
		case Evento.GUARDAR_SECCION:
			res = FactoriaSA.obtenerInstancia().generarSASecciones().crearSeccion((TSeccion) datos);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_ALTA_SECCION_OK, res);
			}else {
				currentIGUI.actualizar(Evento.RES_ALTA_SECCION_KO, res);
			}

			break;
		case Evento.BAJA_SECCION:
			res = FactoriaSA.obtenerInstancia().generarSASecciones().eliminarSeccion((int) datos);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_BAJA_SECCION_OK, null);
			}
			else {
				currentIGUI.actualizar(Evento.RES_BAJA_SECCION_KO, null);
			}

			break;
		case Evento.BUSCAR_SECCION:
			TSeccion seccion = FactoriaSA.obtenerInstancia().generarSASecciones().buscarSeccion((int) datos);
			currentIGUI = new VBuscarSeccion(jframe, seccion);
			
			break;
		case Evento.MODIFICAR_SECCION:
			TSeccion seccionParaEditar = FactoriaSA.obtenerInstancia().generarSASecciones().buscarSeccion((int) datos);
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.MODIFICAR_SECCION, jframe,
					seccionParaEditar);
			break;
		case Evento.UPDATE_SECCION:
			res = FactoriaSA.obtenerInstancia().generarSASecciones().modificarSeccion((TSeccion) datos);
			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_SECCION_OK, res);
			}
			else {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_SECCION_KO, res);
			}
			break;

		// -----PRODUCTOS-----

		case Evento.CREAR_VPRODUCTO:
			Set<TProducto> setProductos = FactoriaSA.obtenerInstancia().generarSAProductos().buscarTodos();
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.CREAR_VPRODUCTO, jframe, setProductos);

			break;

		case Evento.ALTA_PRODUCTO:
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.ALTA_PRODUCTO, jframe, null);
			break;
		case Evento.GUARDAR_PRODUCTO:

			res = FactoriaSA.obtenerInstancia().generarSAProductos().crearProducto((TProducto) datos);
			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_ALTA_PRODUCTO_OK, res);
			}else {
				currentIGUI.actualizar(Evento.RES_ALTA_PRODUCTO_KO, res);
			}

			break;

		case Evento.BAJA_PRODUCTO:
			res = FactoriaSA.obtenerInstancia().generarSAProductos().eliminarProducto((int) datos);
			if (res == -1) {
				currentIGUI.actualizar(Evento.RES_BAJA_PRODUCTO_KO, null);
			} else {
				currentIGUI.actualizar(Evento.RES_BAJA_PRODUCTO_OK, null);
			}

			break;

		case Evento.BUSCAR_PRODUCTO:
			TProducto producto = FactoriaSA.obtenerInstancia().generarSAProductos().buscarProducto((int) datos);
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.BUSCAR_PRODUCTO, jframe, producto);
			break;

		case Evento.MODIFICAR_PRODUCTO:
			TProducto prod = FactoriaSA.obtenerInstancia().generarSAProductos().buscarProducto((int) datos);
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.MODIFICAR_PRODUCTO, jframe, prod);
			break;

		case Evento.UPDATE_PRODUCTO:
			res = FactoriaSA.obtenerInstancia().generarSAProductos().modificarProducto((TProducto) datos);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_MODIFICAR_PRODUCTO_OK, res);
			}else{
				currentIGUI.actualizar(Evento.RES_MODIFICAR_PRODUCTO_KO, res);
			}

			break;

		case Evento.VINCULAR_PRODUCTO_PROVEEDOR:
			Number[] ids = (Number[]) datos;
			res = FactoriaSA.obtenerInstancia().generarSAProductos().vincularProveedor((int)ids[0], (int)ids[1],(double)ids[2]);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_VINCULAR_PRODUCTO_PROVEEDOR_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_VINCULAR_PRODUCTO_PROVEEDOR_KO, null);
			}

			break;

		case Evento.DESVINCULAR_PRODUCTO_PROVEEDOR:
			int[] ids1 = (int[]) datos;
			res = FactoriaSA.obtenerInstancia().generarSAProductos().desvincularProveedor(ids1[0], ids1[1]);

			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_DESVINCULAR_PRODUCTO_PROVEEDOR_OK, res);
			} else {
				currentIGUI.actualizar(Evento.RES_DESVINCULAR_PRODUCTO_PROVEEDOR_KO, null);
			}

			break;

		case Evento.BUSCAR_PRODUCTOS_PROVEEDOR:

			break;

		case Evento.BUSCAR_PRODUCTOS_SECCION:

			break;

		case Evento.BUSCAR_PRODUCTOS_MARCA:
			Object[] argsF = (Object[]) datos;
			Set<TProducto> setProductosFilter;
			setProductosFilter = FactoriaSA.obtenerInstancia().generarSAProductos().mostrarPorFiltro((Set<TProducto>) argsF[1], (String[]) argsF[0]);
			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.CREAR_VPRODUCTO, jframe, setProductosFilter);
			break;

		// - ----- COMPRA -----	

		case Evento.CREAR_VCOMPRA:
			HashMap<String, Object> args = (HashMap<String, Object>) datos;

			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.CREAR_VCOMPRA, jframe, args);
			break;

		case Evento.ANIADIR_PRODUCTO_COMPRA:
			args = (HashMap<String, Object>) datos;

			int id = (int)args.get("id");
			int cantidad = (int)args.get("cantidad");
			TreeMap<Integer, TProductoCantidad> productosEnCarrito = (TreeMap<Integer, TProductoCantidad>)args.get("productosEnCarrito");

			TProductoCantidad productoCantidad = FactoriaSA.obtenerInstancia().generarSACompras().aniadirProductos(id,cantidad);

			if (productoCantidad == null) {
				currentIGUI.actualizar(Evento.RES_ANIADIR_PRODUCTO_COMPRA_KO, "No se ha podido añadir producto");
				return;
			}
			if(productoCantidad.getCantidad() == -1){
				currentIGUI.actualizar(Evento.RES_ANIADIR_PRODUCTO_COMPRA_KO, "No hay suficiente stock de este producto");
				return;
			}

			// Si ya existe se elimina y se vuelve a añadir con la nueva cantidad
			if (productosEnCarrito.containsKey(id)) {
				productosEnCarrito.remove(id);
			}
			
			productosEnCarrito.put(id, productoCantidad);

			this.accion(Evento.CREAR_VCOMPRA, args);

			break;
			
		case Evento.CREAR_V_PAGAR_COMPRA:
			args = (HashMap<String, Object>) datos;
			
			productosEnCarrito = (TreeMap<Integer, TProductoCantidad>)args.get("productosEnCarrito");
			
			if(productosEnCarrito.isEmpty()) return;
			
			{
			double total = 0;
			for (Map.Entry<Integer, TProductoCantidad> entry : productosEnCarrito.entrySet()) {
				total += entry.getValue().getPrecioTotal();
			}
			
			args.put("total", total);

			currentIGUI = FactoriaVistas.obtenerInstancia().generaVistas(Evento.CREAR_V_PAGAR_COMPRA, jframe, args);
			}
		break;

		case Evento.PAGAR_COMPRA:
			args = (HashMap<String, Object>) datos;
			
			int idCliente = (int)args.get("idCliente");
			double cantidadPagada = (double)args.get("cantidadPagada");
			productosEnCarrito = (TreeMap<Integer, TProductoCantidad>)args.get("productosEnCarrito");
			
			
			cliente = FactoriaSA.obtenerInstancia().generarSAClientes().buscarCliente(idCliente);
			if(cliente ==  null){
				currentIGUI.actualizar(Evento.RES_BUSCAR_CLIENTE_KO, null);
				return;
			}
			
			
			int ID_Trabajador;
			if(args.get("loggedTrabajador") == null)
				ID_Trabajador = -1;
			else{
				TTrabajador loggedTrabajador1 = (TTrabajador)args.get("loggedTrabajador");
				ID_Trabajador = loggedTrabajador1.getID_Trabajador();
			}
				

			res = FactoriaSA.obtenerInstancia().generarSACompras().pagarCompra(productosEnCarrito, idCliente, ID_Trabajador, cantidadPagada);
			if (res >= 0) {
				currentIGUI.actualizar(Evento.RES_PAGAR_COMPRA_OK, null);
				
			} else {
				currentIGUI.actualizar(Evento.RES_PAGAR_COMPRA_KO, null);
				return;
			}

			productosEnCarrito = new TreeMap<Integer, TProductoCantidad>();// reiniciar carrito
			
			this.accion(Evento.CREAR_VPRINCIPAL, null);
		break;
		
		case Evento.LOGOUT:
			loggedTr=null;
		}

	}
}