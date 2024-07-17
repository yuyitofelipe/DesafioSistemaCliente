package cl.praxis.servicio;
import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class ArchivoServicio extends Exportador{
    private final Scanner scan = new Scanner(System.in);
    private final ExportaCsv exportaCsv = new ExportaCsv();
    private final ExportaTxt exportaTxt = new ExportaTxt();

    public List<Cliente> cargarDatos() {
        System.out.println("---------Cargar Datos en Windows -----------");
        System.out.print("Ingresa la ruta completa del archivo DBClientes.csv: (src/main/resources/DBClientes.csv)\n");
        String rutaIngresada = scan.nextLine();
        List<Cliente> clientesCarga = new ArrayList<>();
        if (rutaIngresada.equals("src/main/resources/DBClientes.csv")) {
            clientesCarga = cargarDatosDesdeArchivo(rutaIngresada);
            System.out.println("-----------------------------------------------");
            System.out.println("\nDatos cargados correctamente en la lista.\n");
        } else {
            System.out.println("Ruta incorrecta.");
        }
        return clientesCarga;
    }

    private List<Cliente> cargarDatosDesdeArchivo(String fileName) {
        List<Cliente> clientesCarga = new ArrayList<>();
        File archivo = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    try {
                        String runCliente = datos[0];
                        String nombreCliente = datos[1];
                        String apellidoCliente = datos[2];
                        String aniosCliente = datos[3];
                        CategoriaEnum nombreCategoria = CategoriaEnum.valueOf(datos[4].toUpperCase());
                        Cliente cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);
                        clientesCarga.add(cliente);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error con la conversion de datos: " + e.getMessage());
                    }
                } else {
                    System.out.println("Formato incorrecto: " + linea);
                }
            }
            System.out.println("Datos cargados con exito.");
        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
        return clientesCarga;
    }

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        System.out.println("--------Exportar Datos----------");
        System.out.println("Seleccione el formato a exportar:");
        System.out.println("1. Formato csv");
        System.out.println("2. Formato txt");
        System.out.println("Ingrese una opción de exportar:");
        try {
            int op = scan.nextInt();
            scan.nextLine();
            System.out.println("----------------------------------");
            if (op == 1) {
                exportaCsv.exportar(fileName, listaClientes);
            } else if (op == 2) {
                exportaTxt.exportar(fileName, listaClientes);
            } else {
                System.out.println("Ingrese Opción Válida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Debe Ingresar Numeros.");
            scan.nextLine();
        }
    }
}
