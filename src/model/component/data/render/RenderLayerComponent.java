package model.component.data.render;

import model.component.Component;

/**
 * Componente dati che assegna un livello di profondità visiva a un'entità.
 * Viene interrogato dal motore grafico (View) per ordinare la lista degli attori
 * prima di procedere alla stampa dei pixel, garantendo le corrette sovrapposizioni.
 */
public class RenderLayerComponent implements Component {

    /**
     * Il valore enumerato che rappresenta l'indice di profondità associato a questo componente.
     */
    private final RenderLayer layer;

    /**
     * Inizializza il componente assegnandogli un livello di rendering specifico.
     *
     * @param layer L'istanza dell'enumeratore RenderLayer da associare all'entità.
     */
    public RenderLayerComponent(RenderLayer layer) {
        this.layer = layer;
    }

    /**
     * Fornisce in sola lettura il livello di profondità corrente.
     *
     * @return Il valore di RenderLayer memorizzato nel componente.
     */
    public RenderLayer getLayer() {
        return this.layer;
    }
}