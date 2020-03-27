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
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;



/**
 *
 * @author ASUS-PC
 */
public class GoogleMap extends Parent {
    private WebView webview;
    private WebEngine webengine;
    public boolean ready;
    private JSObject doc;
     public String adress;
    public double currentlat;
    public double currentlng;
    public EventHandler<MapEvent> OnMapLatLngChanged;
    public GoogleMap(){
         initMap();
         initCommunication();
        getChildren().add(webview);
      
    }
    private void initMap(){
        webview= new WebView();
        webengine=webview.getEngine();
        webengine.load(getClass().getResource("googlemap.html").toExternalForm());
        ready=false;
        webengine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
        {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> observableValue,
                                final Worker.State oldState,
                                final Worker.State newState)
            {
                if (newState == Worker.State.SUCCEEDED)
                {
                    ready = true;
                   
                   test();
                }
            }
        });
        
       
       
    }
    public void initCommunication(){
       doc = (JSObject) webengine.executeScript("window");
                    doc.setMember("app", GoogleMap.this);
                    
     
    }
    public void test(){
        System.out.println("Map loaded");
    }
   
    public void showadress(String ad){
        adress=ad;
    }
    public void setOnMapLatLngChanged(EventHandler<MapEvent> eventHandler){
        OnMapLatLngChanged=eventHandler;
        
    }
     public void handle(double lat, double lng) {
        if(OnMapLatLngChanged != null) {
            MapEvent event = new MapEvent(this, lat, lng);
            OnMapLatLngChanged.handle(event);  
        }
        
        currentlat=lat;
        currentlng=lng;
    }
     
    private void invokeJS(final String str){
       
            doc.eval(str);
    }
  public void initialize(){
      invokeJS("initialize()");
  }
      public void setdepMarkerPosition(double lat, double lng) {
          
        String sLat = Double.toString(lat);
        String sLng = Double.toString(lng);
        invokeJS("setdepMarkerPosition(" + sLat + ", " + sLng + ")");
        
    }
      public void setdestMarkerPosition(double lat, double lng) {
        String sLat = Double.toString(lat);
        String sLng = Double.toString(lng);
        invokeJS("setdestMarkerPosition(" + sLat + ", " + sLng + ")");
    }
      public void activateListener(){
          invokeJS("activateListener()");
      }

    public void setMapCenter(double lat, double lng) {
        String sLat = Double.toString(lat);
        String sLng = Double.toString(lng);
        invokeJS("setMapCenter(" + sLat + ", " + sLng + ")");
    }

    public void switchSatellite() {
        invokeJS("switchSatellite()");
    }

    public void switchRoadmap() {
        invokeJS("switchRoadmap()");
    }

    public void switchHybrid() {
        invokeJS("switchHybrid()");
    }

    public void switchTerrain() {
        invokeJS("switchTerrain()");
    }

    public void startJumping() {
        invokeJS("startJumping()");
    }

    public void stopJumping() {
        invokeJS("stopJumping()");
    }

    public void setHeight(double h) {
        webview.setPrefHeight(h);
    }

    public void setWidth(double w) {
        webview.setPrefWidth(w);
    }
 public void setInput(String input){
    
                    invokeJS("setInput("+input+")");   
   }

    public ReadOnlyDoubleProperty widthProperty() {
        return webview.widthProperty();
    }
    public WebView getwebview(){
        return this.webview;
    }
}

