package Domain.Objects;

import Domain.Utils.FinalValues;
import Domain.Utils.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Beta_Atom extends Atom{


    public Beta_Atom(Position position,int angle) {
        super(FinalValues.BETA,position,angle);
        super.setNum_of_protons(16);
        super.setStability(0.9);
        List<Integer> givenList = Arrays.asList(15, 16, 17,18,21);
        Random rand = new Random();
        int neutron = givenList.get(rand.nextInt(givenList.size()));
        super.setNum_of_neutrons(neutron);
        super.setEfficiency(getStability()-(0.5*Math.abs(getNum_of_neutrons()-getNum_of_protons())/getNum_of_protons()));
    }
}
