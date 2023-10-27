package org.camposmdev.res_soup;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class ImageFetcherRunnable implements Runnable {
//    static final Object lock = new Object();
    static final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36";

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
//        System.out.print(imgURL + " -> '" + f.getParentFile().getName() + '/' + f.getName() + "' - ");
        if (f.exists()) {
            System.out.println("Already exists: " + imgURL + " -> '" + (f.getParentFile().getName() + '/' + f.getName()) + '\'');
//            System.out.println("ALREADY EXISTS");
        } else {
            var retries = 0;
            final var MAX_RETRIES = 15;
            while (retries < MAX_RETRIES) {
                try {
//                    synchronized (lock) {
                        var url = new URL(imgURL);
                        var img = ImageIO.read(url.openStream());
                        ImageIO.write(img, "png", f);
//                        System.out.println("OK");
                        System.out.println("OK: " + imgURL + " -> '" + (f.getParentFile().getName() + '/' + f.getName()) + '\'');
                        break;
//                    }
                } catch (IOException ex) {
                    System.err.println("Retrying[" + ++retries + '/' + MAX_RETRIES + "]: " + f.getName());
                }
            }
            if (retries > MAX_RETRIES) System.err.println("FAILED TO GET: " + imgURL);
        }
    }
}
