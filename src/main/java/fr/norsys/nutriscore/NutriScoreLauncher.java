package fr.norsys.nutriscore;

import fr.norsys.nutriscore.controller.NutriScoreController;
import fr.norsys.nutriscore.model.NutriScoreModel;
import fr.norsys.nutriscore.service.RandomNumberRetriever;
import fr.norsys.nutriscore.view.NutriScoreComponent;
import fr.norsys.nutriscore.view.NutriScoreSlider;

import javax.swing.*;
import java.io.IOException;

public class NutriScoreLauncher {

    public static void main(String[] args) {
        System.out.println("Starting NutriScore app!");

        JFrame frame = new JFrame("MFI NUTRI-SCORE");
        NutriScoreModel nutriScoreModel = new NutriScoreModel();
        NutriScoreController nutriScoreController = new NutriScoreController();
        NutriScoreComponent nutriScoreComponent = new NutriScoreComponent(nutriScoreController);
        nutriScoreController.setNutriScoreModel(nutriScoreModel);
        nutriScoreController.setNutriScoreComponent(nutriScoreComponent);

        frame.add(nutriScoreComponent);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //testRandom();

    }

    //TODO delete
    private static void testRandom() {
        RandomNumberRetriever randomNumberRetriever = new RandomNumberRetriever();
        try {
            randomNumberRetriever.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //TODO change that
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