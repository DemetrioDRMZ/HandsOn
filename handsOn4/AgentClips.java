package examples.handsOn.handsOn4;

import jade.core.Agent;
import jade.core.behaviours.*;

import examples.handsOn.BassicDemo;

public class AgentClips extends Agent {

	private AgentGui myGui;
  public BassicDemo clips;
  public boolean detener = false; 

  //String hecho = "";
  //String regla = "";

  public boolean ejecutar = false;

    protected void setup() {
      // Create and show the GUI 
	    myGui = new AgentGui(this);
	    myGui.showGui();

      // Inicializamos el objeto clips
      clips = new BassicDemo();

      // Aplicamos el (reset)
      clips.inicializarEI();

      System.out.println("Agent "+getLocalName()+" started.");
      addBehaviour(new MyGenericBehaviour());
    }

    public void CargarReglas(String fact, String rules){
      
      if(!fact.isEmpty()){

        clips.cargarHecho(fact);

        if(!rules.isEmpty()){

          clips.cargarRegla(rules);
        }
      }
      else{
        if(!rules.isEmpty()){

          clips.cargarRegla(rules);
        }
      }
    }
    
    private class MyGenericBehaviour extends Behaviour {

      public void action() {
        if(ejecutar == true){

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