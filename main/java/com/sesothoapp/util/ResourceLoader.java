package com.sesothoapp.util;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.io.InputStream;
import java.net.URL;

/*
 Utility class for loading resources like images, audio, and videos.
 */
public class ResourceLoader {

     //Folder paths for different resource types
    private static final String IMAGES_PATH = "/images/";
    private static final String VIDEOS_PATH = "/videos/";
    private static final String AUDIO_PATH = "/audio/";

    //Private constructor to prevent instantiation
    private ResourceLoader() {
    }

     // Load an image resource from the classpath.
    public static Image loadImage(String imageName) {
        try {
            String imagePath = IMAGES_PATH + imageName;
            InputStream is = ResourceLoader.class.getResourceAsStream(imagePath);

            if (is == null) {
                System.err.println("Could not find image: " + imagePath);
                // Return a placeholder or fallback image
                return new Image(ResourceLoader.class.getResourceAsStream(IMAGES_PATH + "placeholder.png"));
            }

            return new Image(is);

        } catch (Exception e) {
            System.err.println("Error loading image: " + imageName);
            e.printStackTrace();
            return null;
        }
    }

     // Load a video resource from the classpath.
    public static Media loadVideo(String videoName) {
        try {
            String videoPath = VIDEOS_PATH + videoName;
            URL url = ResourceLoader.class.getResource(videoPath);

            if (url == null) {
                System.err.println("Could not find video: " + videoPath);
                return null;
            }
            return new Media(url.toExternalForm());

        } catch (Exception e) {
            System.err.println("Error loading video: " + videoName);
            e.printStackTrace();
            return null;
        }
    }

}