package io.github.camposmdev.foursouls.core.util.assets;

import io.github.camposmdev.foursouls.core.card.attribute.CardType;

/**
 * @param url URL of where additional information can be found about the asset
 * @param hiResUrl High quality version of URL resource of the asset
 * @param loResUrl Low quality version of URL resource of the asset
 * @param dir THe path to the image's parent directory
 */
public record CardAsset(
        CardType cardType,
        String url,
        String hiResUrl,
        String loResUrl,
        String dir
) {
    /**
     * Get the id of the asset
     */
    public String id() {
        final var dot = ".";
        var hiName = hiResName();
        /* remove the file extension */
        hiName = hiName.substring(0, hiName.indexOf(dot));
        if (cardType == CardType.VERSO) return hiName;
        final var slugName = slugName();
        if (slugName == null) return hiName;
        if (Character.isUpperCase(hiName.charAt(0)))
            return slugName;
        return hiName;
    }

    /**
     * Gets the name of the leaf resource ('slug name') that comes after the
     * last forward slash. Uses {@code url} field to parse out the name.
     * @return Name of the leaf resource. Returns null if there is no resource.
     */
    public String slugName() {
        /* split {url} by forward slash */
        final var regex = "/";
        String[] tokens = url.split(regex);
        /* if there are no tokens, no resource present, return null */
        if (tokens.length == 0) return null;
        /* otherwise, return the last token */
        return tokens[tokens.length-1];
    }

    /**
     * @return Name of the high quality asset file with file extensions.
     */
    public String hiResName() {
        return hiResUrl.substring(hiResUrl.lastIndexOf('/') + 1);
    }

    /**
     * @return Name of the low quality asset file with file extensions.
     */
    public String loResName() {
        return loResUrl.substring(loResUrl.lastIndexOf('/') + 1);
    }

    /**
     * @return Path of the high quality asset.
     */
    public String hiResSrc() {
        return dir + hiResName();
    }

    /**
     * @return Path of the low quality asset.
     */
    public String loResSrc() {
        return dir + loResName();
    }
}
