package Domain.Player;

public class Score{

    //Obviously we start from 0 score.
    private double totalScore;

    public Score(double scoreLevel){
        this.totalScore=scoreLevel;
    }

    // Total score formula. We add total collected molecules and (1/collection time in seconds)
    // so that faster player gets more score.

    //Collection time is the current Y / speed of the molecule

    public double updateTotalScore(double efficiency, double collection_time){
        totalScore = totalScore + efficiency + (1/collection_time);
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getTotalScore() {
        return totalScore;
    }

}
