package fr.norsys.nutriscore.controller;

import fr.norsys.nutriscore.view.NutriScoreSlider;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NutriScoreSliderChangeListener implements ChangeListener {
    public NutriScoreSliderChangeListener() {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int sliderValue = (int)source.getValue();
            System.out.println("Slider value: " + sliderValue);
            //TODO controller updateImage(sliderValue);
        }
    }
}
