package fr.norsys.nutriscore.view;

import fr.norsys.nutriscore.controller.NutriScoreController;
import fr.norsys.nutriscore.model.ScoreLevel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Panel that contains the nutriscore image and the slider to change the score
 */
public class NutriScorePanel extends JPanel implements PropertyChangeListener {

    private final ImagePanel imagePanel;
    private final JSlider slider;

    public NutriScorePanel(NutriScoreController nutriScoreController) {
        nutriScoreController.getModel().addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        imagePanel = new ImagePanel();
        add(imagePanel, BorderLayout.NORTH);

        slider = new JSlider(0, ScoreLevel.values().length - 1, ImagePanel.DEFAULT_SCORE_LEVEL);
        slider.addChangeListener(nutriScoreController);
        JPanel sliderPanel = createSliderPanel();
        add(sliderPanel, BorderLayout.SOUTH);

        setSliderSizeToMachOneScoreImage();
    }

    /**
     * @return a panel that contains the slider, to center it
     */
    private JPanel createSliderPanel() {
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
        return sliderPanel;
    }

    private void setSliderSizeToMachOneScoreImage() {
        int imagePanelWidth = imagePanel.getImageWidth();
        int scoreImageWidth = imagePanelWidth / ScoreLevel.values().length;
        Dimension sliderSize = new Dimension(scoreImageWidth * (ScoreLevel.values().length - 1), 50);
        slider.setSize(sliderSize);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //Model has fired us because score changed
        ScoreLevel score = (ScoreLevel) evt.getNewValue();
        slider.setValue(score.ordinal());
        imagePanel.updateImage(score.ordinal());
    }
}
