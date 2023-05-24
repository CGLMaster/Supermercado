	
package negocio.Productos;

import java.util.HashSet;
import java.util.Set;

import integracion.FactoríaIntegración.FactoriaIntegracion;

public class SAProductoImp implements SAProducto {

	public int crearProducto(TProducto producto) {
		if (producto.getTipo() == null && !producto.getFecha().checkDate()) {
			return -2;
		}
		if(producto.getNombre().trim().length() == 0){
			return -3;
		}
		if(producto.getStock() < 0 || producto.getPrecio() < 0.01){
			return -4;
		}
		
		return FactoriaIntegracion.obtenerInstancia().generarDAOProductos().crearProducto(producto);
	}

	public int vincularProveedor(int id_producto, int id_proveedor, double precio) {
		int res = FactoriaIntegracion.obtenerInstancia().generarDAOProductos().vincularProveedor(id_producto,
				id_proveedor, precio);
		if (res >= 0) {
			return id_proveedor;
		}
		return res;
	}

	public Set<TProducto> buscarTodos() {
		return FactoriaIntegracion.obtenerInstancia().generarDAOProductos().buscarTodos();
	}

	public TProducto buscarProducto(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOProductos().buscarProducto(id);
	}

	public int modificarProducto(TProducto producto) {
		if (producto.getPrecio() <= 0 || producto.getNombre().trim().length() == 0 || producto.getStock() < 0) {
			return -2;
		}
		if (producto.getTipo() == null && !producto.getFecha().checkDate()) {
			return -3;
		}
		return FactoriaIntegracion.obtenerInstancia().generarDAOProductos().modificarProducto(producto);
	}

	public int eliminarProducto(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOProductos().eliminarProducto(id);
	}

	public int desvincularProveedor(int id_producto, int id_proveedor) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOProductos().desvincularProveedor(id_producto, id_proveedor);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<TProducto> mostrarPorFiltro(Set<TProducto> prs, String[] f) {
		
		Set<TProducto> filterSet;
		filterSet = new HashSet();
		filterSet.addAll(prs);
		
		for(TProducto p : prs){
			if(!p.getMarca().getNombre().equals(f[0]) && !f[0].equals("Marca")){
				filterSet.remove(p);
			}
			else if(p.getProveedor() == null && !f[1].equals("Proveedor")){
				filterSet.remove(p);
			}else if(!f[1].equals("Proveedor") && !p.getProveedor().getNombre().equals(f[1])){
				filterSet.remove(p);
			}
			else if(!p.getSeccion().getZona().equals(f[2]) && !f[2].equals("Seccion")){
				filterSet.remove(p);
			}
			
		}
		return filterSet;
	}
	
	

}