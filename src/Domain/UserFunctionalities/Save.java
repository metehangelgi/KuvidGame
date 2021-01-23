package Domain.UserFunctionalities;

import Domain.Objects.GameObject;
import Domain.Statistics.GameConfiguration;
import Domain.Statistics.GameData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Save {
    private String username;
    private String saveTitle;

    public Save(String saveTitle,String username){
        this.saveTitle=saveTitle;
        this.username=username;
    }

    public JSONObject SaveTheGame(){
        GameConfiguration gameConfiguration = GameConfiguration.getInstance();
        GameData gameData=gameConfiguration.getData();

        double health= gameData.getHealth();
        double score= gameData.getScore();
        double time= gameData.getRemainingTime();
        String difficulty=gameData.getDifficulty().toString();
        String alphaBetaMovement= gameData.getMovementType();
        String alphaBetaShape= gameData.getAlphaBetaType();
        HashMap<String,HashMap<String,Integer>> ammunition= gameData.getAmmunition();
        HashMap<String,HashMap<String,Integer>> remainingObjects= gameData.getRemainingObjects();
        CopyOnWriteArrayList<GameObject> frameObjects=gameData.getFrameObjects();
        HashMap<String,Integer> remainingShielded=gameData.getRemainingShield();



        JSONObject mainJsonObject=new JSONObject();
        mainJsonObject.put("title",saveTitle);
        mainJsonObject.put("username",username);
        mainJsonObject.put("Health",health);
        mainJsonObject.put("Score",score);
        mainJsonObject.put("Time",time);
        mainJsonObject.put("AlphaBetaMovement",alphaBetaMovement);
        mainJsonObject.put("AlphaBetaShape",alphaBetaShape);
        mainJsonObject.put("Difficulty",difficulty);



        JSONArray jsonRemainingObjectArray=new JSONArray();
        for (String type:remainingObjects.keySet()){
            HashMap<String,Integer> map=remainingObjects.get(type);
            JSONObject jo1=new JSONObject();
            JSONArray jsonArray1=new JSONArray();
            for (String subtype:map.keySet()) {
                JSONObject jo=new JSONObject();
                int amount=map.get(subtype);
                jo.put("Subtype",subtype);
                jo.put("Amount",amount);
                jsonArray1.add(jo);
            }
            jo1.put("Type",type);
            jo1.put("Array",jsonArray1);
            jsonRemainingObjectArray.add(jo1);
        }
        mainJsonObject.put("RemainingObjects",jsonRemainingObjectArray);

        JSONArray jsonAmmunitionArray=new JSONArray();
        for (String type:ammunition.keySet()){
            HashMap<String,Integer> map=ammunition.get(type);
            JSONObject jo1=new JSONObject();
            JSONArray jsonArray1=new JSONArray();
            for (String subtype:map.keySet()) {
                JSONObject jo=new JSONObject();
                int amount=map.get(subtype);
                jo.put("Subtype",subtype);
                jo.put("Amount",amount);
                jsonArray1.add(jo);
            }
            jo1.put("Type",type);
            jo1.put("Array",jsonArray1);
            jsonAmmunitionArray.add(jo1);
        }
        mainJsonObject.put("Ammunition",jsonAmmunitionArray);

        JSONArray jsonRemainingShieldArray=new JSONArray();
        for (String type:remainingShielded.keySet()) {
            int amount=remainingShielded.get(type);
            JSONObject jo=new JSONObject();
            jo.put("Type",type);
            jo.put("Amount",amount);
            jsonRemainingShieldArray.add(jo);
        }
        mainJsonObject.put("Shield",jsonRemainingShieldArray);

        JSONArray jsonFrameObjectArray=new JSONArray();
        for (int i = 0; i < frameObjects.size(); i++) {
            GameObject currentObject=frameObjects.get(i);
            String type=currentObject.getType();
            String subtype=currentObject.getSubType();
            double xPos=currentObject.getX();
            double yPos=currentObject.getY();
            boolean isFallable=currentObject.isFallable();
            JSONObject jo=new JSONObject();
            jo.put("Type",type);
            jo.put("Subtype",subtype);
            jo.put("XPos",xPos);
            jo.put("YPos",yPos);
            jo.put("IsFallable",isFallable);
            jsonFrameObjectArray.add(jo);
        }
        mainJsonObject.put("FrameObjects",jsonFrameObjectArray);
        System.out.println(mainJsonObject);

        return mainJsonObject;
    }

}
