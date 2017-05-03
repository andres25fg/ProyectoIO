import java.util.*;
import java.util.concurrent.TimeUnit;

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
    private boolean modoLento; // Bandera para saber si se va a correr la simulacion en modo lento
    private int segundosModoLento; // Cantidad de segundos entre eventos
    private Interfaz interfaz; // Objeto de la clase interfaz
    private ArrayList<Estadisticas> colaEstadisticas; // Cola con las instancias de estadisticas de las n simulaciones
    private GeneradorRandom generadorRandom; // Instancia del objeto generador de números aleatorios
    private PriorityQueue<Evento> colaEventos; // Cola de eventos del sistema
    private int numeroMensajesRechazados; // Número total de mensajes rechazados
    private int numeroMensajesFinalizados; // Número total de mensajes finalizados
    private int cantidadMensajes; // Cantidad total de mensajes
    private int cantidadVecesDevuelto; // Cantidad de veces que un mensaje es devuelto

    private Deque<Mensaje> computadora1; // Cola de mensajes de la computadora 1
    private Deque<Mensaje> computadora2; // Cola de mensajes de la computadora 2
    private Deque<Mensaje> computadora3; // Cola de mensajes de la computadora 3

    // 1: procesador libre, 0: procesador ocupado
    private int procesadoresLibres_Computadora1; // Estado del procesador 1 de la computadora 1
    private int procesadoresLibres_Computadora3; // Estado del procesador 1 de la computadora 3

    // True: procesador libre, False: procesador ocupado
    private boolean P1_C2; // Estado del procesador 1 de la computadora 2
    private boolean P2_C2; // Estado del procesador 2 de la computadora 2


    /**
     * Constructor
     * @param numSimulaciones cantidad de simulaciones que se vean a realizar
     * @param tiempoSimulacion tiempo en minutos de cada simulación
     */
    public Simulacion(int numSimulaciones, int tiempoSimulacion, boolean modoLento, int segundosModoLento, Interfaz gui) {
        interfaz = gui; // Referencia del objeto interfaz para la comunicación
        this.setNumSimulaciones(numSimulaciones); // Se inicializan los parámetros de la simulación
        this.setTiempoSimulacion(tiempoSimulacion);
        this.setClock(0); // Inicializamos el reloj en 0
        this.setModoLento(modoLento);
        if(modoLento) {
            this.setSegundosModoLento(segundosModoLento);
        }

        // Creamos las intancias de los objetos que se van a requerir para la simulación
        generadorRandom = new GeneradorRandom();
        computadora1 = new ArrayDeque<Mensaje>(); // Cola de mensajes de la computadora 1
        computadora2 = new ArrayDeque<Mensaje>(); // Cola de mensajes de la computadora 2
        computadora3 = new ArrayDeque<Mensaje>(); // Cola de mensajes de la computadora 3

        Comparator<Evento> comparator = new ComparadorEvento(); // Se crea el comparador de la cola de prioridad
        colaEventos = new PriorityQueue<Evento>(comparator); // Se inicializa la cola de prioridad
        colaEstadisticas = new ArrayList<Estadisticas>(); // Se inicializa la cola de estadísticas

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

    /**
     * Método para generar un llegada de un mensaje al sistema
     */
    public void generarLlegada() {
        Mensaje mensaje = new Mensaje();
        double random = generadorRandom.getNextDouble();
        int tipoEvento;
        double tiempoEvento;
        cantidadMensajes++;

        if(random > 0.5) { // Asignamos aleatoriamente a cual computadora va llegar el mensaje
            tipoEvento = 0; // El mensaje va a llegar a la Computadora 2
            mensaje.setComputadoraInicio(2);
            tiempoEvento = generadorRandom.normal(15,1); // generamos el tiempo de llegada para la computadora 2
        } else {
            tipoEvento = 1; // El mensaje va a llegar a la Computadora 3
            mensaje.setComputadoraInicio(3);
            tiempoEvento = generadorRandom.distribucionComputadora3(); // generamos el tiempo de llegada para la computadora 3
        }

        mensaje.setLlegadaSistema(clock + tiempoEvento);
        Evento evento = new Evento(mensaje, clock + tiempoEvento, tipoEvento); // Asociamos el mensaje al evento
        colaEventos.add(evento); // Agregamos el evento a la cola de eventos del sistema
    }

    /**
     * Método que contiene la lógica principal de la simulación y el proceso de eventos
     */
    public void correrSimulacion() {
        // Ciclo que ejecuta el número total de las simulaciones
        for(int sim = 1; sim <= numSimulaciones; sim++) {
            interfaz.showTextoGUI("\nSimulación número: " + sim);
            interfaz.showNumeroSimulacion(sim);

            numeroMensajesFinalizados = 0;
            numeroMensajesRechazados = 0;
            cantidadMensajes = 0;
            cantidadVecesDevuelto = 0;
            clock = 0;

            P1_C2 = true; P2_C2 = true; // Ponemos los procesadores libres
            procesadoresLibres_Computadora1 = 1;
            procesadoresLibres_Computadora3 = 1;

            // Vaciamos las colas
            colaEventos.clear(); computadora1.clear(); computadora2.clear(); computadora3.clear();
            // Reseteamos algunos componentes de la interfaz
            interfaz.resetSimulacion();

            Estadisticas estadisticas = new Estadisticas(); // Instancia del objeto para estadísticas

            // Generamos los datos neecesarios para tener el primer evento y mensaje del sistema
            double tiempoServicio;
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
            cantidadMensajes++;
            /*
             * El bloque de código anterior genera la primera llegada al sistema
             */

            Evento primerEvento = new Evento(mensaje, 0, tipoEvento);
            colaEventos.add(primerEvento);

            // Ciclo que maneja una simulación única. Se detiene hasta alcanzar el tiempo máximo de una simulación
            while(clock < tiempoSimulacion * 60) {
                Evento eventoActual = colaEventos.poll(); // Sacamos el evento de la cola de eventos

                clock = eventoActual.getTiempoEvento();
                // Actualizamos el reloj al momento del evento actual, y lo actualizamos en la interfaz
                if(modoLento) {
                    interfaz.showTextoGUI("Reloj: " + round(clock));
                }
                interfaz.showClock(round(clock));
                interfaz.showEventoActual(eventoActual.getTipoEvento()); // Actualizamos el evento actual e la simulación

                switch (eventoActual.getTipoEvento()){
                    case 0: // Evento: Llegada a Computadora 2
                        tiempoServicio = 0;
                        if(P1_C2 == true || P2_C2 == true) { // revisamos si hay procesadores libres
                            if(P2_C2 == true) {
                                P2_C2 = false; // ocupamos el procesador 2 de C2
                                tipoEvento = 3; // Tipo de evento: C2 Libera P2
                                tiempoServicio = generadorRandom.uniforme(12,25); // Generamos el tiempo de servicio
                                eventoActual.getMensaje().sumarTiempoProcesamiento(tiempoServicio); // Sumamos el total de tiempo de servicio del mensaje
                                estadisticas.sumarTiempoEnP2_C2(tiempoServicio); // Sumamos al total de tiempo de servicio de las estadísticas
                            } else if (P1_C2 == true){
                                P1_C2 = false; // ocupamos el procesador 1 de C2
                                tipoEvento = 2; // Tipo de evento: C2 Libera P1
                                tiempoServicio = generadorRandom.uniforme(12,25); // Generamos el tiempo de servicio
                                eventoActual.getMensaje().sumarTiempoProcesamiento(tiempoServicio); // Sumamos el total de tiempo de servicio del mensaje
                                estadisticas.sumarTiempoEnP1_C2(tiempoServicio); // Sumamos al total de tiempo de servicio de las estadísticas
                            }
                            // Generamos el próximo evento dependiendo del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(eventoActual.getMensaje(), clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            eventoActual.getMensaje().setTiempoInicioCola(clock);
                            computadora2.add(eventoActual.getMensaje());
                        }
                        break;
                    case 1: // Evento: Llegada a la Computadora 3
                        if(procesadoresLibres_Computadora3 != 0) {
                            procesadoresLibres_Computadora3--; // ocupamos el procesador 1 de C3
                            tipoEvento = 4; // Tipo de evento: C3 Libera P1
                            tiempoServicio = generadorRandom.exponencial(4); // Generamos el tiempo de servicio
                            eventoActual.getMensaje().sumarTiempoProcesamiento(tiempoServicio);
                            estadisticas.sumarTiempoEnP1_C3(tiempoServicio);
                            // Generamos el próximo evento del procesador que se va a liberar, y lo agregams a la cola de eventos

                            proximoEvento = new Evento(eventoActual.getMensaje(), clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            eventoActual.getMensaje().setTiempoInicioCola(clock);
                            computadora3.add(eventoActual.getMensaje());
                        }

                        break;
                    case 2: // Evento: C2 libera a P1
                        if(!computadora2.isEmpty()) {
                            mensaje = computadora2.poll();
                            mensaje.sumarTiempoEnCola(clock - mensaje.getTiempoInicioCola());
                            tipoEvento = 2; // Tipo de evento: C2 Libera P1
                            tiempoServicio = generadorRandom.uniforme(12,25); // Generamos el tiempo de servicio
                            mensaje.sumarTiempoProcesamiento(tiempoServicio);
                            estadisticas.sumarTiempoEnP1_C2(tiempoServicio);

                            // Generamos el próximo evento del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(mensaje, clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            P1_C2 = true; // Liberamos el procesador
                        }

                        proximoEvento = new Evento(eventoActual.getMensaje(), clock + 20, 5);
                        colaEventos.add(proximoEvento);
                        break;
                    case 3: // C2 libera a P2
                        if(!computadora2.isEmpty()) {
                            mensaje = computadora2.poll();
                            mensaje.sumarTiempoEnCola(clock - mensaje.getTiempoInicioCola());
                            tipoEvento = 3; // Tipo de evento: C2 Libera P2
                            tiempoServicio = generadorRandom.uniforme(12,25); // Generamos el tiempo de servicio
                            mensaje.sumarTiempoProcesamiento(tiempoServicio);
                            estadisticas.sumarTiempoEnP2_C2(tiempoServicio);

                            // Generamos el próximo evento dependiendo del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(mensaje, clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            P2_C2 = true; // Liberamos el procesador
                        }

                        proximoEvento = new Evento(eventoActual.getMensaje(), clock + 20, 5);
                        colaEventos.add(proximoEvento);

                        break;
                    case 4: // Evento: C3 Libera a P1
                        if(!computadora3.isEmpty()) {
                            mensaje = computadora3.poll();
                            mensaje.sumarTiempoEnCola(clock - mensaje.getTiempoInicioCola());
                            tipoEvento = 4; // Tipo de evento: C3 Libera P1
                            tiempoServicio = generadorRandom.exponencial(4); // Generamos el tiempo de servicio
                            mensaje.sumarTiempoProcesamiento(tiempoServicio);
                            estadisticas.sumarTiempoEnP1_C3(tiempoServicio);

                            // Generamos el próximo del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(mensaje, clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            procesadoresLibres_Computadora3++; // Liberamos el procesador
                        }

                        // Revisamos si el mensaje va a ser rechazado o no una vez fue analizado
                        if (generadorRandom.getNextDouble() * 100 <= 80) {
                            /*
                             * Actualizamos las estadístcas ya que le mensaje va a ser rechazadp
                             */
                            numeroMensajesRechazados++;
                            estadisticas.sumarTiempoEnSistema(clock - eventoActual.getMensaje().getLlegadaSistema());
                            estadisticas.sumarTiempoEnCola(eventoActual.getMensaje().getTiempoEnCola());
                            estadisticas.sumarTiempoEnTransmision(eventoActual.getMensaje().getTiempoTansmision());
                            estadisticas.sumarTiempoTotalProcesamiento(eventoActual.getMensaje().getTiempoProcesamiento());
                            estadisticas.sumarTiempoProcesamientoRechazados(eventoActual.getMensaje().getTiempoProcesamiento());

                        } else { // Si no fue rechazado el mensaje, se procede a enviarlo a la Computadora 1
                            eventoActual.getMensaje().sumarTiempoTansmision(20);
                            proximoEvento = new Evento(eventoActual.getMensaje(), clock + 20, 6);
                            colaEventos.add(proximoEvento);
                        }

                        break;
                    case 5: // Evento: C1 recibe mensaje de C2
                        if(procesadoresLibres_Computadora1 == 1) {
                            procesadoresLibres_Computadora1--; // ocupamos el procesador 1 de C1
                            tipoEvento = 7; // Tipo de evento: C1 Libera P1
                            tiempoServicio = generadorRandom.exponencial(10); // Generamos el tiempo de servicio
                            eventoActual.getMensaje().sumarTiempoProcesamiento(tiempoServicio);
                            estadisticas.sumarTiempoEnP1_C1(tiempoServicio);

                            // Generamos el próximo evento del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(eventoActual.getMensaje(), clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            eventoActual.getMensaje().setTiempoInicioCola(clock);
                            computadora1.add(eventoActual.getMensaje());
                        }
                        break;

                    case 6: // Evento: C1 recibe mensaje de C3
                        if(procesadoresLibres_Computadora1 == 1) {
                            procesadoresLibres_Computadora1--; // ocupamos el procesador 1 de C1
                            tipoEvento = 7; // Tipo de evento: C1 Libera P1
                            tiempoServicio = generadorRandom.exponencial(10); // Generamos el tiempo de servicio
                            eventoActual.getMensaje().sumarTiempoProcesamiento(tiempoServicio);
                            estadisticas.sumarTiempoEnP1_C1(tiempoServicio);

                            // Generamos el próximo evento del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(eventoActual.getMensaje(), clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            eventoActual.getMensaje().setTiempoInicioCola(clock);
                            computadora1.add(eventoActual.getMensaje());
                        }
                        break;

                    case 7: // Evento: C1 libera P1
                        if(!computadora1.isEmpty()) {
                            mensaje = computadora1.poll();
                            mensaje.sumarTiempoEnCola(clock - mensaje.getTiempoInicioCola());
                            tipoEvento = 7; // Tipo de evento: C1 Libera P1
                            tiempoServicio = generadorRandom.exponencial(10); // Generamos el tiempo de servicio
                            mensaje.sumarTiempoProcesamiento(tiempoServicio);
                            estadisticas.sumarTiempoEnP1_C1(tiempoServicio);

                            // Generamos el próximo del procesador que se va a liberar, y lo agregams a la cola de eventos
                            proximoEvento = new Evento(mensaje, clock + tiempoServicio, tipoEvento);
                            colaEventos.add(proximoEvento);
                        } else {
                            procesadoresLibres_Computadora1++;
                        }

                        if(eventoActual.getMensaje().getComputadoraInicio() == 2) {
                            if (generadorRandom.getNextDouble() * 100 <= 20) {
                                cantidadVecesDevuelto++;
                                eventoActual.getMensaje().sumarTiempoTansmision(3);
                                proximoEvento = new Evento(eventoActual.getMensaje(), clock + 3, 0);
                                colaEventos.add(proximoEvento);
                            } else {
                                /*
                                 * Actualizamos estadísticas
                                 */
                                estadisticas.sumarTiempoEnSistema(clock - eventoActual.getMensaje().getLlegadaSistema());
                                estadisticas.sumarTiempoEnCola(eventoActual.getMensaje().getTiempoEnCola());
                                estadisticas.sumarTiempoEnTransmision(eventoActual.getMensaje().getTiempoTansmision());
                                estadisticas.sumarTiempoTotalProcesamiento(eventoActual.getMensaje().getTiempoProcesamiento());
                                numeroMensajesFinalizados++;
                            }
                        } else {
                            if (generadorRandom.getNextDouble() * 100 <= 50) {
                                cantidadVecesDevuelto++;
                                eventoActual.getMensaje().sumarTiempoTansmision(3);
                                proximoEvento = new Evento(eventoActual.getMensaje(), clock + 3, 1);
                                colaEventos.add(proximoEvento);
                            } else {
                                /*
                                 * Actualizamos estadísticas
                                 */
                                estadisticas.sumarTiempoEnSistema(clock - eventoActual.getMensaje().getLlegadaSistema());
                                estadisticas.sumarTiempoEnCola(eventoActual.getMensaje().getTiempoEnCola());
                                estadisticas.sumarTiempoEnTransmision(eventoActual.getMensaje().getTiempoTansmision());
                                estadisticas.sumarTiempoTotalProcesamiento(eventoActual.getMensaje().getTiempoProcesamiento());
                                numeroMensajesFinalizados++;
                            }
                        }
                        break;
                }
                this.generarLlegada(); // Generamos ls siguiente llgada al sistema
                if(modoLento) { // Revisamos si es necesario hacer la pausa entre eventos
                    try {
                        TimeUnit.SECONDS.sleep(segundosModoLento); // El hilo se duerme por el tiempo establecido en modo lento
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                }
                // Actualizamos los datos de la simulación en la interfaz
                interfaz.showInformacionLq(computadora1.size(), computadora2.size(), computadora3.size());
                interfaz.showInformacionMensajes(numeroMensajesFinalizados, numeroMensajesRechazados);
                interfaz.showInformacionProcesadores(procesadoresLibres_Computadora1, P1_C2, P2_C2, procesadoresLibres_Computadora3);

            }
            /*
             * Mostrar estadisticas por simulación
             */
            // El objeto estadísticas recibe los datos necesarios para hacer las estadísticas de la simulación
            estadisticas.hacerEstadisticas(clock, numeroMensajesRechazados, cantidadMensajes, cantidadVecesDevuelto);
            interfaz.showTextoGUI(" - Estadísticas para la simulación " + sim + 1);
            interfaz.showTextoGUI("Porcentaje de tiempo en P1 de Computadora 1: " + this.round(estadisticas.getPorcetanjeP1_C1()) + " %");
            interfaz.showTextoGUI("Porcentaje de tiempo en P1 de Computadora 2: " + this.round(estadisticas.getPorcetanjeP1_C2()) + " %");
            interfaz.showTextoGUI("Porcentaje de tiempo en P2 de Computadora 2: " + this.round(estadisticas.getPorcetanjeP2_C2()) + " %");
            interfaz.showTextoGUI("Porcentaje de tiempo en P1 de Computadora 3: " + this.round(estadisticas.getPorcetanjeP1_C3()) + " %");
            interfaz.showTextoGUI("Porcentaje de tiempo en mensajes rechazados: " + this.round(estadisticas.getPorcentajeTiempoRechazo()) + " %");
            interfaz.showTextoGUI("Porcentaje de mensajes rechazados: " + this.round(estadisticas.getPorcentajeMensajesRechazados()) + " %");
            interfaz.showTextoGUI("Promedio de mensajes devueltos: " + this.round(estadisticas.getPromedioMensajeDevuelto()));
            interfaz.showTextoGUI("Porcentaje de tiempo en transmisión: " + this.round(estadisticas.getTiempoEnTransmision()) + " %");
            interfaz.showTextoGUI("Tiempo promedio en el sistema: " + this.round(estadisticas.getTiempo_W()) + " segundos");
            interfaz.showTextoGUI("Tiempo promedio en cola: " + this.round(estadisticas.getTiempo_WQ()) + " segundos");
            interfaz.showTextoGUI("Porcentaje de tiempo usado en procesamiento: " + this.round(estadisticas.getPorcentajeTiempo_WS()) + " %");
            // Agregamos el objeto a la cola de estadísticas que luego se utilizará para las globales y los intervalos
            colaEstadisticas.add(estadisticas);
        }
        /*
         * Estadísticas globales de las n simulaciones
         */
        double porcetanjeP1_C1 = 0;
        double porcetanjeP1_C2 = 0;
        double porcetanjeP2_C2 = 0;
        double porcetanjeP1_C3 = 0;
        double porcentajeTiempoRechazo = 0;
        double porcentajeMensajesRechazados = 0;
        double tiempo_W = 0;
        double promedioMensajeDevuelto = 0;
        double tiempo_WQ = 0;
        double tiempoTransmision = 0;
        double tiempo_WS = 0;

        // Recorremos las estadísticas de las 10 simulaciones, y hacemos una sumatoria para cada estadística
        for(int i = 0; i < numSimulaciones; i++)  {
            Estadisticas estadisticas = colaEstadisticas.get(i);

            porcetanjeP1_C1 += estadisticas.getPorcetanjeP1_C1();
            porcetanjeP1_C2 += estadisticas.getPorcetanjeP1_C2();
            porcetanjeP2_C2 += estadisticas.getPorcetanjeP2_C2();
            porcetanjeP1_C3 += estadisticas.getPorcetanjeP1_C3();
            porcentajeTiempoRechazo += estadisticas.getPorcentajeTiempoRechazo();
            porcentajeMensajesRechazados += estadisticas.getPorcentajeMensajesRechazados();
            tiempo_W += estadisticas.getTiempo_W();
            promedioMensajeDevuelto += estadisticas.getPromedioMensajeDevuelto();
            tiempo_WQ += estadisticas.getTiempo_WQ();
            tiempoTransmision += estadisticas.getTiempoEnTransmision();
            tiempo_WS += estadisticas.getPorcentajeTiempo_WS();
        }

        // Dividimos cada estadística entre el número de simulaciones para promediar las n simulaciones
        porcetanjeP1_C1 = porcetanjeP1_C1 / numSimulaciones;
        porcetanjeP1_C2 = porcetanjeP1_C2 / numSimulaciones;
        porcetanjeP2_C2 = porcetanjeP2_C2 / numSimulaciones;
        porcetanjeP1_C3 = porcetanjeP1_C3 / numSimulaciones;
        porcentajeTiempoRechazo = porcentajeTiempoRechazo / numSimulaciones;
        porcentajeMensajesRechazados = porcentajeMensajesRechazados / numSimulaciones;
        tiempo_W = tiempo_W / numSimulaciones;
        promedioMensajeDevuelto = promedioMensajeDevuelto / numSimulaciones;
        tiempo_WQ = tiempo_WQ / numSimulaciones;
        tiempoTransmision = tiempoTransmision / numSimulaciones;
        tiempo_WS = tiempo_WS / numSimulaciones;

        // Mostramos en la interfaz las estadísticas calculadas, y redondeamos a 4 decimales
        interfaz.showTextoGUI("\n - Estadísticas globales para las " + numSimulaciones +  " simulaciones");
        interfaz.showTextoGUI("Porcentaje de tiempo en P1 de Computadora 1: " + this.round(porcetanjeP1_C1) + " %");
        interfaz.showTextoGUI("Porcentaje de tiempo en P1 de Computadora 2: " + this.round(porcetanjeP1_C2) + " %");
        interfaz.showTextoGUI("Porcentaje de tiempo en P2 de Computadora 2: " + this.round(porcetanjeP2_C2) + " %");
        interfaz.showTextoGUI("Porcentaje de tiempo en P1 de Computadora 3: " + this.round(porcetanjeP1_C3) + " %");
        interfaz.showTextoGUI("Porcentaje de tiempo en mensajes rechazados: " + this.round(porcentajeTiempoRechazo) + " %");
        interfaz.showTextoGUI("Porcentaje de mensajes rechazados: " + this.round(porcentajeMensajesRechazados) + " %");
        interfaz.showTextoGUI("Promedio de mensajes devueltos: " + this.round(promedioMensajeDevuelto));
        interfaz.showTextoGUI("Porcentaje de tiempo en transmisión: " + this.round(tiempoTransmision) + " %");
        interfaz.showTextoGUI("Tiempo promedio en el sistema: " + this.round(tiempo_W) + " segundos");
        interfaz.showTextoGUI("Tiempo promedio en cola: " + this.round(tiempo_WQ) + " segundos");
        interfaz.showTextoGUI("Porcentaje de tiempo usado en procesamiento: " + this.round(tiempo_WS) + " %");

        /*
         * Estadísticas: Intervalo de confianza
         * Aquí calculamos los intervalos de confianza para cada una de las estadísticas
         */
        double intervalo_porcetanjeP1_C1 = 0;
        double intervalo_porcetanjeP1_C2 = 0;
        double intervalo_porcetanjeP2_C2 = 0;
        double intervalo_porcetanjeP1_C3 = 0;
        double intervalo_porcentajeTiempoRechazo = 0;
        double intervalo_porcentajeMensajesRechazados = 0;
        double intervalo_tiempo_W = 0;
        double intervalo_promedioMensajeDevuelto = 0;
        double intervalo_tiempo_WQ = 0;
        double intervalo_tiempoTransmision = 0;
        double intervalo_tiempo_WS = 0;

        // Hacemos la sumatoria de las estadísticas de las n simulaciones
        for(int i = 0; i < numSimulaciones; i++)  {
            Estadisticas estadisticas = colaEstadisticas.get(i);

            intervalo_porcetanjeP1_C1 += estadisticas.getPorcetanjeP1_C1() - porcetanjeP1_C1;
            intervalo_porcetanjeP1_C2 += estadisticas.getPorcetanjeP1_C2() - porcetanjeP1_C2;
            intervalo_porcetanjeP2_C2 += estadisticas.getPorcetanjeP2_C2() - porcetanjeP2_C2;
            intervalo_porcetanjeP1_C3 += estadisticas.getPorcetanjeP1_C3() - porcetanjeP1_C3;
            intervalo_porcentajeTiempoRechazo += estadisticas.getPorcentajeTiempoRechazo() - porcentajeTiempoRechazo;
            intervalo_porcentajeMensajesRechazados += estadisticas.getPorcentajeMensajesRechazados() - porcentajeMensajesRechazados;
            intervalo_tiempo_W += estadisticas.getTiempo_W() - tiempo_W;
            intervalo_promedioMensajeDevuelto += estadisticas.getPromedioMensajeDevuelto() - promedioMensajeDevuelto;
            intervalo_tiempo_WQ += estadisticas.getTiempo_WQ() - tiempo_WQ;
            intervalo_tiempoTransmision += estadisticas.getTiempoEnTransmision() - tiempoTransmision;
            intervalo_tiempo_WS += estadisticas.getPorcentajeTiempo_WS() - tiempo_WS;
        }
        interfaz.showTextoGUI("\n - Intervalos de confianza para las estadísticas globales");

        /*
         * Sacamos los intervalos de confianza
         */
        intervalo_porcetanjeP1_C1 = Math.pow(intervalo_porcetanjeP1_C1, 2)/numSimulaciones-1;
        intervalo_porcetanjeP1_C1 = 1.96 * Math.pow(intervalo_porcetanjeP1_C1/numSimulaciones, 1/2);

        intervalo_porcetanjeP1_C2 = Math.pow(intervalo_porcetanjeP1_C2, 2)/numSimulaciones-1;
        intervalo_porcetanjeP1_C2 = 1.96 * Math.pow(intervalo_porcetanjeP1_C2/numSimulaciones, 1/2);

        intervalo_porcetanjeP2_C2 = Math.pow(intervalo_porcetanjeP2_C2, 2)/numSimulaciones-1;
        intervalo_porcetanjeP2_C2 = 1.96 * Math.pow(intervalo_porcetanjeP2_C2/numSimulaciones, 1/2);

        intervalo_porcetanjeP1_C3 = Math.pow(intervalo_porcetanjeP1_C3, 2)/numSimulaciones-1;
        intervalo_porcetanjeP1_C3 = 1.96 * Math.pow(intervalo_porcetanjeP1_C3/numSimulaciones, 1/2);

        intervalo_porcentajeTiempoRechazo = Math.pow(intervalo_porcentajeTiempoRechazo, 2)/numSimulaciones-1;
        intervalo_porcentajeTiempoRechazo = 1.96 * Math.pow(intervalo_porcentajeTiempoRechazo/numSimulaciones, 1/2);

        intervalo_porcentajeMensajesRechazados = Math.pow(intervalo_porcentajeMensajesRechazados, 2)/numSimulaciones-1;
        intervalo_porcentajeMensajesRechazados = 1.96 * Math.pow(intervalo_porcentajeMensajesRechazados/numSimulaciones, 1/2);

        intervalo_tiempo_W = Math.pow(intervalo_tiempo_W, 2)/numSimulaciones-1;
        intervalo_tiempo_W = 1.96 * Math.pow(intervalo_tiempo_W/numSimulaciones, 1/2);

        intervalo_promedioMensajeDevuelto = Math.pow(intervalo_promedioMensajeDevuelto, 2)/numSimulaciones-1;
        intervalo_promedioMensajeDevuelto = 1.96 * Math.pow(intervalo_promedioMensajeDevuelto/numSimulaciones, 1/2);

        intervalo_tiempo_WQ = Math.pow(intervalo_tiempo_WQ, 2)/numSimulaciones-1;
        intervalo_tiempo_WQ = 1.96 * Math.pow(intervalo_tiempo_WQ/numSimulaciones, 1/2);

        intervalo_tiempoTransmision = Math.pow(intervalo_tiempoTransmision, 2)/numSimulaciones-1;
        intervalo_tiempoTransmision = 1.96 * Math.pow(intervalo_tiempoTransmision/numSimulaciones, 1/2);

        intervalo_tiempo_WS = Math.pow(intervalo_tiempo_WS, 2)/numSimulaciones-1;
        intervalo_tiempo_WS = 1.96 * Math.pow(intervalo_tiempo_WS/numSimulaciones, 1/2);

        // Mostramos los invervalos de confianza en la interfaz
        interfaz.showTextoGUI("Porcentaje de tiempo en P1 de Computadora 1: [" + this.round(porcetanjeP1_C1 - intervalo_porcetanjeP1_C1) + " , " + this.round(porcetanjeP1_C1 + intervalo_porcetanjeP1_C1) + "]");
        interfaz.showTextoGUI("Porcentaje de tiempo en P1 de Computadora 2: [" + this.round(porcetanjeP1_C2 - intervalo_porcetanjeP1_C2) + " , " + this.round(porcetanjeP1_C2 + intervalo_porcetanjeP1_C2) + "]");
        interfaz.showTextoGUI("Porcentaje de tiempo en P2 de Computadora 2: [" + this.round(porcetanjeP2_C2 - intervalo_porcetanjeP2_C2) + " , " + this.round(porcetanjeP2_C2 + intervalo_porcetanjeP2_C2) + "]");
        interfaz.showTextoGUI("Porcentaje de tiempo en P1 de Computadora 3: [" + this.round(porcetanjeP1_C3 - intervalo_porcetanjeP1_C3) + " , " + this.round(porcetanjeP1_C3 + intervalo_porcetanjeP1_C3) + "]");
        interfaz.showTextoGUI("Porcentaje de tiempo en mensajes rechazados: [" + this.round(porcentajeTiempoRechazo - intervalo_porcentajeTiempoRechazo) + " , " + this.round(porcentajeTiempoRechazo + intervalo_porcentajeTiempoRechazo) + "]");
        interfaz.showTextoGUI("Porcentaje de mensajes rechazados: [" + this.round(intervalo_porcentajeMensajesRechazados - intervalo_porcentajeMensajesRechazados) + " , " + this.round(intervalo_porcentajeMensajesRechazados + intervalo_porcentajeMensajesRechazados) + "]");
        interfaz.showTextoGUI("Promedio de mensajes devueltos: [" + this.round(promedioMensajeDevuelto - intervalo_promedioMensajeDevuelto) + " , " + this.round(promedioMensajeDevuelto + intervalo_promedioMensajeDevuelto) + "]");
        interfaz.showTextoGUI("Porcentaje de tiempo en transmisión: [" + this.round(tiempoTransmision - intervalo_tiempoTransmision) + " , " + this.round(tiempoTransmision + intervalo_tiempoTransmision) + "]");
        interfaz.showTextoGUI("Tiempo promedio en el sistema: [" + this.round(tiempo_W - intervalo_tiempo_W) + " , " + this.round(tiempo_W + intervalo_tiempo_W) + "]");
        interfaz.showTextoGUI("Tiempo promedio en cola: [" + this.round(tiempo_WQ - intervalo_tiempo_WQ) + " , " + this.round(tiempo_WQ + intervalo_tiempo_WQ) + "]");
        interfaz.showTextoGUI("Porcentaje de tiempo usado en procesamiento: [" + this.round(tiempo_WS - intervalo_tiempo_WS) + " , " + this.round(tiempo_WS + intervalo_tiempo_WS) + "]");
        interfaz.activarBotonRegreso();
    }

    /**
     * Método para redondear a 4 decimales
     * @param number número a redondear
     * @return número redondeado a 4 decimales
     */
    public double round(double number){
        number = Math.round(number*10000);
        return number/10000;
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

    public void setModoLento(boolean modoLento) {
        this.modoLento = modoLento;
    }

    public void setSegundosModoLento(int segundosModoLento) {
        this.segundosModoLento = segundosModoLento;
    }

    /**
     * Main del programa
     * Aquí se genera el hilo de la interfaz
     * @param args
     */
    public static void main(String args[]) {
        // Sets the GUI with a Nimbus look
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Creates the GUI form and displays it
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
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


