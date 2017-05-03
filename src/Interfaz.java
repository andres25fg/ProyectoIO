import javax.swing.JOptionPane;

/**
 * Clase Interfaz
 *
 * Contiene la definición de la interfaz de usuario de la aplicación
 *
 * Jose Alvarado González
 * Andrés González Caldas
 */
public class Interfaz extends javax.swing.JFrame {
    private boolean banderaModoLento; // Bandera para saber si se va a usar el modo lento
    private int segundosModoLento; // Cantidad de segundos de espera para el modo lento
    private int numSims; // Número total de simulaciones
    private int tiempoSimulacion; // tiempo máximo de una simulación
    private Simulacion simulacion; // Instancia del objeto Simulación

    /**
     * Constructor
     */
    public Interfaz() {
        initComponents(); // Inicializamos los componentes de la GUI
        this.setParamPanelInvisible(); // Ponemos invisibles algunos de los componentes
        this.simPanel.setVisible(false);
    }

    /**
     * Initiates the GUI components
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenu = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        paramMenu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        minutosSimulacion_paramPanel = new javax.swing.JTextField();
        modoLentoCasilla = new javax.swing.JCheckBox();
        segundosModoLento_paramPanel = new javax.swing.JTextField();
        slowModeLabel = new javax.swing.JLabel();
        nextSimButton = new javax.swing.JButton();
        numSimulaciones_paramPanel = new javax.swing.JTextField();
        helpButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        simPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        simText = new javax.swing.JTextArea();
        starSimButton = new javax.swing.JButton();
        returnButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        slowModeLabel_simPanel = new javax.swing.JLabel();
        segundosSimulacion_simPanel = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        numeroSimulaciones_simPanel = new javax.swing.JTextField();
        segundosModoLento_simPanel = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        comp1_p1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        comp2_p1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        comp3_p1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        comp2_p2 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        colaComputadora1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        colaComputadora3 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        colaComputadora2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        mensajesRechazados = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        mensajesFinalizados = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        clock = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        eventoActual = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        numeroSim = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto Simulación - Investigación de Operaciones");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setName("frame"); // NOI18N

        mainMenu.setBackground(new java.awt.Color(255, 255, 255));

        startButton.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        startButton.setText("Iniciar");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel1.setText("Proyecto de Simulación CI-1453");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel11.setText("Simulación por eventos discretos");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("Integrantes:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setText("B12867 - Andrés González Caldas");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setText("B00286 - Jose Alvarado González");

        javax.swing.GroupLayout mainMenuLayout = new javax.swing.GroupLayout(mainMenu);
        mainMenu.setLayout(mainMenuLayout);
        mainMenuLayout.setHorizontalGroup(
                mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuLayout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addGroup(mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(mainMenuLayout.createSequentialGroup()
                                                .addGroup(mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(809, Short.MAX_VALUE))
                                        .addGroup(mainMenuLayout.createSequentialGroup()
                                                .addGroup(mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(249, 249, 249))))
        );
        mainMenuLayout.setVerticalGroup(
                mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainMenuLayout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addGroup(mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(mainMenuLayout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(120, 120, 120)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(120, Short.MAX_VALUE))
        );

        paramMenu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("(NOTA: Este valor lo pasamos a segundos)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel4.setText("Parámetros de la simulación");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Número de simulaciones:");

        minutosSimulacion_paramPanel.setText("1000");

        modoLentoCasilla.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        modoLentoCasilla.setText("  Simulación en modo lento (Usar esta opción si desea que la simulación corra lento)");
        modoLentoCasilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoLentoCasillaActionPerformed(evt);
            }
        });

        segundosModoLento_paramPanel.setText("0");

        slowModeLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        slowModeLabel.setText("Cantidad de segundos reales entre eventos:");

        nextSimButton.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        nextSimButton.setText("Siguiente");
        nextSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextSimButtonActionPerformed(evt);
            }
        });

        numSimulaciones_paramPanel.setText("1");

        helpButton.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/question.png"))); // NOI18N
        helpButton.setText("Ayuda");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Cantidad de máxima de minutos por simulación:");

        javax.swing.GroupLayout paramMenuLayout = new javax.swing.GroupLayout(paramMenu);
        paramMenu.setLayout(paramMenuLayout);
        paramMenuLayout.setHorizontalGroup(
                paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(paramMenuLayout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
                                                .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(88, 88, 88)
                                                .addComponent(nextSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36))
                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                                .addComponent(slowModeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(60, 60, 60)
                                                                .addComponent(segundosModoLento_paramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(modoLentoCasilla)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(61, 61, 61)
                                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(minutosSimulacion_paramPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                                                        .addComponent(numSimulaciones_paramPanel))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        paramMenuLayout.setVerticalGroup(
                paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(paramMenuLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nextSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(144, 144, 144)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numSimulaciones_paramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(minutosSimulacion_paramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                .addComponent(modoLentoCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(segundosModoLento_paramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(7, 7, 7))
                                        .addComponent(slowModeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(155, Short.MAX_VALUE))
        );

        simPanel.setBackground(new java.awt.Color(255, 255, 255));

        simText.setEditable(false);
        simText.setColumns(20);
        simText.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        simText.setRows(5);
        jScrollPane2.setViewportView(simText);

        starSimButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        starSimButton.setText("Iniciar");
        starSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                starSimButtonActionPerformed(evt);
            }
        });

        returnButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        returnButton.setText("Regresar");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Cantidad de máxima de segundos por simulación:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setText("Parámetros de la simulación");

        slowModeLabel_simPanel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        slowModeLabel_simPanel.setText("Cantidad de segundos entre eventos (Modo lento) :");

        segundosSimulacion_simPanel.setEditable(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Número de simulaciones:");

        numeroSimulaciones_simPanel.setEditable(false);

        segundosModoLento_simPanel.setEditable(false);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setText("Estado de los procesadores");

        comp1_p1.setEditable(false);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Computadora 1 - Procesador 1");

        comp2_p1.setEditable(false);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Computadora 2 - Procesador 1");

        comp3_p1.setEditable(false);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Computadora 3 - Procesador 3");

        comp2_p2.setEditable(false);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Computadora 2 - Procesador 2");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel29.setText("Estado de las colas de mensajes");

        colaComputadora1.setEditable(false);
        colaComputadora1.setText("0");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Cola de mensajes - Computadora 1");

        colaComputadora3.setEditable(false);
        colaComputadora3.setText("0");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Cola de mensajes - Computadora 3");

        colaComputadora2.setEditable(false);
        colaComputadora2.setText("0");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Cola de mensajes - Computadora 2");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        mensajesRechazados.setEditable(false);
        mensajesRechazados.setText("0");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("Mensajes rechazados:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel30.setText("Estado del sistema");

        mensajesFinalizados.setEditable(false);
        mensajesFinalizados.setText("0");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Mensajes finalizados:");

        clock.setEditable(false);
        clock.setText("0");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("Reloj (segundos):");

        eventoActual.setEditable(false);

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("Evento actual:");

        numeroSim.setEditable(false);
        numeroSim.setText("0");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setText("Simulación número:");

        javax.swing.GroupLayout simPanelLayout = new javax.swing.GroupLayout(simPanel);
        simPanel.setLayout(simPanelLayout);
        simPanelLayout.setHorizontalGroup(
                simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(simPanelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simPanelLayout.createSequentialGroup()
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jSeparator2)
                                                                        .addComponent(jSeparator1)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap())
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(comp1_p1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(comp2_p1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(comp3_p1))
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(comp2_p2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(61, 61, 61))
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(colaComputadora1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(72, 72, 72)
                                                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(colaComputadora3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(colaComputadora2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel16)
                                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(jLabel15)
                                                                                                        .addComponent(slowModeLabel_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(jLabel22))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(segundosModoLento_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(segundosSimulacion_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(numeroSimulaciones_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                        .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, simPanelLayout.createSequentialGroup()
                                                                                                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                        .addComponent(numeroSim))
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, simPanelLayout.createSequentialGroup()
                                                                                                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                        .addComponent(mensajesFinalizados))
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, simPanelLayout.createSequentialGroup()
                                                                                                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                        .addComponent(mensajesRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                                .addGap(0, 57, Short.MAX_VALUE))
                                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(eventoActual))))
                                                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap())))
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(starSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(97, 97, 97)
                                                .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(73, 73, 73))))
        );
        simPanelLayout.setVerticalGroup(
                simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(simPanelLayout.createSequentialGroup()
                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(starSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(47, 47, 47)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(mensajesRechazados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(mensajesFinalizados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(numeroSim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(22, 22, 22)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(eventoActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(numeroSimulaciones_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(segundosSimulacion_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(slowModeLabel_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(segundosModoLento_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(21, 21, 21)
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(comp1_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(comp3_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(comp2_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(comp2_p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(27, 27, 27)
                                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(colaComputadora3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(colaComputadora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(9, 9, 9)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(colaComputadora2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(paramMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(simPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(paramMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(simPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.mainMenu.setVisible(false);
        this.setParamPanelVisible();
    }

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showOptionDialog(null,"Digite los parámetros (valores numéricos) que desea utilizar en la simulación."
                + "\n - En el primer espacio ingrese la cantidad de simulaciones que desea correr."
                + "\n - En el segundo espacio ingrese la cantidad máxima en minutos por simulación. \n(NOTA: Para efectos de la simulación y datos en pantalla, este valor lo pasamos a segundos)"
                + "\n - Si desea utilizar la simulación en modo lento, marque la casilla y procesa a ingresar los segundos de espera entre eventos", "Ayuda", JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Continuar"},"default");
    }

    private void nextSimButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(banderaModoLento == false) {
            this.setSegundosModoLento(0);
        } else {
            this.setSegundosModoLento(this.parseInt(this.segundosModoLento_paramPanel.getText()));
        }
        this.setNumSims(this.parseInt(this.numSimulaciones_paramPanel.getText()));
        this.setTiempoSimulacion(this.parseInt(this.minutosSimulacion_paramPanel.getText()));

        this.paramMenu.setVisible(false);
        this.setSimPanelVisible();
    }

    private void modoLentoCasillaActionPerformed(java.awt.event.ActionEvent evt) {
        if(banderaModoLento == true) {
            banderaModoLento = false;
            this.slowModeLabel.setVisible(false);
            this.segundosModoLento_paramPanel.setVisible(false);
        } else {
            banderaModoLento = true;
            this.slowModeLabel.setVisible(true);
            this.segundosModoLento_paramPanel.setVisible(true);
        }
    }

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.simPanel.setVisible(false);
        this.paramMenu.setVisible(true);
        this.setParamPanelVisible();
        this.simText.setText("");
    }

    private void starSimButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.starSimButton.setEnabled(false);
        this.returnButton.setEnabled(false);
        simulacion = new Simulacion(numSims, tiempoSimulacion, banderaModoLento, segundosModoLento, this);
    }

    public void showTextoGUI(String texto) {
        this.simText.setText(simText.getText() + "\n" + texto);
        simText.setCaretPosition(simText.getDocument().getLength());
    }

    public void showInformacionProcesadores(int procesadoresComputadora1, boolean P1_C2, boolean P2_C2, int procesadoresComputadora3) {
        if(procesadoresComputadora1 == 1) {
            this.comp1_p1.setText("Libre");
        } else {
            this.comp1_p1.setText("Ocupado");
        }

        if(P1_C2 == true) {
            this.comp2_p1.setText("Libre");
        } else {
            this.comp2_p1.setText("Ocupado");
        }

        if(P2_C2 == true) {
            this.comp2_p2.setText("Libre");
        } else {
            this.comp2_p2.setText("Ocupado");
        }

        if(procesadoresComputadora3 == 1) {
            this.comp3_p1.setText("Libre");
        } else {
            this.comp3_p1.setText("Ocupado");
        }
    }

    public void showInformacionLq(int colaComputadora1, int colaComputadora2, int colaComputadora3) {
        this.colaComputadora1.setText(Integer.toString(colaComputadora1));
        this.colaComputadora2.setText(Integer.toString(colaComputadora2));
        this.colaComputadora3.setText(Integer.toString(colaComputadora3));
    }

    public void showInformacionMensajes(int finalizados, int rechazados) {
        this.mensajesFinalizados.setText(Integer.toString(finalizados));
        this.mensajesRechazados.setText(Integer.toString(rechazados));
    }

    public void showClock(double clock) {
        this.clock.setText(Double.toString(clock));
    }

    public void showEventoActual(int eventoActual) {
        switch (eventoActual) {
            case 0:
                this.eventoActual.setText("Llegada a Computadora 2");
                break;
            case 1:
                this.eventoActual.setText("Llegada a Computadora 3");
                break;
            case 2:
                this.eventoActual.setText("Computadora 2 libera P1");
                break;
            case 3:
                this.eventoActual.setText("Computadora 2 libera P2");
                break;
            case 4:
                this.eventoActual.setText("Computadora 3 libera P1");
                break;
            case 5:
                this.eventoActual.setText("C1 recibe mensaje de C2");
                break;
            case 6:
                this.eventoActual.setText("C1 recibe mensaje de C3");
                break;
            case 7:
                this.eventoActual.setText("C1 libera P1");
                break;
        }
    }

    public void showNumeroSimulacion(int numeroSim) {
        this.numeroSim.setText(Integer.toString(numeroSim));
    }

    public void resetSimulacion() {
        this.comp1_p1.setText("Libre");
        this.comp3_p1.setText("Libre");
        this.comp2_p2.setText("Libre");
        this.comp2_p1.setText("Libre");

        this.mensajesFinalizados.setText("0");
        this.mensajesRechazados.setText("0");
        this.colaComputadora1.setText("0");
        this.colaComputadora2.setText("0");
        this.colaComputadora3.setText("0");

        this.clock.setText("0");
        this.eventoActual.setText("");
    }

    public void activarBotonRegreso() {
        this.returnButton.setEnabled(true);
    }

    public int parseInt(String text) {
        int textInt = 0;
        try {
            textInt = Integer.parseInt(text);
        } catch(Exception e) {
            textInt = 0;
        }
        return textInt;
    }

    public void setSegundosModoLento(int segundos) {
        this.segundosModoLento = segundos;
    }

    public void setNumSims(int numSims) {
        this.numSims = numSims;
    }

    public void setTiempoSimulacion(int tiempoSimulacion) {
        this.tiempoSimulacion = tiempoSimulacion;
    }

    public void setSimPanelVisible(){
        this.starSimButton.setEnabled(true);
        this.simPanel.setVisible(true);
        this.numeroSimulaciones_simPanel.setText(Integer.toString(numSims));
        this.segundosSimulacion_simPanel.setText(Integer.toString(tiempoSimulacion * 60));
        this.segundosModoLento_simPanel.setText(Integer.toString(segundosModoLento));
        if(banderaModoLento) {
            this.segundosModoLento_simPanel.setVisible(true);
            this.slowModeLabel_simPanel.setVisible(true);
        } else {
            this.segundosModoLento_simPanel.setVisible(false);
            this.slowModeLabel_simPanel.setVisible(false);
        }
        this.comp1_p1.setText("Libre");
        this.comp3_p1.setText("Libre");
        this.comp2_p2.setText("Libre");
        this.comp2_p1.setText("Libre");
        this.mensajesFinalizados.setText("0");
        this.mensajesRechazados.setText("0");
        this.colaComputadora1.setText("0");
        this.colaComputadora2.setText("0");
        this.clock.setText("0");
        this.eventoActual.setText("");
        this.numeroSim.setText("0");
    }

    public void setParamPanelVisible(){
        this.modoLentoCasilla.setVisible(true);
        this.nextSimButton.setVisible(true);
        this.helpButton.setVisible(true);
        this.numSimulaciones_paramPanel.setVisible(true);
        this.minutosSimulacion_paramPanel.setVisible(true);
        this.segundosModoLento_paramPanel.setVisible(false);
        if(banderaModoLento) {
            this.slowModeLabel.setVisible(true);
            this.segundosModoLento_paramPanel.setVisible(true);
        } else {
            this.slowModeLabel.setVisible(false);
            this.segundosModoLento_paramPanel.setVisible(false);
        }
    }

    public void setParamPanelInvisible(){
        this.modoLentoCasilla.setVisible(false);
        this.helpButton.setVisible(false);
        this.nextSimButton.setVisible(false);
        this.numSimulaciones_paramPanel.setVisible(false);
        this.minutosSimulacion_paramPanel.setVisible(false);
        this.segundosModoLento_paramPanel.setVisible(false);
    }

    // <editor-fold defaultstate="collapsed" desc=" Variables declaration - do not modify ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clock;
    private javax.swing.JTextField colaComputadora1;
    private javax.swing.JTextField colaComputadora2;
    private javax.swing.JTextField colaComputadora3;
    private javax.swing.JTextField comp1_p1;
    private javax.swing.JTextField comp2_p1;
    private javax.swing.JTextField comp2_p2;
    private javax.swing.JTextField comp3_p1;
    private javax.swing.JTextField eventoActual;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel mainMenu;
    private javax.swing.JTextField mensajesFinalizados;
    private javax.swing.JTextField mensajesRechazados;
    private javax.swing.JTextField minutosSimulacion_paramPanel;
    private javax.swing.JCheckBox modoLentoCasilla;
    private javax.swing.JButton nextSimButton;
    private javax.swing.JTextField numSimulaciones_paramPanel;
    private javax.swing.JTextField numeroSim;
    private javax.swing.JTextField numeroSimulaciones_simPanel;
    private javax.swing.JPanel paramMenu;
    private javax.swing.JButton returnButton;
    private javax.swing.JTextField segundosModoLento_paramPanel;
    private javax.swing.JTextField segundosModoLento_simPanel;
    private javax.swing.JTextField segundosSimulacion_simPanel;
    private javax.swing.JPanel simPanel;
    private javax.swing.JTextArea simText;
    private javax.swing.JLabel slowModeLabel;
    private javax.swing.JLabel slowModeLabel_simPanel;
    private javax.swing.JButton starSimButton;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
//</editor-fold>
}
