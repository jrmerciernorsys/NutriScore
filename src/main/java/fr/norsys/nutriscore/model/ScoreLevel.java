package fr.norsys.nutriscore.model;

public enum ScoreLevel {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4);

    private final int value;

    ScoreLevel(int value) {
        this.value = value;
    }

    public static ScoreLevel valueOf(int value) {
        for (ScoreLevel scoreLevel : ScoreLevel.values()) {
            if (scoreLevel.value == value) {
                return scoreLevel;
            }
        }
        throw new IllegalArgumentException("No enum constant ScoreLevel with value " + value);
    }
}
