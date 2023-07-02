package com.camposmdev.res_soup;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Handles one monster card and downloads its image and contents
 */
public class MonsterCardThreadHandler extends Thread {
    private Element cardTag;

    public MonsterCardThreadHandler(Element cardTag) {
        this.cardTag = cardTag;
    }


    @Override
    public void run() {
        super.run();
        Elements childTags = cardTag.getElementsByTag("a");
        /* try and get the <div> tag that should contain the link to the image and link to more details of the given card */
        assert !childTags.isEmpty();
        /* get the <a> tag  */
        Element aTag = childTags.get(0);
        /* get the attributes of the given <a> tag */
        var attribs = aTag.attributes();
        /* get the {href} attribute of the <a> tag */
        var link = attribs.get("href");
        /* get list of images (should only be one image) */
        var imgTags = aTag.getElementsByTag("img");
        assert !imgTags.isEmpty();
        var imgTag = imgTags.get(0);
        var imgLink = imgTag.attributes().get("data-src");
        try {
            URL url = new URL(imgLink);
            BufferedImage buffImg = ImageIO.read(url);
            final String fileDest = imgLink.substring(imgLink.lastIndexOf('/') + 1);
            File file = new File(fileDest);
            ImageIO.write(buffImg, "png", file);
            System.out.println("Downloaded image " + fileDest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
