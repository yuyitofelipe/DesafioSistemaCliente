package cl.praxis.servicio;
import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import cl.praxis.utilidades.Utilidad;
import java.util.*;

public class ClienteServicio {
    private List<Cliente> listaClientes;
    Scanner scan;
    Utilidad utilidad;

    public ClienteServicio() {
        this.listaClientes = new ArrayList<>();
        this.scan = new Scanner(System.in);
        this.utilidad = new Utilidad();
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            Utilidad.imprimir("No hay clientes");
        } else {
            for (Cliente cliente : listaClientes) {
                Utilidad.imprimir("-------------Datos del Cliente------------- ");
                Utilidad.imprimir(cliente.toString());
                Utilidad.imprimir("-------------------------------------------");
            }
        }
    }

    public void agregarCliente(String run, String nombre, String apellido, String anios) {
        if (run == null || nombre == null || apellido == null || anios == null) {
            throw new IllegalArgumentException("Los Campos no Pueden ser Nulos");
        }
        Cliente cliente = new Cliente();
        cliente.setRunCliente(run);
        cliente.setNombreCliente(nombre);
        cliente.setApellidoCliente(apellido);
        cliente.setAniosCliente(anios);
        cliente.setNombreCategoria(CategoriaEnum.Activo);
        listaClientes.add(cliente);
    }


    public void agregarCliente() {
        Utilidad.imprimir("-------------Crear Cliente------------- ");
        try {
            Utilidad.imprimir("Ingresa el RUN del cliente: ");
            String run = scan.nextLine();
            Utilidad.imprimir("Ingresa el Nombre del cliente: ");
            String nombre = scan.nextLine();
            Utilidad.imprimir("Ingresa el Apellido del cliente: ");
            String apellido = scan.nextLine();
            Utilidad.imprimir("Ingresa los años del cliente: ");
            String anios = scan.nextLine();
            agregarCliente(run, nombre, apellido, anios);
            Utilidad.imprimir("--------------------------------------- ");
        } catch (InputMismatchException e) {
            Utilidad.imprimir("Error: Debe ingresar un número. Intente de nuevo.");
            scan.nextLine();
        }
    }

    public void editarClientes() {
        Utilidad.imprimir("-------------Editar Cliente------------- ");
        Utilidad.imprimir("Seleccione qué desea hacer: ");
        Utilidad.imprimir("1.- Cambiar el estado del cliente");
        Utilidad.imprimir("2.- Editar datos del cliente");
        Utilidad.imprimir("Ingrese opcion: ");
        try {
            int opcion = scan.nextInt();
            scan.nextLine();
            Utilidad.imprimir("----------------------------------------");
            if (opcion == 1) {
                cambiarEstadoCliente();
            } else if (opcion == 2) {
                editarDatosCliente();
            } else {
                Utilidad.imprimir("Opción no válida, intente con un número entre 1 y 2.");
            }
        } catch (InputMismatchException e) {
            Utilidad.imprimir("Error: Debe ingresar un número. Intente de nuevo.");
            scan.nextLine();
        }
    }

    public void cambiarEstadoCliente(){
        System.out.print("Ingrese RUN del Cliente a editar: ");
        try {
            String runCliente = scan.nextLine();
            Optional<Cliente> clienteOptional = listaClientes.stream()
                    .filter(cliente -> cliente.getRunCliente().equals(runCliente))
                    .findFirst();
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                Utilidad.imprimir("-----Actualizando estado del Cliente----");
                Utilidad.imprimir("El estado actual del cliente es: " + cliente.getNombreCategoria());
                Utilidad.imprimir("1.-Si desea cambiar el estado del Cliente a Inactivo");
                Utilidad.imprimir("2.-Si desea mantener el estado del cliente Activo");
                Utilidad.imprimir("Ingrese opcion: ");
                int opcionEstado = scan.nextInt();
                scan.nextLine();
                Utilidad.imprimir("----------------------------------------");
                switch (opcionEstado) {
                    case 1:
                        cliente.setNombreCategoria(CategoriaEnum.Inactivo);
                        break;
                    case 2:
                        cliente.setNombreCategoria(CategoriaEnum.Activo);
                        break;
                    default:
                        Utilidad.imprimir("Opción no válida, intente con un número 1 y 2.");
                }
                Utilidad.imprimir("Estado cambiado");
            } else {
                Utilidad.imprimir("Cliente con RUN " + runCliente + " no encontrado.");
            }
        } catch (InputMismatchException e) {
            Utilidad.imprimir("Error: Debe ingresar un número.");
            scan.nextLine();
        }
    }

    public void editarDatosCliente() {
        try {
            System.out.print("Ingrese RUN del Cliente para editar:");
            String runCliente = scan.nextLine();
            Optional<Cliente> clienteOpcional = listaClientes.stream()
                    .filter(cliente -> cliente.getRunCliente().equals(runCliente))
                    .findFirst();

            if (clienteOpcional.isPresent()) {
                Cliente cliente = clienteOpcional.get();
                Utilidad.imprimir("Datos del Cliente : \n");
                Utilidad.imprimir("1. RUN del Cliente : \n" + cliente.getRunCliente());
                Utilidad.imprimir("2. Nombre del Cliente : \n" + cliente.getNombreCliente());
                Utilidad.imprimir("3. Apellido del Cliente : \n" + cliente.getApellidoCliente());
                Utilidad.imprimir("4. Años del Cliente : \n" + cliente.getAniosCliente());
                System.out.print("Ingrese opcion de la cual quiera editar de los datos del cliente: \n");
                Utilidad.imprimir("---------------------------------------- ");
                int opEditar = scan.nextInt();
                scan.nextLine();
                switch (opEditar) {
                    case 1:
                        Utilidad.imprimir("1.-Ingrese nuevo RUN del Cliente:");
                        cliente.setRunCliente(scan.nextLine());
                        break;
                    case 2:
                        System.out.print("2. -Ingrese el nuevo nombre del cliente: ");
                        cliente.setNombreCliente(scan.nextLine());
                        break;
                    case 3:
                        System.out.print("3. -Ingrese el nuevo apellido del cliente: ");
                        cliente.setApellidoCliente(scan.nextLine());
                        break;
                    case 4:
                        System.out.print("4. -Ingrese los nuevos años del cliente: ");
                        cliente.setAniosCliente(scan.nextLine());
                        break;
                    default:
                        Utilidad.imprimir("Opción no válida, intente entre 1 y 4.");
                }
                Utilidad.imprimir("Datos cambiados");
            } else {
                Utilidad.imprimir("Cliente con RUN " + runCliente + " no encontrado.");
            }
        } catch (InputMismatchException e) {
            Utilidad.imprimir("Error: Debe ingresar un número.");
            scan.nextLine();
        }
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

}
