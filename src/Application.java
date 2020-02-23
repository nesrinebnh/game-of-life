public class Application implements IHM {

    public static void main(String[] args){
        System.out.println("looking for path");
        Application app = new Application();
        app.Start();
    }

    public void displayResults(String path, double distance){
        System.out.println("path ( cost: "+distance+" ): "+path);
    }

    private void Start(){
        String strCart = "..  XX   .\n"
                        +"*.  *X  *.\n"
                        +" .  XX ...\n"
                        +" .* X *.* \n"
                        +" ...=...   \n"
                        +" .* X      \n"
                        +" .  XXX*   \n"
                        +" .  * =    \n"
                        +" .... XX   \n"
                        +"   *.  X*  \n";

        Carte carte = new Carte(strCart, 0, 0, 9, 9);
        Algorithm algo = new AStar(carte, this);
        algo.Solve();

    }


}
