package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Gamma_Atom extends Atom {



    public Gamma_Atom(Position position,int angle) {
        super(FinalValues.GAMMA,position,angle);
        super.setStability(0.8);
        super.setNum_of_protons(32);
        List<Integer> givenList = Arrays.asList(29, 32, 33);
        Random rand = new Random();
        int neutron = givenList.get(rand.nextInt(givenList.size()));
        super.setNum_of_neutrons(neutron);
        super.setEfficiency(getStability() + (Math.abs(getNum_of_neutrons()-getNum_of_protons())/(2*getNum_of_protons())));
    }
}
