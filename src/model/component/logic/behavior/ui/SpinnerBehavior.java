package model.component.logic.behavior.ui;

import model.component.logic.behavior.AbstractBehavior;
import model.component.logic.primitive.math.Counter;
import model.component.logic.primitive.spatial.Rotator;
import model.component.spatial.Transform;

/**
 * Componente logico personalizzato per l'animazione di caricamento (Spinner).
 * Estende AbstractBehavior e assembla internamente le primitive ECS.
 */
public class SpinnerBehavior extends AbstractBehavior {

    /**
     * La primitiva matematica che genera il segnale ciclico.
     */
    private final Counter rotationSignal;

    /**
     * La primitiva attuatrice che applica il segnale alle coordinate.
     */
    private final Rotator rotationActuator;

    /**
     * Costruisce il comportamento dello spinner.
     *
     * @param targetTransform Il componente spaziale dell'entità da far ruotare.
     * @param speed           La velocità di rotazione in gradi al secondo.
     */
    public SpinnerBehavior(Transform targetTransform, double speed) {
        super(); // Richiama il costruttore di AbstractBehavior (imposta active = true)
        this.rotationSignal = new Counter(360.0, speed, 0.0);
        this.rotationActuator = new Rotator(this.rotationSignal, targetTransform);
    }

    /**
     * Esegue le primitive interne solo se il behavior è attualmente attivo.
     */
    @Override
    public void update() {
        if (this.active) {
            this.rotationSignal.update();
            this.rotationActuator.update();
        }
    }
}