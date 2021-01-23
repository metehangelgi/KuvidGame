package Domain.DomainControl;

import Domain.Player.Player;
import Domain.Statistics.GameConfiguration;
import Domain.Statistics.GameData;
import Domain.Utils.*;

import java.util.ArrayList;
import java.util.HashMap;

public class BuildingMode {

    public void startNewGame(HashMap<String, String> configMap) {
        GameData gameData = hashToGameData(configMap);
        GameConfiguration.getInstance().setData(gameData);

        Player.getInstance().setScore(gameData.getScore());
        Player.getInstance().setHealth(gameData.getHealth());
        //RunningMode runningMode = new RunningMode();
    }

    public void loadTheGame(GameData data) {
        GameConfiguration gameConfiguration=GameConfiguration.getInstance();
        GameData gameData=gameConfiguration.getData();
        if(data!=null){
            if(gameData!=null){
                gameConfiguration.setTime(data.getRemainingTime());
                gameConfiguration.setAmmunition(data.getAmmunition());
                gameConfiguration.setRemainingShield(data.getRemainingShield());
                gameData.setAlphaBetaType(data.getAlphaBetaType());
                gameData.setMovementType(data.getMovementType());
                gameData.setRemainingObjects(data.getRemainingObjects());
                gameData.setObjectsOnFrame(data.getObjectsOnFrame());
                gameData.setDifficulty(data.getDifficulty());
                gameData.setShieldedAtoms(new ArrayList<>());
                Player.getInstance().setHealth(data.getHealth());
                Player.getInstance().setScore(data.getScore());
            }else {
                GameConfiguration.getInstance().setData(data);
            }
        }
    }

    public GameData hashToGameData(HashMap<String, String> configHash) {
        GameData gameData = new GameData();

        gameData.setGameScreenWidth(Integer.parseInt(configHash.get("width")));
        gameData.setGameScreenHeight(Integer.parseInt(configHash.get("height")));

        boolean isLoaded = false;
        gameData.setLoaded(isLoaded);

        //----Ammunition
        HashMap<String, HashMap<String, Integer>> ammunition = new HashMap<>();

        int atomAmount = Integer.parseInt(configHash.get(FinalValues.ATOM));
        HashMap<String, Integer> initialAtomAmountHash = new HashMap<>();
        initialAtomAmountHash.put(FinalValues.ALPHA, atomAmount);
        initialAtomAmountHash.put(FinalValues.BETA, atomAmount);
        initialAtomAmountHash.put(FinalValues.GAMMA, atomAmount);
        initialAtomAmountHash.put(FinalValues.SIGMA, atomAmount);

        HashMap<String, Integer> initialPowerupAmountHash = new HashMap<>();
        initialPowerupAmountHash.put(FinalValues.ALPHA, 0);
        initialPowerupAmountHash.put(FinalValues.BETA, 0);
        initialPowerupAmountHash.put(FinalValues.GAMMA, 0);
        initialPowerupAmountHash.put(FinalValues.SIGMA, 0);

        ammunition.put(FinalValues.ATOM, initialAtomAmountHash);
        ammunition.put(FinalValues.POWERUP, initialPowerupAmountHash);
        gameData.setAmmunition(ammunition);

        //----Remaining Objects (Falling)
        HashMap<String, HashMap<String, Integer>> remainingObjects = new HashMap<>();

        int moleculeAmount = Integer.parseInt(configHash.get(FinalValues.MOLECULE));
        HashMap<String, Integer> moleculeAmountHash = new HashMap<>();
        moleculeAmountHash.put(FinalValues.ALPHA, moleculeAmount);
        moleculeAmountHash.put(FinalValues.BETA, moleculeAmount);
        moleculeAmountHash.put(FinalValues.GAMMA, moleculeAmount);
        moleculeAmountHash.put(FinalValues.SIGMA, moleculeAmount);

        int blockerAmount = Integer.parseInt(configHash.get(FinalValues.BLOCKER));
        HashMap<String, Integer> blockerAmountHash = new HashMap<>();
        blockerAmountHash.put(FinalValues.ALPHA, blockerAmount);
        blockerAmountHash.put(FinalValues.BETA, blockerAmount);
        blockerAmountHash.put(FinalValues.GAMMA, blockerAmount);
        blockerAmountHash.put(FinalValues.SIGMA, blockerAmount);

        int powerupAmount = Integer.parseInt(configHash.get(FinalValues.POWERUP));
        HashMap<String, Integer> powerupAmountHash = new HashMap<>();
        powerupAmountHash.put(FinalValues.ALPHA, powerupAmount);
        powerupAmountHash.put(FinalValues.BETA, powerupAmount);
        powerupAmountHash.put(FinalValues.GAMMA, powerupAmount);
        powerupAmountHash.put(FinalValues.SIGMA, powerupAmount);

        remainingObjects.put(FinalValues.MOLECULE, moleculeAmountHash);
        remainingObjects.put(FinalValues.BLOCKER, blockerAmountHash);
        remainingObjects.put(FinalValues.POWERUP, powerupAmountHash);

        //Clock
        int clockAmount=50;
        HashMap<String,Integer> clockAmountHash=new HashMap<>();
        clockAmountHash.put("1",clockAmount);
        remainingObjects.put(FinalValues.CLOCK,clockAmountHash);

        //Hearth
        int hearthAmount=50;
        HashMap<String,Integer> hearthAmountHash=new HashMap<>();
        hearthAmountHash.put("1",hearthAmount);
        remainingObjects.put(FinalValues.HEARTH,hearthAmountHash);

        gameData.setRemainingObjects(remainingObjects);

        //----Difficulty
        String difficultyStr = configHash.get("difficulty");
        Difficulty difficulty;
        if (difficultyStr.equals(Difficulty.HARD.toString())) {
            difficulty = Difficulty.HARD;
        } else if (difficultyStr.equals(Difficulty.NORMAL.toString())) {
            difficulty = Difficulty.NORMAL;
        } else {
            difficulty = Difficulty.EASY;
        }
        gameData.setDifficulty(difficulty);

        //---Length
        int length = Integer.parseInt(configHash.get("length"));
        gameData.setL(length);

        //---Type
        String alphaBetaType = configHash.get("structure");
        gameData.setAlphaBetaType(alphaBetaType);

        //---Movement
        String movementType = configHash.get("fallingType");
        gameData.setMovementType(movementType);

        double time = 3600.0;
        gameData.setRemainingTime(time);

        double score = 0.0;
        gameData.setScore(score);


        double health = 100.0;
        gameData.setHealth(health);


        HashMap<String, Integer> remainingShield = new HashMap<>();
        remainingShield.put(FinalValues.ETA, 10);
        remainingShield.put(FinalValues.LOTA, 10);
        remainingShield.put(FinalValues.THETA, 10);
        remainingShield.put(FinalValues.ZETA, 10);
        gameData.setRemainingShield(remainingShield);

        gameData.setShieldedAtoms(new ArrayList<>());
        return gameData;
    }
}
