package fr.norsys.nutriscore.controller;

import fr.norsys.nutriscore.model.NutriScoreModel;
import fr.norsys.nutriscore.model.ScoreLevel;
import fr.norsys.nutriscore.service.RandomNumberRetriever;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NutriScoreController implements ChangeListener {

    public static final int API_CALL_DELAY = 10000;
    private final NutriScoreModel nutriScoreModel;

    public NutriScoreController(NutriScoreModel nutriScoreModel) {
        this.nutriScoreModel = nutriScoreModel;
    }

    public NutriScoreModel getModel() {
        return nutriScoreModel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int sliderValue = -1;
        if (!(e.getSource() instanceof JSlider source)) {
            throw new IllegalArgumentException("Event source is not a JSlider");
        }
        System.out.println("controller: change event from slider");
        if (!source.getValueIsAdjusting()) {
            sliderValue = source.getValue();
            System.out.println("Slider value: " + sliderValue);
            nutriScoreModel.setScore(ScoreLevel.valueOf(sliderValue));
        }
    }

    public void startRandomNumberUpdater() {
        Runnable randomNumberRetrieverTask = () -> {
            System.out.println("Calling random API");
            try {
                int randomNumber = RandomNumberRetriever.retrieveRandomNumberFromUrl();
                System.out.println("Random number: " + randomNumber);
                getModel().setScore(ScoreLevel.valueOf(randomNumber));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(randomNumberRetrieverTask, API_CALL_DELAY, API_CALL_DELAY, TimeUnit.MILLISECONDS);
        System.out.println("Timer started, waiting " + API_CALL_DELAY + " ms before first call and between each calls");
    }
}
