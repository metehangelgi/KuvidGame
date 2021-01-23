package Domain.ObjectCreation;

import Domain.Objects.ObjectListener;
import Domain.Objects.GameObject;
import Domain.Statistics.GameConfiguration;
import Domain.Statistics.GameData;
import Domain.Utils.FinalValues;
import Domain.Utils.Position;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectCreationHandler {

    private final CopyOnWriteArrayList<GameObject> frameObjects;
    private final ObjectListener frame;
    private final HashMap<String, HashMap<String, Integer>> remainingObjects;
    private final int L;
    private final int gameScreenWidth;
    private ObjectFactory objectFactory;


    public void initiateFrame(){
        GameData gameData=GameConfiguration.getInstance().getData();
        ArrayList<String[]> frame= gameData.getObjectsOnFrame();
        if (frame!=null){
            for (int i = 1; i < frame.size(); i++) {
                String[] info= frame.get(i);
                String type=info[0];
                String subtype=info[1];
                double xPos=Double.parseDouble(info[2]);
                double yPos=Double.parseDouble(info[3]);
                boolean isFallable=Boolean.parseBoolean(info[4]);
                Position position=new Position(xPos,yPos);
                createGameObject(type,subtype,position,isFallable);
            }
        }
    }

    public ObjectCreationHandler(CopyOnWriteArrayList<GameObject> frameObjects, ObjectListener frame) {
        GameData gameData=GameConfiguration.getInstance().getData();
        this.frameObjects=frameObjects;
        this.frame=frame;
        this.objectFactory=ObjectFactory.getInstance();
        this.remainingObjects=gameData.getRemainingObjects();
        L=gameData.getL();
        gameScreenWidth =gameData.getGameScreenWidth();
        initiateFrame();
    }


    public GameObject createRandomFallingObject(){
        //
        // requires: remaining objects not null
        // effects: decrease remainingObjects(type,subtype) by 1.
        //          call createGameObject(type,subtype, randomInitialPosition()) to create object
        //          and add it to the frameObject list.
        // returns: Created gameObject
        String type=null;
        String subtype=null;
        while (subtype == null) {
            type = getRandomType();
            if(type.equals(FinalValues.CLOCK)||type.equals(FinalValues.HEARTH))
                subtype="1";
            else
                subtype = getRandomSubType(type,subtype);
        }

        remainingObjects.get(type).replace(subtype,remainingObjects.get(type).get(subtype)-1);
        return createGameObject(type,subtype, randomInitialPosition());
    }

    public GameObject createGameObject(String type, String subtype, Position position){
        // requires: type,subtype is String ; position is position (x,y)
        //           type,subtype,position is not null
        // effects: call createGameObject(type,subtype, position,true) to create fallable object
        //          and add it to the frameObject list.
        // returns: Created gameObject
        return createGameObject(type,subtype,position,true);
    }

    public GameObject createGameObject(String type,String subtype,Position position,boolean isFallable){
        // requires: type,subtype is String ; position is position (x,y)
        //           type,subtype,position,isFallable is not null
        // effects: create new gameObject with given parameters,
        //          call createGameObject(gameObject),
        //          and add created object to the frameObject list.
        // returns: Created gameObject
        GameObject gameObject=objectFactory.createObject(type,subtype, position,isFallable);
        createGameObject(gameObject);
        return gameObject;
    }

    public GameObject createGameObject(GameObject gameObject){
        // requires: gameObject is instance of GameObject, and not null
        // effects: add given object to the frameObject list. call frame.onCreate to show added object on Frame
        // returns: Created gameObject
        frameObjects.add(gameObject);
        frame.onCreate(gameObject);
        return gameObject;
    }

    private String getRandomType() {
        int random=(int) (Math.random() * 140);
        if (random<100){
            return FinalValues.MOLECULE;
        }
        else if(random<115 && random>=100) {
            return FinalValues.BLOCKER;
        } else if(random<130&&random>=115){
            return FinalValues.POWERUP;
        }else if(random<135&&random>=130){
            return FinalValues.CLOCK;
        }else {
            return FinalValues.HEARTH;
        }
    }

    private String getRandomSubType(String type,String subtype) {
        int length=remainingObjects.get(type).keySet().size();
        int random = (int) (Math.random() * length);
        if (random == 0) {if (remainingObjects.get(type).get(FinalValues.ALPHA) > 0) subtype = FinalValues.ALPHA;}
        else if (random == 1){ if(remainingObjects.get(type).get(FinalValues.BETA)>0) subtype =FinalValues.BETA;}
        else if (random == 2){ if(remainingObjects.get(type).get(FinalValues.GAMMA)>0) subtype =FinalValues.GAMMA;}
        else {if(remainingObjects.get(type).get(FinalValues.SIGMA)>0) subtype =FinalValues.SIGMA;}
        return subtype;
    }

    private Position randomInitialPosition(){
        int y=-L;
        int x= (new Random()).nextInt(gameScreenWidth *4/5-L);

        return new Position(x,y); /// randomize position
    }

}
