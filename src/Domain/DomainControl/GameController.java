package Domain.DomainControl;

import Domain.Blend.Blender;
import Domain.Statistics.GameData;
import Domain.UserFunctionalities.GameStatueControl;
import Domain.UserFunctionalities.SaveLoadHandler;
import Domain.Utils.AudioListener;

import java.util.HashMap;
import java.util.List;

public class GameController {

    BuildingMode buildingMode;
    RunningMode runningMode;

    private boolean isBlendModeActive = false;
    private boolean isFirstAtomSelected = false;
    private boolean isSecondAtomSelected = false;

    private int blenderCounter = 0;
    private String firstAtomForBlender = "";
    private String secondAtomForBlender = "";
    private GameStatueControl statueControl;
    private long lastTimeShoot = 0;
    private final int FIRE_RATE = 1000;

    private AudioListener audioListener;
    private static GameController gameController;

    private GameController(){}

    public static GameController getInstance(){
        if(gameController==null)
            gameController=new GameController();
        return gameController;
    }

    public void initialize(RunningMode runningMode) {
        this.runningMode = runningMode;
        buildingMode = new BuildingMode();
        statueControl = GameStatueControl.getInstance();
    }

    public void setAudioListener(AudioListener audioListener){
        this.audioListener = audioListener;
    }

    public void StartGame(HashMap<String, String> configurationInfo) {
        buildingMode.startNewGame(configurationInfo);
        runningMode.startGame();
    }

    GameData gameData;

    public void SaveGameData(String saveTitle, String fileName) {
        SaveLoadHandler saveLoadHandler=new SaveLoadHandler();
        saveLoadHandler.Save(saveTitle,fileName);
    }

    public List<HashMap<String, String>> LoadAllGames(){
        SaveLoadHandler saveLoadHandler=new SaveLoadHandler();
        return saveLoadHandler.LoadAllGames();
    }

    public void LoadGameData(String fileName) {
        SaveLoadHandler saveLoadHandler=new SaveLoadHandler();
        gameData=saveLoadHandler.Load(fileName);
    }

    public void LoadGame(){
        buildingMode.loadTheGame(gameData);
        runningMode.startGame();
    }


    public void Move(String direction) {
        if (!statueControl.isGamePaused()) {
            runningMode.shooterHandler.moveShooter(direction);
        }
    }

    public void Rotate(String direction) {
        if (!statueControl.isGamePaused()) {
            runningMode.shooterHandler.rotateShooter(direction);
        }
    }

    public void ChangeAtom() {
        if (!statueControl.isGamePaused()) {
            runningMode.shooterHandler.changeBullet();
        }
    }

    public void PickPowerup(String subtype) {
        if (!statueControl.isGamePaused()) {
            runningMode.shooterHandler.changeBulletToPowerup(subtype);
        }
    }

    public void Shoot() {
        if (!statueControl.isGamePaused() && (System.currentTimeMillis() - lastTimeShoot) >= FIRE_RATE) {
            runningMode.shooterHandler.fire(runningMode.objectCreationHandler);
            lastTimeShoot = System.currentTimeMillis();

            audioListener.onFire();
        }
    }

    public void Blend() {
        if (!statueControl.isGamePaused()) {
            if (isBlendModeActive) {
                isBlendModeActive = false;
            } else {
                isBlendModeActive = true;
            }
        }
    }

    public void Pause() {
        if (!statueControl.isGamePaused()) {
            runningMode.pauseGame();
        }
    }

    public void Resume() {
        if (statueControl.isGamePaused()) {
            runningMode.resumeGame();
        }
    }

    public void ChooseAtomForBlender(String type) {
        if (!statueControl.isGamePaused()) {
            blenderCounter++;
            if (isBlendModeActive) {
                if (blenderCounter == 1) {
                    // SET FIRST ATOM
                    firstAtomForBlender = type;
                    isFirstAtomSelected = true;
                } else if (blenderCounter == 2) {
                    // SET SECOND ATOM
                    secondAtomForBlender = type;
                    isSecondAtomSelected = true;
                }
            }
            if (isFirstAtomSelected && isSecondAtomSelected) {
                Blender blender = new Blender();
                blender.Transform(firstAtomForBlender, secondAtomForBlender);

                // RESET BLEND VALUES
                blenderCounter = 0;
                firstAtomForBlender = "";
                secondAtomForBlender = "";
                isSecondAtomSelected = false;
                isSecondAtomSelected = false;
            }
        }
    }

    public void EndGame(){
        runningMode.endGame();
    }

    public void GameOver(){
        audioListener.onGameOver();
    }

    public boolean isGamePaused() {
        return statueControl.isGamePaused();
    }

    public void addShield(String shieldtype) {
        runningMode.shooterHandler.addShield(shieldtype);
    }
}
