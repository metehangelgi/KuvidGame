package Domain.UserFunctionalities;

import Domain.Statistics.GameData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Load {

    public Load(){}

    public GameData LoadTheGame(JSONObject mainJsonObject){

        GameData data=new GameData();

        double health= (double) mainJsonObject.get("Health");
        data.setHealth(health);
        double score= (double) mainJsonObject.get("Score");
        data.setScore(score);
        double time= (double) mainJsonObject.get("Time");
        data.setRemainingTime(time);
        String alphaBetaMovement= (String) mainJsonObject.get("AlphaBetaMovement");
        data.setMovementType(alphaBetaMovement);
        String alphaBetaShape= (String) mainJsonObject.get("AlphaBetaShape");
        data.setAlphaBetaType(alphaBetaShape);
        String difficulty=(String) mainJsonObject.get("Difficulty");
        data.setDifficulty(difficulty);

        HashMap<String,HashMap<String,Integer>> remainingObjects= new HashMap<>();
        data.setRemainingObjects(remainingObjects);
        HashMap<String,HashMap<String,Integer>> ammunition= new HashMap<>();
        data.setAmmunition(ammunition);
        HashMap<String,Integer> remainingShield= new HashMap<>();
        data.setRemainingShield(remainingShield);
        ArrayList<String[]> objectsOnFrame=new ArrayList<>();
        data.setObjectsOnFrame(objectsOnFrame);

        JSONArray jsonRemainingObjectArray=(JSONArray) mainJsonObject.get("RemainingObjects");
        for (int i = 0; i < jsonRemainingObjectArray.size(); i++) {
            JSONObject jsonObject= (JSONObject) jsonRemainingObjectArray.get(i);
            String type= (String) jsonObject.get("Type");
            JSONArray jsonArray= (JSONArray) jsonObject.get("Array");
            HashMap<String,Integer> hashMap=new HashMap<>();
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jo= (JSONObject) jsonArray.get(j);
                String subtype= (String) jo.get("Subtype");
                int amount= (int) (long) jo.get("Amount");
                hashMap.put(subtype,amount);
            }
            remainingObjects.put(type,hashMap);
        }

        JSONArray jsonAmmunitionArray= (JSONArray) mainJsonObject.get("Ammunition");
        for (int i = 0; i < jsonAmmunitionArray.size(); i++) {
            JSONObject jsonObject= (JSONObject) jsonAmmunitionArray.get(i);
            String type= (String) jsonObject.get("Type");
            JSONArray jsonArray= (JSONArray) jsonObject.get("Array");
            HashMap<String,Integer> hashMap=new HashMap<>();
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jo= (JSONObject) jsonArray.get(j);
                String subtype= (String) jo.get("Subtype");
                int amount= (int) (long) jo.get("Amount");
                hashMap.put(subtype,amount);
            }
            ammunition.put(type,hashMap);
        }

        JSONArray jsonRemainingShieldArray= (JSONArray) mainJsonObject.get("Shield");
        for (int i = 0; i < jsonRemainingShieldArray.size(); i++) {
            JSONObject jo= (JSONObject) jsonRemainingShieldArray.get(i);
            String type= (String) jo.get("Type");
            int amount= (int) (long) jo.get("Amount");
            remainingShield.put(type,amount);
        }


        JSONArray jsonFrameObjectArray= (JSONArray) mainJsonObject.get("FrameObjects");
        for (int i = 0; i < jsonFrameObjectArray.size(); i++) {
            JSONObject jo= (JSONObject) jsonFrameObjectArray.get(i);
            String[] info=new String[5];
            info[0]= (String) jo.get("Type");
            info[1]= (String) jo.get("Subtype");
            double xPos= (double) jo.get("XPos");
            double yPos=(double) jo.get("YPos");
            info[2]=String.valueOf(xPos);
            info[3]=String.valueOf(yPos);
            boolean isFallable= (boolean) jo.get("IsFallable");
            info[4]=String.valueOf(isFallable);
            objectsOnFrame.add(info);
        }

        return data;

    }
}
