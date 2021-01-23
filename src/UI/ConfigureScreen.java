package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.HashMap;

import Domain.Utils.*;
import UI.Audio.AudioController;
import Domain.Utils.AudioListener;

public class ConfigureScreen {

    private UIController uiController;
    private AudioListener audioListener;

    public JPanel panelMain;
    private JPanel panelSelectionFields;

    private JFormattedTextField textFieldNumberOfAtoms;
    private JFormattedTextField textFieldNumberOfBlockers;
    private JFormattedTextField textFieldNumberOfPowerups;
    private JFormattedTextField textFieldNumberOfMolecules;
    private JFormattedTextField textFieldLength;

    private JPanel panelMoleculeStructure;
    private JRadioButton radioButtonLinear;
    private JRadioButton radioButtonTriangle;

    private JPanel panelMoleculeFallingType;
    private JRadioButton radioButtonStationary;
    private JRadioButton radioButtonSpinning;

    private JPanel panelGameDifficulty;
    private JRadioButton radioButtonEasy;
    private JRadioButton radioButtonNormal;
    private JRadioButton radioButtonHard;

    private JButton buttonStart;

    private JTextArea textAreaAtoms;
    private JTextArea textAreaBlockers;
    private JTextArea textAreaPowerups;
    private JTextArea textAreaMolecules;
    private JTextArea footnote;
    private JTextArea textAreaLength;
    private JTextArea textAreaMoleculeStructure;
    private JTextArea textAreaMoleculeFallingType;
    private JTextArea textAreaDifficulty;
    private JButton buttonSetDefaultValues;
    private JButton buttonResetValues;

    private ButtonGroup structureGroup;
    private ButtonGroup fallingTypeGroup;
    private ButtonGroup gameDifficultyGroup;

    private HashMap<String, String> configurationInfo;

    private JFrame configureScreenFrame;

    public ConfigureScreen() {
        audioListener = AudioController.GetInstance();
        uiController = UIController.GetInstance();
        configurationInfo = new HashMap<>();
        CreateUIElements();
        BoundRBGroups();
        ActionListener();
    }

    private void CreateUIElements() {
        configureScreenFrame = new JFrame("Game Configuration");
        configureScreenFrame.setContentPane(panelMain);
        configureScreenFrame.pack();
        configureScreenFrame.setVisible(true);
        configureScreenFrame.setSize(2*ScreenInfo.WINDOW_WIDTH / 3, ScreenInfo.WINDOW_WIDTH / 2);
        configureScreenFrame.setResizable(false);

        radioButtonLinear.setText(MoleculeStructure.LINEAR.toString());
        radioButtonTriangle.setText(MoleculeStructure.TRIANGLE.toString());

        radioButtonSpinning.setText("SPINNING");
        radioButtonStationary.setText("STATIONARY");

        radioButtonEasy.setText(Difficulty.EASY.toString());
        radioButtonNormal.setText(Difficulty.NORMAL.toString());
        radioButtonHard.setText(Difficulty.HARD.toString());

        SetFonts();

        CenterFrame(configureScreenFrame);
    }

    private void SetFonts(){
        textAreaAtoms.setFont(ScreenInfo.textFontSmall);
        textFieldNumberOfAtoms.setFont(ScreenInfo.textFontSmall);

        textAreaBlockers.setFont(ScreenInfo.textFontSmall);
        textFieldNumberOfBlockers.setFont(ScreenInfo.textFontSmall);

        textAreaPowerups.setFont(ScreenInfo.textFontSmall);
        textFieldNumberOfPowerups.setFont(ScreenInfo.textFontSmall);

        textAreaMolecules.setFont(ScreenInfo.textFontSmall);
        textFieldNumberOfMolecules.setFont(ScreenInfo.textFontSmall);

        textAreaLength.setFont(ScreenInfo.textFontSmall);
        textFieldLength.setFont(ScreenInfo.textFontSmall);

        textAreaMoleculeStructure.setFont(ScreenInfo.textFontSmall);
        radioButtonLinear.setFont(ScreenInfo.textFontSmall);
        radioButtonTriangle.setFont(ScreenInfo.textFontSmall);

        textAreaMoleculeFallingType.setFont(ScreenInfo.textFontSmall);
        radioButtonStationary.setFont(ScreenInfo.textFontSmall);
        radioButtonSpinning.setFont(ScreenInfo.textFontSmall);

        textAreaDifficulty.setFont(ScreenInfo.textFontSmall);
        radioButtonEasy.setFont(ScreenInfo.textFontSmall);
        radioButtonNormal.setFont(ScreenInfo.textFontSmall);
        radioButtonHard.setFont(ScreenInfo.textFontSmall);

        buttonSetDefaultValues.setFont(ScreenInfo.textFontSmall);
        buttonResetValues.setFont(ScreenInfo.textFontSmall);
        buttonStart.setFont(ScreenInfo.buttonFont);
    }

