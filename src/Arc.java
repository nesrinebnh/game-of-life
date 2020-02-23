public class Arc {
    protected Node source;
    protected Node target;
    protected double cost;

    public Arc(Node _source, Node _target, double _cost){
        this.source = _source;
        this.target = _target;
        this.cost = _cost;
    }
}
