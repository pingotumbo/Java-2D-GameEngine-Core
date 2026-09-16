package model.component.visual.media.sequence;

import model.component.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Componente dati che definisce un'animazione sequenziale.
 * Memorizza un elenco di sprite e la velocità in FPS.
 * Concepito come puro contenitore dati per l'ECS.
 */
public class SequenceComponent implements Component {

    /**
     * Identificativo univoco immutabile della sequenza (es. "WALK").
     */
    private final String id;

    /**
     * Lista degli identificativi testuali delle immagini che compongono l'animazione.
     */
    private List<String> sprites;

    /**
     * Velocità di riproduzione espressa in Fotogrammi Per Secondo (FPS).
     */
    private double fps;

    /**
     * Moltiplicatore di velocità dinamico (1.0 = normale, 2.0 = velocità doppia).
     */
    private double speed;

    /**
     * Contatore temporale (in secondi) gestito dal sistema di aggiornamento esterno.
     */
    private double time;

    /**
     * Indice numerico che punta allo sprite attualmente attivo nella lista.
     */
    private int idx;

    /**
     * Flag per determinare se l'animazione ricomincia da capo una volta terminata.
     */
    private boolean loop;

    /**
     * Flag per determinare se l'animazione è attiva o in pausa.
     */
    private boolean playing;

    /**
     * Costruttore base della sequenza.
     * Inizializza automaticamente una lista vuota se non fornita.
     *
     * @param id   L'identificativo univoco della sequenza.
     * @param fps  I fotogrammi per secondo desiderati.
     * @param loop true per riproduzione ciclica.
     */
    public SequenceComponent(String id, double fps, boolean loop) {
        this.id = id;
        this.sprites = new ArrayList<>();
        this.fps = fps;
        this.speed = 1.0;
        this.time = 0.0;
        this.idx = 0;
        this.loop = loop;
        this.playing = true;
    }

    /**
     * @return L'ID immutabile della sequenza.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @return La lista completa degli ID degli sprite.
     */
    public List<String> getSprites() {
        return this.sprites;
    }

    /**
     * Sovrascrive l'intera lista degli sprite.
     *
     * @param sprites La nuova lista di stringhe.
     */
    public void setSprites(List<String> sprites) {
        this.sprites = sprites;
    }

    /**
     * Aggiunge un singolo sprite in coda alla sequenza.
     *
     * @param spriteId L'ID testuale dell'immagine da accodare.
     */
    public void addSprite(String spriteId) {
        this.sprites.add(spriteId);
    }

    /**
     * @return I fotogrammi per secondo attuali.
     */
    public double getFps() {
        return this.fps;
    }

    /**
     * @param fps I nuovi fotogrammi per secondo.
     */
    public void setFps(double fps) {
        this.fps = fps;
    }

    /**
     * @return Il moltiplicatore di velocità corrente.
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * @param speed Il nuovo moltiplicatore (es. 0.5 per dimezzare la velocità).
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return Il tempo accumulato in secondi.
     */
    public double getTime() {
        return this.time;
    }

    /**
     * @param time Il nuovo tempo accumulato.
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * @return L'indice dello sprite corrente.
     */
    public int getIdx() {
        return this.idx;
    }

    /**
     * @param idx Il nuovo indice (deve essere entro i limiti della lista).
     */
    public void setIdx(int idx) {
        if (this.sprites != null && idx >= 0 && idx < this.sprites.size()) {
            this.idx = idx;
        }
    }

    /**
     * @return true se in loop, false altrimenti.
     */
    public boolean isLoop() {
        return this.loop;
    }

    /**
     * @param loop true per abilitare il loop.
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    /**
     * @return true se l'animazione scorre, false se è ferma.
     */
    public boolean isPlaying() {
        return this.playing;
    }

    /**
     * @param playing true per avviare, false per mettere in pausa.
     */
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    /**
     * Metodo di utilità per estrarre direttamente lo sprite corrente.
     *
     * @return L'ID dello sprite attivo, o null se la lista è vuota.
     */
    public String getCurSprite() {
        if (this.sprites.isEmpty()) return null;
        return this.sprites.get(this.idx);
    }
}