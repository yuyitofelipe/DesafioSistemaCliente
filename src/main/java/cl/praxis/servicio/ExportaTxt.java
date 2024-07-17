package cl.praxis.servicio;
import cl.praxis.modelo.Cliente;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
public class ExportaTxt extends Exportador{

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        String data = "Listado de clientes\n";
        File directorio = new File("src/main/resources");
        if(!fileName.endsWith(".txt")) {
            fileName = fileName.concat(".txt");
        }
        File fichero = new File(directorio, fileName);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        try {
            fichero.createNewFile();
            PrintWriter pw = new PrintWriter(new FileWriter(fichero));
            for(Cliente cliente : listaClientes) {
                data += "\n" + cliente.toString() + "\n";
            }
            pw.write(data);
            pw.close();
            System.out.println("Datos de clientes exportados formato txt.");
        } catch (Exception e) {
            System.out.println("Error al generar archivo txt");
        }
    }
}
