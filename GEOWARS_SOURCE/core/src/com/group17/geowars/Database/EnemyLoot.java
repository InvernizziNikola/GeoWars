package com.group17.geowars.database;

/**
 * Created by nikola on 07/11/2016.
 */
public class EnemyLoot
{
    private int scorePoints = 0;
    private int experience  = 0;
    private int multiplier  = 0;

    public EnemyLoot(int score, int exp, int mult)
    {
        scorePoints = score;
        experience = exp;
        multiplier = mult;
    }

    public int getExperience() {
        return experience;
    }

    public int getScorePoints() {
        return scorePoints;
    }

    public int getMultiplier() {
        return multiplier;
    }

    @Override
    public String toString() {
        return "Experience: " + experience + ", Scorepoints: " + scorePoints + ", multiplier: " + multiplier;
    }
}
