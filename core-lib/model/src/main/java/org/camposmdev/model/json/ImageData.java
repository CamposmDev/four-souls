package org.camposmdev.model.json;

public record ImageData(
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

    public String source1() {
        return directory + highResImgName();
    }

    public String source2() {
        return directory + lowResImgName();
    }

}
