package io.github.camposmdev.foursouls.app.miser;

import io.github.camposmdev.foursouls.core.util.assets.CardAsset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Downloads
 */
public class ImagePillagerRunnable implements Runnable {
    private final String des;
    private final CardAsset info;

    /**
     * Creates an ImagePillagerRunnable object
     * @param des Destination directory to save the image file in
     * @param info Information about the image
     */
    public ImagePillagerRunnable(String des, CardAsset info) {
        this.des = des;
        this.info = info;
    }

    @Override
    public void run() {
        start(info.loResUrl(), info.loResName());
        start(info.hiResUrl(), info.hiResName());
    }

    private void start(String imgURL, String imgName) {
        final File f = new File(des + imgName);
        if (f.exists()) /* file already exists, abort pillage */
            System.out.printf("File already exists: %s\n", f);
        else /* pillage the image */
            pillage(f, imgURL);
    }

    private void pillage(File file, String imgUrl) {
        var retries = 0;
        final var MAX_RETRIES = 15;
        while (retries < MAX_RETRIES) {
            try {
                URL url = URI.create(imgUrl).toURL();
                BufferedImage img = ImageIO.read(url.openStream());
                ImageIO.write(img, "png", file);
                System.out.printf("Pillaged: %s'\n", url);
                break;
            } catch (IOException ex) {
                System.err.printf("Retrying[%d/%d]: %s\n", ++retries, MAX_RETRIES, file.getName());
            }
        }
        if (retries > MAX_RETRIES) System.err.printf("Failed to pillage: %s\n", imgUrl);
    }
}
