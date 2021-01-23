package Domain.Collision;

import Domain.Objects.GameObject;

public class Collision {

    private CollisionStrategy collisionStrategy;

    public Collision(CollisionStrategy collisionStrategy){
        this.collisionStrategy=collisionStrategy;
    }

    public void executeCollision(GameObject object1,GameObject object2){
        if(object1.isCollectible() && object2.isCollectible()) collisionStrategy.doCollision(object1,object2);
    }
}
