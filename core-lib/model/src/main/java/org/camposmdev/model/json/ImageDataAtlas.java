package org.camposmdev.model.json;

import java.util.List;
import java.util.Map;

public record ImageDataAtlas(
        Map<String, ImageData> images
) {

}
