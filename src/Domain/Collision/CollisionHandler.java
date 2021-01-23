package Domain.Collision;

import Domain.Objects.ObjectListener;
import Domain.Objects.GameObject;
import Domain.Utils.FinalValues;
import Domain.Utils.Position;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollisionHandler {

    private final CopyOnWriteArrayList<GameObject> frameObjects;
    private final ObjectListener frame;
    private HashMap<String, HashMap<String, CollisionStrategy>> strategyMap;

    public CollisionHandler(CopyOnWriteArrayList<GameObject> frameObjects, ObjectListener frame) {
        this.frameObjects = frameObjects;
        this.frame = frame;
        initializeStrategyMap();
    }

    public void initializeStrategyMap() {
        strategyMap = new HashMap<String, HashMap<String, CollisionStrategy>>();
        HashMap<String, CollisionStrategy> moleculeStrategies = new HashMap<String, CollisionStrategy>();
        moleculeStrategies.put(FinalValues.ATOM, new MoleculeAtomCollision());
        strategyMap.put(FinalValues.MOLECULE, moleculeStrategies);
        HashMap<String, CollisionStrategy> blockerStrategies = new HashMap<String, CollisionStrategy>();
        blockerStrategies.put(FinalValues.MOLECULE, new BlockerAtomMoleculeCollision());
        blockerStrategies.put(FinalValues.ATOM, new BlockerAtomMoleculeCollision());
        blockerStrategies.put(FinalValues.POWERUP, new BlockerPoweupCollision());
        blockerStrategies.put(FinalValues.SHOOTER, new ShooterBlockerCollision());
        strategyMap.put(FinalValues.BLOCKER, blockerStrategies);
        HashMap<String, CollisionStrategy> shooterStrategies = new HashMap<String, CollisionStrategy>();
        shooterStrategies.put(FinalValues.POWERUP, new ShooterPowerupCollision());
        shooterStrategies.put(FinalValues.CLOCK,new ShooterClockCollision());
        shooterStrategies.put(FinalValues.HEARTH,new ShooterHearthCollision());
        strategyMap.put(FinalValues.SHOOTER, shooterStrategies);
    }

    public void collisionDetect() {
        Collider collider1;
        Collider collider2;
        GameObject obj1;
        GameObject obj2;
        for (int i = 0; i < frameObjects.size(); i++) {
            obj1 = frameObjects.get(i);
            for (int j = 0; j < frameObjects.size(); j++) {
                obj2 = frameObjects.get(j);

                collider1 = new Collider(new Position((int) obj1.getX(), (int) obj1.getY()), obj1.getWidth(), obj2.getHeight(), 0,
                        obj1.getType().equals(FinalValues.BLOCKER));
                collider2 = new Collider(new Position((int) obj2.getX(), (int) obj2.getY()), obj2.getWidth(), obj2.getHeight(), 0,
                        obj2.getType().equals(FinalValues.BLOCKER));

                if (collider1.intersects(collider2)) {
                    if (strategyMap.get(obj1.getType()) != null) {
                        CollisionStrategy collisionStrategy = strategyMap.get(obj1.getType()).get(obj2.getType());
                        if (collisionStrategy != null) {
                            Collision collision = new Collision(collisionStrategy);
                            collision.executeCollision(obj1, obj2);

                            if (!obj2.isAlive()) {
                                frameObjects.remove(obj2);
                            }
                            frame.onDestroy(obj2);
                        }
                    }
                }
            }
            if (!obj1.isAlive()) {
                frameObjects.remove(obj1);
            }
            frame.onDestroy(obj1);
        }
    }





    /*
    public void collisionDetect(){

        for (int i=0;i<framePanels.size();i++) {
            ObjectPanel current= framePanels.get(i);
            for (int j = i; j < frameObjects.size(); j++) {
                ObjectPanel temp= framePanels.get(j);
                if(current.getBounds().intersects(temp.getBounds())){
                    frameObjects.get(i).collision(frameObjects.get(j));
                }
            }
        }
    }



    public void killObj(GameObject gameObject) {
        int index=-1;
        for (int i = 0; i < frameObjects.size(); i++) {
            if(frameObjects.get(i).equals(gameObject))
                index=i;
        }
        if(index!=-1){
            frameObjects.remove(index);
            framePanels.remove(index);
            notifyAll();
        }
    }

     */


}
