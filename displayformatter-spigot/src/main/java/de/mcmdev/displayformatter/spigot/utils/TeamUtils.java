package de.mcmdev.displayformatter.spigot.utils;

public class TeamUtils {

    public static String generateTeamName(String name, int weight) {
        String numberString;
        if (weight < 10) {
            numberString = "00" + weight;
        } else if (weight < 100) {
            numberString = "0" + weight;
        } else if (weight < 1000) {
            numberString = "" + weight;
        } else {
            numberString = "0";
        }

        return numberString + name.substring(0, 5);
    }

}
