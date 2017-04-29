import java.util.*;

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
    private ArrayList<Estadisticas> colaEstadisticas; // Cola con las instancias de estadisticas de las n simulaciones
    private GeneradorRandom generadorRandom; // Instancia del objeto generador de números aleatorios
    private PriorityQueue<Evento> colaEventos; // Cola de eventos del sistema
    private int numeroMensajesRechazados;
    private int numeroMensajesFinalizados;
    private int cantidadMensajes;
    private int cantidadVecesDevuelto;

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
        cantidadMensajes++;

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
        cantidadMensajes++;

        Evento primerEvento = new Evento(mensaje, 0, tipoEvento);
        colaEventos.add(primerEvento);

        for(int sim = 0; sim < numSimulaciones; sim++) {
            Estadisticas estadisticas = new Estadisticas(); // Instancia del objeto para estadísticas
            while(clock < tiempoSimulacion * 60) {
                Evento eventoActual = colaEventos.poll();

                clock = eventoActual.getTiempoEvento();

                switch (eventoActual.getTipoEvento()){
                    case 0: // Llegada a Computadora 2
                        tiempoServicio = 0;
                        if(procesadoresLibres_Computadora2 != 0) {
                            if(procesadoresLibres_Computadora2 == 2) {
                                procesadoresLibres_Computadora2--; // ocupamos el procesador 2 de C2
                                tipoEvento = 3; // Tipo de evento: C2 Libera P2
                                tiempoServicio = generadorRandom.uniforme(12,25);
                                eventoActual.getMensaje().sumarTiempoProcesamiento(tiempoServicio);
                                estadisticas.sumarTiempoEnP2_C2(tiempoServicio);
                            } else if (procesadoresLibres_Computadora2 == 1){
                                procesadoresLibres_Computadora2--; // ocupamos el procesador 1 de C2
                                tipoEvento = 2; // Tipo de evento: C2 Libera P1
                                tiempoServicio = generadorRandom.uniforme(12,25);
                                eventoActual.getMensaje().sumarTiempoProcesamiento(tiempoServicio);
                                estadisticas.sumarTiempoEnP1_C2(tiempoServicio);
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
                    case 2: // C2 lbera a P1
                        if(!computadora2.isEmpty()) {
                            mensaje = computadora2.poll();
                            mensaje.sumarTiempoEnCola(clock - mensaje.getTiempoInicioCola());
                            tipoEvento = 2; // Tipo de evento: C2 Libera P1
                            tiempoServicio = generadorRandom.uniforme(12,25);
                            mensaje.sumarTiempoProcesamiento(tiempoServicio);
                            estadisticas.sumarTiempoEnP1_C2(tiempoServicio);

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
                            mensaje.sumarTiempoProcesamiento(tiempoServicio);
                            estadisticas.sumarTiempoEnP2_C2(tiempoServicio);

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
                            mensaje.sumarTiempoProcesamiento(tiempoServicio);
                            estadisticas.sumarTiempoEnP1_C3(tiempoServicio);

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
                            estadisticas.sumarTiempoEnSistema(clock - eventoActual.getMensaje().getLlegadaSistema());
                            estadisticas.sumarTiempoEnCola(eventoActual.getMensaje().getTiempoEnCola());
                            estadisticas.sumarTiempoEnTransmision(eventoActual.getMensaje().getTiempoTansmision());
                            estadisticas.sumarTiempoTotalProcesamiento(eventoActual.getMensaje().getTiempoProcesamiento());
                            estadisticas.sumarTiempoProcesamientoRechazados(eventoActual.getMensaje().getTiempoProcesamiento());

                        } else {
                            eventoActual.getMensaje().sumarTiempoTansmision(20);
                            proximoEvento = new Evento(eventoActual.getMensaje(), clock + 20, 5);
                            colaEventos.add(proximoEvento);
                        }

                        break;
                    case 5: // C1 recibe mensaje de C2 o de C3
                        if(procesadoresLibres_Computadora1 != 0) {
                            procesadoresLibres_Computadora1--; // ocupamos el procesador 1 de C1
                            tipoEvento = 6; // Tipo de evento: C1 Libera P1
                            tiempoServicio = generadorRandom.exponencial(10);
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
                    case 6: // C1 libera P1
                        if(!computadora1.isEmpty()) {
                            mensaje = computadora1.poll();
                            mensaje.sumarTiempoEnCola(clock - mensaje.getTiempoInicioCola());
                            tipoEvento = 6; // Tipo de evento: C1 Libera P1
                            tiempoServicio = generadorRandom.exponencial(10);
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
                                /**
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
                                /**
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
            }
            /**
             * Mostrar estadisticas por simulación
             */
            estadisticas.hacerEstadisticas(clock, numeroMensajesRechazados, cantidadMensajes, cantidadVecesDevuelto);
            colaEstadisticas.add(estadisticas);
        }
        /**
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

        /**
         * Estadísticas: Intervalo de confianza
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

    }

    public double round(double number){
        number = Math.round(number*1000);
        return number/1000;
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


