package org.camposmdev.model.card.meta;

import org.camposmdev.model.card.BaseCard;

import java.io.Serializable;

public class MetaCard implements Serializable {
    private String originURL;
    private String imgURL;
    private BaseCard card;

    public MetaCard(String originURL, String imgURL, BaseCard card) {
        this.originURL = originURL;
        this.imgURL = imgURL;
        this.card = card;
    }

    public String getOriginURL() {
        return originURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public BaseCard getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "MetaCard{" +
                "originURL='" + originURL + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", card=" + card +
                '}';
    }
}
