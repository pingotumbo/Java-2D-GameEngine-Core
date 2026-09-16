package model.component.logic.primitive.spatial;

import model.component.logic.primitive.Primitive;
import model.component.logic.primitive.math.Counter;
import model.component.spatial.Transform;

/**
 * Primitivo logico che funge da ponte (cablaggio) tra un input e un output.
 * Legge un segnale numerico e lo inietta direttamente in un componente spaziale.
 */
public class Rotator implements Primitive {

    /**
     * Il nodo di input da cui leggere il valore ciclico.
     */
    private final Counter input;

    /**
     * Il componente spaziale di output su cui scrivere la rotazione.
     */
    private final Transform output;

    /**
     * Collega fisicamente il generatore di segnale alla destinazione.
     *
     * @param input  Il generatore numerico.
     * @param output Il componente spaziale da alterare.
     */
    public Rotator(Counter input, Transform output) {
        this.input = input;
        this.output = output;
    }

    /**
     * Legge il segnale dall'input e lo sovrascrive nel parametro di rotazione dell'output.
     */
    @Override
    public void update() {
        if (this.input != null && this.output != null) {
            this.output.setRotation(this.input.getValue());
        }
    }
}