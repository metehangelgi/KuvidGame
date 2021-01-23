package UI;

import Domain.DomainControl.GameController;
import UI.Audio.AudioController;
import Domain.Utils.AudioListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class GameOverWindow {

    private GameController gameController;
    private UIController uiController;
    private AudioListener audioListener;

    private JTextArea textAreaTitle;
    private JPanel panelMain;

    private JButton buttonRestart;
    private JButton buttonMainMenu;
    private JButton buttonExit;

    private JTextArea textAreaScore;
    private JTextArea textAreaHighscore;

    private JFrame gameOverFrame;

    private double score;

    public GameOverWindow(GameController gameController, double score) {
        audioListener = AudioController.GetInstance();
        uiController = UIController.GetInstance();
        this.gameController = gameController;
        this.score = score;
        CreateUIElements();
        ActionListener();
    }

    private void CreateUIElements() {
        gameOverFrame = new JFrame();
        panelMain.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 20));
        gameOverFrame.setContentPane(panelMain);
        gameOverFrame.setSize(ScreenInfo.WINDOW_WIDTH * 3 / 7, ScreenInfo.WINDOW_HEIGHT * 2 / 3);
        gameOverFrame.setResizable(false);
        gameOverFrame.setUndecorated(true);
        gameOverFrame.setVisible(true);

        CenterFrame(gameOverFrame);

        textAreaTitle.setFont(ScreenInfo.titleFont);
        textAreaScore.setFont(ScreenInfo.textFontMedium);

        DecimalFormat df = new DecimalFormat("#.00");
        textAreaScore.setText("Score: " + df.format(score));
        textAreaHighscore.setFont(ScreenInfo.textFontMedium);

        buttonRestart.setFont(ScreenInfo.buttonFont);
        buttonMainMenu.setFont(ScreenInfo.textFontSmall);
        buttonExit.setFont(ScreenInfo.textFontSmall);
    }

    private void ActionListener() {
        buttonRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseGameOverWindow();
                uiController.restartNewGame();
            }
        });

        buttonMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game and go back to main menu?", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    // END GAME
                    // HOME SCREEN
                }
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game?", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(-1);
                }
            }
        });
    }

    private void CenterFrame(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (dim.width - frame.getSize().width) / 2;
        int y = (dim.height - frame.getSize().height) / 2;

        frame.setLocation(x, y);
    }

    public void CloseGameOverWindow() {
        gameOverFrame.dispose();
    }
}
