import java.util.ArrayList;

public interface Graph {
    Node startingNode();
    Node outputNode();

    ArrayList<Node> nodesList();
    ArrayList<Node> outgoingNodesList(Node source);
    ArrayList<Arc> arcsList();
    ArrayList<Arc> outgoingArcsList(Node source);

    int numberNodes();
    double cost(Node source, Node arrival);
    String rebuildPath();
    void calculateEstimatedDistance();
    void erase();
}
