package Domain.Collision;

import Domain.Objects.GameObject;
import Domain.Player.Player;
import UI.Audio.AudioController;
import Domain.Utils.AudioListener;

public class ShooterBlockerCollision implements CollisionStrategy {

    @Override
    public void doCollision(GameObject object1, GameObject object2) {
        double centerYofBlocker=object1.getCurrentPosition().getY()+object1.getHeight()/2.0;
        double centerXofBlocker=object1.getCurrentPosition().getX()+object1.getWidth()/2.0;
        double centerYofShooter=object2.getCurrentPosition().getY()+object2.getHeight()/2.0;
        double centerXofShooter=object2.getCurrentPosition().getX()+object2.getHeight()/2.0;
        int distance=(int) Math.sqrt(Math.pow(centerYofShooter-centerYofBlocker,2)+Math.pow(centerXofShooter-centerXofBlocker,2));
        Player.getInstance().hit(distance);
        object1.destroy();

        AudioListener audioListener = AudioController.GetInstance();
        audioListener.onExplosion();
    }
}
