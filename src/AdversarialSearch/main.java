package AdversarialSearch;

public class main {
    public static void main(String[] args) {
        Player max=new Player();
        Player min=new Player();
        Game game=new Game(max,min);
        game.printGameBoard();
        System.out.println(game.moveGems(1,2));
        game.printGameBoard();
        System.out.println(game.moveGems(1,3));
        game.printGameBoard();
        game.moveGems(2,2);
        game.printGameBoard();
    }
}
