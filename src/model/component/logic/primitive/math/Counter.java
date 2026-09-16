package model.component.logic.primitive.math;

import model.component.logic.primitive.Primitive;
import model.service.TimeManager;

/**
 * Primitivo logico che funge da generatore di segnale.
 * Produce un valore numerico che cresce nel tempo e si azzera raggiunto il limite.
 */
public class Counter implements Primitive {

    /**
     * Il valore massimo del contatore prima del riavvolgimento a zero.
     */
    private final double limit;

    /**
     * La quantità di incremento calcolata per ogni secondo reale.
     */
    private final double speed;

    /**
     * Il segnale numerico generato in tempo reale.
     */
    private double value;

    /**
     * Inizializza il contatore ciclico.
     *
     * @param limit La soglia di riavvolgimento.
     * @param speed La velocità di incremento al secondo.
     * @param start Il valore di partenza iniziale.
     */
    public Counter(double limit, double speed, double start) {
        this.limit = limit;
        this.speed = speed;
        this.value = start;
    }

    /**
     * Incrementa il segnale garantendo l'indipendenza dal framerate tramite il Delta Time.
     */
    @Override
    public void update() {
        /** Frazione di secondo trascorsa dall'ultimo tick. */
        double dt = TimeManager.getInstance().getDeltaTime();
        this.value = (this.value + (this.speed * dt)) % this.limit;
    }

    /**
     * Fornisce il segnale in uscita per altri nodi.
     *
     * @return Il valore corrente del contatore.
     */
    public double getValue() {
        return this.value;
    }
}