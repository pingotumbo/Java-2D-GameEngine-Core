package model.component.spatial;

import model.component.Component;

/**
 * Componente spaziale fondamentale per ogni Entità del gioco.
 * Contiene le informazioni vettoriali relative alla posizione nel mondo (X, Y),
 * alla rotazione sull'asse Z e al fattore di scala dimensionale.
 * Viene interrogato sia dai componenti grafici (per il rendering) sia dai
 * componenti fisici (per le collisioni).
 */
public class Transform implements Component {

    /**
     * Coordinata orizzontale assoluta nel mondo di gioco.
     */
    private double x;

    /**
     * Coordinata verticale assoluta nel mondo di gioco.
     */
    private double y;

    /**
     * Gradi di rotazione (0-360) rispetto al centro dell'entità.
     */
    private double rotation;

    /**
     * Moltiplicatore dimensionale (es. 1.0 = 100%, 0.5 = 50%, 2.0 = 200%).
     */
    private double scale;

    /**
     * Costruttore completo per definire lo stato spaziale iniziale dell'entità.
     * I valori vengono definiti nei Dati (SceneData) e passati al momento
     * della creazione tramite lo SceneLoader.
     *
     * @param x        La posizione iniziale sull'asse X.
     * @param y        La posizione iniziale sull'asse Y.
     * @param rotation I gradi iniziali di rotazione.
     * @param scale    Il fattore di ingrandimento/riduzione iniziale.
     */
    public Transform(double x, double y, double rotation, double scale) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.scale = scale;
    }

    /**
     * Costruttore semplificato per entità standard (scala 1.0 di default).
     *
     * @param x        La posizione iniziale sull'asse X.
     * @param y        La posizione iniziale sull'asse Y.
     * @param rotation I gradi iniziali di rotazione.
     */
    public Transform(double x, double y, double rotation) {
        this(x, y, rotation, 1.0); // Richiama il costruttore principale
    }

    // --- GETTER & SETTER ---

    /**
     * @return La coordinata X attuale.
     */
    public double getX() {
        return x;
    }

    /**
     * @param x La nuova coordinata X.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return La coordinata Y attuale.
     */
    public double getY() {
        return y;
    }

    /**
     * @param y La nuova coordinata Y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return I gradi di rotazione attuali.
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * @param rotation I nuovi gradi di rotazione.
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    /**
     * @return Il moltiplicatore dimensionale attuale.
     */
    public double getScale() {
        return scale;
    }

    /**
     * @param scale Il nuovo moltiplicatore dimensionale.
     */
    public void setScale(double scale) {
        this.scale = scale;
    }
}