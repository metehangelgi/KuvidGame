package Domain.Utils;

import java.awt.event.KeyEvent;

public enum HotKeys {
    MOVE_LEFT_PRIMARY(KeyEvent.VK_A),
    MOVE_LEFT_SECONDARY(KeyEvent.VK_LEFT),

    MOVE_RIGHT_PRIMARY(KeyEvent.VK_D),
    MOVE_RIGHT_SECONDARY(KeyEvent.VK_RIGHT),

    ROTATE_LEFT_PRIMARY(KeyEvent.VK_Q),
    ROTATE_RIGHT_PRIMARY(KeyEvent.VK_E),

    CHANGE_ATOM_PRIMARY(KeyEvent.VK_C),
    CHANGE_ATOM_SECONDARY(KeyEvent.VK_DOWN),

    SHOOT_PRIMARY(KeyEvent.VK_SPACE),
    SHOOT_SECONDARY(KeyEvent.VK_UP),

    BLEND_PRIMARY(KeyEvent.VK_B),
    BLEND_SECONDARY(KeyEvent.VK_NUMPAD0),

    ATOM_TYPE_ALPHA_PRIMARY(KeyEvent.VK_1),
    ATOM_TYPE_ALPHA_SECONDARY(KeyEvent.VK_NUMPAD1),

    ATOM_TYPE_BETA_PRIMARY(KeyEvent.VK_2),
    ATOM_TYPE_BETA_SECONDARY(KeyEvent.VK_NUMPAD2),

    ATOM_TYPE_GAMMA_PRIMARY(KeyEvent.VK_3),
    ATOM_TYPE_GAMMA_SECONDARY(KeyEvent.VK_NUMPAD3),

    ATOM_TYPE_SIGMA_PRIMARY(KeyEvent.VK_4),
    ATOM_TYPE_SIGMA_SECONDARY(KeyEvent.VK_NUMPAD4),

    PAUSE_PRIMARY(KeyEvent.VK_ESCAPE),
    PAUSE_SECONDARY(KeyEvent.VK_P),

    // ALT + ATOM TYPE -> PICKING POWERUP
    POWERUP_SELECTOR(KeyEvent.ALT_DOWN_MASK),

    // ALT + ATOM TYPE -> ADD SHIELD (1: Eta, 2: Lota, 3: Theta, 4: Zeta)
    SHIELD_SELECTOR(KeyEvent.CTRL_DOWN_MASK);

    private int id;

    HotKeys(int id) {
        this.id = id;
    }

    public int getValue(){
        return id;
    }

}
