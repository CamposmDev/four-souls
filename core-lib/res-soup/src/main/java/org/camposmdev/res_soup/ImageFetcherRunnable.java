package org.camposmdev.res_soup;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageFetcherRunnable implements Runnable {
    private final String imgdir;
    private final String imgURL;

    public ImageFetcherRunnable(String imgdir, String imgURL) {
        this.imgdir = imgdir;
        this.imgURL = imgURL;
    }

    @Override
    public void run() {
        var name = imgURL.substring(imgURL.lastIndexOf('/') + 1);
        var des = imgdir + name;
        var f = new File(des);
        if (f.exists()) {
            /* File already exists, so don't download it */
            System.out.println("Already exists: " + imgURL + " -> '" + (f.getParentFile().getName() + '/' + f.getName()) + '\'');
        } else {
            /* Download the image and save it */
            var retries = 0;
            final var MAX_RETRIES = 15;
            while (retries < MAX_RETRIES) {
                try {
                    var url = new URL(imgURL);
                    var img = ImageIO.read(url.openStream());
                    ImageIO.write(img, "png", f);
                    System.out.println("OK: " + imgURL + " -> '" + (f.getParentFile().getName() + '/' + f.getName()) + '\'');
                    break;
                } catch (IOException ex) {
                    System.err.println("Retrying[" + ++retries + '/' + MAX_RETRIES + "]: " + f.getName());
                }
            }
            if (retries > MAX_RETRIES) System.err.println("FAILED TO GET: " + imgURL);
        }
    }
}
