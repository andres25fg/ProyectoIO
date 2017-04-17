import java.util.Deque;

/**
 * Clase Computadora
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class Computadora {
    private Estadisticas estadisticas; // Instancia del objeto para estadísticas
    private GeneradorRandom generadorRandom; // Instancia del objeto generador de números aleatorios
    private Deque<Mensaje> colaMensajes; // Cola de mensajes
    private int procesadores; // Cantidad de procesadores de la computadora

    public Computadora() {
        estadisticas = new Estadisticas();
        generadorRandom = new GeneradorRandom();
    }
}
