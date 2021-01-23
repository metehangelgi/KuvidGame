package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Alpha_Atom extends Atom {

    public Alpha_Atom(Position position,int angle) {
        super(FinalValues.ALPHA,position,angle);
        super.setNum_of_protons(8);
        super.setStability(0.85);
        List<Integer> givenList = Arrays.asList(7, 8, 9);
        Random rand = new Random();
        int neutron = givenList.get(rand.nextInt(givenList.size()));
        super.setNum_of_neutrons(neutron);
        super.setEfficiency((1-(Math.abs(getNum_of_neutrons()-getNum_of_protons())/getNum_of_protons()))*getStability());
    }
}
