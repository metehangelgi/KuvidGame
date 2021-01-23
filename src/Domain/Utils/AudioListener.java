package Domain.Utils;

public interface AudioListener {
    void onStart();
    void onGameStart();
    void onExplosion();
    void onFire();
    void onGameOver();
    void onBlock();
    void onButtonClick();

    int getGameMusicVolumeAsInteger();
    void setGameMusicVolume(double volume);

    int getMenuMusicVolumeAsInteger();
    void setMenuMusicVolume(double volume);

    int getSFXVolumeAsInteger();
    void setSFXVolume(double volume);
}
