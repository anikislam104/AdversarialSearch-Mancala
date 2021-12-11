package AdversarialSearch;

public class Game {
    Player max;
    Player min;

    public Game(Player max, Player min) {
        this.max = max;
        this.min = min;
    }

    int moveGems(int player,int position){
        if (player==1){
            int loop=max.nodes.get(position).gems;
            int currentPos=position;
            int currentUser=player;
            for (int i=0;i<loop;i++){
                if (currentPos < 5 && currentUser==player) {
                    max.nodes.get(position).gems=0;
                    max.nodes.get(currentPos+1).gems+=1;
                    currentPos++;
                }

                else if (currentPos==5 && currentUser==player){
                    max.mancala.gems+=1;
                    currentPos++;
                }

                else if (currentPos==6 && currentUser==player){
                    currentUser=2;
                    currentPos=5;
                    min.nodes.get(currentPos).gems+=1;
                }

                else if (currentPos>0 && currentUser==player+1){
                    min.nodes.get(currentPos-1).gems+=1;
                    currentPos--;
                }

                else if (currentPos==0 && currentUser==player+1){
                    currentUser=player;
                    currentPos=0;
                    max.nodes.get(currentPos).gems+=1;
                }
            }
            if (currentPos == 6){
                return 1;
            }

            else if(currentUser==player && max.nodes.get(currentPos).gems==1){
                max.nodes.get(currentPos).gems=0;
                int gemTaken=min.nodes.get(5-currentPos).gems;
                min.nodes.get(5-currentPos).gems=0;
                max.mancala.gems+=1+gemTaken;
                return 0;
            }

            else {
                return 0;
            }
        }

        else if (player==2){
            int loop=min.nodes.get(position).gems;
            int currentPos=position;
            int currentUser=player;
            for (int i=0;i<loop;i++){
                if (currentPos < 5 && currentUser==player) {
                    min.nodes.get(position).gems=0;
                    min.nodes.get(currentPos+1).gems+=1;
                    currentPos++;
                }

                else if (currentPos==5 && currentUser==player){
                    min.mancala.gems+=1;
                    currentPos++;
                }

                else if (currentPos==6 && currentUser==player){
                    currentUser=1;
                    currentPos=5;
                    max.nodes.get(currentPos).gems+=1;
                }

                else if (currentPos>0 && currentUser==player-1){
                    min.nodes.get(currentPos-1).gems+=1;
                    currentPos--;
                }

                else if (currentPos==0 && currentUser==player-1){
                    currentUser=player;
                    currentPos=0;
                    min.nodes.get(currentPos).gems+=1;
                }
            }
            if (currentPos == 6){
                return 1;
            }

            else if(currentUser==player && min.nodes.get(currentPos).gems==1){
                min.nodes.get(currentPos).gems=0;
                int gemTaken=max.nodes.get(5-currentPos).gems;
                max.nodes.get(5-currentPos).gems=0;
                min.mancala.gems+=1+gemTaken;
                return 0;
            }

            else {
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
                max.mancala.gems+=min.nodes.get(i).gems;
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
                min.mancala.gems+=max.nodes.get(i).gems;
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
}
