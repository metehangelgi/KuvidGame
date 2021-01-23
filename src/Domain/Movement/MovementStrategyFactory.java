package Domain.Movement;

import Domain.Objects.GameObject;

public class MovementStrategyFactory {
    private static MovementStrategy atomMovement = new AtomMovement();
    private static MovementStrategy blockerMovement = new BlockerMovement();
    private static MovementStrategy alphaMolMovement = new AlphaMoleculeMovement();
    private static MovementStrategy betaMolMovement = new BetaMoleculeMovement();
    private static MovementStrategy gammaMolMovement = new GammaMoleculeMovement();
    private static MovementStrategy sigmaMolMovement = new SigmaMoleculeMovement();
    private static MovementStrategy powerupMovement = new PowerupMovement();
    private static MovementStrategy clockMovement = new ClockMovement();
    private static MovementStrategy hearthMovement = new HearthMovement();


    public static MovementStrategy getStrategy(GameObject obj){
        MovementStrategy movementStrategy=null;
        switch (obj.getType()){
            case "Atom":
                movementStrategy = atomMovement;
                break;
            case "Blocker":
                movementStrategy=blockerMovement;
                break;
            case "Molecule":
                switch (obj.getSubType()){
                    case "Alpha":
                        movementStrategy=alphaMolMovement;
                        break;
                    case "Beta":
                        movementStrategy=betaMolMovement;
                        break;
                    case "Gamma":
                        movementStrategy=gammaMolMovement;
                        break;
                    case "Sigma":
                        movementStrategy=sigmaMolMovement;
                        break;
                }
                break;
            case "Powerup":
                movementStrategy=powerupMovement;
                break;
            case "Clock":
                movementStrategy=clockMovement;
                break;
            case "Hearth":
                movementStrategy=hearthMovement;
                break;
            default:
                //How to handle Shooter...
                movementStrategy=sigmaMolMovement;
                //throw new IllegalStateException("Unexpected value: " + obj.getType());
        }
        return  movementStrategy;
    }
}
