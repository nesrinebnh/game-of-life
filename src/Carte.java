import java.util.ArrayList;
import java.util.Arrays;

public class Carte implements Graph {

    Tile [][] tiles;
    int nbLine;
    int nbColonne;
    Tile startingNode;
    Tile arrivalNode;
    ArrayList<Node> nodesList = null;
    ArrayList<Arc> arcsList = null;

    public Carte(String _carte, int _startingLine, int _startingColonne, int _arivalLine, int _arrivalColonne){
        String[] lines = _carte.split("\n");
        this.nbLine = lines.length;
        this.nbColonne = lines[0].length();
        tiles = new Tile[nbLine][];

        for(int i = 0; i <nbLine;i++){
            tiles[i] = new Tile[nbColonne];
            for(int j = 0; j<nbColonne;j++){
                TileType type = convertTileType.charToType(lines[i].charAt(j));
                tiles[i][j] = new Tile(type,i,j);
            }
        }

        startingNode = tiles[_startingLine][_startingColonne];
        startingNode.distanceFromStart = startingNode.cost();
        arrivalNode = tiles[_arivalLine][_arrivalColonne];

        nodesList();
        arcsList();
    }

    @Override
    public Node startingNode(){
        return startingNode;
    }

    @Override
    public Node outputNode(){
        return arrivalNode;
    }

    @Override
    public ArrayList<Node> nodesList(){
        if(nodesList == null){
            nodesList = new ArrayList<>();
            for(int i = 0; i < nbLine ; i++){
                nodesList.addAll(Arrays.asList(tiles[i]));
            }
        }
        return nodesList;
    }

    @Override
    public ArrayList<Node> outgoingNodesList(Node source){
        //init
        ArrayList<Node> outgoingNodeList = new ArrayList<>();
        int line = ((Tile)source).line;
        int colonne = ((Tile)source).colonne;

        //neighbor on the right
        if(colonne - 1 >=0 && tiles[line][colonne-1].Accessible()){
            outgoingNodeList.add(tiles[line][colonne-1]);
        }

        //neighbor on the left
        if(colonne+1 < nbColonne && tiles[line][colonne+1].Accessible()){
            outgoingNodeList.add(tiles[line][colonne+1]);
        }

        //neighbor on the top
        if(line-1 >=0 && tiles[line-1][colonne].Accessible()){
            outgoingNodeList.add(tiles[line-1][colonne]);
        }

        //neighbor on the buttom
        if(line+1 < nbLine && tiles[line+1][colonne].Accessible()){
            outgoingNodeList.add(tiles[line+1][colonne]);
        }

        return outgoingNodeList;
    }

    @Override
    public ArrayList<Arc> arcsList(){
        if(arcsList == null){
            arcsList = new ArrayList<>();

            //browse the nodes
            for(int line = 0; line < nbLine; line++){
                for(int colonne =0; colonne<nbColonne; colonne++){
                    if(tiles[line][colonne].Accessible()){
                        //right
                        if(colonne-1>=0 && tiles[line][colonne-1].Accessible()){
                            arcsList.add(new Arc(tiles[line][colonne],tiles[line][colonne-1],tiles[line][colonne-1].cost()));
                        }
                        //left
                        if(colonne+1 < nbColonne && tiles[line][colonne+1].Accessible()){
                            arcsList.add(new Arc(tiles[line][colonne],tiles[line][colonne+1],tiles[line][colonne+1].cost()));
                        }
                        //top
                        if(line-1>=0&&tiles[line-1][colonne].Accessible()){
                            arcsList.add(new Arc(tiles[line][colonne],tiles[line-1][colonne],tiles[line-1][colonne].cost()));
                        }
                        //buttom
                        if(line+1<nbLine && tiles[line+1][colonne].Accessible()){
                            arcsList.add(new Arc(tiles[line][colonne],tiles[line+1][colonne],tiles[line+1][colonne].cost()));
                        }
                    }
                }
            }
        }
        return arcsList;
    }

    @Override
    public ArrayList<Arc> outgoingArcsList(Node source){
        ArrayList<Arc> outgoingArcList = new ArrayList<>();
        int line = ((Tile)source).line;
        int colonne = ((Tile)source).colonne;

        if(tiles[line][colonne].Accessible()){
            //right
            if(colonne - 1 >=0 && tiles[line][colonne-1].Accessible()){
                outgoingArcList.add(new Arc(tiles[line][colonne],tiles[line][colonne-1],tiles[line][colonne-1].cost()));
            }

            //neighbor on the left
            if(colonne+1 < nbColonne && tiles[line][colonne+1].Accessible()){
                outgoingArcList.add(new Arc(tiles[line][colonne],tiles[line][colonne+1],tiles[line][colonne+1].cost()));
            }

            //neighbor on the top
            if(line-1 >=0 && tiles[line-1][colonne].Accessible()){
                outgoingArcList.add(new Arc(tiles[line][colonne],tiles[line-1][colonne],tiles[line-1][colonne].cost()));
            }

            //neighbor on the buttom
            if(line+1 < nbLine && tiles[line+1][colonne].Accessible()){
                outgoingArcList.add(new Arc(tiles[line][colonne],tiles[line+1][colonne],tiles[line+1][colonne].cost()));
            }
        }
        return outgoingArcList;
    }

    @Override
    public int numberNodes(){
        return nbLine * nbColonne;
    }

    @Override
    public double cost(Node source, Node arrival){
        return ((Tile)arrival).cost();
    }

    @Override
    public String rebuildPath(){
        //init
        String path = "";
        Tile currentNode = arrivalNode;
        Tile precedentNode = (Tile) arrivalNode.precursor;

        while (precedentNode != null){
            path = "-"+currentNode.toString()+path;
            currentNode = precedentNode;
            precedentNode = (Tile) currentNode.precursor;
        }

        path = currentNode.toString()+path;

        return path;
    }

    @Override
    public void calculateEstimatedDistance(){
        /**Manhattan distance
         * this is the number of horizontal boxes
         * added to the number of vertical boxes to
         * connect the current box to the output
         */
        for(int line = 0; line<nbLine;line++){
            for(int colonne=0; colonne<nbColonne;colonne++){
                tiles[line][colonne].estimatedDistance = Math.abs(arrivalNode.line - line) + Math.abs(arrivalNode.colonne - colonne);
            }
        }
    }

    @Override
    public void erase(){
        nodesList = null;
        arcsList = null;

        for(int line = 0; line < nbLine ; line ++ ){
            for(int colonne  = 0 ; colonne < nbColonne ; colonne ++){
                tiles[line][colonne].estimatedDistance = Double.POSITIVE_INFINITY;
                tiles[line][colonne].precursor = null;
            }
        }

        startingNode.distanceFromStart = startingNode.cost();
    }
}
