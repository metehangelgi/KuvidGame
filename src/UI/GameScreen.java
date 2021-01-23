package UI;

import Domain.DomainControl.GameController;
import Domain.Objects.ObjectListener;
import Domain.DomainControl.RunningMode;
import Domain.Objects.GameObject;
import Domain.Statistics.GameConfiguration;
import Domain.Statistics.GameData;
import Domain.Utils.GameActionHandler;
import Domain.Utils.HotKeys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameScreen extends JFrame implements ObjectListener {

    private StatisticsWindow statisticsWindow;
    private JFrame gameScreen;
    private GameController gameController;
    private GamePanel gamePanel;

    public GameScreen() {
        CreateUIElements();
    }

    public void InitializeGameScreen(GameController gameController) {
        this.gameController = gameController;

        statisticsWindow.SetData(GameConfiguration.getInstance().getData());
        statisticsWindow.SetGameController(gameController);
        GameActionListener();
    }

    public void LoadGameScreen(GameController gameController, GameData data){
        this.gameController = gameController;
        statisticsWindow.SetData(data);
        statisticsWindow.SetGameController(gameController);
        GameActionListener();
    }

    private void CreateUIElements() {
        gameScreen = new JFrame("KUVID GAME");
        gameScreen.setSize(ScreenInfo.WINDOW_WIDTH, ScreenInfo.WINDOW_HEIGHT);
        gameScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameScreen.setResizable(false);

        statisticsWindow = new StatisticsWindow(ScreenInfo.STATISTICS_WINDOW_WIDTH, ScreenInfo.GAME_SCREEN_HEIGHT);
        GameConfiguration.getInstance().setStaticWindowListener(statisticsWindow);
        gameScreen.add(statisticsWindow.panelMain, BorderLayout.EAST);
        statisticsWindow.panelMain.setPreferredSize(new Dimension(ScreenInfo.STATISTICS_WINDOW_WIDTH, ScreenInfo.GAME_SCREEN_HEIGHT));

        gamePanel = new GamePanel(ScreenInfo.GAME_SCREEN_WIDTH, ScreenInfo.GAME_SCREEN_HEIGHT);
        gameScreen.add(gamePanel);

        pack();
        gameScreen.setVisible(true);
    }

    private void GameActionListener() {
        final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

        JPanel contentPane = (JPanel) gameScreen.getContentPane();

        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.MOVE_LEFT_PRIMARY.getValue(), 0), "move left");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.MOVE_LEFT_SECONDARY.getValue(), 0), "move left");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.MOVE_RIGHT_PRIMARY.getValue(), 0), "move right");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.MOVE_RIGHT_SECONDARY.getValue(), 0), "move right");

        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ROTATE_LEFT_PRIMARY.getValue(), 0), "rotate left");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ROTATE_RIGHT_PRIMARY.getValue(), 0), "rotate right");

        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.SHOOT_PRIMARY.getValue(), 0), "fire");
        contentPane.getInputMap().put(KeyStroke.getKeyStroke(HotKeys.SHOOT_SECONDARY.getValue(), 0), "fire");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.CHANGE_ATOM_PRIMARY.getValue(), 0), "pick atom");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.CHANGE_ATOM_SECONDARY.getValue(), 0), "pick atom");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.BLEND_PRIMARY.getValue(), 0), "blend");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.BLEND_SECONDARY.getValue(), 0), "blend");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.PAUSE_PRIMARY.getValue(), 0), "pause");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.PAUSE_SECONDARY.getValue(), 0), "pause");

        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_ALPHA_PRIMARY.getValue(), HotKeys.POWERUP_SELECTOR.getValue()), "choose powerup 1");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_ALPHA_SECONDARY.getValue(), HotKeys.POWERUP_SELECTOR.getValue()), "choose powerup 1");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_BETA_PRIMARY.getValue(), HotKeys.POWERUP_SELECTOR.getValue()), "choose powerup 2");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_BETA_SECONDARY.getValue(), HotKeys.POWERUP_SELECTOR.getValue()), "choose powerup 2");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_GAMMA_PRIMARY.getValue(), HotKeys.POWERUP_SELECTOR.getValue()), "choose powerup 3");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_GAMMA_SECONDARY.getValue(), HotKeys.POWERUP_SELECTOR.getValue()), "choose powerup 3");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_SIGMA_PRIMARY.getValue(), HotKeys.POWERUP_SELECTOR.getValue()), "choose powerup 4");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_SIGMA_SECONDARY.getValue(), HotKeys.POWERUP_SELECTOR.getValue()), "choose powerup 4");

        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_ALPHA_PRIMARY.getValue(), HotKeys.SHIELD_SELECTOR.getValue()), "eta shield");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_ALPHA_SECONDARY.getValue(), HotKeys.SHIELD_SELECTOR.getValue()), "eta shield");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_BETA_PRIMARY.getValue(), HotKeys.SHIELD_SELECTOR.getValue()), "lota shield");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_BETA_SECONDARY.getValue(), HotKeys.SHIELD_SELECTOR.getValue()), "lota shield");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_GAMMA_PRIMARY.getValue(), HotKeys.SHIELD_SELECTOR.getValue()), "theta shield");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_GAMMA_SECONDARY.getValue(), HotKeys.SHIELD_SELECTOR.getValue()), "theta shield");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_SIGMA_PRIMARY.getValue(), HotKeys.SHIELD_SELECTOR.getValue()), "zeta shield");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_SIGMA_SECONDARY.getValue(), HotKeys.SHIELD_SELECTOR.getValue()), "zeta shield");

        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_ALPHA_PRIMARY.getValue(), 0), "atom 1");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_ALPHA_SECONDARY.getValue(), 0), "atom 1");

        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_BETA_PRIMARY.getValue(), 0), "atom 2");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_BETA_SECONDARY.getValue(), 0), "atom 2");

        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_GAMMA_PRIMARY.getValue(), 0), "atom 3");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_GAMMA_SECONDARY.getValue(), 0), "atom 3");

        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_SIGMA_PRIMARY.getValue(), 0), "atom 4");
        contentPane.getInputMap(IFW).put(KeyStroke.getKeyStroke(HotKeys.ATOM_TYPE_SIGMA_SECONDARY.getValue(), 0), "atom 4");

        // SECONDARY ROTATE ACTION
        gameScreen.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    new GameActionHandler("rotate right", gameController).PerformAction();
                } else {
                    new GameActionHandler("rotate left", gameController).PerformAction();
                }
            }
        });

        contentPane.getActionMap().put("move right", new GameActionHandler("move right", gameController));
        contentPane.getActionMap().put("move left", new GameActionHandler("move left", gameController));
        contentPane.getActionMap().put("rotate left", new GameActionHandler("rotate left", gameController));
        contentPane.getActionMap().put("rotate right", new GameActionHandler("rotate right", gameController));
        contentPane.getActionMap().put("pick atom", new GameActionHandler("pick atom", gameController));
        contentPane.getActionMap().put("fire", new GameActionHandler("fire", gameController));
        contentPane.getActionMap().put("blend", new GameActionHandler("blend", gameController));
        contentPane.getActionMap().put("pause", new GameActionHandler("pause", gameController));

        contentPane.getActionMap().put("choose powerup 1", new GameActionHandler("choose powerup 1", gameController));
        contentPane.getActionMap().put("choose powerup 2", new GameActionHandler("choose powerup 2", gameController));
        contentPane.getActionMap().put("choose powerup 3", new GameActionHandler("choose powerup 3", gameController));
        contentPane.getActionMap().put("choose powerup 4", new GameActionHandler("choose powerup 4", gameController));

        contentPane.getActionMap().put("eta shield", new GameActionHandler("eta shield", gameController));
        contentPane.getActionMap().put("lota shield", new GameActionHandler("lota shield", gameController));
        contentPane.getActionMap().put("theta shield", new GameActionHandler("theta shield", gameController));
        contentPane.getActionMap().put("zeta shield", new GameActionHandler("zeta shield", gameController));

        contentPane.getActionMap().put("atom 1", new GameActionHandler("atom 1", gameController));
        contentPane.getActionMap().put("atom 2", new GameActionHandler("atom 2", gameController));
        contentPane.getActionMap().put("atom 3", new GameActionHandler("atom 3", gameController));
        contentPane.getActionMap().put("atom 4", new GameActionHandler("atom 4", gameController));
    }

    @Override
    public void onCreate(GameObject obj) {
        gamePanel.onCreate(obj);
    }

    @Override
    public void onLocationChange() {
        gamePanel.onLocationChange();
    }

    @Override
    public void onDestroy(GameObject obj) {
        gamePanel.onDestroy(obj);
    }

    @Override
    public void onShooterTriggerBulletChange() {
        gamePanel.onShooterTriggerBulletChange();
    }

    @Override
    public void onShooterPositionChange() {
        gamePanel.onShooterPositionChange();
    }

    public void initialize(RunningMode runningMode) {
        runningMode.setFrameListener(this);
    }

    public void CloseGameScreen() {
        gameScreen.dispose();
    }
}
