package examples.handsOn.handsOn4;

import net.sf.clipsrules.jni.*;

class BassicDemo {

    Environment clips;

    public BassicDemo(){
        clips = new Environment();
    }

    public void inicializarEI(){
        clips.eval("(reset)");
    }

    public void cargarHecho(String fact){
        clips.eval("(assert"+ fact + ")");
    }

    public void cargarRegla(String rule){
        clips.build("(defrule " + rule + ")");
    }

    public void ejecutarReglas(){
        clips.run();
    }

    public void mostarHechos(){
        clips.eval("(facts)");
    }

    public void cargarPlantillas(String Ruta){
        clips.load(Ruta);
    }

    public void limpiarEI(){
        clips.eval("(clear)");
    }
}