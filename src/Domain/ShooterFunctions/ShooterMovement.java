package Domain.ShooterFunctions;

import Domain.Statistics.GameData;
import Domain.Utils.Position;

import static Domain.Utils.FinalValues.POWERUP;

public class ShooterMovement {

    private Shooter shooter;
    private GameData gameData;

    public ShooterMovement(Shooter shooter,GameData gameData){
        this.shooter=shooter;
        this.gameData=gameData;
    }

    public void moveShooter(String direction){
        double currentX=shooter.getX();
        double currentY=shooter.getY();
        double xPos=0;

        if (direction.equals("right") && gameData.getGameScreenWidth()-gameData.getL()>currentX+shooter.getVelocityX()){
            xPos=currentX+shooter.getVelocityX();
        } else if(direction.equals("left") && 0<currentX-shooter.getVelocityX()){
            xPos=currentX-shooter.getVelocityX();
        }
        Position newPos=new Position(xPos,currentY);
        newPos.setRotation(shooter.getRotationAngle());
        shooter.setCurrentPosition(newPos);
        Position triggerPosition = setTriggerPosition(shooter.getObjectInTrigger().getType());
        shooter.getObjectInTrigger().setCurrentPosition(triggerPosition);
    }

    public void rotateShooter(String direction) {
        if(direction.equals("right") && shooter.getRotationAngle()<90){
            shooter.setRotationAngle(shooter.getRotationAngle()+10);
        } else{
            if(shooter.getRotationAngle()>-90){
                shooter.setRotationAngle(shooter.getRotationAngle()-10);
            }
        }
    }

    public Position setTriggerPosition(String triggerType){

        int x=(int) shooter.getX()+shooter.getWidth()/3;
        int y=(int) shooter.getY()-shooter.getHeight()/4;
        if(triggerType.equals(POWERUP)){
            x=(int) shooter.getX();
            y=(int) shooter.getY()-shooter.getHeight()/2;
        }
        return new Position(x,y);
    }


    public void setShooter(Shooter shooter) {
        this.shooter = shooter;
    }
}
