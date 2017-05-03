import java.util.Random;

/**
 * Clase GeneradorRandom
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class GeneradorRandom {
    private Random random; // Usamos la clase random del API de Java

    public GeneradorRandom() {
        random = new Random();
    }

    /**
     * Método que genera un número random entre 0.0 y 1
     * @return
     */
    public double getNextDouble() {
        return random.nextDouble();
    }

    /**
     * Método que genera un número aleatorio con la distribución uniforme
     * @param a valor inferior del intervalo
     * @param b valor superior del intervalo
     * @return
     */
    public double uniforme(double a, double b) {
        return a + (b-a) * random.nextDouble();
    }

    /**
     * Método que genera un número aleatorio con la distribución exponencial
     * @param lambda recibe el lambda
     * @return
     */
    public double exponencial(double lambda) {
        return -1/lambda * Math.log(random.nextDouble());
    }

    /**
     * Método que genera un número aleatorio con la distribución normal
     *
     * Para generar estos aleatorios, utilizamos el método directo para esta distribución
     * @param u media
     * @param o varianza
     * @return
     */
    public double normal(double u, double o) {
        double r1 = random.nextDouble();
        double r2 = random.nextDouble();

        double z = Math.pow(-2*Math.log(r1), .5)*Math.sin(2*Math.PI*r2);
        z = u+o*z;

        return z;
    }

    /**
     * Método que genera un número aleatorio con la distribución de la computadora 3
     * @return
     */
    public double distribucionComputadora3() {
        return Math.sqrt(48*this.getNextDouble() + 16);
    }
}