    private void BoundRBGroups() {
        structureGroup = new ButtonGroup();
        structureGroup.add(radioButtonLinear);
        structureGroup.add(radioButtonTriangle);
        radioButtonLinear.setSelected(true);

        fallingTypeGroup = new ButtonGroup();
        fallingTypeGroup.add(radioButtonStationary);
        fallingTypeGroup.add(radioButtonSpinning);
        radioButtonStationary.setSelected(true);

        gameDifficultyGroup = new ButtonGroup();
        gameDifficultyGroup.add(radioButtonEasy);
        gameDifficultyGroup.add(radioButtonNormal);
        gameDifficultyGroup.add(radioButtonHard);
        radioButtonNormal.setSelected(true);
    }

    private void ActionListener() {
        radioButtonLinear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If Linear Structure is selected, then enable falling type options
                radioButtonStationary.setEnabled(true);
                radioButtonSpinning.setEnabled(true);
            }
        });

        radioButtonTriangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If Triangle Structure is selected, then unable falling type options and set Stationary type as default
                radioButtonStationary.setEnabled(false);
                radioButtonStationary.setSelected(true);
                radioButtonSpinning.setEnabled(false);
            }
        });

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    audioListener.onButtonClick();

                    CheckInput();

                    // Put object amounts and length into hashmap as String
                    configurationInfo.put(FinalValues.ATOM, textFieldNumberOfAtoms.getText());
                    configurationInfo.put(FinalValues.BLOCKER, textFieldNumberOfBlockers.getText());
                    configurationInfo.put(FinalValues.POWERUP, textFieldNumberOfPowerups.getText());
                    configurationInfo.put(FinalValues.MOLECULE, textFieldNumberOfMolecules.getText());
                    configurationInfo.put("length", textFieldLength.getText());

                    String moleculeStructure = new String();
                    String fallingType = new String();
                    String gameDifficulty = new String();

                    // Get the selected RadioButton from radioButtonGroups and assign it to strings
                    for (Enumeration<AbstractButton> rb = structureGroup.getElements(); rb.hasMoreElements(); ) {
                        AbstractButton radioButton = rb.nextElement();
                        if (radioButton.isSelected())
                            moleculeStructure = radioButton.getText();
                    }

                    for (Enumeration<AbstractButton> rb = fallingTypeGroup.getElements(); rb.hasMoreElements(); ) {
                        AbstractButton radioButton = rb.nextElement();
                        if (radioButton.isSelected())
                            fallingType = radioButton.getText();
                    }

                    for (Enumeration<AbstractButton> rb = gameDifficultyGroup.getElements(); rb.hasMoreElements(); ) {
                        AbstractButton radioButton = rb.nextElement();
                        if (radioButton.isSelected())
                            gameDifficulty = radioButton.getText();
                    }

                    // Put those strings into hashmap
                    configurationInfo.put("structure", moleculeStructure);
                    configurationInfo.put("fallingType", fallingType);
                    configurationInfo.put("difficulty", gameDifficulty);

                    // Put screen width and height into hashmap
                    configurationInfo.put("width", String.valueOf(ScreenInfo.GAME_SCREEN_WIDTH));
                    configurationInfo.put("height", String.valueOf(ScreenInfo.GAME_SCREEN_HEIGHT));

                    // CONFIGURE SCREEN'S JOB IS DONE
                    CloseConfigureScreen();
                    uiController.setConfigurationInfo(configurationInfo);
                    uiController.initializeGame();
                    uiController.startGame();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panelMain, "Please enter a non-negative integer.");
                }
            }
        });

        buttonSetDefaultValues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();

                // If Triangle Structure is selected, then unable falling type options and set Stationary type as default
                textFieldNumberOfAtoms.setText("100");
                textFieldNumberOfBlockers.setText("10");
                textFieldNumberOfPowerups.setText("20");
                textFieldNumberOfMolecules.setText("100");
                textFieldLength.setText("10");
                radioButtonLinear.setSelected(true);
                radioButtonStationary.setSelected(true);
                radioButtonNormal.setSelected(true);

                radioButtonStationary.setEnabled(true);
                radioButtonSpinning.setEnabled(true);
            }
        });

        buttonResetValues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();

                textFieldNumberOfAtoms.setText("");
                textFieldNumberOfBlockers.setText("");
                textFieldNumberOfPowerups.setText("");
                textFieldNumberOfMolecules.setText("");
                textFieldLength.setText("");
                radioButtonLinear.setSelected(true);
                radioButtonStationary.setSelected(true);
                radioButtonNormal.setSelected(true);

                radioButtonStationary.setEnabled(true);
                radioButtonSpinning.setEnabled(true);
            }
        });

        configureScreenFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                uiController.openHomeScreen();
            }
        });
    }

    private void CheckInput() throws NumberFormatException{
        Integer.parseUnsignedInt(textFieldNumberOfAtoms.getText());
        Integer.parseUnsignedInt(textFieldNumberOfBlockers.getText());
        Integer.parseUnsignedInt(textFieldNumberOfPowerups.getText());
        Integer.parseUnsignedInt(textFieldNumberOfMolecules.getText());
        Integer.parseUnsignedInt(textFieldLength.getText());
    }

    private void CenterFrame(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (dim.width - frame.getSize().width) / 2;
        int y = (dim.height - frame.getSize().height) / 2;

        frame.setLocation(x, y);
    }

    private void CloseConfigureScreen() {
        configureScreenFrame.dispose();
    }
}