package fr.norsys.nutriscore.controller;

import fr.norsys.nutriscore.model.NutriScoreModel;
import fr.norsys.nutriscore.model.ScoreLevel;
import fr.norsys.nutriscore.service.RandomNumberRetriever;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;

public class NutriScoreController implements ChangeListener {

    private final NutriScoreModel nutriScoreModel;
    RandomNumberRetriever randomNumberRetriever;

    public NutriScoreController(NutriScoreModel nutriScoreModel) {
        this.nutriScoreModel = nutriScoreModel;
        randomNumberRetriever = new RandomNumberRetriever();
        try {
            randomNumberRetriever.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public NutriScoreModel getModel() {
        return nutriScoreModel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int sliderValue = source.getValue();
            System.out.println("Slider value: " + sliderValue);
            nutriScoreModel.setScore(ScoreLevel.valueOf(sliderValue));
        }
    }
}
