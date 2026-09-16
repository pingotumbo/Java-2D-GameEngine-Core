package view.component;

import model.entity.Entity;
import view.service.RenderSystem;

import javax.swing.*;
import java.awt.*;

/**
 * Superficie di disegno nativa del framework Swing.
 * Sfrutta il Double Buffering hardware di Java per prevenire lo sfarfallio (flickering).
 * Riceve la lista grezza delle entità ECS e delega il filtraggio, l'ordinamento (Z-Index)
 * e il disegno fisico dei pixel al RenderSystem.
 */
public class Panel extends JPanel {

    /**
     * Il sistema delegato all'ispezione dei componenti visivi e al disegno polimorfico.
     * (Sostituisce il vecchio concetto di FrameBuilder).
     */
    private final RenderSystem renderSystem;

    /**
     * Riferimento iterabile alle entità della scena correntemente attiva.
     * Viene aggiornato a ogni ciclo dal Flow (Main Loop) prima di chiamare il repaint().
     */
    private Iterable<Entity> currentEntities;

    /**
     * Costruttore del pannello grafico.
     * Configura le dimensioni fisse e le ottimizzazioni di base per il rendering.
     *
     * @param width  La larghezza del pannello in pixel.
     * @param height L'altezza del pannello in pixel.
     */
    public Panel(int width, int height) {
        this.renderSystem = new RenderSystem();
        this.currentEntities = null;

        // Impostazioni Swing ottimali per i videogiochi
        this.setPreferredSize(new Dimension(width, height));
        this.setDoubleBuffered(true); // Attiva l'accelerazione per il rendering fluido
        this.setBackground(Color.BLACK); // Colore di fallback se non ci sono sfondi
        this.setFocusable(true); // Indispensabile per ricevere l'input da tastiera in futuro
    }

    /**
     * Riceve e memorizza il riferimento alle entità del fotogramma corrente.
     * In un'architettura ECS, si passa l'intero gruppo di entità e sarà il RenderSystem
     * a scartare quelle invisibili o prive di componenti grafici.
     *
     * @param entities La collezione di entità ECS estratta dalla scena attiva.
     */
    public void setEntities(Iterable<Entity> entities) {
        this.currentEntities = entities;
    }

    /**
     * Metodo di callback interno di Swing, invocato automaticamente ad ogni richiesta
     * di repaint() generata dal Loop Principale.
     * Esegue il cast del contesto grafico a Graphics2D per funzionalità avanzate
     * e innesca la pipeline del RenderSystem.
     *
     * @param g L'oggetto Graphics base fornito dal sistema operativo.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Pulisce il frame precedente disegnando il colore di background
        super.paintComponent(g);

        /** Conversione al contesto grafico bidimensionale avanzato. */
        Graphics2D g2d = (Graphics2D) g;

        // Opzionale: attiva l'antialiasing per ammorbidire le linee diagonali, le scale e le rotazioni
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Se la scena ha fornito delle entità, innesca il sistema di rendering
        if (this.currentEntities != null) {
            // I parametri 0.0, 0.0 rappresentano le coordinate iniziali della telecamera.
            this.renderSystem.render(g2d, this.currentEntities, 0.0, 0.0);
        }

        // Libera le risorse grafiche allocate per questo singolo ciclo
        g2d.dispose();
    }
}