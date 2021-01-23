package Domain.Collision;

import Domain.Objects.GameObject;

public class BlockerPoweupCollision implements CollisionStrategy{
    @Override
    public void doCollision(GameObject object1, GameObject object2) {
        if(object1.getSubType().equals(object2.getSubType())){
            object1.destroy();
        }
        object2.destroy();
    }
}
