package UI;

import UI.Audio.AudioController;
import Domain.Utils.AudioListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsWindow {

    private AudioListener audioListener;

    private JPanel panelMain;
    private JTextArea textAreaTitle;

    private JPanel panelMenu;
    private JButton buttonSound;
    private JButton buttonControls;
    private JButton buttonBack;

    private JScrollPane scrollPane;
    private JPanel panelCardLayout;

    // PANEL - SOUNDS
    private JPanel panelSound;

    private JSlider sliderMenuMusic;
    private JTextArea textAreaMenuMusic;
    private JSlider sliderSFX;
    private JSlider sliderGameMusic;
    private JTextArea textAreaGameMusic;
    private JTextArea textAreaSFX;

    // PANEL - GAME SETTINGS
    private JPanel panelGameSettings;

    // PANEL - CONTROLS
    private JPanel panelControls;

    private JTextArea textAreaActivity;
    private JTextArea textAreaPrimaryKey;
    private JTextArea textAreaSecondaryKey;

    private JTextArea textAreaMoveRight;
    private JTextArea textAreaMoveLeft;
    private JTextArea textAreaRotateRight;
    private JTextArea textAreaRotateLeft;
    private JTextArea textAreaChangeAtom;
    private JTextArea textAreaShoot;
    private JTextArea textAreaBlend;
    private JTextArea textAreaAlphaAtom;
    private JTextArea textAreaBetaAtom;
    private JTextArea textAreaGammaAtom;
    private JTextArea textAreaSigmaAtom;
    private JTextArea textAreaPause;
    private JTextArea textAreaAlphaPowerup;
    private JTextArea textAreaBetaPowerup;
    private JTextArea textAreaGammaPowerup;
    private JTextArea textAreaSigmaPowerup;
    private JTextArea textAreaEtaShield;
    private JTextArea textAreaLotaShield;
    private JTextArea textAreaThetaShield;
    private JTextArea textAreaZetaShield;

    // Primary Key: pk
    private JButton pkMoveRight;
    private JButton pkMoveLeft;
    private JButton pkRotateRight;
    private JButton pkRotateLeft;
    private JButton pkChangeAtom;
    private JButton pkShoot;
    private JButton pkBlend;
    private JButton pkAlphaAtom;
    private JButton pkBetaAtom;
    private JButton pkGammaAtom;
    private JButton pkSigmaAtom;
    private JButton pkPause;

    private JButton pkPoweupAlphaSelectorFirst;
    private JTextArea textAreaPlus1;
    private JButton pkPowerupAlpha;

    private JButton pkPoweupBetaSelectorFirst;
    private JTextArea textAreaPlus2;
    private JButton pkPowerupBeta;

    private JButton pkPoweupGammaSelectorFirst;
    private JTextArea textAreaPlus3;
    private JButton pkPowerupGamma;

    private JButton pkPoweupSigmaSelectorFirst;
    private JTextArea textAreaPlus4;
    private JButton pkPowerupSigma;

    private JButton pkEtaShieldSelectorFirst;
    private JTextArea textAreaPlus5;
    private JButton pkEtaShield;

    private JButton pkLotaShieldSelectorFirst;
    private JTextArea textAreaPlus6;
    private JButton pkLotaShield;

    private JButton pkThetaShieldSelectorFirst;
    private JTextArea textAreaPlus7;
    private JButton pkThetaShield;

    private JButton pkZetaShieldSelectorFirst;
    private JTextArea textAreaPlus8;
    private JButton pkZetaShield;

    // Secondary Key: sk
    private JButton skMoveRight;
    private JButton skMoveLeft;
    private JButton skRotateRight;
    private JButton skRotateLeft;
    private JButton skChangeAtom;
    private JButton skShoot;
    private JButton skBlend;
    private JButton skAlphaAtom;
    private JButton skBetaAtom;
    private JButton skGammaAtom;
    private JButton skSigmaAtom;
    private JButton skPause;
    private JButton skAlphaPowerup;
    private JButton skBetaPowerup;
    private JButton skGammaPowerup;
    private JButton skSigmaPowerup;
    private JButton skEtaShield;
    private JButton skLotaShield;
    private JButton skThetaShield;
    private JButton skZetaShield;
    private JLabel textAreaMenuMusicVolume;
    private JLabel textAreaGameMusicVolume;
    private JLabel textAreaSFXVolume;

    private JFrame optionsFrame;

    public OptionsWindow() {
        audioListener = AudioController.GetInstance();
        CreateUIElements();
        ActionListener();
    }

    private void CreateUIElements() {
        optionsFrame = new JFrame();
        optionsFrame.setContentPane(panelMain);
        optionsFrame.setSize(ScreenInfo.WINDOW_WIDTH, ScreenInfo.WINDOW_HEIGHT);
        optionsFrame.setResizable(false);
        optionsFrame.setUndecorated(true);
        optionsFrame.setVisible(true);

        textAreaTitle.setFont(ScreenInfo.titleFont);
        buttonSound.setFont(ScreenInfo.buttonFont);
        buttonControls.setFont(ScreenInfo.buttonFont);
        buttonBack.setFont(ScreenInfo.buttonFont);

        scrollPane.getVerticalScrollBar().setUnitIncrement(ScreenInfo.SCROLL_INCREMENT_AMOUNT);

        sliderMenuMusic.setValue(audioListener.getMenuMusicVolumeAsInteger());
        sliderGameMusic.setValue(audioListener.getGameMusicVolumeAsInteger());
        sliderSFX.setValue(audioListener.getSFXVolumeAsInteger());

        SetFonts();
    }

    private void SetFonts() {
        // PANEL - SOUND
        textAreaMenuMusic.setFont(ScreenInfo.textFontSmall);
        textAreaMenuMusicVolume.setFont(ScreenInfo.textFontSmall);
        textAreaMenuMusicVolume.setText(audioListener.getMenuMusicVolumeAsInteger() + "%");

        textAreaGameMusic.setFont(ScreenInfo.textFontSmall);
        textAreaGameMusicVolume.setFont(ScreenInfo.textFontSmall);
        textAreaGameMusicVolume.setText(audioListener.getGameMusicVolumeAsInteger() + "%");

        textAreaSFX.setFont(ScreenInfo.textFontSmall);
        textAreaSFXVolume.setFont(ScreenInfo.textFontSmall);
        textAreaSFXVolume.setText(audioListener.getSFXVolumeAsInteger() + "%");

        // PANEL - CONTROLS
        textAreaActivity.setFont(ScreenInfo.textFontMedium);
        textAreaPrimaryKey.setFont(ScreenInfo.textFontMedium);
        textAreaSecondaryKey.setFont(ScreenInfo.textFontMedium);

        textAreaMoveRight.setFont(ScreenInfo.textFontSmall);
        pkMoveRight.setFont(ScreenInfo.textFontSmall);
        skMoveRight.setFont(ScreenInfo.textFontSmall);

        textAreaMoveLeft.setFont(ScreenInfo.textFontSmall);
        pkMoveLeft.setFont(ScreenInfo.textFontSmall);
        skMoveLeft.setFont(ScreenInfo.textFontSmall);

        textAreaRotateRight.setFont(ScreenInfo.textFontSmall);
        pkRotateRight.setFont(ScreenInfo.textFontSmall);
        skRotateRight.setFont(ScreenInfo.textFontSmall);

        textAreaRotateLeft.setFont(ScreenInfo.textFontSmall);
        pkRotateLeft.setFont(ScreenInfo.textFontSmall);
        skRotateLeft.setFont(ScreenInfo.textFontSmall);

        textAreaChangeAtom.setFont(ScreenInfo.textFontSmall);
        pkChangeAtom.setFont(ScreenInfo.textFontSmall);
        skChangeAtom.setFont(ScreenInfo.textFontSmall);

        textAreaShoot.setFont(ScreenInfo.textFontSmall);
        pkShoot.setFont(ScreenInfo.textFontSmall);
        skShoot.setFont(ScreenInfo.textFontSmall);

        textAreaBlend.setFont(ScreenInfo.textFontSmall);
        pkBlend.setFont(ScreenInfo.textFontSmall);
        skBlend.setFont(ScreenInfo.textFontSmall);

        textAreaAlphaAtom.setFont(ScreenInfo.textFontSmall);
        pkAlphaAtom.setFont(ScreenInfo.textFontSmall);
        skAlphaAtom.setFont(ScreenInfo.textFontSmall);

        textAreaBetaAtom.setFont(ScreenInfo.textFontSmall);
        pkBetaAtom.setFont(ScreenInfo.textFontSmall);
        skBetaAtom.setFont(ScreenInfo.textFontSmall);

        textAreaGammaAtom.setFont(ScreenInfo.textFontSmall);
        pkGammaAtom.setFont(ScreenInfo.textFontSmall);
        skGammaAtom.setFont(ScreenInfo.textFontSmall);

        textAreaSigmaAtom.setFont(ScreenInfo.textFontSmall);
        pkSigmaAtom.setFont(ScreenInfo.textFontSmall);
        skSigmaAtom.setFont(ScreenInfo.textFontSmall);

        textAreaPause.setFont(ScreenInfo.textFontSmall);
        pkPause.setFont(ScreenInfo.textFontSmall);
        skPause.setFont(ScreenInfo.textFontSmall);

        textAreaAlphaPowerup.setFont(ScreenInfo.textFontSmall);
        pkPoweupAlphaSelectorFirst.setFont(ScreenInfo.textFontSmall);
        textAreaPlus1.setFont(ScreenInfo.textFontSmall);
        pkPowerupAlpha.setFont(ScreenInfo.textFontSmall);
        skAlphaPowerup.setFont(ScreenInfo.textFontSmall);

        textAreaBetaPowerup.setFont(ScreenInfo.textFontSmall);
        pkPoweupBetaSelectorFirst.setFont(ScreenInfo.textFontSmall);
        textAreaPlus2.setFont(ScreenInfo.textFontSmall);
        pkPowerupBeta.setFont(ScreenInfo.textFontSmall);
        skBetaPowerup.setFont(ScreenInfo.textFontSmall);

        textAreaGammaPowerup.setFont(ScreenInfo.textFontSmall);
        pkPoweupGammaSelectorFirst.setFont(ScreenInfo.textFontSmall);
        textAreaPlus3.setFont(ScreenInfo.textFontSmall);
        pkPowerupGamma.setFont(ScreenInfo.textFontSmall);
        skGammaPowerup.setFont(ScreenInfo.textFontSmall);

        textAreaSigmaPowerup.setFont(ScreenInfo.textFontSmall);
        pkPoweupSigmaSelectorFirst.setFont(ScreenInfo.textFontSmall);
        textAreaPlus4.setFont(ScreenInfo.textFontSmall);
        pkPowerupSigma.setFont(ScreenInfo.textFontSmall);
        skSigmaPowerup.setFont(ScreenInfo.textFontSmall);

        textAreaEtaShield.setFont(ScreenInfo.textFontSmall);
        pkEtaShieldSelectorFirst.setFont(ScreenInfo.textFontSmall);
        textAreaPlus5.setFont(ScreenInfo.textFontSmall);
        pkEtaShield.setFont(ScreenInfo.textFontSmall);
        skEtaShield.setFont(ScreenInfo.textFontSmall);

        textAreaLotaShield.setFont(ScreenInfo.textFontSmall);
        pkLotaShieldSelectorFirst.setFont(ScreenInfo.textFontSmall);
        textAreaPlus6.setFont(ScreenInfo.textFontSmall);
        pkLotaShield.setFont(ScreenInfo.textFontSmall);
        skLotaShield.setFont(ScreenInfo.textFontSmall);

        textAreaThetaShield.setFont(ScreenInfo.textFontSmall);
        pkThetaShieldSelectorFirst.setFont(ScreenInfo.textFontSmall);
        textAreaPlus7.setFont(ScreenInfo.textFontSmall);
        pkThetaShield.setFont(ScreenInfo.textFontSmall);
        skThetaShield.setFont(ScreenInfo.textFontSmall);

        textAreaZetaShield.setFont(ScreenInfo.textFontSmall);
        pkZetaShieldSelectorFirst.setFont(ScreenInfo.textFontSmall);
        textAreaPlus8.setFont(ScreenInfo.textFontSmall);
        pkZetaShield.setFont(ScreenInfo.textFontSmall);
        skZetaShield.setFont(ScreenInfo.textFontSmall);
    }

    private void ActionListener() {
        buttonSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();

                panelSound.show();
                panelControls.hide();
            }
        });

        buttonControls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();

                panelControls.show();
                panelSound.hide();
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioListener.onButtonClick();
                CloseSettingsWindow();
            }
        });

        sliderMenuMusic.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textAreaMenuMusicVolume.setText(sliderMenuMusic.getValue() + "%");
                audioListener.setMenuMusicVolume(sliderMenuMusic.getValue() / 100.0);
            }
        });

        sliderGameMusic.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textAreaGameMusicVolume.setText(sliderGameMusic.getValue() + "%");
                audioListener.setGameMusicVolume(sliderGameMusic.getValue() / 100.0);
            }
        });

        sliderSFX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textAreaSFXVolume.setText(sliderSFX.getValue() + "%");
                audioListener.setSFXVolume(sliderSFX.getValue() / 100.0);
            }
        });
    }

    public void CloseSettingsWindow() {
        optionsFrame.dispose();
    }
}
