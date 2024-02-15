package fr.norsys.nutriscore.controller;

import fr.norsys.nutriscore.model.NutriScoreModel;
import fr.norsys.nutriscore.service.RandomNumberRetriever;
import fr.norsys.nutriscore.view.NutriScoreComponent;
import fr.norsys.nutriscore.view.NutriScoreSlider;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;

public class NutriScoreController implements ChangeListener {

    private NutriScoreModel nutriScoreModel;
    private NutriScoreComponent nutriScoreComponent;
    RandomNumberRetriever randomNumberRetriever;

    public NutriScoreController() {
        randomNumberRetriever = new RandomNumberRetriever();
        try {
            randomNumberRetriever.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setNutriScoreModel(NutriScoreModel nutriScoreModel) {
        this.nutriScoreModel = nutriScoreModel;
    }
    
    public void setNutriScoreComponent(NutriScoreComponent nutriScoreComponent) {
        this.nutriScoreComponent = nutriScoreComponent;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int sliderValue = (int)source.getValue();
            System.out.println("Slider value: " + sliderValue);
            //TODO fire event to update image

            nutriScoreComponent.getImagePanel().updateImage(sliderValue);

        }
    }
}
