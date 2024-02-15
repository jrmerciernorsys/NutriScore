package fr.norsys.nutriscore.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImagePanel extends JPanel {

    public static final String RESOURCES_FOLDER_IMAGES = "/images/";
    public static final List<String> IMAGE_NAMES = List.of("nutriscore-a.png", "nutriscore-b.png", "nutriscore-c.png", "nutriscore-d.png", "nutriscore-e.png");
    public static final int DEFAULT_SCORE_LEVEL = 0;

    private final List<BufferedImage> bufferedImages = new ArrayList<>();

    private final JLabel pictureLabel = new JLabel();

    public ImagePanel() {

        //Load the 5 images from resources/images
        try {
            loadImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        add(pictureLabel);
        updateImage(DEFAULT_SCORE_LEVEL);
    }

    private void loadImages() throws IOException {

        //TODO separates the nutriscore label from the rest of the images
        String imagePath = null;

        for (String imageName : IMAGE_NAMES) {
            try {
                imagePath = RESOURCES_FOLDER_IMAGES + imageName;
                bufferedImages.add(ImageIO.read(Objects.requireNonNull(getClass().getResource(RESOURCES_FOLDER_IMAGES + imageName), "Cannot find image in resources: " + imagePath)));
            } catch (IOException  e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateImage(int index) {
        Image image = bufferedImages.get(index);
        pictureLabel.setIcon(new ImageIcon(image));
        repaint();
    }
}
