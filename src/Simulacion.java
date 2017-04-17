import java.util.PriorityQueue;

/**
 * Clase Simulación
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class Simulacion {
    private int numSimulaciones; // Cantidad de simulaciones que se van a realizar
    private int tiempoSimulacion; // Tiempo en minutos de cada simulación
    private int clock; // Reloj del sistema
    private Interfaz interfaz; // Objeto de la clase interfaz
    private Computadora computadora1; // Instancia de la Computadora No. 1
    private Computadora computadora2; // Instancia de la Computadora No. 2
    private Computadora computadora3; // Instancia de la Computadora No. 3
    private Estadisticas estadisticas; // Instancia del objeto para estadísticas
    private PriorityQueue<Evento> colaEventos; // Cola de eventos del sistema

    public Simulacion(int numSimulaciones, int tiempoSimulacion) {
        this.setNumSimulaciones(numSimulaciones); // Se inicializan los parámetros de la simulación
        this.setTiempoSimulacion(tiempoSimulacion);
        this.setClock(0); // Inicializamos el reloj en 0

        // Creamos las intancias de los objetos que se van a requerir para la simulación
        computadora1 = new Computadora();
        computadora1 = new Computadora();
        computadora1 = new Computadora();
        estadisticas = new Estadisticas();

        // La lógica de la simulación se corre en otro hilo diferente para poder correr la simulación y refrescar la interfaz simultáneamente
        (new Thread() {
            public void run(){
                try {
                    correrSimulacion(); // Se llama al proceso principal que corre la simulación
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void correrSimulacion() {
        for(int sim = 0; sim < numSimulaciones; sim++) {
            while(clock < tiempoSimulacion) {

            }
        }
    }

    public void setNumSimulaciones(int numSimulaciones) {
        this.numSimulaciones = numSimulaciones;
    }

    public void setTiempoSimulacion(int tiempoSimulacion) {
        this.tiempoSimulacion = tiempoSimulacion;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }
}
