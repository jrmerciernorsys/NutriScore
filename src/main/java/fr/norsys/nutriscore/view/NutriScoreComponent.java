package fr.norsys.nutriscore.view;

import fr.norsys.nutriscore.controller.NutriScoreController;
import fr.norsys.nutriscore.model.ScoreLevel;

import javax.swing.*;

public class NutriScoreComponent extends JPanel {

    private final NutriScoreController nutriScoreController;

    private final ImagePanel imagePanel;
    private final JSlider slider;

    public NutriScoreComponent(NutriScoreController nutriScoreController) {
        this.nutriScoreController = nutriScoreController;

        imagePanel = new ImagePanel();
        slider = new JSlider(0, ScoreLevel.values().length - 1);
        slider.addChangeListener(nutriScoreController);

        //TODO manage layout
        add(imagePanel);
        add(slider);
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public JSlider getSlider() {
        return slider;
    }
}
