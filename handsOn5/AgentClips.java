package examples.handsOn.handsOn5;

import jade.core.Agent;
import jade.core.behaviours.*;

import examples.handsOn.BassicDemo;

public class AgentClips extends Agent {

	private AgentGui myGui;
  public BassicDemo clips;
  public boolean detener = false; 
  public boolean ejecutar = false;

  //String Ruta1 = "";
  //String Ruta2 = "";


    protected void setup() {
      // Create and show the GUI 
	    myGui = new AgentGui(this);
	    myGui.showGui();

      // Inicializamos el objeto clips
      clips = new BassicDemo();

      System.out.println("Agent "+getLocalName()+" started.");
      addBehaviour(new MyGenericBehaviour());
    }

    public void cargarArchivos(String rutaF, String rutaR){
      
      clips.cargarPlantillas(rutaF);
      clips.cargarPlantillas(rutaR);
      clips.inicializarEI();
      

    }
    
    private class MyGenericBehaviour extends Behaviour {

      public void action() {
        if(ejecutar == true){
          
          clips.mostrarHechos();
          clips.mostrarReglas();
          clips.ejecutarReglas();

          ejecutar = false;
        }  
      } 
        
      public boolean done() { 
        if(detener == false)  
          return false;
        else
          return true;
      }
       
      public int onEnd() {
        myAgent.doDelete();
        return super.onEnd();
      } 
    }
}