package UI.Audio;

import Domain.Utils.AudioListener;

public class AudioController implements AudioListener {

    private double gameMusicVolume = 1.0;
    private double menuMusicVolume = 1.0;
    private double SFXVolume = 1.0;

    private Audio backgroundMenuMusic;
    private Audio backgroundGameMusic;
    private Audio explosionSFX;
    private Audio fireSFX;
    private Audio gameOverSFX;
    private Audio blockSFX;
    private Audio buttonClick;

    private static AudioController instance;

    public static AudioController GetInstance() {
        if (instance == null) {
            instance = new AudioController();
        }
        return instance;
    }

    public AudioController() {
        instance = this;
    }

    @Override
    public void setGameMusicVolume(double volume) {
        gameMusicVolume = volume;
    }

    @Override
    public int getGameMusicVolumeAsInteger() {
        return (int) (gameMusicVolume * 100);
    }

    @Override
    public int getMenuMusicVolumeAsInteger() {
        return (int) (menuMusicVolume * 100);
    }

    @Override
    public void setMenuMusicVolume(double volume) {
        menuMusicVolume = volume;
        backgroundMenuMusic.SetVolume(volume);
    }

    @Override
    public int getSFXVolumeAsInteger() {
        return (int) (SFXVolume * 100);
    }

    @Override
    public void setSFXVolume(double volume) {
        SFXVolume = volume;
    }

    @Override
    public void onStart() {
        backgroundMenuMusic = new Audio("backgroundMenu");
        backgroundMenuMusic.Start(true);
    }

    @Override
    public void onGameStart() {
        backgroundGameMusic = new Audio("backgroundGame");
        backgroundGameMusic.Start(true);
    }

    @Override
    public void onExplosion() {
        explosionSFX = new Audio("explosion");
        explosionSFX.Start(false);
    }

    @Override
    public void onFire() {
        fireSFX = new Audio("fire");
        fireSFX.Start(false);
    }

    @Override
    public void onGameOver() {
        gameOverSFX = new Audio("fire");
        gameOverSFX.Start(false);
    }

    @Override
    public void onBlock() {
        blockSFX = new Audio("fire");
        blockSFX.Start(false);
    }

    @Override
    public void onButtonClick() {
        buttonClick = new Audio("buttonClick");
        buttonClick.Start(false);
    }
}
