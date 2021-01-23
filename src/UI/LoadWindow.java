package UI;

import Domain.DomainControl.GameController;
import UI.Audio.AudioController;
import Domain.Utils.AudioListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class LoadWindow {

    private  UIController uiController;
    private AudioListener audioListener;

    private JFrame frame;

    private JPanel panelMain;

    private JPanel panelMenu;
    private JButton buttonBack;

    private JScrollPane scrollPane;
    private JPanel panelLoad;
    private JPanel infoPanel;
    private JTextPane textAreaGameTitle;
    private JTextPane textAreaUsername;
    private JTextPane textAreaScore;
    private JTextPane textAreaTime;
    private JTextPane textAreaHealth;

    private int buttonIndex = 0;

    public LoadWindow() {
        uiController = UIController.GetInstance();
        audioListener = AudioController.GetInstance();
        CreateUIElements();
        ActionListener();
    }

    private void CreateUIElements() {
        frame = new JFrame();
        frame.setSize(ScreenInfo.WINDOW_WIDTH, ScreenInfo.WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setUndecorated(true);

        panelMain = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        panelMain.setLayout(borderLayout);

        AddTitle(); // to panelMain
        AddMenu(); // to panelMain
        AddLoadPanel(); // to panelMain

        frame.setContentPane(panelMain);
        frame.setVisible(true);
        CenterFrame(frame);
    }

    private void AddTitle() {
        JTextPane title = new JTextPane();
        title.setText("LOAD GAME");
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

    private void AddLoadPanel() {
        panelLoad = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panelLoad, BoxLayout.PAGE_AXIS);
        panelLoad.setLayout(boxLayout);
        List<HashMap<String, String>> GameList = GameController.getInstance().LoadAllGames();
        for (HashMap<String, String> game: GameList) {
            AddSavedGameArea(game);
        }

        scrollPane = new JScrollPane(panelLoad);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        panelMain.add(scrollPane);
    }

    /**
     * Adds new button and info panel for a saved game
     *
     * @param hash
     */
    public void AddSavedGameArea(HashMap<String, String> hash) {
        JPanel panel = new JPanel(new BorderLayout());

        // Add Button
        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setFont(ScreenInfo.buttonFont);
        loadGameButton.setBorderPainted(false);
        loadGameButton.setBackground(ScreenInfo.buttonBackgroundColor);
        loadGameButton.setForeground(ScreenInfo.buttonTextColor);
        loadGameButton.setVerticalTextPosition(SwingConstants.CENTER);
        loadGameButton.setHorizontalTextPosition(SwingConstants.CENTER);
        panel.add(loadGameButton, BorderLayout.EAST);

        // Add Info Panel
        panel.add(SetInfoPanel(hash), BorderLayout.WEST);

        // Panel adjustments
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.DARK_GRAY);
        panel.setMaximumSize(new Dimension(ScreenInfo.WINDOW_WIDTH - panelMenu.getWidth(), ScreenInfo.textFontMedium.getSize()*4));
        panelLoad.add(panel);
        panelLoad.add(Box.createRigidArea(new Dimension(0, 20)));

        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();

                int result = JOptionPane.showConfirmDialog(null, "Do you want to load this game? ", "Load Game", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    LoadGame(hash.get("username"));
                    CloseLoadWindow();
                }
            }
        });
    }

    private void LoadGame(String fileName) {
        if(!UIController.GetInstance().isGameRunning()){

            UIController.GetInstance().initializeGame();
            GameController.getInstance().LoadGameData(fileName);
            GameController.getInstance().LoadGame();
            UIController.GetInstance().loadGame();
        }
        GameController.getInstance().LoadGameData(fileName);
        GameController.getInstance().LoadGame();
    }

    private JPanel SetInfoPanel(HashMap<String, String> hash) {
        JPanel myInfoPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2,3);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        myInfoPanel.setLayout(gridLayout);

        textAreaGameTitle = new JTextPane();
        textAreaGameTitle.setText("Game: " + hash.get("title"));
        textAreaGameTitle.setEditable(false);
        textAreaGameTitle.setOpaque(false);
        textAreaGameTitle.setFont(ScreenInfo.textFontMedium);

        textAreaUsername = new JTextPane();
        textAreaUsername.setText("Username: " + hash.get("username"));
        textAreaUsername.setEditable(false);
        textAreaUsername.setOpaque(false);
        textAreaUsername.setFont(ScreenInfo.textFontMedium);

        textAreaScore = new JTextPane();
        textAreaScore.setText("Score: " + hash.get("score"));
        textAreaScore.setEditable(false);
        textAreaScore.setOpaque(false);
        textAreaScore.setFont(ScreenInfo.textFontMedium);

        textAreaTime = new JTextPane();
        textAreaTime.setText("Time: " + hash.get("time"));
        textAreaTime.setEditable(false);
        textAreaTime.setOpaque(false);
        textAreaTime.setFont(ScreenInfo.textFontMedium);

        textAreaHealth = new JTextPane();
        textAreaHealth.setText("Health: " + hash.get("health"));
        textAreaHealth.setEditable(false);
        textAreaHealth.setOpaque(false);
        textAreaHealth.setFont(ScreenInfo.textFontMedium);

        myInfoPanel.add(textAreaGameTitle, 0);
        myInfoPanel.add(textAreaUsername, 1);
        myInfoPanel.add(new JPanel(), 2); // DUMMY PANEL
        myInfoPanel.add(textAreaScore, 3);
        myInfoPanel.add(textAreaTime, 4);
        myInfoPanel.add(textAreaHealth, 5);

        return myInfoPanel;
    }

    private void ActionListener() {
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();
                CloseLoadWindow();
                if(!uiController.isGameRunning()){
                    uiController.openHomeScreen();
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

    public void CloseLoadWindow() {
        frame.dispose();
    }
}
