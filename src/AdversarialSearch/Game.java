package AdversarialSearch;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    Player max;
    Player min;
    int currentPlayer;
    int additionalMove;
    int heuristicValue;
    Game parent;
    static ArrayList<Game> games=new ArrayList<>();
    public Game(Player max, Player min) {
        this.max = max;
        this.min = min;
        max.opponent=min;
        currentPlayer=1;
        additionalMove=0;
    }

    public Game() {
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    int moveGems(int player, int position,Game game){
        //game.printGameBoard();
        if (player==1){
            currentPlayer=1;
            int loop=game.max.nodes.get(position).gems;
            //this.printGameBoard();
            int currentPos=position;
            int currentUser=player;
            game.max.nodes.get(position).gems=0;
            for (int i=0;i<loop;i++){
                if (currentPos < 5 && currentUser==player) {
                    game.max.nodes.get(currentPos+1).gems+=1;
                    currentPos++;
                   // System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos==5 && currentUser==player){
                    game.max.mancala.gems+=1;
                    currentPos++;
                   // System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos==6 && currentUser==player){
                    currentUser=2;
                    currentPos=5;
                    game.min.nodes.get(currentPos).gems+=1;
                   // System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos>0 && currentUser==player+1){
                    game.min.nodes.get(currentPos-1).gems+=1;
                    currentPos--;
                  //  System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos==0 && currentUser==player+1){
                    currentUser=player;
                    currentPos=0;
                    game.max.nodes.get(currentPos).gems+=1;
                   // System.out.println(max.nodes.get(1).gems);
                }
            }

            if (currentPos == 6){
                currentPlayer=1;
                additionalMove++;
                return 1;
            }

            else if(currentUser==player && game.max.nodes.get(currentPos).gems==1 && game.min.nodes.get(currentPos).gems!=0){
                game.max.nodes.get(currentPos).gems=0;
                int gemTaken=game.min.nodes.get(currentPos).gems;
                game.min.nodes.get(currentPos).gems=0;
                game.max.mancala.gems+=1+gemTaken;
                currentPlayer=2;
                additionalMove=0;
                return 0;
            }

            else {
                currentPlayer=2;
                additionalMove=0;
                return 0;
            }
        }

        else if (player==2){
            currentPlayer=2;
            int loop=game.min.nodes.get(position).gems;
            int currentPos=position;
            int currentUser=player;
            game.min.nodes.get(position).gems=0;
            for (int i=0;i<loop;i++){
                if (currentPos > 0 && currentUser==player) {
                    game.min.nodes.get(position).gems=0;
                    game.min.nodes.get(currentPos-1).gems+=1;
                    currentPos--;
                   // System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos==0 && currentUser==player){
                    game.min.mancala.gems+=1;
                    currentPos--;
                   // System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos==-1 && currentUser==player){
                    currentUser=1;
                    currentPos=0;
                   // System.out.println(max.nodes.get(1).gems);
                    game.max.nodes.get(currentPos).gems+=1;
                }

                else if (currentPos<5 && currentUser==player-1){

                    game.max.nodes.get(currentPos+1).gems+=1;
                   // System.out.println(max.nodes.get(1).gems);
                    currentPos++;
                }

                else if (currentPos==5 && currentUser==player-1){
                    currentUser=player;
                    currentPos=5;
                    game.min.nodes.get(currentPos).gems+=1;
                 //   System.out.println(max.nodes.get(1).gems);
                }
            }
//            this.printGameBoard();
            if (currentPos == -1){
                currentPlayer=2;
                additionalMove++;
                return 1;
            }

            else if(currentUser==player && game.min.nodes.get(currentPos).gems==1 && game.max.nodes.get(currentPos).gems!=0){
                game.min.nodes.get(currentPos).gems=0;
                int gemTaken=game.max.nodes.get(currentPos).gems;
                game.max.nodes.get(currentPos).gems=0;
                game.min.mancala.gems+=1+gemTaken;
                currentPlayer=1;
                additionalMove=0;
               // System.out.println(max.nodes.get(1).gems);
                return 0;

            }

            else {
                currentPlayer=1;
                additionalMove=0;
                return 0;
            }
        }
        return 0;
    }

    boolean isGameOver(){
        int check=1;
        for (int i=0;i<6;i++){
            if (max.nodes.get(i).gems!=0){
                check=0;
            }
        }
        if (check==1){
            for (int i=0;i<6;i++){
                min.mancala.gems+=min.nodes.get(i).gems;
                min.nodes.get(i).gems=0;
            }
            return true;
        }
        check=1;
        for (int i=0;i<6;i++){
            if (min.nodes.get(i).gems!=0){
                check=0;
            }
        }
        if (check==1){
            for (int i=0;i<6;i++){
                max.mancala.gems+=max.nodes.get(i).gems;
                max.nodes.get(i).gems=0;
            }
            return true;
        }
        return false;
    }

    int getPoint(int player){
        if (player==1){
            return max.mancala.gems;
        }
        else {
            return min.mancala.gems;
        }
    }

    void printGameBoard(){
        System.out.println("Gameboard:");
        System.out.print("      ");
        for (int i=0;i<6;i++){
            System.out.print(min.nodes.get(i).gems+"  ");
        }
        System.out.println();
        System.out.print(min.mancala.gems+"  ");
        for (int i=0;i<6;i++){
            System.out.print("   ");
        }
        System.out.print("     "+max.mancala.gems+"  ");
        System.out.println();
        System.out.print("      ");
        for (int i=0;i<6;i++){
            System.out.print(max.nodes.get(i).gems+"  ");
        }
        System.out.println();
    }

    int heuristicONE(Game game){
        return game.max.mancala.gems-game.min.mancala.gems;
    }

    Game getChildAtPosition(Game game,int position,int player){
        Player maxi=game.max;
        Player mini=game.min;
        Game g=new Game();
        g.max=maxi;
        g.min=mini;

        g.moveGems(player,position,g);
        //game.printGameBoard();
        //g.printGameBoard();
        return g;
    }

    boolean checkZeroAtPosition(int player,int pos){
        if(player==1) {
            if (max.nodes.get(pos).gems == 0) {
                return true;
            } else {
                return false;
            }
        }
        else {
            if (min.nodes.get(pos).gems == 0) {
                return true;
            } else {
                return false;
            }
        }
    }




    int alphaBeta(Game game,int alpha,int beta,int curPlayer,int depth){
        if(game.isGameOver() || depth==0){
            return game.heuristics(2,game);
        }

        if(curPlayer==1){
            int bestValue=Integer.MIN_VALUE;
            int[] arr = new int[] {0,1,5,4,3,2};
            arr=shuffleArray(arr);
            ArrayList<Game> children=new ArrayList<>();
            int count=0;
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
                if(!save.checkZeroAtPosition(1, arr[j])) {
                    children.add(save.getChildAtPosition(save, arr[j], 1));
                    children.get(count).parent=game;
                    games.add(children.get(count));
                    //children.get(count).printGameBoard();
                    count++;
                }
            }
            //games.add(game);
            for (int i=0;i<children.size();i++){
                int value=this.alphaBeta(children.get(i),alpha,beta,children.get(i).currentPlayer,depth-1);
//                children.get(i).heuristicValue=value;
                children.get(i).setHeuristicValue(children.get(i),game,value);
//                System.out.println(value);
//                games.add(children.get(i));

                bestValue=Math.max(bestValue,value);
                alpha=Math.max(alpha,bestValue);
                if(beta<=alpha){
                    break;
                }

            }
            return bestValue;
        }
        else {
            int bestValue=Integer.MAX_VALUE;
            int[] arr = new int[] { 2,1,0,4,5,3};
            arr=shuffleArray(arr);
            ArrayList<Game> children=new ArrayList<>();
            int count=0;
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
                    children.add(save.getChildAtPosition(save, arr[j], 2));
                    children.get(count).parent=game;
                    games.add(children.get(count));
                    //children.get(count).printGameBoard();
                    count++;
                }
            }
            //games.add(game);
            for (int i=0;i<children.size();i++){

                    int value=this.alphaBeta(children.get(i),alpha,beta,children.get(i).currentPlayer,depth-1);
                    children.get(i).setHeuristicValue(children.get(i),game,value);
                    bestValue=Math.min(bestValue,value);
                    beta=Math.min(beta,bestValue);
                    if (beta<=alpha){
                        break;
                    }
            }
            return bestValue;
        }
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

    int heuristicTWO(Game game){
        int myGem=0,hisGem=0;
        for (int i=0;i<6;i++){
            myGem+=game.max.nodes.get(i).gems;
            hisGem+=game.min.nodes.get(i).gems;
        }
        int h1=2*(myGem-hisGem);
        int h2=3*(game.max.mancala.gems-game.min.mancala.gems);
        return h1+h2;
    }

    int heuristics(int num,Game game){
        if(num==1){
            return game.max.mancala.gems-game.min.mancala.gems;
        }
        else if (num==2){
            int myGem=0,hisGem=0;
            for (int i=0;i<6;i++){
                myGem+=game.max.nodes.get(i).gems;
                hisGem+=game.min.nodes.get(i).gems;
            }
            int h1=2*(myGem-hisGem);
            int h2=30*(game.max.mancala.gems-game.min.mancala.gems);
            return h1+h2;
        }
        else if(num==3){
            return this.heuristicTWO(game)+100*additionalMove;
        }
        return 0;
    }

    static void printPath(int h){
        System.out.println(games.size());
        for (int i=0;i<games.size();i++){
            System.out.println(games.get(i).heuristicValue+"  "+h);
            if(games.get(i).heuristicValue==h){
                games.get(i).printGameBoard();
            }
        }
    }

    void setHeuristicValue(Game game,Game parent,int h){
        for(int i=0;i<games.size();i++){
            if(games.get(i)==game && games.get(i).parent==parent){
                games.get(i).heuristicValue=h;
            }
        }
    }


}
