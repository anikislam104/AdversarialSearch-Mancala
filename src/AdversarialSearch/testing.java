package AdversarialSearch;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class testing {
    public static void main(String[] args) {
        Player max=new Player();
        Player min=new Player();
        for (int i=0;i<3;i++){
            max.nodes.get(i).gems=0;
        }
        max.nodes.get(3).gems=1;
        max.nodes.get(4).gems=1;
        max.nodes.get(5).gems=1;
        max.mancala.gems=29;
        min.mancala.gems=15;
        min.nodes.get(0).gems=1;
        for (int i=1;i<6;i++){
            min.nodes.get(i).gems=0;
        }
        Game game=new Game(max,min);
        ArrayList<Game> games=getChildern(game);
        for (int i=0;i<games.size();i++){
            System.out.println("start");
            games.get(i).printGameBoard();
        }
    }



    static ArrayList<Game> getChildern(Game game){
        int[] arr = new int[] {0,1,5,4,3,2};
        arr=shuffleArray(arr);
        ArrayList<Game> children=new ArrayList<>();

        for (int j=0;j<6;j++){
            Game save=new Game();
            Player temp1=new Player();
            Player temp2=new Player();
            for (int i=0;i<6;i++){
                temp1.nodes.get(i).gems=game.max.nodes.get(i).gems;
                temp2.nodes.get(i).gems=game.min.nodes.get(i).gems;
            }
            temp1.mancala.gems=game.max.mancala.gems;
            temp2.mancala.gems=game.min.mancala.gems;
            save.max=temp1;
            save.min=temp2;
            if(!save.checkZeroAtPosition(2, arr[j])) {
              System.out.println("children no: "+(j+1));
                children.add(save.getChildAtPosition(save, arr[j], 2));
//                game.printGameBoard();
                //game.getChildAtPosition(game, arr[i], 1).printGameBoard();
            }
        }
        return children;
    }
    static int[] shuffleArray(int[] ar){
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
}
