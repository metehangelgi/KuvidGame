package UI;

import Domain.Objects.Atom;
import Domain.Objects.GameObject;
import Domain.ShooterFunctions.Shooter;
import Domain.Utils.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class GamePanel extends JPanel {
    public ConcurrentHashMap<GameObject, ObjectPanel> hashMap = new ConcurrentHashMap<>();
    public Shooter shooter;
    public GameObject triggerObject;
    private ObjectPanel triggerPanel;

    public GamePanel(int gameScreenWidth,int gameScreenHeight){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("assets/kuvid_bc.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(gameScreenWidth, gameScreenHeight, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        this.add(new JLabel(imageIcon));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (ObjectPanel panel2:hashMap.values()) {
            panel2.draw(g);
            triggerPanel.draw(g);
        }
    }


    public void onCreate(GameObject obj) {
        ObjectPanel objectPanel = new ObjectPanel(obj.getType(), obj.getSubType(), obj.getCurrentPosition(),obj.getWidth(),obj.getHeight(),obj.getShape(),obj.getRotationType());
        hashMap.put(obj, objectPanel);
        if(obj.getType().equals("Shooter")){
            shooter=(Shooter) obj;
            triggerObject=shooter.getObjectInTrigger();
            triggerPanel=new ObjectPanel(triggerObject.getType(),triggerObject.getSubType(),triggerObject.getCurrentPosition(),
                    triggerObject.getWidth(),triggerObject.getHeight()," "," ");
        }
        repaint();
    }


    public void onLocationChange() {
        for (GameObject object : hashMap.keySet()) {
            if(!object.isAlive()){
                hashMap.remove(object);
                continue;
            }
            hashMap.get(object).updatePosition(object.getCurrentPosition());
        }
        repaint();
    }


    public void onDestroy(GameObject obj1) {
        removeFromScreen(obj1);
    }

    public void onShooterTriggerBulletChange() {
        triggerObject=shooter.getObjectInTrigger();
        //System.out.println("is shielded:(gamepanel içinde) "+((Atom)triggerObject).isShielded() );
        triggerPanel = new ObjectPanel(triggerObject.getType(), triggerObject.getSubType(), triggerObject.getCurrentPosition(),
                triggerObject.getWidth(),triggerObject.getHeight()," "," ");
        repaint();
    }


    public void onShooterPositionChange() {
        hashMap.get(shooter).updatePosition(shooter.getCurrentPosition());
        triggerPanel.updatePosition(triggerObject.getCurrentPosition());
        repaint();
    }

    public void removeFromScreen(GameObject object) {
        if (!object.isAlive()) {
            hashMap.remove(object);
            repaint();
        }
    }
}
