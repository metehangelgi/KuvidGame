package Domain.Movement;

import Domain.Objects.GameObject;
import Domain.Statistics.GameConfiguration;

public class HearthMovement implements MovementStrategy{
    @Override
    public void doMovement(GameObject obj) {

        double currentY=obj.getY();
        double yPos= obj.getVelocityY()* Math.sin(Math.toRadians(obj.getAngle()))+currentY;
        obj.setY(yPos);
        killObj(obj);
    }

    public void killObj(GameObject obj){
        int height= GameConfiguration.getInstance().getData().getGameScreenHeight();
        if(height<obj.getY()){
            obj.destroy();
        }
    }
}
