public class Tile extends Node {
    protected TileType type;
    protected int line;
    protected int colonne;

    public Tile(TileType _type, int _line, int _colonne){
        this.type = _type;
        this.line = _line;
        this.colonne = _colonne;
    }

    boolean Accessible(){
        return (type.equals(TileType.path) || type.equals(TileType.grass) || type.equals(TileType.bridge));
    }

    double cost(){
        switch (type){
            case path: return 1;
            case bridge: return 2;
            case grass: return 2;
            default: return Double.POSITIVE_INFINITY;
        }
    }

    @Override
    public String toString(){
        return "["+line+";"+colonne+";"+type.toString()+"]";
    }
}
