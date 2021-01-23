package Domain.Movement;

import Domain.Objects.GameObject;
import Domain.Statistics.GameConfiguration;

public class BetaMoleculeMovement implements MovementStrategy{
    @Override
    public void doMovement(GameObject obj) {

        changeTheAngle(obj, GameConfiguration.getInstance().getData().getGameScreenHeight()/4);
        changeTheVelocity(obj);

        double currentX=obj.getX();
        double currentY=obj.getY();

        double xPos=obj.getVelocityX()* Math.cos(Math.toRadians(obj.getAngle()))+currentX;
        double yPos= obj.getVelocityY()* Math.sin(Math.toRadians(obj.getAngle()))+currentY;

        obj.setX(xPos);
        obj.setY(yPos);
        if(GameConfiguration.getInstance().getData().getMovementType().equals("SPINNING")){
            obj.getCurrentPosition().setRotation(obj.getCurrentPosition().getRotation()+5);
        }
        killObj(obj);
    }

    private void changeTheAngle(GameObject obj,int quartOfGameScreen){
        if(obj.getY()>=quartOfGameScreen){
            obj.setAngle(45);
        }
    }
    private void changeTheVelocity(GameObject obj){
        if((int)obj.getY()%PositionLimit==0&&obj.getAngle()==45){
            obj.setVelocityX(-obj.getVelocityX());
        }
    }
    public void killObj(GameObject obj){
        if(GameConfiguration.getInstance().getData().getGameScreenHeight()<=obj.getY()){
            obj.destroy();
        }
    }
}
