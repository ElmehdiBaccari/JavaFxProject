/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muse;

/**
 *
 * @author Duroy Mehdi
 */

import javafx.event.Event;
import javafx.event.EventType;

/**
 *
 * @author ASUS-PC
 */
public class MapEvent extends Event {
    private double lat;
    private double lng;
    public MapEvent(GoogleMap map,double lat,double lng) {
        super(map,Event.NULL_SOURCE_TARGET,Event.ANY);
        this.lat=lat;
        this.lng=lng;
    }
    public double getlat(){
        return this.lat;
    }
    public double getlng(){
        return this.lng;
    }
    
}