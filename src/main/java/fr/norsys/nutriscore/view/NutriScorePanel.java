package fr.norsys.nutriscore.view;

import fr.norsys.nutriscore.controller.NutriScoreController;
import fr.norsys.nutriscore.model.ScoreLevel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NutriScorePanel extends JPanel implements PropertyChangeListener {

    private final NutriScoreController nutriScoreController;

    private final ImagePanel imagePanel;
    private final JSlider slider;

    public NutriScorePanel(NutriScoreController nutriScoreController) {
        this.nutriScoreController = nutriScoreController;
        nutriScoreController.getModel().addPropertyChangeListener(this);

        imagePanel = new ImagePanel();
        slider = new JSlider(0, ScoreLevel.values().length - 1, ImagePanel.DEFAULT_SCORE_LEVEL);
        slider.setPaintTicks(false);
        slider.addChangeListener(nutriScoreController);

        nutriScoreController.getModel().addPropertyChangeListener(evt -> {
            ScoreLevel score = (ScoreLevel) evt.getNewValue();
            slider.setValue(score.ordinal());
        });

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(imagePanel, BorderLayout.NORTH);

        //Panel that contains the slider, to center it
        JPanel sliderPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        sliderPanel.add(Box.createHorizontalBox(), gbc);
        gbc.gridx = 1;
        sliderPanel.add(slider, gbc);
        gbc.gridx = 2;
        sliderPanel.add(Box.createHorizontalBox(), gbc);
        sliderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(sliderPanel);

        setSliderSize();

    }

    private void setSliderSize() {
        int imagePanelWidth = imagePanel.getImageWidth();
        int scoreImageWidth = imagePanelWidth / ScoreLevel.values().length;
        Dimension sliderSize = new Dimension(scoreImageWidth * (ScoreLevel.values().length - 1), 50);
        slider.setSize(sliderSize);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ScoreLevel score = (ScoreLevel) evt.getNewValue();
        imagePanel.updateImage(score.ordinal());
    }
}
