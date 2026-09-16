package view.service;

import model.component.data.render.RenderLayerComponent;
import model.component.visual.media.sprite.SpriteComponent;
import model.entity.Entity;
import view.component.SpriteRenderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sistema ECS responsabile dell'ordinamento e della renderizzazione delle entità.
 * Sostituisce il vecchio FrameBuilder. Filtra dinamicamente le entità attive,
 * le ordina in base allo Z-Index (RenderLayer) e delega il disegno effettivo
 * all'utilità stateless SpriteRenderer. Gestisce inoltre l'offset della telecamera.
 */
public class RenderSystem {

    /**
     * Comparatore interno utilizzato per ordinare la lista delle entità renderizzabili.
     * Sfrutta l'ordine naturale (ordinal) dell'enumerazione RenderLayer contenuta nel componente.
     */
    private final Comparator<Entity> layerComparator;

    /**
     * Costruttore di default del sistema di rendering.
     * Inizializza l'algoritmo di confronto estraendo dinamicamente il valore ordinale
     * dal RenderLayerComponent di ogni entità.
     */
    public RenderSystem() {
        this.layerComparator = Comparator.comparingInt(entity ->
                entity.getComponent(RenderLayerComponent.class).getLayer().ordinal()
        );
    }

    /**
     * Esegue il ciclo di rendering completo per il singolo fotogramma.
     * Filtra le entità prive di grafica, le ordina per profondità e applica lo spostamento
     * della telecamera globale solo per gli elementi appartenenti al mondo di gioco.
     *
     * @param g2d      Il contesto grafico nativo di Swing su cui applicare i pixel.
     * @param entities La collezione grezza di tutte le entità attive nella scena corrente.
     * @param cameraX  L'offset orizzontale della telecamera.
     * @param cameraY  L'offset verticale della telecamera.
     */
    public void render(Graphics2D g2d, Iterable<Entity> entities, double cameraX, double cameraY) {

        /** Lista temporanea di cache per isolare solo le entità effettivamente visibili. */
        List<Entity> renderables = new ArrayList<>();

        // 1. FILTRAGGIO DINAMICO (ECS)
        for (Entity e : entities) {
            /** Modulo contenente i dati dell'immagine e la flag di visibilità. */
            SpriteComponent sprite = e.getComponent(SpriteComponent.class);

            /** Modulo contenente il livello di profondità (Z-Index). */
            RenderLayerComponent layer = e.getComponent(RenderLayerComponent.class);

            // Scarta le entità logiche (es. timer invisibili) o quelle temporaneamente nascoste
            if (sprite != null && layer != null && sprite.isVisible()) {
                renderables.add(e);
            }
        }

        // 2. ORDINAMENTO Z-INDEX (Algoritmo del Pittore)
        renderables.sort(this.layerComparator);

        // 3. CICLO DI DISEGNO
        for (Entity element : renderables) {

            /** Salva lo stato originale del pennello grafico prima di applicare le trasformazioni. */
            AffineTransform originalTransform = g2d.getTransform();

            /** Il componente layer dell'entità corrente, necessario per verificare l'appartenenza alla UI. */
            RenderLayerComponent layerComp = element.getComponent(RenderLayerComponent.class);

            /** * Flag dinamico: se il nome dell'enum inizia con "UI", l'elemento è fissato allo schermo.
             * Sostituisce il vecchio metodo isUIElement().
             */
            boolean isUI = layerComp.getLayer().name().startsWith("UI");

            // 4. GESTIONE TELECAMERA
            // Gli elementi dell'interfaccia ignorano la telecamera per restare in sovrimpressione
            if (!isUI) {
                g2d.translate(-cameraX, -cameraY);
            }

            // 5. DISEGNO DELEGATO
            // Il RenderSystem passa i dati estratti all'utilità visiva che applicherà scala, rotazione e pixel
            SpriteRenderer.render(element, g2d);

            // 6. RIPRISTINO CONTESTO
            // Pulisce il pennello per evitare che la telecamera si sommi al frame successivo
            g2d.setTransform(originalTransform);
        }
    }
}