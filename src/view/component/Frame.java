package view.component;

import javax.swing.*;

/**
 * Contenitore principale (Finestra) del sistema operativo.
 * Ospita la superficie di disegno nativa (Panel) e gestisce il ciclo di vita
 * del processo dell'applicazione a livello di interfaccia utente.
 */
public class Frame extends JFrame {

    /**
     * Costruttore della finestra dell'applicazione.
     * Applica le configurazioni strutturali e aggancia la superficie di rendering.
     *
     * @param panel L'istanza già configurata del Panel da mostrare a schermo.
     */
    public Frame(Panel panel) {
        // Titolo visualizzato nella barra superiore della finestra
        this.setTitle("Impossible Mission Remake");

        // Termina immediatamente il processo Java quando la finestra viene chiusa
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Impedisce all'utente di ridimensionare manualmente la finestra per preservare la risoluzione fissa
        this.setResizable(false);

        // Inserisce il pannello di disegno all'interno della cornice
        this.add(panel);

        // Calcola e adatta i bordi della finestra esattamente attorno alle dimensioni del panel interno
        this.pack();

        // Centra la finestra esattamente in mezzo al monitor principale dell'utente
        this.setLocationRelativeTo(null);

        // Rende fisicamente visibile la finestra e il suo contenuto al sistema operativo
        this.setVisible(true);
    }
}