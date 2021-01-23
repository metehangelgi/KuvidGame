package UI;

import UI.Audio.AudioController;

public class Main {
    public static void main(String[] args) {
        UIController uiController = UIController.GetInstance();
        uiController.openHomeScreen();

        AudioController.GetInstance().onStart();
    }
}
