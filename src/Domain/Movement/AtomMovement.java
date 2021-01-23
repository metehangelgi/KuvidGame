package Domain.Movement;

import Domain.Objects.GameObject;
import Domain.Statistics.GameConfiguration;

public class AtomMovement implements MovementStrategy{
    @Override
    public void doMovement(GameObject obj) {
        changeTheVelocity(obj);

        double currentX=obj.getX();
        double currentY=obj.getY();

        double xPos=obj.getVelocityX()* Math.cos(Math.toRadians(obj.getAngle()))+currentX;
        double yPos= obj.getVelocityY()* Math.sin(Math.toRadians(obj.getAngle()))+currentY;

        obj.setX(xPos);
        obj.setY(yPos);
        killObj(obj);
    }


    private void changeTheVelocity(GameObject obj){
        int screenWidth=GameConfiguration.getInstance().getData().getGameScreenWidth();
        if(obj.getX()<=0||obj.getX()+obj.getWidth()>=screenWidth) {
            obj.setVelocityX(-obj.getVelocityX());
        }
    }

    public void killObj(GameObject obj){
        if(obj.getY()<-20){
            obj.destroy();
        }
    }
}
