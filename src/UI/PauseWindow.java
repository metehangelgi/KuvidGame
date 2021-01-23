package UI;

import Domain.DomainControl.GameController;
import Domain.Utils.GameActionHandler;
import UI.Audio.AudioController;
import Domain.Utils.AudioListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseWindow {

    private GameController gameController;
    private UIController uiController;
    private AudioListener audioListener;

    private JTextArea textAreaTitle;
    private JPanel panelMain;
    private JButton buttonResume;
    private JButton buttonRestart;
    private JButton buttonOptions;
    private JButton buttonMainMenu;
    private JButton buttonExit;
    private JButton buttonSave;
    private JButton buttonLoad;

    private JFrame pauseFrame;

    private Font titleFont = new Font("Text Me One", Font.PLAIN, 48);
    private Font buttonFont = new Font("Text Me One", Font.PLAIN, 28);

    public PauseWindow(GameController gameController) {
        audioListener = AudioController.GetInstance();
        uiController = UIController.GetInstance();
        this.gameController = gameController;
        CreateUIElements();
        ActionListener();
    }

    private void CreateUIElements() {
        pauseFrame = new JFrame();
        panelMain.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 20));
        pauseFrame.setContentPane(panelMain);
        pauseFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 4, 600);
        pauseFrame.setResizable(false);
        pauseFrame.setUndecorated(true);
        pauseFrame.setVisible(true);

        CenterFrame(pauseFrame);

        textAreaTitle.setFont(titleFont);
        buttonResume.setFont(buttonFont);
        buttonSave.setFont(buttonFont);
        buttonLoad.setFont(buttonFont);
        buttonRestart.setFont(buttonFont);
        buttonOptions.setFont(buttonFont);
        buttonMainMenu.setFont(buttonFont);
        buttonExit.setFont(buttonFont);
    }

    private void ActionListener() {
        buttonResume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();

                new GameActionHandler("resume", gameController).PerformAction();
                ClosePauseWindow();
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();
                uiController.openSaveWindow();
            }
        });

        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();
                uiController.openLoadWindow();
            }
        });

        buttonRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to restart the game?", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    // END GAME
                    // START GAME
                    uiController.restartGame();
                }
            }
        });

        buttonOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();
                uiController.openOptions();
            }
        });

        buttonMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game and go back to main menu?", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    ClosePauseWindow();
                    uiController.endGame();
                    uiController.openHomeScreen();
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

    public void ClosePauseWindow() {
        pauseFrame.dispose();
    }
}
