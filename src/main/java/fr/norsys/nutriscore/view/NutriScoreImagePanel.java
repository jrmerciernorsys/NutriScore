package fr.norsys.nutriscore.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NutriScoreImagePanel extends JPanel {

    public static final String RESOURCES_FOLDER_IMAGES = "/images/";
    public static final List<String> IMAGE_NAMES = List.of("nutriscore-a.png", "nutriscore-b.png", "nutriscore-c.png", "nutriscore-d.png", "nutriscore-e.png");

    private List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();

    public NutriScoreImagePanel() {

        //Load the 5 images from resources/images
        try {
            loadImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadImages() throws IOException {

        // TODO put in enum level ?
        //TODO separates the nutriscore label from the rest of the images
        for (String imageName : IMAGE_NAMES) {
            bufferedImages.add(ImageIO.read(Objects.requireNonNull(getClass().getResource(RESOURCES_FOLDER_IMAGES + imageName))));
        }
    }

    public void displayImage(int index) {
        Image image = bufferedImages.get(index);
        image.getGraphics().drawImage(image, 0, 0, this);
    }

}
