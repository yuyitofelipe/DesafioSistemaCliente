package cl.praxis.utilidades;

public class Utilidad {

    public static void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    public static void limpiarPantalla() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public static void tiempoEspera() {
        try {
            System.out.println("Saliendo del sistema");
            Thread.sleep(5000);
            System.out.println("Usted ha salido");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
