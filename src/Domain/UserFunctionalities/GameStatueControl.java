package Domain.UserFunctionalities;

public class GameStatueControl {

    private static GameStatueControl singleton;

    private boolean isPaused=false;
    private boolean isEnded=false;

    private GameStatueControl(){}

    public synchronized static GameStatueControl getInstance() {
        if(singleton==null){
            singleton=new GameStatueControl();
        }
        return singleton;
    }

    public boolean isGamePaused(){
        return isPaused;
    }

    public void setPaused(){
        this.isPaused=true;
    }

    public void setResumed(){
        this.isPaused=false;
    }

    public boolean isGameEnded(){
        return isEnded;
    }

    public void setGameEnded(boolean bool){
        this.isEnded=bool;
    }
}
