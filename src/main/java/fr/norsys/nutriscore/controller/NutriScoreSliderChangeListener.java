package fr.norsys.nutriscore.controller;

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
            int sliderValue = source.getValue();
            System.out.println("Slider value: " + sliderValue);
            //TODO controller updateImage(sliderValue);
        }
    }
}
