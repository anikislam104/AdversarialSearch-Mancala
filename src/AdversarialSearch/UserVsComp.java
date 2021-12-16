package AdversarialSearch;

import java.util.Scanner;

public class UserVsComp {
    public static void main(String[] args) throws InterruptedException {
            System.out.println("****MANCALA****");
            System.out.println();
            System.out.println("Input depth");
            Scanner d=new Scanner(System.in);
            int depth=d.nextInt();
            int maxWin=0,minWin=0;
            Player max=new Player();
            Player min=new Player();
            Game game=new Game(max,min);
            int currentPlayer=2;
            int loop=0;
            game.printGameBoard();
            while (true) {
                //game.printGameBoard();
                if (game.isGameOver()) {
                    if (game.max.mancala.gems > game.min.mancala.gems) {
                        maxWin++;
                    } else {
                        minWin++;
                    }
                    break;
                }
                if(currentPlayer==1) {
                    System.out.println();
                    //System.out.println("game:" + loop + "  " + currentPlayer);
                    System.out.println("Currently playing:Computer");
                    System.out.println();
                    int a = game.alphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE, currentPlayer, depth);
                    for (int i = 0; i < Game.games.size(); i++) {
                        if (Game.games.get(i).parent == game && Game.games.get(i).heuristicValue == a) {
                            Game.games.get(i).printGameBoard();
                            game = Game.games.get(i);
                            currentPlayer = game.currentPlayer;
                            Game.games.clear();
                            Thread.sleep(3000);
                            break;
                        }
                    }
                }
                else{
                    System.out.println();
                    System.out.println("Currently playing:User");
                    System.out.println();
                    System.out.println("Choose a bin(0-5)");
                    System.out.println();
                    Scanner scanner=new Scanner(System.in);
                    int input=scanner.nextInt();
                    if(game.min.nodes.get(input).gems==0){
                        System.out.println("Invalid move");
                        System.out.println();
                    }
                    else {
                        game.moveGems(2, input, game);
                        game.printGameBoard();
                        currentPlayer = game.currentPlayer;
                        Thread.sleep(3000);
                    }
                }
                loop++;
            }
        game.printGameBoard();
        if(maxWin==0){
            System.out.println("You won");
        }
        else{
            System.out.println("You lost");
        }
    }
}
