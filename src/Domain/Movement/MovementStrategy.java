package Domain.Movement;

import Domain.Objects.GameObject;

public interface MovementStrategy {

    void doMovement(GameObject obj);
    int PositionLimit=100;
}
