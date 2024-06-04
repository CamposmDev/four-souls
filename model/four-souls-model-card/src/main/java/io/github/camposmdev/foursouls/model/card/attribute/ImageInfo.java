package io.github.camposmdev.foursouls.model.card.attribute;

import java.util.List;

/**
 * @param url URL of where additional information about the image
 * @param hiResUrl High quality version URL resource of the image
 * @param loResUrl Low quality version URL resource of the image
 * @param dir THe path to the image's parent directory
 */
public record ImageInfo (
        String url,
        String hiResUrl,
        String loResUrl,
        String dir
) {
    private static final List<String> OUTLIARS = List.of("DeliriousDukeOfFlies", "DeliriousMonstro");
    /* Gets the id of the image */
    public String id() {
        /* get the file name of the higher quality image */
        var name = hiResImgName();
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
        /* split {url} by forward slash */
        String[] tokens = url.split("/");
//        System.out.println(Arrays.toString(tokens));
        /* if for some reason there no tokens, then something is wrong with {url} */
        if (tokens.length == 0) return null;
        /* otherwise, return the last token */
        return tokens[tokens.length-1];
    }

    /**
     * @return Name of the higher quality image file including its file extensions
     */
    public String hiResImgName() {
        return hiResUrl.substring(hiResUrl.lastIndexOf('/') + 1);
    }

    /**
     * Name of the lower quality image file including its file extensions
     * @return
     */
    public String loResImgName() {
        return loResUrl.substring(loResUrl.lastIndexOf('/') + 1);
    }

    /**
     * @return Location of the high quality version of the image
     */
    public String hiResSrc() {
        return dir + hiResImgName();
    }

    /**
     * @return Location of the low quality version of the image
     */
    public String loResSrc() {
        return dir + loResImgName();
    }
}
