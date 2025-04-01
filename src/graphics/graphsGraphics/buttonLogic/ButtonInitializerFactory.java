package graphics.graphsGraphics.buttonLogic;

import logic.graphs.AbstractGraph;
import logic.graphs.OrientedGraph;
import logic.graphs.WeightedGraph;
import graphics.graphsGraphics.buttonLogic.buttonInitializers.*;

import java.util.List;


public class ButtonInitializerFactory {
    public ButtonInitializer createButtonInitializer(AbstractGraph<?> graph){
        if(graph instanceof OrientedGraph) {
            return new OrientedGraphButtonInitializer((OrientedGraph<?>) graph);
        }else if(graph instanceof WeightedGraph){
            return new WeightedGraphButtonInitializer((WeightedGraph<?>) graph);
        }else{
            return null;
        }
    }

    public <K> ButtonInitializer createIOInitializer(List<AbstractGraph<K>> graphs){
        return new IOButtonInitializer<>(graphs);
    }

    public <K> ButtonInitializer createBasicGraphButtonInitializer(List<AbstractGraph<K>> graphs){
        return new BasicGraphButtonInitializer(graphs);
    }
}
