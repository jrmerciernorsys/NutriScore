package fr.norsys.nutriscore;

import fr.norsys.nutriscore.service.RandomNumberRetriever;

import javax.swing.*;
import java.io.IOException;

public class NutriScoreLauncher {

    public static void main(String[] args) {
        System.out.println("Starting NutriScore app!");

        RandomNumberRetriever randomNumberRetriever = new RandomNumberRetriever();
        try {
            randomNumberRetriever.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSlider slider = new JSlider(0, 4);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        int testLoopNb = 2;
        for (int i = 0; i < testLoopNb; i++) {

            try {
                System.out.println(randomNumberRetriever.retrieveRandomNumberFromUrl());
                Thread.sleep(1000);

                //TODO Create a JSlider in a panel that will receive the value of the random number

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}