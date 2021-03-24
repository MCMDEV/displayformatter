package de.mcmdev.displayformatter.spigot;

public class TeamWeightPair {

    private final String teamName;
    private final int weight;

    public TeamWeightPair(String teamName, int weight) {
        this.teamName = teamName;
        this.weight = weight;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamWeightPair that = (TeamWeightPair) o;

        if (weight != that.weight) return false;
        return teamName != null ? teamName.equals(that.teamName) : that.teamName == null;
    }

    @Override
    public int hashCode() {
        int result = teamName != null ? teamName.hashCode() : 0;
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "TeamWeightPair{" +
                "teamName='" + teamName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
