package UI;

import Domain.DomainControl.GameController;
import Domain.Statistics.GameData;

import Domain.Statistics.StaticWindowListener;
import Domain.Utils.FinalValues;
import Domain.Utils.GameActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class StatisticsWindow extends JPanel implements StaticWindowListener {
    public JPanel panelMain;

    private JPanel panelGameInfo;
    private JTextArea textAreaStaticScore;
    private JTextArea textAreaScore;
    private JTextArea textAreaStaticTime;
    private JTextArea textAreaTime;
    private JTextArea textAreaStaticHealth;
    private JTextArea textAreaHealth;

    private JPanel panelPowerupInfo;
    private JButton powerupAlpha;
    private JTextArea textAreaAlphaPowerupAmount;
    private JButton powerupBeta;
    private JTextArea textAreaBetaPowerupAmount;
    private JButton powerupGamma;
    private JTextArea textAreaGammaPowerupAmount;
    private JButton powerupSigma;
    private JTextArea textAreaSigmaPowerupAmount;

    private JPanel panelAtomInfo;
    private JLabel blendIcon;
    private JLabel atomIconAlpha;
    private JTextArea textAreaAlphaAtomAmount;
    private JLabel atomIconGamma;
    private JTextArea textAreaGammaAtomAmount;
    private JLabel atomIconSigma;
    private JTextArea textAreaSigmaAtomAmount;
    private JLabel atomIconBeta;
    private JTextArea textAreaBetaAtomAmount;
    private JButton buttonEtaShield;
    private JTextArea textAreaEtaShieldAmount;
    private JButton buttonLotaShield;
    private JButton buttonThetaShield;
    private JButton buttonZetaShield;
    private JTextArea textAreaLotaShieldAmount;
    private JTextArea textAreaThetaShieldAmount;
    private JTextArea textAreaZetaShieldAmount;

    private int width;
    private int height;

    GameController gameController;

    public StatisticsWindow(int width, int height) {
        this.width = width;
        this.height = height;

        CreateUIElements();
        SetPowerupIcons();
        SetAtomIcons();
        SetFonts();
        ActionListener();
    }

    public void SetGameController(GameController gameController){
        this.gameController = gameController;
    }

    private void CreateUIElements() {
        panelMain = new JPanel();
        panelMain.setOpaque(true);

        panelMain.add(panelGameInfo);
        panelGameInfo.setPreferredSize(new Dimension(width-10, ScreenInfo.textFontMedium.getSize()*5));

        panelMain.add(panelPowerupInfo);
        panelPowerupInfo.setPreferredSize(new Dimension(width-10, height*3/9));

        panelMain.add(panelAtomInfo);
        panelAtomInfo.setPreferredSize(new Dimension(width-10, height*4/9));

        panelMain.setVisible(true);
    }

    private void SetFonts(){
        textAreaStaticScore.setFont(ScreenInfo.textFontMedium);
        textAreaScore.setFont(ScreenInfo.textFontMedium);

        textAreaTime.setFont(ScreenInfo.textFontMedium);
        textAreaStaticTime.setFont(ScreenInfo.textFontMedium);

        textAreaHealth.setFont(ScreenInfo.textFontMedium);
        textAreaStaticHealth.setFont(ScreenInfo.textFontMedium);

        textAreaAlphaPowerupAmount.setFont(ScreenInfo.textFontMedium);
        textAreaBetaPowerupAmount.setFont(ScreenInfo.textFontMedium);
        textAreaGammaPowerupAmount.setFont(ScreenInfo.textFontMedium);
        textAreaSigmaPowerupAmount.setFont(ScreenInfo.textFontMedium);

        buttonEtaShield.setFont(ScreenInfo.textFontMedium);
        buttonEtaShield.setBackground(ScreenInfo.buttonBackgroundColor);
        buttonEtaShield.setFocusPainted(false);
        buttonEtaShield.setFocusable(false);
        textAreaEtaShieldAmount.setFont(ScreenInfo.textFontMedium);

        textAreaLotaShieldAmount.setFont(ScreenInfo.textFontMedium);
        buttonLotaShield.setBackground(ScreenInfo.buttonBackgroundColor);
        buttonLotaShield.setFont(ScreenInfo.textFontMedium);
        buttonLotaShield.setFocusPainted(false);
        buttonLotaShield.setFocusable(false);

        textAreaThetaShieldAmount.setFont(ScreenInfo.textFontMedium);
        buttonThetaShield.setBackground(ScreenInfo.buttonBackgroundColor);
        buttonThetaShield.setFont(ScreenInfo.textFontMedium);
        buttonThetaShield.setFocusPainted(false);
        buttonThetaShield.setFocusable(false);

        textAreaZetaShieldAmount.setFont(ScreenInfo.textFontMedium);
        buttonZetaShield.setBackground(ScreenInfo.buttonBackgroundColor);
        buttonZetaShield.setFont(ScreenInfo.textFontMedium);
        buttonZetaShield.setFocusPainted(false);
        buttonZetaShield.setFocusable(false);

        textAreaAlphaAtomAmount.setFont(ScreenInfo.textFontMedium);
        textAreaBetaAtomAmount.setFont(ScreenInfo.textFontMedium);
        textAreaGammaAtomAmount.setFont(ScreenInfo.textFontMedium);
        textAreaSigmaAtomAmount.setFont(ScreenInfo.textFontMedium);
    }

    private void SetPowerupIcons(){
        int iconHeight = height/15;

        ImageIcon alphaIcon = new ImageIcon("./assets/Powerups/Alpha.png");
        Image newAlphaIcon = alphaIcon.getImage().getScaledInstance(65, iconHeight,  java.awt.Image.SCALE_SMOOTH);
        powerupAlpha.setIcon(new ImageIcon(newAlphaIcon));
        powerupAlpha.setPreferredSize(new Dimension(iconHeight, iconHeight));
        powerupAlpha.setFocusPainted(false);
        powerupAlpha.setFocusable(false);

        ImageIcon betaIcon = new ImageIcon("./assets/Powerups/Beta.png");
        Image newBetaIcon = betaIcon.getImage().getScaledInstance(iconHeight, iconHeight,  java.awt.Image.SCALE_SMOOTH);
        powerupBeta.setIcon(new ImageIcon(newBetaIcon));
        powerupBeta.setPreferredSize(new Dimension(iconHeight, iconHeight));
        powerupBeta.setFocusPainted(false);
        powerupBeta.setFocusable(false);

        ImageIcon gammaIcon = new ImageIcon("./assets/Powerups/Gamma.png");
        Image newGammaIcon = gammaIcon.getImage().getScaledInstance(32, iconHeight,  java.awt.Image.SCALE_SMOOTH);
        powerupGamma.setIcon(new ImageIcon(newGammaIcon));
        powerupGamma.setPreferredSize(new Dimension(iconHeight, iconHeight));
        powerupGamma.setFocusPainted(false);
        powerupGamma.setFocusable(false);

        ImageIcon sigmaIcon = new ImageIcon("./assets/Powerups/Sigma.png");
        Image newSigmaIcon = sigmaIcon.getImage().getScaledInstance((int) (iconHeight*0.76), iconHeight,  java.awt.Image.SCALE_SMOOTH);
        powerupSigma.setIcon(new ImageIcon(newSigmaIcon));
        powerupSigma.setPreferredSize(new Dimension(iconHeight, iconHeight));
        powerupSigma.setFocusPainted(false);
        powerupSigma.setFocusable(false);
    }

    private void SetAtomIcons(){
        int atomHeight = height/20;
        ImageIcon alphaIcon = new ImageIcon("./assets/Atoms/Alpha.png");
        Image newAlphaIcon = alphaIcon.getImage().getScaledInstance(atomHeight, atomHeight,  java.awt.Image.SCALE_SMOOTH);
        atomIconAlpha.setIcon(new ImageIcon(newAlphaIcon));
        atomIconAlpha.setPreferredSize(new Dimension(atomHeight, atomHeight));

        ImageIcon betaIcon = new ImageIcon("./assets/Atoms/Beta.png");
        Image newBetaIcon = betaIcon.getImage().getScaledInstance(atomHeight, atomHeight,  java.awt.Image.SCALE_SMOOTH);
        atomIconBeta.setIcon(new ImageIcon(newBetaIcon));
        atomIconBeta.setPreferredSize(new Dimension(atomHeight, atomHeight));

        ImageIcon gammaIcon = new ImageIcon("./assets/Atoms/Gamma.png");
        Image newGammaIcon = gammaIcon.getImage().getScaledInstance(atomHeight, atomHeight,  java.awt.Image.SCALE_SMOOTH);
        atomIconGamma.setIcon(new ImageIcon(newGammaIcon));
        atomIconGamma.setPreferredSize(new Dimension(atomHeight, atomHeight));

        ImageIcon sigmaIcon = new ImageIcon("./assets/Atoms/Sigma.png");
        Image newSigmaIcon = sigmaIcon.getImage().getScaledInstance(atomHeight, atomHeight,  java.awt.Image.SCALE_SMOOTH);
        atomIconSigma.setIcon(new ImageIcon(newSigmaIcon));
        atomIconSigma.setPreferredSize(new Dimension(atomHeight, atomHeight));

        ImageIcon blendIconLocal = new ImageIcon("./Assets/mixer.png");
        Image newBlendIcon = blendIconLocal.getImage().getScaledInstance(atomHeight*2, atomHeight*2,  java.awt.Image.SCALE_SMOOTH);
        blendIcon.setIcon(new ImageIcon(newBlendIcon));
        blendIcon.setPreferredSize(new Dimension(atomHeight*2, atomHeight*2));
    }

    public void SetData(GameData gameData){
        textAreaScore.setText(String.valueOf(gameData.getScore()));
        textAreaTime.setText(String.valueOf(gameData.getRemainingTime()));
        textAreaHealth.setText(String.valueOf(gameData.getHealth()));

        textAreaAlphaPowerupAmount.setText(String.valueOf(gameData.getAmmunition().get(FinalValues.POWERUP).get(FinalValues.ALPHA)));
        textAreaBetaPowerupAmount.setText(String.valueOf(gameData.getAmmunition().get(FinalValues.POWERUP).get(FinalValues.BETA)));
        textAreaGammaPowerupAmount.setText(String.valueOf(gameData.getAmmunition().get(FinalValues.POWERUP).get(FinalValues.GAMMA)));
        textAreaSigmaPowerupAmount.setText(String.valueOf(gameData.getAmmunition().get(FinalValues.POWERUP).get(FinalValues.SIGMA)));

        textAreaEtaShieldAmount.setText(String.valueOf(gameData.getRemainingShield().get(FinalValues.ETA)));
        textAreaLotaShieldAmount.setText(String.valueOf(gameData.getRemainingShield().get(FinalValues.LOTA)));
        textAreaThetaShieldAmount.setText(String.valueOf(gameData.getRemainingShield().get(FinalValues.THETA)));
        textAreaZetaShieldAmount.setText(String.valueOf(gameData.getRemainingShield().get(FinalValues.ZETA)));

        textAreaAlphaAtomAmount.setText(String.valueOf(gameData.getAmmunition().get(FinalValues.ATOM).get(FinalValues.ALPHA)));
        textAreaBetaAtomAmount.setText(String.valueOf(gameData.getAmmunition().get(FinalValues.ATOM).get(FinalValues.BETA)));
        textAreaGammaAtomAmount.setText(String.valueOf(gameData.getAmmunition().get(FinalValues.ATOM).get(FinalValues.GAMMA)));
        textAreaSigmaAtomAmount.setText(String.valueOf(gameData.getAmmunition().get(FinalValues.ATOM).get(FinalValues.SIGMA)));
    }

    private void ActionListener(){
        powerupAlpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameActionHandler("choose powerup 1", gameController).PerformAction();
            }
        });
        powerupBeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameActionHandler("choose powerup 2", gameController).PerformAction();
            }
        });
        powerupGamma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameActionHandler("choose powerup 3", gameController).PerformAction();
            }
        });
        powerupSigma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameActionHandler("choose powerup 4", gameController).PerformAction();
            }
        });
        buttonEtaShield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameActionHandler("eta shield", gameController).PerformAction();
            }
        });

        //buttonEtaShield.setVisible(true);
        buttonLotaShield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameActionHandler("lota shield", gameController).PerformAction();
            }
        });
        buttonThetaShield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameActionHandler("theta shield", gameController).PerformAction();
            }
        });
        buttonZetaShield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameActionHandler("zeta shield", gameController).PerformAction();
            }
        });
    }

    @Override
    public void onTimeChange(String time) {
        textAreaTime.setText(time);
    }

    @Override
    public void onHealthChange(String health) {
        textAreaHealth.setText(health);
    }

    @Override
    public void onScoreChange(String score) {
        textAreaScore.setText(score);
    }

    @Override
    public void onAmmunitionChange(HashMap<String, HashMap<String, Integer>> map) {
        textAreaAlphaPowerupAmount.setText(String.valueOf(map.get(FinalValues.POWERUP).get(FinalValues.ALPHA)));
        textAreaBetaPowerupAmount.setText(String.valueOf(map.get(FinalValues.POWERUP).get(FinalValues.BETA)));
        textAreaGammaPowerupAmount.setText(String.valueOf(map.get(FinalValues.POWERUP).get(FinalValues.GAMMA)));
        textAreaSigmaPowerupAmount.setText(String.valueOf(map.get(FinalValues.POWERUP).get(FinalValues.SIGMA)));

        textAreaAlphaAtomAmount.setText(String.valueOf(map.get(FinalValues.ATOM).get(FinalValues.ALPHA)));
        textAreaBetaAtomAmount.setText(String.valueOf(map.get(FinalValues.ATOM).get(FinalValues.BETA)));
        textAreaGammaAtomAmount.setText(String.valueOf(map.get(FinalValues.ATOM).get(FinalValues.GAMMA)));
        textAreaSigmaAtomAmount.setText(String.valueOf(map.get(FinalValues.ATOM).get(FinalValues.SIGMA)));
    }

    @Override
    public void onShieldChange(HashMap<String, Integer> remainingShields) {
        textAreaEtaShieldAmount.setText(String.valueOf(remainingShields.get(FinalValues.ETA)));
        textAreaLotaShieldAmount.setText(String.valueOf(remainingShields.get(FinalValues.LOTA)));
        textAreaThetaShieldAmount.setText(String.valueOf(remainingShields.get(FinalValues.THETA)));
        textAreaZetaShieldAmount.setText(String.valueOf(remainingShields.get(FinalValues.ZETA)));
    }
}