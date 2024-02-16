package fr.norsys.nutriscore.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NutriScoreModel {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    ScoreLevel actualScore;

    private final static ScoreLevel INITIAL_SCORE_LEVEL = ScoreLevel.A;

    public NutriScoreModel() {
        actualScore = INITIAL_SCORE_LEVEL;
    }

    public void setScore(ScoreLevel score) {
        if (actualScore.equals(score)) {
            return;
        }
        ScoreLevel previousScore = actualScore;
        actualScore = score;
        System.out.printf("Model: value changed to %d%n", score.ordinal());
        propertyChangeSupport.firePropertyChange(ScoreLevel.class.getName(), previousScore, actualScore);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}
