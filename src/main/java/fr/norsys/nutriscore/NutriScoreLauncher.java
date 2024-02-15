package fr.norsys.nutriscore;

import fr.norsys.nutriscore.controller.NutriScoreController;
import fr.norsys.nutriscore.model.NutriScoreModel;
import fr.norsys.nutriscore.view.NutriScorePanel;

import javax.swing.*;

public class NutriScoreLauncher {

    public static void main(String[] args) {
        System.out.println("Starting NutriScore app!");

        JFrame frame = new JFrame("MFI NUTRI-SCORE");
        NutriScoreModel nutriScoreModel = new NutriScoreModel();
        NutriScoreController nutriScoreController = new NutriScoreController(nutriScoreModel);
        NutriScorePanel nutriScorePanel = new NutriScorePanel(nutriScoreController);

        frame.add(nutriScorePanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        nutriScoreController.startRandomNumberUpdater();
    }

}