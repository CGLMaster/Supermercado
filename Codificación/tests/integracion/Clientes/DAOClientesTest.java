package integracion.Clientes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.Clientes.TCliente;

public class DAOClientesTest {
	static DAOClienteimp clientes;

	@BeforeClass
	public static void BeforeClass() {
		clientes = new DAOClienteimp();
	}

	@Test
	public void pruebaCreaBien() {
		TCliente t_param = new TCliente("15912345W", "miralaprueba@cuidao.es", "PruebaTestEdu");

		assertNotEquals(-1, clientes.crearCliente(t_param));
	}

	@Test
	public void pruebaCreaMal() {
		TCliente t_param = new TCliente("1233", "123", "Luis");
		assertNotEquals(1, clientes.crearCliente(t_param));
	}

	@Test
	public void pruebaeliminaBien() {

		assertEquals(1, clientes.eliminarCliente(44));
	}

	@Test
	public void pruebaeliminaMal() {
		assertEquals(0, clientes.eliminarCliente(-1));
	}

	@Test
	public void pruebaBuscaBien() {
		TCliente elem = clientes.buscarCliente(2);
		assertNotNull(elem);
		assertEquals(elem.getDNI(), "6453451M");
		assertEquals(elem.getMail(), "manolito12@gmail.com");
		assertEquals(elem.getNombre(), "Manuel");
	}

	@Test
	public void pruebaBuscaMal() {
		assertNull(clientes.buscarCliente(3));
	}

	@Test
	public void pruebaModificarBien() {
		TCliente t_param = new TCliente("15995225W", "miralaprueba@cuidao.es", "PruebaTestEduCambiado");
		t_param.setId(43);
		assertNotEquals(-1, clientes.crearCliente(t_param));
	}

	@Test
	public void pruebaModificarMal() {
		TCliente t_param = new TCliente("15995115W", "miralaprueba@cuidao.es", "PruebaTestEduCambiadoMal");
		t_param.setId(7);
		assertNotEquals(1, clientes.crearCliente(t_param));
	}
}