package model.scene.data;

import model.component.data.render.RenderLayer;
import model.entity.Entity;
import model.event.EventType;
import model.factory.ProcessFactory;
import model.factory.UIFactory;
import model.scene.SceneData;

import java.util.ArrayList;
import java.util.List;

/**
 * Configurazione concreta della scena del Prologo Cinematografico.
 * Assembla le entità visive e inietta l'entità di processo invisibile (Timer)
 * che detterà la durata della scena.
 */
public class PrologueSceneData extends SceneData {

    /**
     * Configura il Prologo definendone l'ID di base.
     */
    public PrologueSceneData() {
        super("PROLOGO");
    }

    /**
     * Assembla la lista degli attori fisici e logici della scena.
     *
     * @return La lista delle entità ECS pronte all'uso.
     */
    @Override
    public List<Entity> buildEntities() {
        /** La collezione temporanea che raccoglie gli attori da inviare al motore. */
        List<Entity> objects = new ArrayList<>();

        // 1. Entità Copertina (Visiva)
        objects.add(UIFactory.createAdaptiveImage(
                "ENTITY_PROLOGO_COVER",
                "res/copertinaVideogioco.jpg",
                800,
                600,
                RenderLayer.BACKGROUND
        ));

        // 2. Entità Spinner (Visiva + Logica rotante)
        objects.add(UIFactory.createLoadingSpinner(
                "ENTITY_SPINNER",
                "res/spinner/spinner0001.png",
                750,
                550,
                2.00,
                RenderLayer.UI_OVERLAY1
        ));

        // 3. Entità Regista (Processo Invisibile)
        // Creiamo il timer che vive nella scena. Dopo 3 secondi, urlerà "PROLOGUE_END" nell'EventManager.
        objects.add(ProcessFactory.createSceneDirector(
                "ENTITY_PROLOGUE_TIMER",
                3000,
                EventType.PROLOGUE_END
        ));

        return objects;
    }
}