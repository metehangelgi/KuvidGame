package Domain.Movement;

import Domain.Objects.GameObject;
import Domain.Statistics.GameConfiguration;

public class AlphaMoleculeMovement implements MovementStrategy{

    @Override
    public void doMovement(GameObject obj) {

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

    private void changeTheVelocity(GameObject obj){
        if((int) (obj.getY()%PositionLimit)==PositionLimit-1){
            obj.setVelocityX(-obj.getVelocityX());
        }

    }

    public void killObj(GameObject obj){
        if(GameConfiguration.getInstance().getData().getGameScreenHeight()<=obj.getY()){
            obj.destroy();
        }
    }
}
