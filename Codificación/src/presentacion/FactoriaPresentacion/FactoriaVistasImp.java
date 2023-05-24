package presentacion.FactoriaPresentacion;

import java.util.HashMap;
import java.util.Set;

import javax.swing.JFrame;

import negocio.Clientes.TCliente;
import negocio.Marcas.TMarca;
import negocio.Productos.TProducto;
import negocio.Proveedores.TProveedor;
import negocio.Secciones.TSeccion;
import negocio.Trabajadores.TTrabajador;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Clientes.VCliente;
import presentacion.Clientes.VistasCasos_de_Uso.VAltaCliente;
import presentacion.Clientes.VistasCasos_de_Uso.VBuscarCliente;
import presentacion.Clientes.VistasCasos_de_Uso.VModificarCliente;
import presentacion.Compras.VCompra;
import presentacion.Compras.VistasCasos_de_Uso.VmostrarAyuda;
import presentacion.Compras.VistasCasos_de_Uso.VpagarCompra;
import presentacion.Marcas.VMarca;
import presentacion.Marcas.VistasCasos_de_Uso.VBuscarMarca;
import presentacion.Marcas.VistasCasos_de_Uso.VCrearMarca;
import presentacion.Marcas.VistasCasos_de_Uso.VEliminarMarca;
import presentacion.Marcas.VistasCasos_de_Uso.VModificarMarca;
import presentacion.Productos.VProducto;
import presentacion.Productos.VistasCasos_de_UsoPr.VAltaProducto;
import presentacion.Productos.VistasCasos_de_UsoPr.VBuscarProducto;
import presentacion.Productos.VistasCasos_de_UsoPr.VModificarPr;
import presentacion.Proveedores.VProveedor;
import presentacion.Proveedores.VistasCasos_de_Uso_Prov.VAltaProveedor;
import presentacion.Proveedores.VistasCasos_de_Uso_Prov.VBuscarProvedor;
import presentacion.Proveedores.VistasCasos_de_Uso_Prov.VModificarProveedor;
import presentacion.Secciones.VSeccion;
import presentacion.Secciones.VistasCasos_de_Uso.*;

import presentacion.Trabajadores.VistasCasos_de_uso.VAltaTrabajador;
import presentacion.Trabajadores.VistasCasos_de_uso.VBajaTrabajador;
import presentacion.Trabajadores.VistasCasos_de_uso.VBuscarTrabajador;
import presentacion.Trabajadores.VistasCasos_de_uso.VIdentificarTrabajador;
import presentacion.Trabajadores.VistasCasos_de_uso.VModificarTrabajador;
import presentacion.launcher.VPrincipal;
import presentacion.Trabajadores.VTrabajador;

public class FactoriaVistasImp extends FactoriaVistas {
	
	@SuppressWarnings("unchecked")
	public IGUI generaVistas(int id_vista, JFrame frame, Object datos) {
		IGUI view = null;
		switch (id_vista) {
		case Evento.CREAR_VPRINCIPAL:
			view=new VPrincipal(frame,(TTrabajador) datos);
			break;
		// ----- CLIENTES -----
		case Evento.CREAR_VCLIENTE:
			view = new VCliente(frame, (Set<TCliente>) datos);
			break;
		case Evento.ALTA_CLIENTE:
			view = new VAltaCliente(frame);
			break;
		case Evento.MODIFICAR_CLIENTE:
			view = new VModificarCliente(frame, (TCliente) datos);
			break;
		case Evento.BUSCAR_CLIENTE:
			view = new VBuscarCliente(frame, (TCliente) datos);
			break;
		// ----- PROVEEDOR -----
		case Evento.CREAR_VPROVEEDOR:
			view = new VProveedor(frame, (Set<TProveedor>) datos);
			break;
		case Evento.ALTA_PROVEEDOR:
			view = new VAltaProveedor(frame);
			break;
		case Evento.MODIFICAR_PROVEEDOR:
			view = new VModificarProveedor(frame, (TProveedor) datos);
			break;
		case Evento.BUSCAR_PROVEEDOR:
			view = new VBuscarProvedor(frame, (TProveedor) datos);
			break;
			
		// ----- MARCAS -----
		case Evento.CREAR_VMARCA:
			view = new VMarca(frame, (Set<TMarca>) datos);
			break;
		case Evento.BUSCAR_MARCA:
			view = new VBuscarMarca(frame, (TMarca) datos);
			break;
		case Evento.MODIFICAR_MARCA:
			view = new VModificarMarca((TMarca) datos, frame);
			break;
		case Evento.BAJA_MARCA:
			view = new VEliminarMarca(frame, (TMarca) datos);
			break;
		case Evento.ALTA_MARCA:
			view = new VCrearMarca(frame);
			break;
			
		// ----- TRABAJADORES -----
		case Evento.CREAR_VTRABAJADOR:
			view = new VTrabajador(frame, (Set<TTrabajador>) datos);
			break;
		case Evento.ALTA_TRABAJADOR:
			view = new VAltaTrabajador(frame);
			break;
		case Evento.BAJA_TRABAJADOR:
			view = new VBajaTrabajador((TTrabajador) datos, frame);
			break;
		case Evento.BUSCAR_TRABAJADOR:
			view = new VBuscarTrabajador(frame, (TTrabajador) datos);
			break;
		case Evento.MODIFICAR_TRABAJADOR:
			view = new VModificarTrabajador(frame, (TTrabajador) datos);
			break;
		case Evento.IDENTIFICAR_TRABAJADOR:
			view = new VIdentificarTrabajador(frame);
			break;

		// ----- SECCIONES -----
		case Evento.CREAR_VSECCION:
			view = new VSeccion(frame, (Set<TSeccion>) datos);
			break;
		case Evento.ALTA_SECCION:
			view = new VCrearSeccion(frame);
			break;
		case Evento.BUSCAR_SECCION:
			view = new VBuscarSeccion(frame, (TSeccion) datos);
			break;
		case Evento.MODIFICAR_SECCION:
			view = new VEditarSeccion((TSeccion) datos, frame);
			break;
			
		// ----- PRODUCTOS -----
		
		case Evento.CREAR_VPRODUCTO:
			 view = new VProducto(frame,(Set<TProducto>) datos);
			break;
		case Evento.ALTA_PRODUCTO:
			 view = new VAltaProducto(frame);
			break;
		case Evento.BUSCAR_PRODUCTO:
			view = new VBuscarProducto(frame, (TProducto) datos);
			break;
		case Evento.MODIFICAR_PRODUCTO:
			view = new VModificarPr(frame,(TProducto) datos);
			break;
			//COMPRA
		case Evento.CREAR_VCOMPRA:
				view = new VCompra(frame, (HashMap<String, Object>) datos);
		break;
		case Evento.MOSTRAR_AYUDA:
				view = new VmostrarAyuda();
		break;
		case Evento.CREAR_V_PAGAR_COMPRA:
				view = new VpagarCompra(frame, (HashMap<String, Object>) datos);
		break;

		}

		return view;
	}
}