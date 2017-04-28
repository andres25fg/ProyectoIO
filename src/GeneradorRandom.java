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

    public double getNextDouble() {
        return random.nextDouble();
    }

    public double uniforme(double a, double b) {
        return a + (b-a) * random.nextDouble();
    }

    public double exponencial(double lambda) {
        return -1/lambda * Math.log(random.nextDouble());
    }

    public double normal(double u, double o) {
        double r1 = random.nextDouble();
        double r2 = random.nextDouble();

        double z = Math.pow(-2*Math.log(r1), .5)*Math.sin(2*Math.PI*r2);
        z = u+o*z;

        return z;
    }

    public double distribucionComputadora3() {
        return Math.sqrt(48*this.getNextDouble() + 16);
    }
}
