public class convertTileType {
    public static TileType charToType(char c){
        switch (c){
            case ' ':
                return TileType.grass;
            case '*':
                return TileType.tree;
            case '=':
                return TileType.bridge;
            case 'X':
                return TileType.water;
            case '.':
                return TileType.path;
        }
        return null;
    }
}
