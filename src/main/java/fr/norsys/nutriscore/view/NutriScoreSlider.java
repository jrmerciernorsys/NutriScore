package fr.norsys.nutriscore.view;

import fr.norsys.nutriscore.controller.NutriScoreController;
import fr.norsys.nutriscore.controller.NutriScoreSliderChangeListener;
import fr.norsys.nutriscore.model.ScoreLevel;

import javax.swing.*;

public class NutriScoreSlider extends JSlider {

    private final NutriScoreController nutriScoreController;

    //TODO voir si cette classe est utile
    public NutriScoreSlider(NutriScoreController nutriScoreController) {
        super(0, ScoreLevel.values().length - 1);
        this.nutriScoreController = nutriScoreController;

        this.addChangeListener(nutriScoreController);
    }
}
