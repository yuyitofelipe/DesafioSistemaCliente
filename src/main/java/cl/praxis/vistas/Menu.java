package cl.praxis.vistas;

import cl.praxis.servicio.*;
import cl.praxis.utilidades.Utilidad;
import cl.praxis.modelo.Cliente;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private ClienteServicio clienteServicio;
    private ArchivoServicio archivoServicio;
    private ExportaCsv exportadorCsv;
    private ExportaTxt exportadorTxt;
    private final String fileName = "clientes";
    private final String fileName1 = "DBClientes.csv";
    private Scanner scanner;
    private List<Cliente> listaClientes;

    public Menu() {
        this.clienteServicio = new ClienteServicio();
        this.archivoServicio = new ArchivoServicio();
        this.exportadorCsv = new ExportaCsv();
        this.exportadorTxt = new ExportaTxt();
        this.scanner = new Scanner(System.in);
        this.listaClientes = new ArrayList<>();
    }

    public void iniciarMenu(){
        int op = 0;
        do {
            mostrarMenu();
            try {
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op){
                    case 1:
                        clienteServicio.listarClientes();
                        break;
                    case 2:
                        clienteServicio.agregarCliente();
                        break;
                    case 3:
                        clienteServicio.editarClientes();
                        break;
                    case 4:
                        cargarDatos();
                        break;
                    case 5:
                        archivoServicio.exportar( fileName, listaClientes);
                        break;
                    case 6:
                        terminarPrograma();
                        break;
                    default:
                        Utilidad.imprimir("Opción no válida");
                }
            } catch (InputMismatchException e) {
                Utilidad.imprimir("Debe ingresar los números de las opciones disponibles");
                scanner.nextLine();
            }
        }while (op != 6);
    }

    private void mostrarMenu() {
        Utilidad.imprimir("-------Menú:-------");
        Utilidad.imprimir("1. Listar Cliente");
        Utilidad.imprimir("2. Agregar Cliente");
        Utilidad.imprimir("3. Editar Cliente");
        Utilidad.imprimir("4. Cargar Datos");
        Utilidad.imprimir("5. Exportar Datos");
        Utilidad.imprimir("6. Salir");
        Utilidad.imprimir("Ingrese una opción: ");
    }

    private void cargarDatos() {
        listaClientes = archivoServicio.cargarDatos();
        clienteServicio.setListaClientes(listaClientes);
    }


    public void terminarPrograma() {
        Utilidad.limpiarPantalla();
        Utilidad.imprimir("Finalizando sistema");
        Utilidad.tiempoEspera();
    }
}
