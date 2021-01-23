package UI;

import Domain.DomainControl.GameController;
import Domain.DomainControl.GameOverListener;
import Domain.DomainControl.RunningMode;
import Domain.Statistics.GameConfiguration;
import Domain.UserFunctionalities.GameStatueControl;
import UI.Audio.AudioController;

import java.util.HashMap;

public class UIController implements GameOverListener {

    private PauseWindow pauseWindow;

    private static UIController instance;

    private GameController gameController;

    public boolean isGameRunning = false;

    public static UIController GetInstance() {
        if (instance == null) {
            instance = new UIController();
        }
        return instance;
    }

    public UIController() {
        instance = this;
    }

    private GameScreen gameScreen;
    private HashMap<String, String> configurationInfo;

    public void openHomeScreen() {
        new HomeScreen();
    }

    public void openConfigureScreen() {
        new ConfigureScreen();
    }

    public void setConfigurationInfo(HashMap<String, String> configurationInfo){
        this.configurationInfo = configurationInfo;
    }

    public void initializeGame(){
        gameScreen = new GameScreen();
        RunningMode runningMode = new RunningMode(gameScreen);
        runningMode.setGameOverListener(this);
        gameScreen.initialize(runningMode);
        gameController = GameController.getInstance();
        gameController.initialize(runningMode);
        gameController.setAudioListener(AudioController.GetInstance());
    }

    public void startGame() {
        gameController.StartGame(configurationInfo);
        gameScreen.InitializeGameScreen(gameController);

        isGameRunning = true;
    }

    public void openLoadWindow() {
        new LoadWindow();
    }

    public void loadGame(){
        gameScreen.InitializeGameScreen(gameController);

        isGameRunning = true;
    }

    public void restartGame(){
        restartNewGame();
        ClosePauseWindow();
    }

    public void restartNewGame() {
        gameScreen.CloseGameScreen();
        GameStatueControl.getInstance().setResumed();
        GameStatueControl.getInstance().setGameEnded(false);
        initializeGame();
        startGame();
    }

    private void ClosePauseWindow() {
        pauseWindow.ClosePauseWindow();
    }

    public void openSaveWindow() {
        new SaveWindow();
    }

    public void openGameOverWindow() {
        new GameOverWindow(gameController, GameConfiguration.getInstance().getData().getScore());
    }

    public void endGame() {
        // close game screen
        gameController.EndGame();
        gameScreen.CloseGameScreen();
        isGameRunning = false;
    }

    public void pause() {
        pauseWindow = new PauseWindow(gameController);
    }

    public void openCredits() {

    }

    public void openOptions() {
        new OptionsWindow();
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    @Override
    public void onGameOver() {
        openGameOverWindow();
    }
}