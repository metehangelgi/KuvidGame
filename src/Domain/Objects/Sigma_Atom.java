package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sigma_Atom extends Atom {


    public Sigma_Atom(Position position,int angle) {
        super(FinalValues.SIGMA,position,angle);
        super.setNum_of_protons(64);
        super.setStability(0.7);
        List<Integer> givenList = Arrays.asList(63, 64, 67);
        Random rand = new Random();
        int neutron = givenList.get(rand.nextInt(givenList.size()));
        super.setNum_of_neutrons(neutron);
        super.setEfficiency((1 + getStability())/ 2 + (Math.abs(getNum_of_neutrons()-getNum_of_protons())/getNum_of_protons()));
    }
}
