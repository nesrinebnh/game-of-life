public abstract class Algorithm {
    protected Graph graph;
    protected IHM ihm;

    public Algorithm(Graph _graph, IHM _ihm){
        this.graph = _graph;
        this.ihm = _ihm;
    }

    public final void Solve(){
        //cleaning the graph
        graph.erase();
        //start the algorith
        Run();
        //display the results
        ihm.displayResults(graph.rebuildPath(),graph.outputNode().distanceFromStart);
    }

    protected abstract void Run();
}

