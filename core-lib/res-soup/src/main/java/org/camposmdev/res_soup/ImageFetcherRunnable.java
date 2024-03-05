package org.camposmdev.res_soup;

import org.camposmdev.model.json.ImageData;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class ImageFetcherRunnable implements Runnable {
    private String dir_img;
    private final ImageData ir;

    public ImageFetcherRunnable(String dir_img, ImageData ir) {
        this.dir_img = dir_img;
        this.ir = ir;
    }

    @Override
    public void run() {
        start(ir.highResImgURL(), ir.highResImgName());
        start(ir.lowResImgURL(), ir.lowResImgName());
    }

    private void start(String imgURL, String imgName) {
        File f = new File(dir_img + imgName);
        if (f.exists()) {
            /* File already exists, so don't download it */
            System.out.printf("Already exists: %s -> '%s'\n", imgURL, f);
        } else {
            /* download the image and save it */
            download(f, imgURL);
        }
    }

    private void download(File file, String imgURL) {
        var retries = 0;
        final var MAX_RETRIES = 15;
        while (retries < MAX_RETRIES) {
            try {
                var url = URI.create(imgURL).toURL();
                var img = ImageIO.read(url.openStream());
                ImageIO.write(img, "png", file);
                System.out.printf("Pillaged: %s -> '%s'\n", url, file);
                break;
            } catch (IOException ex) {
                System.err.printf("Retrying[%d/%d]: %s\n", ++retries, MAX_RETRIES, file.getName());
            }
        }
        if (retries > MAX_RETRIES) System.err.println("FAILED TO GET: "+ imgURL);
    }
}
