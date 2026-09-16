package model.component.logic.primitive;

import model.component.Component;
import model.component.logic.Updatable;

/**
 * Contratto base per tutti i blocchi logici elementari (Primitive).
 * Unisce i contratti Component (per essere inserito in un'Entity) e
 * Updatable (per essere aggiornato a ogni tick del motore).
 */
public interface Primitive extends Component, Updatable {
    // Nessun metodo aggiuntivo, serve solo per raggruppamento polimorfico
}