package fr.norsys.nutriscore.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NutriScoreModel {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    ScoreLevel actualScore;
    public NutriScoreModel() {
        actualScore = ScoreLevel.A;
    }

    public void setScore(ScoreLevel score) {
        ScoreLevel previousScore = actualScore;
        actualScore = score;
        propertyChangeSupport.firePropertyChange(ScoreLevel.class.getName(), previousScore, actualScore);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}
