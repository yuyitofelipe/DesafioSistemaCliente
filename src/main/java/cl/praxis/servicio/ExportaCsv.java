package cl.praxis.servicio;
import cl.praxis.modelo.Cliente;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ExportaCsv extends Exportador{

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        File directorio = new File("src/main/resources/");
        if (!fileName.endsWith(".csv")) {
            fileName = fileName.concat(".csv");
        }
        File fichero = new File(directorio, fileName);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        try {
            boolean archivoCreado = fichero.createNewFile();
            if (!archivoCreado) {
                System.out.println("El archivo no se pudo crear.");
            }
            fichero.createNewFile();
            PrintWriter pw = new PrintWriter(new FileWriter(fichero));
            for (Cliente cliente : listaClientes) {
                pw.printf("%s,%s,%s,%s,%s%n",
                        cliente.getRunCliente(),
                        cliente.getNombreCliente(),
                        cliente.getApellidoCliente(),
                        cliente.getAniosCliente(),
                        cliente.getNombreCategoria());
            }
            pw.close();
            System.out.println("Datos de clientes exportados correctamente en formato csv.");
        } catch (Exception e) {
            System.out.println("Error al generar archivo csv");
        }
    }
}
