package negocio.Compras;

import java.util.TreeMap;

public class TCompraTotal {
	
	public TCompraTotal(){

	}
	
	public TCompraTotal(TCompras tCompras){
		this.tCompras=tCompras;
		mapa_compra =new TreeMap<Integer, TProductoCantidad>();
	}
	
	public TCompraTotal(TCompras tCompras, TreeMap<Integer, TProductoCantidad> mapa_compra){
		this.tCompras=tCompras;
		this.mapa_compra = mapa_compra;
	}

	private TreeMap<Integer, TProductoCantidad> mapa_compra;

	private TCompras tCompras;
	
	public void add(TProductoCantidad producto, Integer id){
		if(!mapa_compra.containsKey(id)){
			mapa_compra.put(id, producto);
		}
		else{
			int cantidad = producto.getCantidad() + mapa_compra.get(id).getCantidad();
			mapa_compra.get(id).setCantidad(cantidad);
		}
	}
	
	
	public TCompras getCompra(){
		return tCompras;
	}
	
	public TreeMap<Integer, TProductoCantidad> getMapaProductos(){
		return mapa_compra;
	}
	
	public void setMapaProductos(TreeMap<Integer, TProductoCantidad> productosEnCarrito){
		this.mapa_compra = productosEnCarrito;
	}
	
}