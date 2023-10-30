package org.camposmdev.app.character_builder.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.camposmdev.app.character_builder.CharacterCardBuilderApp;
import org.camposmdev.app.character_builder.model.CharacterCardBuilderAppModel;
import org.camposmdev.model.card.character.CharacterCard;
import org.jsoup.Jsoup;

import java.io.*;

public class CharacterCardBuilderAppController {
    @FXML
    Stage stage;
    @FXML
    ImageView iv;
//    @FXML
//    WebView wv;
    @FXML
    TextField tfName, tfHP, tfATK;
    @FXML
    Label lblNote;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            stage.setTitle(CharacterCardBuilderApp.TITLE);
            loadMetaCardInfo();
        });
    }

    public void submit() {
        var strName = tfName.getText();
        var strHP = tfHP.getText();
        var strATK = tfATK.getText();
        var hp = -1;
        var atk = -1;
        try {
            hp = Integer.parseInt(strHP);
            atk = Integer.parseInt(strATK);
        } catch (Exception ex) {
            lblNote.setText("FAILED TO CREATE CHARACTER (" + ex + ')');
            return;
        }
        var m = CharacterCardBuilderAppModel.getInstance().peek();
        CharacterCard card = new CharacterCard(strName, hp, atk);
        card.setEternalCard(null); /* TODO implement eternal card chooser */
        lblNote.setText(card.getName() + ": HP=" + card.getHP().getMax() + ", ATK=" + card.getATK().getMax());
        var isFinished = CharacterCardBuilderAppModel.getInstance().submit(card);
        tfName.clear();
        tfHP.clear();
        tfATK.clear();
        if (isFinished) {
            lblNote.setText("ALL DONE");
        } else loadMetaCardInfo();
    }

    public void loadMetaCardInfo() {
        var m = CharacterCardBuilderAppModel.getInstance().peek();
        if (m == null) {
            lblNote.setText("NO MORE CHARACTERS TO EDIT");
        } else {
            iv.setImage(new Image(m.getImgURL()));
            if (m.getCard() != null && m.getCard() instanceof CharacterCard cc) {
                tfName.setText(cc.getName());
                tfHP.setText(String.valueOf(cc.getHP().getMax()));
                tfATK.setText(String.valueOf(cc.getATK().getMax()));
            } else {
                try {
                    var doc = Jsoup.connect(m.getOriginURL()).get();
                    var cardpage = doc.getElementsByClass("cardpage");
                    var name = cardpage.get(0).getElementsByTag("h1").get(0).text();
                    tfName.setText(name);
                    var statTable = doc.getElementById("StatTable");
                    var valueTags = statTable.getElementsByClass("value");
                    var strHP = valueTags.get(0).text().replaceAll(":", "").trim();
                    var strATK = valueTags.get(1).text().replaceAll(":", "").trim();
                    tfHP.setText(strHP);
                    tfATK.setText(strATK);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void previous() {
        if (CharacterCardBuilderAppModel.getInstance().previous())
            loadMetaCardInfo();
        else
            lblNote.setText("No characters come before this");

    }

    public void skip() {
        CharacterCardBuilderAppModel.getInstance().submit(null);
        loadMetaCardInfo();
    }

    public void exit() throws IOException {
        /* Save Data */
        CharacterCardBuilderAppModel.getInstance().save();
    }

    public void chooseEternal() {
        /* TODO */
    }
}
