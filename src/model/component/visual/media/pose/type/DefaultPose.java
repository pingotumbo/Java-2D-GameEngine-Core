package model.component.visual.media.pose.type;

import model.component.Component;
import model.component.visual.media.pose.EntityPose;

/**
 * Enumeratore per entità basilari che possiedono un'unica rappresentazione visiva
 * e non necessitano di transizioni di stato (es. ostacoli statici, spinner di caricamento).
 */
public enum DefaultPose implements EntityPose, Component {
    /**
     * Posa singola e immutabile per l'intero ciclo di vita dell'entità.
     */
    STATIC
}