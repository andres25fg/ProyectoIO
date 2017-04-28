import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
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
    private double clock; // Reloj del sistema
    private Interfaz interfaz; // Objeto de la clase interfaz
    //private Computadora computadora1; // Instancia de la Computadora No. 1
    //private Computadora computadora2; // Instancia de la Computadora No. 2
    // private Computadora computadora3; // Instancia de la Computadora No. 3
    private Estadisticas estadisticas; // Instancia del objeto para estadísticas
    private GeneradorRandom generadorRandom; // Instancia del objeto generador de números aleatorios
    private PriorityQueue<Evento> colaEventos; // Cola de eventos del sistema
    private int numeroMensajesRechazados;
    private int numeroMensajesFinalizados;

    private Deque<Mensaje> computadora1; // Cola de mensajes de la computadora 1
    private Deque<Mensaje> computadora2; // Cola de mensajes de la computadora 2
    private Deque<Mensaje> computadora3; // Cola de mensajes de la computadora 3

    private int procesadoresLibres_Computadora1;
    private int procesadoresLibres_Computadora2;
    private int procesadoresLibres_Computadora3;

    /**
     * Constructor
     * @param numSimulaciones cantidad de simulaciones que se vean a realizar
     * @param tiempoSimulacion tiempo en minutos de cada simulación
     */
    public Simulacion(int numSimulaciones, int tiempoSimulacion) {
        this.setNumSimulaciones(numSimulaciones); // Se inicializan los parámetros de la simulación
        this.setTiempoSimulacion(tiempoSimulacion);
        this.setClock(0); // Inicializamos el reloj en 0

        // Creamos las intancias de los objetos que se van a requerir para la simulación
        estadisticas = new Estadisticas();
        generadorRandom = new GeneradorRandom();
        computadora1 = new ArrayDeque<Mensaje>();
        computadora2 = new ArrayDeque<Mensaje>();
        computadora3 = new ArrayDeque<Mensaje>();

        procesadoresLibres_Computadora1 = 1;
        procesadoresLibres_Computadora2 = 2;
        procesadoresLibres_Computadora3 = 1;

        Comparator<Evento> comparator = new ComparadorEvento(); // Se crea el comparador de la cola de prioridad
        colaEventos = new PriorityQueue<Evento>(comparator); // Se inicializa la cola de prioridad

        // La lógica de la simulación se corre en otro hilo diferente para poder correr la simulación y refrescar la interfaz simultáneamente
        (new Thread() {
            public void run(){
                try {
                    correrSimulacion(); // Se llama al proceso principal que corre la simulación
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void generarLlegada() {
        Mensaje mensaje = new Mensaje();
        double random = generadorRandom.getNextDouble();
        int tipoEvento;
        double tiempoEvento;

        if(random > 0.5) {
            tipoEvento = 0; // Computadora 2
            mensaje.setComputadoraInicio(2);
            tiempoEvento = generadorRandom.normal(15,1); // generamos el tiempo de llegada para la computadora 2
        } else {
            tipoEvento = 1; // Computadora 3
            mensaje.setComputadoraInicio(3);
            tiempoEvento = generadorRandom.distribucionComputadora3(); // generamos el tiempo de llegada para la computadora 3
        }

        mensaje.setLlegadaSistema(clock + tiempoEvento);
        Evento evento = new Evento(mensaje, tiempoEvento, tipoEvento);
        colaEventos.add(evento);
    }

    public void correrSimulacion() {
        double tiempoServicio = 0;
        Evento proximoEvento;

        double random = generadorRandom.getNextDouble();
        Mensaje mensaje = new Mensaje();
        int tipoEvento;
        if(random > 0.5) {
            tipoEvento = 0; // Computadora 2
            mensaje.setComputadoraInicio(2);
        } else {
            tipoEvento = 1; // Computadora 3
            mensaje.setComputadoraInicio(3);
        }
        mensaje.setLlegadaSistema(0);

        Evento primerEvento = new Evento(mensaje, 0, tipoEvento);
        colaEventos.add(primerEvento);

        for(int sim = 0; sim < numSimulaciones; sim++) {
            while(clock < tiempoSimulacion * 60) {
                Evento eventoActual = colaEventos.poll();

                clock = eventoActual.getTiempoEvento();

                switch (eventoActual.getTipoEvento()){
                    case 0: // Llegada a Computadora 2
                        tiempoServicio = 0;
                        if(procesadoresLibres_Computadora2 != 0) {
                            if(procesadoresLibres_Computadora2 == 2) {
                                procesadoresLibres_Computadora2--; // ocupamos el procesador 2 de C3
                                tipoEvento = 3; // Tipo de evento: C2 Libera P2
                                tiempoServicio = generadorRandom.uniforme(12,25);
                            } else if (procesadoresLibres_Computadora2 == 1){
                                procesadoresLibres_Computadora2--; // ocupamos el procesador 1 de C3
                                tipoEvento = 2; // Tipo de evento: C2 Libera P1
                                tiempoServicio = generadorRandom.uniforme(12,25);
                            }
                            // Generamos el próximo evento dependiendo del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(eventoActual.getMensaje(), clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            eventoActual.getMensaje().setTiempoInicioCola(clock);
                            computadora2.add(eventoActual.getMensaje());
                        }
                        break;
                    case 1: // Llegada a la Computadora 3

                        if(procesadoresLibres_Computadora3 != 0) {
                            procesadoresLibres_Computadora3--; // ocupamos el procesador 1 de C3
                            tipoEvento = 4; // Tipo de evento: C3 Libera P1
                            tiempoServicio = generadorRandom.exponencial(4);
                            // Generamos el próximo evento del procesador que se va a liberar, y lo agregams a la cola de eventos

                            proximoEvento = new Evento(eventoActual.getMensaje(), clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            eventoActual.getMensaje().setTiempoInicioCola(clock);
                            computadora3.add(eventoActual.getMensaje());
                        }

                        break;
                    case 2: // C2 lbera a P1
                        if(!computadora2.isEmpty()) {
                            mensaje = computadora2.poll();
                            mensaje.sumarTiempoEnCola(clock - mensaje.getTiempoInicioCola());
                            tipoEvento = 2; // Tipo de evento: C2 Libera P1
                            tiempoServicio = generadorRandom.uniforme(12,25);

                            // Generamos el próximo evento del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(mensaje, clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            procesadoresLibres_Computadora2++;
                        }

                        proximoEvento = new Evento(eventoActual.getMensaje(), clock + 20, 5);
                        colaEventos.add(proximoEvento);
                        break;
                    case 3: // C2 libera a P2
                        if(!computadora2.isEmpty()) {
                            mensaje = computadora2.poll();
                            mensaje.sumarTiempoEnCola(clock - mensaje.getTiempoInicioCola());
                            tipoEvento = 3; // Tipo de evento: C2 Libera P2
                            tiempoServicio = generadorRandom.uniforme(12,25);

                            // Generamos el próximo evento dependiendo del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(mensaje, clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            procesadoresLibres_Computadora2++;
                        }

                        proximoEvento = new Evento(eventoActual.getMensaje(), clock + 20, 5);
                        colaEventos.add(proximoEvento);

                        break;
                    case 4: // C3 Libera a P1
                        if(!computadora3.isEmpty()) {
                            mensaje = computadora3.poll();
                            mensaje.sumarTiempoEnCola(clock - mensaje.getTiempoInicioCola());
                            tipoEvento = 4; // Tipo de evento: C3 Libera P1
                            tiempoServicio = generadorRandom.exponencial(4);

                            // Generamos el próximo del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(mensaje, clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            procesadoresLibres_Computadora2++;
                        }

                        if (generadorRandom.getNextDouble() * 100 <= 80) {
                            /**
                             * Actualizar estadístcas
                             */
                            numeroMensajesRechazados++;
                        } else {
                            proximoEvento = new Evento(eventoActual.getMensaje(), clock + 20, 5);
                            colaEventos.add(proximoEvento);
                        }

                        break;
                    case 5: // C1 recibe mensaje de C2 o de C3
                        if(procesadoresLibres_Computadora1 != 0) {
                            procesadoresLibres_Computadora1--; // ocupamos el procesador 1 de C1
                            tipoEvento = 6; // Tipo de evento: C1 Libera P1
                            tiempoServicio = generadorRandom.exponencial(10);
                            // Generamos el próximo evento del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(eventoActual.getMensaje(), clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            eventoActual.getMensaje().setTiempoInicioCola(clock);
                            computadora1.add(eventoActual.getMensaje());
                        }

                        break;
                    case 6: // C1 libera P1
                        if(!computadora1.isEmpty()) {
                            mensaje = computadora1.poll();
                            mensaje.sumarTiempoEnCola(clock - mensaje.getTiempoInicioCola());
                            tipoEvento = 6; // Tipo de evento: C1 Libera P1
                            tiempoServicio = generadorRandom.exponencial(10);

                            // Generamos el próximo del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(mensaje, clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            procesadoresLibres_Computadora1++;
                        }

                        if(eventoActual.getMensaje().getComputadoraInicio() == 2) {
                            if (generadorRandom.getNextDouble() * 100 <= 20) {
                                proximoEvento = new Evento(eventoActual.getMensaje(), clock + 3, 0);
                                colaEventos.add(proximoEvento);
                            } else {
                                /**
                                 * Actualizamos estadísticas
                                 */
                                numeroMensajesFinalizados++;
                            }
                        } else {
                            if (generadorRandom.getNextDouble() * 100 <= 50) {
                                proximoEvento = new Evento(eventoActual.getMensaje(), clock + 3, 1);
                                colaEventos.add(proximoEvento);
                            } else {
                                /**
                                 * Actualizamos estadísticas
                                 */
                                numeroMensajesFinalizados++;
                            }
                        }
                        break;
                }
                this.generarLlegada(); // Generamos ls siguiente llgada al sistema
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

    /**
     * Clase anidada ComparadorEvento.
     * Definición del comparador a usar en la cola de prioridad
     */
    public class ComparadorEvento implements Comparator<Evento> {
        /**
         * Método que realiza la comparación entre 2 eventos para determinar cual va primero
         * @param evento1
         * @param evento2
         * @return
         */
        public int compare(Evento evento1, Evento evento2) {
            // Se hace una resta para determinar cual de los dos eventos debe ir primero en la cola
            int prioridad;
            if(evento1.getTiempoEvento() > evento2.getTiempoEvento()){
                prioridad = 1;
            }else{
                prioridad = -1;
            }
            return prioridad;
        }
    }
}


