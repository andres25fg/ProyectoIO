/**
 * Clase Mensaje
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class Mensaje {
    double llegadaSistema;
    double tiempoEnCola; // WQ
    double tiempoTansmision;
    double tiempoProcesamiento; // WS
    int cantidadVecesDevuelto; // Cantidad de veces que fue devuelto desde la C1

    double procesador1; // P1 de C1
    double procesador2; // P1 de C2
    double procesador3; // P2 de C2
    double procesador4; // P1 de C3

    public Mensaje() {

    }

    public void setLlegadaSistema(double llegadaSistema) {
        this.llegadaSistema = llegadaSistema;
    }
}
