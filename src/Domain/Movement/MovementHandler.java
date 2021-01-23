package Domain.Movement;

import Domain.Objects.ObjectListener;
import Domain.Objects.GameObject;

import java.util.concurrent.CopyOnWriteArrayList;

public class MovementHandler {

    private final CopyOnWriteArrayList<GameObject> frameObjects;
    private final ObjectListener frame;


    public MovementHandler(CopyOnWriteArrayList<GameObject> frameObjects,ObjectListener frame) {
        this.frameObjects=frameObjects;
        this.frame=frame;
    }

    public void move(){
        MovementStrategy movementStrategy;
        Movement movement;
        for (GameObject object: frameObjects) {
            if(object.equals(frameObjects.get(0))) continue;
            if(!object.isAlive()){
                frameObjects.remove(object);
                continue;
            }
            movementStrategy= MovementStrategyFactory.getStrategy(object);
            movement=new Movement(movementStrategy);
            movement.executeMovement(object);
        }
        frame.onLocationChange();
    }
}
