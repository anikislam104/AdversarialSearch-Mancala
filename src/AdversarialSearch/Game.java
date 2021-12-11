package AdversarialSearch;

public class Game {
    Player max;
    Player min;
    int currentPlayer;
    public Game(Player max, Player min) {
        this.max = max;
        this.min = min;
        max.opponent=min;
        currentPlayer=1;
    }

    public Game() {
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    int moveGems(int player, int position){
        if (player==1){
            currentPlayer=1;
            int loop=max.nodes.get(position).gems;
            this.printGameBoard();
            int currentPos=position;
            int currentUser=player;
            max.nodes.get(position).gems=0;
            for (int i=0;i<loop;i++){
                if (currentPos < 5 && currentUser==player) {
                    max.nodes.get(currentPos+1).gems+=1;
                    currentPos++;
                   // System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos==5 && currentUser==player){
                    max.mancala.gems+=1;
                    currentPos++;
                   // System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos==6 && currentUser==player){
                    currentUser=2;
                    currentPos=5;
                    min.nodes.get(currentPos).gems+=1;
                   // System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos>0 && currentUser==player+1){
                    min.nodes.get(currentPos-1).gems+=1;
                    currentPos--;
                  //  System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos==0 && currentUser==player+1){
                    currentUser=player;
                    currentPos=0;
                    max.nodes.get(currentPos).gems+=1;
                   // System.out.println(max.nodes.get(1).gems);
                }
            }

            if (currentPos == 6){
                currentPlayer=1;
                return 1;
            }

            else if(currentUser==player && max.nodes.get(currentPos).gems==1 && min.nodes.get(currentPos).gems!=0){
                max.nodes.get(currentPos).gems=0;
                int gemTaken=min.nodes.get(currentPos).gems;
                min.nodes.get(currentPos).gems=0;
                max.mancala.gems+=1+gemTaken;
                currentPlayer=2;
                return 0;
            }

            else {
                currentPlayer=2;
                return 0;
            }
        }

        else if (player==2){
            currentPlayer=2;
            int loop=min.nodes.get(position).gems;
            int currentPos=position;
            int currentUser=player;
            min.nodes.get(position).gems=0;
            for (int i=0;i<loop;i++){
                if (currentPos > 0 && currentUser==player) {
                    min.nodes.get(position).gems=0;
                    min.nodes.get(currentPos-1).gems+=1;
                    currentPos--;
                   // System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos==0 && currentUser==player){
                    min.mancala.gems+=1;
                    currentPos--;
                   // System.out.println(max.nodes.get(1).gems);
                }

                else if (currentPos==-1 && currentUser==player){
                    currentUser=1;
                    currentPos=0;
                   // System.out.println(max.nodes.get(1).gems);
                    max.nodes.get(currentPos).gems+=1;
                }

                else if (currentPos<5 && currentUser==player-1){

                    max.nodes.get(currentPos+1).gems+=1;
                   // System.out.println(max.nodes.get(1).gems);
                    currentPos++;
                }

                else if (currentPos==5 && currentUser==player-1){
                    currentUser=player;
                    currentPos=5;
                    min.nodes.get(currentPos).gems+=1;
                 //   System.out.println(max.nodes.get(1).gems);
                }
            }
//            this.printGameBoard();
            if (currentPos == -1){
                currentPlayer=2;
                return 1;
            }

            else if(currentUser==player && min.nodes.get(currentPos).gems==1 && max.nodes.get(currentPos).gems!=0){
                min.nodes.get(currentPos).gems=0;
                int gemTaken=max.nodes.get(currentPos).gems;
                max.nodes.get(currentPos).gems=0;
                min.mancala.gems+=1+gemTaken;
                currentPlayer=1;
               // System.out.println(max.nodes.get(1).gems);
                return 0;

            }

            else {
                currentPlayer=1;
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

    int heuristicONE(int player){
        if (player==1){
            return max.mancala.gems-min.mancala.gems;
        }
        else {
            return min.mancala.gems-max.mancala.gems;
        }
    }

    Game getChildAtPosition(Game game,int position,int player){
        Player maxi=game.max;
        Player mini=game.min;
        Game g=new Game();
        g.max=maxi;
        g.min=mini;
        g.moveGems(player,position);
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
            if (max.nodes.get(pos).gems == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    int alphaBeta(Game game,int alpha,int beta,Player winner,Player loser){
        if(game.isGameOver()){
            return game.heuristicONE(1);
        }
        if(game.currentPlayer==1){
            int bestValue=Integer.MIN_VALUE;
            for (int i=0;i<6;i++){
                if(game.checkZeroAtPosition(1,i)==false){
                    Game child=game.getChildAtPosition(game,i,1);
                    int value=game.alphaBeta(child,alpha,beta,game.max,game.min);
                    child.printGameBoard();
                    bestValue=Math.max(bestValue,value);
                    alpha=Math.max(alpha,bestValue);
                    if(beta<=alpha){
                        break;
                    }
                }
            }
            return bestValue;
        }
        else {
            int bestValue=Integer.MAX_VALUE;
            for (int i=0;i<6;i++){
                if (game.checkZeroAtPosition(2,i)==false){
                    Game child=game.getChildAtPosition(game,i,2);
                    int value=game.alphaBeta(child,alpha,beta,game.min,game.max);
                    child.printGameBoard();
                    bestValue=Math.min(bestValue,value);
                    beta=Math.min(beta,bestValue);
                    if (beta<=alpha){
                        break;
                    }
                }
            }
            return bestValue;
        }
//        return 0;
    }
}
