package Domain.ShooterFunctions;

import Domain.Objects.GameObject;
import Domain.Statistics.GameConfiguration;
import Domain.Statistics.GameData;
import Domain.Utils.Position;

import java.util.HashMap;

import static Domain.Utils.FinalValues.ATOM;

public class Shooter extends GameObject {
    private HashMap<String, HashMap<String, Integer>>  numOfBullets;
    private GameObject objectInTrigger=null;

    private static GameData gameData = GameConfiguration.getInstance().getData();

    public Shooter() {
        super("Shooter","1",new Position(gameData.getGameScreenWidth()/2,gameData.getGameScreenHeight() - 2*gameData.getL()),90,false);
        numOfBullets=GameConfiguration.getInstance().getData().getAmmunition();
        setVelocity(new Position(getL()/20,0));
        setHeight(getL());
        setWidth(getL()/3);
        initializeShooter();
    }

    public void setRotationAngle(int rotationAngle){
        getCurrentPosition().setRotation(rotationAngle);
    }

    public int getRotationAngle(){
        return getCurrentPosition().getRotation();
    }

    public void initializeShooter(){
        initializeBullets();
    }

    public void initializeBullets(){
        numOfBullets = getAmmunition();
    }

    public GameObject getObjectInTrigger() {
        return objectInTrigger;
    }

    public void setObjectInTrigger(GameObject objectInTrigger) {
        this.objectInTrigger = objectInTrigger;
    }

    public void reduceTheBullet(){ // reduce the number of bullet in the fire operation
        numOfBullets.get(objectInTrigger.getType()).replace(objectInTrigger.getSubType(),
                numOfBullets.get(objectInTrigger.getType()).get(objectInTrigger.getSubType())-1);
        GameConfiguration.getInstance().setAmmunition(numOfBullets);
    }

    public HashMap<String, HashMap<String, Integer>> getNumOfBullets() {
        return numOfBullets;
    }
    public HashMap<String, Integer> getNumOfAtoms(){
        return numOfBullets.get(ATOM);
    }


    public HashMap<String, HashMap<String, Integer>> getAmmunition(){
        return GameConfiguration.getInstance().getData().getAmmunition();
    }
}
