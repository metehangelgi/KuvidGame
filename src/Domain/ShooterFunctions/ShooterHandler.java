package Domain.ShooterFunctions;

import Domain.ObjectCreation.ObjectCreationHandler;
import Domain.ObjectCreation.ObjectFactory;
import Domain.Objects.*;
import Domain.ShieldFeature.Eta_Shield;
import Domain.ShieldFeature.Lota_Shield;
import Domain.ShieldFeature.Theta_Shield;
import Domain.ShieldFeature.Zeta_Shield;
import Domain.Statistics.GameConfiguration;
import Domain.Statistics.GameData;
import Domain.Utils.FinalValues;
import Domain.Utils.Position;

import java.util.HashMap;

import static Domain.Utils.FinalValues.ATOM;
import static Domain.Utils.FinalValues.POWERUP;


public class ShooterHandler {

    private Shooter shooter=GameConfiguration.getInstance().getData().getShooter();
    private ShooterMovement shooterMovement;
    private ObjectListener frameListener;
    private GameData gameData;
    private HashMap<String,Integer> remainingShield;


    public ShooterHandler(ObjectListener frameListener) {
        this.frameListener=frameListener;
        this.gameData = GameConfiguration.getInstance().getData();
        this.remainingShield= gameData.getRemainingShield();
        this.shooterMovement=new ShooterMovement(shooter,gameData);
    }

    public Shooter createShooter() {
        this.shooter=new Shooter();
        shooterMovement.setShooter(shooter);
        shooter.setCollectible(true);
        shooter.setY(GameConfiguration.getInstance().getData().getGameScreenHeight()-200);
        int x=(int) shooter.getX()+shooter.getWidth()/3;
        int y=(int) shooter.getY()-shooter.getHeight()/4;
        shooter.setObjectInTrigger(new Alpha_Atom(new Position(x,y),90));
        frameListener.onCreate(shooter);
        GameConfiguration.getInstance().getData().setShooter(shooter);
        return shooter;
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

    public void moveShooter(String direction){
        shooterMovement.moveShooter(direction);
        frameListener.onShooterPositionChange();
    }

    public void fire(ObjectCreationHandler objectCreationHandler){
        Position triggerPosition = setTriggerPosition(shooter.getObjectInTrigger().getType());

        if(shooter.getObjectInTrigger().getType().equals(ATOM)){
            if (((Atom) shooter.getObjectInTrigger()).isShielded())
                gameData.getShieldedAtoms().remove(shooter.getObjectInTrigger());
        } else {
            triggerPosition.setY((int) shooter.getY()-shooter.getHeight());
        }

        shooter.getObjectInTrigger().setCurrentPosition(triggerPosition);
        GameObject fired=objectCreationHandler.createGameObject(shooter.getObjectInTrigger());
        fired.setAngle(90-shooter.getRotationAngle());
        shooter.reduceTheBullet();
        changeBullet();

    }

    public void rotateShooter(String direction) {
        shooterMovement.rotateShooter(direction);
        frameListener.onShooterPositionChange();
    }

    public void changeBullet(){
        GameObject object;
        Position triggerPosition = setTriggerPosition(ATOM);
        if((!gameData.getShieldedAtoms().isEmpty()) && (Math.random() * 4)<=1){
            object=gameData.getShieldedAtoms().get(0);
            object.setCurrentPosition(triggerPosition);
        } else {
            String subtype = null;
            while (subtype == null) {
                int random = (int) (Math.random() * 4);
                if (random == 0) {
                    if (shooter.getNumOfAtoms().get(FinalValues.ALPHA) > 0) subtype = FinalValues.ALPHA;
                } else if (random == 1) {
                    if (shooter.getNumOfAtoms().get(FinalValues.BETA) > 0) subtype = FinalValues.BETA;
                } else if (random == 2) {
                    if (shooter.getNumOfAtoms().get(FinalValues.GAMMA) > 0) subtype = FinalValues.GAMMA;
                } else {
                    if (shooter.getNumOfAtoms().get(FinalValues.SIGMA) > 0) subtype = FinalValues.SIGMA;
                }
            }

            object = ObjectFactory.getInstance().createObject(ATOM, subtype,triggerPosition, false);
        }

        shooter.setObjectInTrigger(object);
        frameListener.onShooterTriggerBulletChange();
    }

    public void changeBulletToPowerup(String subtype){ //change the bullet to desired type powerup object
        if(shooter.getNumOfBullets().get(FinalValues.POWERUP).get(subtype)>0) {
            Position triggerPosition = setTriggerPosition(POWERUP);
            GameObject object=ObjectFactory.getInstance().createObject(POWERUP,subtype,triggerPosition,false);
            shooter.setObjectInTrigger(object);
            frameListener.onShooterTriggerBulletChange();
        }
    }


    public void addShield(String shieldType){
        if(shooter.getObjectInTrigger().getType().equals(POWERUP)) return;

        GameObject shieldedObj=null;
        Integer remaining=0;
        switch (shieldType){
            case FinalValues.ETA:
                remaining=remainingShield.get(FinalValues.ETA);
                if(remaining>0)
                shieldedObj=add_Eta(shooter.getObjectInTrigger());
                break;
            case FinalValues.LOTA:
                remaining=remainingShield.get(FinalValues.LOTA);
                if(remaining>0)
                shieldedObj=add_Lota(shooter.getObjectInTrigger());
                break;
            case FinalValues.THETA:
                remaining=remainingShield.get(FinalValues.THETA);
                if(remaining>0)
                shieldedObj=add_Theta(shooter.getObjectInTrigger());
                break;
            case FinalValues.ZETA:
                remaining=remainingShield.get(FinalValues.ZETA);
                if(remaining>0)
                shieldedObj=add_Zeta(shooter.getObjectInTrigger());
                break;
        }
        if(remaining>0){
            Position triggerPosition = setTriggerPosition(ATOM);
            shieldedObj.setCurrentPosition(triggerPosition);
            shooter.setObjectInTrigger(shieldedObj);

            remainingShield.replace(shieldType,remainingShield.get(shieldType)-1);
            GameConfiguration.getInstance().setRemainingShield(remainingShield);

            GameConfiguration.getInstance().getData().getShieldedAtoms().add(shieldedObj);
            frameListener.onShooterTriggerBulletChange();
        }


    }


    public GameObject add_Eta(GameObject obj){
        return new Eta_Shield((Atom) obj);
    }
    public GameObject add_Lota(GameObject obj){
        return new Lota_Shield((Atom) obj);
    }
    public GameObject add_Theta(GameObject obj){
        return new Theta_Shield((Atom) obj);
    }
    public GameObject add_Zeta(GameObject obj){
        return new Zeta_Shield((Atom) obj);
    }

}
