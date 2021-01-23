package Domain.Collision;

import Domain.Objects.GameObject;

public class BlockerAtomMoleculeCollision implements CollisionStrategy{
    @Override
    public void doCollision(GameObject object1, GameObject object2) {
        object2.destroy();
    }
}
