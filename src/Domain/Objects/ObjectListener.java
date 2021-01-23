package Domain.Objects;

public interface ObjectListener {

    void onCreate(GameObject obj);
    void onLocationChange();
    void onDestroy(GameObject obj);
    void onShooterTriggerBulletChange();
    void onShooterPositionChange();
}
