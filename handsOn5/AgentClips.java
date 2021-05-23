package examples.handsOn.handsOn5;

import jade.core.Agent;
import jade.core.behaviours.*;

import examples.handsOn.handsOn5.BassicDemo;

public class AgentClips extends Agent {

	private AgentGui myGui;
  public BassicDemo clips;

  public boolean detener = false; 
  public boolean tellTime = false;
  public boolean askTime = false;

  private String rutaPH = "";
  private String rutaPR = "";


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
      
      rutaPH = rutaF;
      rutaPR = rutaR;
      
      tellTime = true;
    }
    
    private class MyGenericBehaviour extends Behaviour {

      public void action() {
        
        if(tellTime == true){

          addBehaviour(new TellBehaviour());
          tellTime = false;
        }
        
        if(askTime == true){

          addBehaviour(new AskBehaviour());
          askTime = false;
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

    private class TellBehaviour extends Behaviour {
      boolean tellDone = false;

      public void action(){
        System.out.println("TELL in action");
        
        clips.cargarPlantillas(rutaPH);
        clips.cargarPlantillas(rutaPR);
        clips.inicializarEI();

        tellDone = true;     
      }

      public boolean done() { 
        if(tellDone == false)  
          return false;
        else
          return true;
      }
    }
    /////////// ----- Fin TellBehaviour

    private class AskBehaviour extends Behaviour {
      boolean askDone = false;

      public void action(){
        System.out.println("ASK in action");

        clips.mostrarHechos();
        clips.mostrarReglas();
        clips.ejecutarReglas();
        
        askDone = true;
      }

      public boolean done() { 
        if(askDone == false)  
          return false;
        else
          return true;
      }
    }

    ////// ----- Fin AskBehaviour
}