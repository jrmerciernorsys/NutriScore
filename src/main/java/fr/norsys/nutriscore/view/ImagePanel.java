package fr.norsys.nutriscore.view;

import fr.norsys.nutriscore.model.ScoreLevel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImagePanel extends JPanel {

    public static final int DEFAULT_SCORE_LEVEL = 0;
    private static final String RESOURCES_FOLDER_IMAGES = "/images/";
    private static final List<String> SCORE_SELECTED_IMAGE_NAMES = List.of("A_selected.png", "B_selected.png", "C_selected.png", "D_selected.png", "E_selected.png");
    private static final List<String> SCORE_UNSELECTED_IMAGE_NAMES = List.of("A_unselected.png", "B_unselected.png", "C_unselected.png", "D_unselected.png", "E_unselected.png");

    private static final String NUTRI_SCORE_LABEL_IMAGE_NAME = "nutriscore-label.png";
    // TODO get the image size after loading
    private final static int SCORE_SELECTED_IMAGE_WIDTH = 80;
    private final static int SCORE_SELECTED_IMAGE_HEIGHT = 130;
    public final static int SCORE_UNSELECTED_IMAGE_WIDTH = 60;
    private final static int SCORE_UNSELECTED_IMAGE_HEIGHT = 100;
    private final static int NUTRI_SCORE_LABEL_IMAGE_HEIGHT = 25;
    public static final int SCORE_ELEMENT_NUMBER = ScoreLevel.values().length;
    private final List<BufferedImage> scoreSelectedImages = new ArrayList<>();
    private final List<BufferedImage> scoreUnselectedImages = new ArrayList<>();
    private BufferedImage nutriScoreLabelImage;

    private int imageWidth;
    private int imageHeight;
    private int actualImageIndex = -1;
    private int scoreUnselectedImageStartPositionX;
    private int scoreUnselectedImageStartPositionY;

    public ImagePanel() {

        //Load the all images from resources
        try {
            loadImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initView();
    }

    private void initView() {
        int diffScoreSelectedUnselectedImageWidth = (SCORE_SELECTED_IMAGE_WIDTH - SCORE_UNSELECTED_IMAGE_WIDTH) / 2;
        int diffScoreSelectedUnselectedImageHeight = (SCORE_SELECTED_IMAGE_HEIGHT - SCORE_UNSELECTED_IMAGE_HEIGHT) / 2;
        imageWidth = 2 * diffScoreSelectedUnselectedImageWidth + SCORE_ELEMENT_NUMBER * SCORE_UNSELECTED_IMAGE_WIDTH;
        imageHeight = NUTRI_SCORE_LABEL_IMAGE_HEIGHT + SCORE_SELECTED_IMAGE_HEIGHT;
        setPreferredSize(new Dimension(imageWidth, imageHeight));
        scoreUnselectedImageStartPositionY = NUTRI_SCORE_LABEL_IMAGE_HEIGHT + diffScoreSelectedUnselectedImageHeight;
        scoreUnselectedImageStartPositionX = diffScoreSelectedUnselectedImageWidth;

        updateImage(DEFAULT_SCORE_LEVEL);
    }

    private void loadImages() throws IOException {

        nutriScoreLabelImage = loadImageFromResources(NUTRI_SCORE_LABEL_IMAGE_NAME);

        for (String imageName : SCORE_SELECTED_IMAGE_NAMES) {
            scoreSelectedImages.add(loadImageFromResources(imageName));
        }

        for (String imageName : SCORE_UNSELECTED_IMAGE_NAMES) {
            scoreUnselectedImages.add(loadImageFromResources(imageName));
        }
    }

    private BufferedImage loadImageFromResources(final String imageFileName) throws IOException {
        String imagePath = RESOURCES_FOLDER_IMAGES + imageFileName;
        return ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath), "Cannot find image in resources: " + imagePath));
    }

    public void updateImage(int index) {
        if (actualImageIndex == index) {
            return;
        }
        actualImageIndex = index;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draw nutriscore label
        g.drawImage(nutriScoreLabelImage, 0, 0, this);

        //fill background under nutriscore label; done each time because it can be dirty
        g.setColor(Color.white);
        g.fillRect(0, NUTRI_SCORE_LABEL_IMAGE_HEIGHT, imageWidth, imageHeight);

        //draw all unselected elements
        for (int i = 0; i < SCORE_ELEMENT_NUMBER; i++) {
            int x = scoreUnselectedImageStartPositionX + i * SCORE_UNSELECTED_IMAGE_WIDTH;
            g.drawImage(scoreUnselectedImages.get(i),x, scoreUnselectedImageStartPositionY, this);
        }

        //draw the selected element
        g.drawImage(scoreSelectedImages.get(actualImageIndex), SCORE_UNSELECTED_IMAGE_WIDTH * actualImageIndex, NUTRI_SCORE_LABEL_IMAGE_HEIGHT, this);
    }
}
