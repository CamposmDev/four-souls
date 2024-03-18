package org.camposmdev.model.atlas;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.image.Image;

public record ImageInfo (
        String origin,
        String highResImgURL,
        String lowResImgURL,
        String directory
) {
    public String name() {
        var name = highResImgName();
        if (name.contains(".jpg")) return name.substring(0, name.indexOf(".jpg"));
        return name.substring(0, name.indexOf(".png"));
    }

    public String highResImgName() {

        return highResImgURL.substring(highResImgURL.lastIndexOf('/') + 1);
    }

    public String lowResImgName() {

        return lowResImgURL.substring(lowResImgURL.lastIndexOf('/') + 1);
    }

    /**
     * Returns the resource location of source1 image
     * @return String Location of source1 image
     */
    public String source1() {
        return directory + highResImgName();
    }

    /**
     * Returns the resource location of source2 image
     * @return String Location of source2 image
     */
    public String source2() {
        return directory + lowResImgName();
    }

    public Image loadSource1() {
        return FXGL.image(source1());
    }

    public Image loadSource2() {
        return FXGL.image(source2());
    }
}
