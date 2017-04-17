/**
 * Clase Computadora
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class Computadora {
    private Estadisticas estadisticas; // Instancia del objeto para estadísticas
    private GeneradorRandom generadorRandom; // Instancia del objeto generador de números aleatorios

    public Computadora() {
        estadisticas = new Estadisticas();
        generadorRandom = new GeneradorRandom();
    }
}
