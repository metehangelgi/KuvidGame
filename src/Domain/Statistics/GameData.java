package Domain.Statistics;

import Domain.Objects.*;
import Domain.ShooterFunctions.Shooter;
import Domain.Utils.Difficulty;
import UI.ScreenInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData{

    private CopyOnWriteArrayList<GameObject> frameObjects = null;
    private ArrayList<GameObject> shieldedAtoms = new ArrayList<>();

    private double health=100;
    private double score=0;
    private double remainingTime=10000;

    private boolean isLoaded = false;
    private HashMap<String, HashMap<String, Integer>> ammunition;
    private HashMap<String, HashMap<String, Integer>> remainingObjects;
    private HashMap<String,Integer> remainingShield;
    private ArrayList<String[]> objectsOnFrame=null;

    private Difficulty difficulty=Difficulty.NORMAL;
    private int L;
    private String alphaBetaType;
    private String movementType;

    private int gameScreenWidth= ScreenInfo.GAME_SCREEN_WIDTH;
    private int gameScreenHeight=ScreenInfo.GAME_SCREEN_HEIGHT;

    private Shooter shooter;

    public GameData(){
        setL(10);
    }

    public CopyOnWriteArrayList<GameObject> getFrameObjects() {
        return frameObjects;
    }

    public void setFrameObjects(CopyOnWriteArrayList<GameObject> frameObjects) {
        this.frameObjects = frameObjects;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(double remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public HashMap<String, HashMap<String, Integer>> getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(HashMap<String, HashMap<String, Integer>> ammunition) {
        this.ammunition = ammunition;
    }

    public HashMap<String, HashMap<String, Integer>> getRemainingObjects() {
        return remainingObjects;
    }

    public void setRemainingObjects(HashMap<String, HashMap<String, Integer>> remainingObjects) {
        this.remainingObjects = remainingObjects;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty diff=Difficulty.NORMAL;
        switch (difficulty){
            case "EASY":
                diff=Difficulty.EASY;
                break;
            case "NORMAL":
                diff=Difficulty.NORMAL;
                break;
            case "HARD":
                diff=Difficulty.HARD;
                break;
        }
        this.difficulty = diff;
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L=(gameScreenHeight*l)/100;
    }

    public String getAlphaBetaType() {
        return alphaBetaType;
    }

    public void setAlphaBetaType(String alphaBetaType) {
        this.alphaBetaType = alphaBetaType;
    }

    public int getGameScreenWidth(){
        return gameScreenWidth;
    }

    public void setGameScreenWidth(int gameScreenWidth){
        this.gameScreenWidth = gameScreenWidth;
    }

    public int getGameScreenHeight(){
        return gameScreenHeight;
    }

    public void setGameScreenHeight(int gameScreenHeight){
        this.gameScreenHeight = gameScreenHeight;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public void setShooter(Shooter shooter) {
        this.shooter = shooter;
    }

    public HashMap<String, Integer> getRemainingShield() {
        return remainingShield;
    }

    public void setRemainingShield(HashMap<String, Integer> remainingShield) {
        this.remainingShield = remainingShield;
    }

    public ArrayList<GameObject> getShieldedAtoms() {
        return shieldedAtoms;
    }

    public void setShieldedAtoms(ArrayList<GameObject> shieldedAtoms) {
        this.shieldedAtoms = shieldedAtoms;
    }

    public ArrayList<String[]> getObjectsOnFrame() {
        return objectsOnFrame;
    }

    public void setObjectsOnFrame(ArrayList<String[]> objectsOnFrame) {
        this.objectsOnFrame = objectsOnFrame;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "frameObjects=" + frameObjects +
                ", shieldedAtoms=" + shieldedAtoms +
                ", health=" + health +
                ", score=" + score +
                ", remainingTime=" + remainingTime +
                ", isLoaded=" + isLoaded +
                ", ammunition=" + ammunition +
                ", remainingObjects=" + remainingObjects +
                ", remainingShield=" + remainingShield +
                ", difficulty=" + difficulty +
                ", alphaBetaType='" + alphaBetaType + '\'' +
                ", movementType='" + movementType + '\'' +
                ", gameScreenWidth=" + gameScreenWidth +
                ", gameScreenHeight=" + gameScreenHeight +
                ", shooter=" + shooter +
                '}';
    }
}
