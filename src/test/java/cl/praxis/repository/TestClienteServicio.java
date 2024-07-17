package cl.praxis.repository;
import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import cl.praxis.servicio.ClienteServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.*;

public class TestClienteServicio {
    private final static Logger logger = Logger.getLogger(TestClienteServicio.class.getName());
    private final ClienteServicio clienteServicio = new ClienteServicio();

@BeforeAll
public static void init(){
    logger.warning("-> Inicio de los test ");
}
@BeforeEach
public void initEach(){
    logger.info("-> Inicio del test");
}
@AfterEach
public void closeEach(){
    logger.info("-> Fin del test");
}

@AfterAll
public static void close(){
    logger.warning("-> Fin de los test");
}

@Test
public void testAgregarCliente() {
    logger.info("Agregar un cliente");
    Cliente cliente = new Cliente("87654321-9", "Pedro", "Piedra", "30", CategoriaEnum.Activo);
    List<Cliente> listaClientes = new ArrayList<>();
    listaClientes.add(cliente);
    clienteServicio.setListaClientes(listaClientes);
    clienteServicio.agregarCliente("45123678-0", "Juana", "DeArco", "25");
    assertEquals(2, clienteServicio.getListaClientes().size());
    logger.info("El test para agregar un cliente funciona ");
}
@Test
public void testAgregarClienteNulo() {
    logger.info("Agregar un cliente nulo");
    assertThrows(IllegalArgumentException.class, () -> clienteServicio.agregarCliente(null, "Nombre", "Apellido", "30"));
    assertThrows(IllegalArgumentException.class, () -> clienteServicio.agregarCliente("87654321-9", null, "Apellido", "300"));
    assertThrows(IllegalArgumentException.class, () -> clienteServicio.agregarCliente("12678345-9", "Nombre", null, "5000"));
    assertThrows(IllegalArgumentException.class, () -> clienteServicio.agregarCliente("45123678-9", "Nombre", "Apellido", null));
    assertEquals(0, clienteServicio.getListaClientes().size());
    logger.info("El test para agregar un cliente null funciona");
}
}
