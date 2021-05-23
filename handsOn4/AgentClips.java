package examples.handsOn.handsOn4;

import jade.core.Agent;
import jade.core.behaviours.*;

import examples.handsOn.handsOn4.BassicDemo;

public class AgentClips extends Agent {

	private AgentGui myGui;
  private BassicDemo clips;

  public boolean detener = false; 
  public boolean tellTime = false;
  public boolean askTime =false;

  public String hecho = "";
  public String regla = "";

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

      hecho = fact;
      regla = rules;

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
    ////////// ---- Fin Generic Behaviour 

    private class TellBehaviour extends Behaviour {
      boolean tellDone = false;

      public void action(){
        System.out.println("TELL in action");
        if(!hecho.isEmpty()){

          clips.cargarHecho(hecho);

          if(!regla.isEmpty()){

            clips.cargarRegla(regla);

          }
        }
        else{
          if(!regla.isEmpty()){

            clips.cargarRegla(regla);

          }
        }

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