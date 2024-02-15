package fr.norsys.nutriscore.view;

import fr.norsys.nutriscore.controller.NutriScoreController;
import fr.norsys.nutriscore.model.ScoreLevel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NutriScorePanel extends JPanel implements PropertyChangeListener {

    private final NutriScoreController nutriScoreController;

    private final ImagePanel imagePanel;
    private final JSlider slider;

    public NutriScorePanel(NutriScoreController nutriScoreController) {
        this.nutriScoreController = nutriScoreController;
        nutriScoreController.getModel().addPropertyChangeListener(this);

        imagePanel = new ImagePanel();
        slider = new JSlider(0, ScoreLevel.values().length - 1, ImagePanel.DEFAULT_SCORE_LEVEL);
        slider.addChangeListener(nutriScoreController);

        nutriScoreController.getModel().addPropertyChangeListener(evt -> {
            ScoreLevel score = (ScoreLevel) evt.getNewValue();
            slider.setValue(score.ordinal());
        });

        //TODO manage layout
        add(imagePanel);
        add(slider);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ScoreLevel score = (ScoreLevel) evt.getNewValue();
        imagePanel.updateImage(score.ordinal());
    }
}
