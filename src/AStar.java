import java.util.ArrayList;

public class AStar extends Algorithm {
    public AStar(Graph _graphe, IHM _ihm){
        super(_graphe,_ihm);
    }

    @Override
    protected void Run(){
        //init
        graph.calculateEstimatedDistance();
        ArrayList<Node> nodesList = graph.nodesList();
        boolean outputFound = false;

        while(nodesList.size() != 0 && !outputFound){
            Node currentNode = nodesList.get(0);
            for(Node node: nodesList){
                if(node.distanceFromStart + node.estimatedDistance < currentNode.distanceFromStart + currentNode.estimatedDistance){
                    currentNode = node;
                }
            }

            if(currentNode.equals(graph.outputNode())){
                outputFound = true;
            }else{
                ArrayList<Arc> outputArcs = graph.outgoingArcsList(currentNode);
                for(Arc arc : outputArcs){
                    if(arc.source.distanceFromStart + arc.cost < arc.target.distanceFromStart){
                        arc.target.distanceFromStart = arc.source.distanceFromStart + arc.cost;
                        arc.target.precursor = arc.source;
                    }
                }
                nodesList.remove(currentNode);
            }
        }
    }
}
