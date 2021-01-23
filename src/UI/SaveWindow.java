package UI;

import Domain.DomainControl.GameController;
import UI.Audio.AudioController;
import Domain.Utils.AudioListener;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveWindow {

    private AudioListener audioListener;

    private JFrame frame;

    private JPanel panelMain;

    private JPanel panelMenu;
    private JButton buttonBack;

    private JScrollPane scrollPane;
    private JPanel panelSavedGame;
    private JPanel infoPanel;
    private JTextPane textAreaGameTitle;
    private JTextPane textAreaUsername;
    private JTextPane textAreaScore;
    private JTextPane textAreaTime;
    private JTextPane textAreaHealth;
    private JButton buttonSaveNewGame;

    public SaveWindow() {
        audioListener = AudioController.GetInstance();
        CreateUIElements();
        ActionListener();
    }

    private void CreateUIElements() {
        frame = new JFrame();
        frame.setSize(ScreenInfo.WINDOW_WIDTH, ScreenInfo.WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setUndecorated(true);

        panelMain = new JPanel(new BorderLayout());

        AddTitle(); // to panelMain
        AddMenu(); // to panelMain
        AddSavedGamePanel(); // to panelMain

        frame.setContentPane(panelMain);
        frame.setVisible(true);
    }

    private void AddTitle() {
        JTextPane title = new JTextPane();
        title.setText("SAVE GAME");
        title.setFont(ScreenInfo.titleFont);
        title.setEditable(false);

        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        title.setParagraphAttributes(right, false);

        panelMain.add(title, BorderLayout.NORTH);
        title.setMargin(new Insets(ScreenInfo.titleFont.getSize()/2, 0, ScreenInfo.titleFont.getSize()/2, ScreenInfo.titleFont.getSize()));
    }

    private void AddMenu() {
        buttonBack.setFont(ScreenInfo.buttonFont);
        buttonBack.setMargin(new Insets(0, ScreenInfo.buttonFont.getSize(), 0, ScreenInfo.buttonFont.getSize()));
        panelMain.add(panelMenu, BorderLayout.WEST);
    }

    private void AddSavedGamePanel() {
        panelSavedGame = new JPanel();
        //BoxLayout boxLayout = new BoxLayout(panelLoad, BoxLayout.PAGE_AXIS);
        //panelLoad.setLayout(boxLayout);

        panelSavedGame.add(buttonSaveNewGame);
        buttonSaveNewGame.setFont(ScreenInfo.buttonFont);
        buttonSaveNewGame.setBackground(ScreenInfo.buttonBackgroundColor);

        scrollPane = new JScrollPane(panelSavedGame);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        panelMain.add(scrollPane);
    }

    private void ActionListener() {
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();
                CloseSaveWindow();
            }
        });

        buttonSaveNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();

                JTextField textFieldTitle = new JTextField(10);
                JTextField textFieldUsername = new JTextField(10);

                JPanel myPanel = new JPanel(new GridLayout(2, 2));

                JLabel labelTitle = new JLabel("Game Title: ");
                labelTitle.setHorizontalAlignment(SwingConstants.RIGHT);
                myPanel.add(labelTitle, 0);
                myPanel.add(textFieldTitle, 1);

                JLabel labelUsername = new JLabel("Username: ");
                labelUsername.setHorizontalAlignment(SwingConstants.RIGHT);
                myPanel.add(labelUsername, 2);
                myPanel.add(textFieldUsername, 3);

                String[] options = new String[1];
                options[0] = "Save Game";

                int result = JOptionPane.showOptionDialog(null, myPanel,
                        "", 0, JOptionPane.PLAIN_MESSAGE, null, options, null);

                if (result == 0) {
                    // SAVE GAME
                    SaveGame(textFieldTitle.getText(), textFieldUsername.getText());
                    CloseSaveWindow();
                }
            }
        });
    }

    private void SaveGame(String saveTitle, String username) {

        GameController.getInstance().SaveGameData(saveTitle,username);

    }

    public void CloseSaveWindow() {
        frame.dispose();
    }
}
