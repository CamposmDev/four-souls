package org.camposmdev.model.atlas;

import java.util.List;

/**
 * @param origin URL of where additional information about the image
 * @param highResImgURL High quality version URL resource of the image
 * @param lowResImgURL Low quality version URL resource of the image
 * @param dir THe path to the image's parent directory
 */
public record ImageInfo (
        String origin,
        String highResImgURL,
        String lowResImgURL,
        String dir
) {
    private static final List<String> OUTLIARS = List.of("DeliriousDukeOfFlies", "DeliriousMonstro");
    /* Gets the id of the image */
    public String id() {
        /* get the file name of the higher quality image */
        var name = highResImgName();
        /*  */
        final var extension1 = ".jpg";
        final var extension2 = ".png";
        if (name.contains(extension1)) {
            name = name.substring(0, name.indexOf(extension1));
        } else if (name.contains(extension2)) {
            name = name.substring(0, name.indexOf(extension2));
        }
        if (OUTLIARS.contains(name)) return slugName();
        return name;
    }

    /**
     * @return Name of the slug which is the specific part of a resource after the forward slash
     */
    public String slugName() {
        /* split {origin} by forward slash */
        String[] tokens = origin.split("/");
//        System.out.println(Arrays.toString(tokens));
        /* if for some reason there no tokens, then something is wrong with {origin} */
        if (tokens.length == 0) return null;
        /* otherwise, return the last token */
        return tokens[tokens.length-1];
    }

    /**
     * @return Name of the higher quality image file including its file extensions
     */
    public String highResImgName() {
        return highResImgURL.substring(highResImgURL.lastIndexOf('/') + 1);
    }

    /**
     * Name of the lower quality image file including its file extensions
     * @return
     */
    public String lowResImgName() {
        return lowResImgURL.substring(lowResImgURL.lastIndexOf('/') + 1);
    }

    /**
     * @return Location of the high quality version of the image
     */
    public String source1() {
        return dir + highResImgName();
    }

    /**
     * @return Location of the low quality version of the image
     */
    public String source2() {
        return dir + lowResImgName();
    }
}
