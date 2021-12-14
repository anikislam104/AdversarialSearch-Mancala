package AdversarialSearch;

public class main {
    public static void main(String[] args) {

        int maxWin=0,minWin=0;
        for(int i=0;i<100;i++) {
            Player max=new Player();
            Player min=new Player();
            Game game=new Game(max,min);
            //game.printGameBoard();
            int a = game.alphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if(a>0){
                maxWin++;
            }
            else {
                minWin++;
            }
        }
        System.out.println("maxWin= "+maxWin);
        System.out.println("minWin= "+minWin);
//        int a = game.alphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE);
//        System.out.println(a);
    }
}
