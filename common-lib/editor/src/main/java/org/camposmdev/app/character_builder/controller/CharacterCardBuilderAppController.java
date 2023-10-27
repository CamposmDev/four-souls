package org.camposmdev.app.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.camposmdev.Launcher;
import org.camposmdev.app.CharacterCardBuilderApp;
import org.camposmdev.app.model.CharacterCardBuilderAppModel;
import org.camposmdev.model.card.CharacterCard;

import java.io.*;
import java.time.LocalTime;

public class CharacterCardBuilderAppController {
    @FXML
    Stage stage;
    @FXML
    ImageView iv;
    @FXML
    WebView wv;
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
        CharacterCard card = new CharacterCard(strName, hp, atk);
        card.setEternalCard(null); /* TODO implement eternal card chooser */
        lblNote.setText('[' + LocalTime.now().toString() + "]: CREATED " + card);
        var isFinished = CharacterCardBuilderAppModel.getInstance().submit(card);
        if (isFinished) {
            lblNote.setText("ALL DONE");
        } else loadMetaCardInfo();
        tfName.clear();
        tfHP.clear();
        tfATK.clear();
    }

    public void loadMetaCardInfo() {
        var m = CharacterCardBuilderAppModel.getInstance().peek();
        wv.getEngine().load(m.getOriginURL());
        iv.setImage(new Image(m.getImgURL()));
        if (m.getCard() != null && m.getCard() instanceof CharacterCard cc) {
            tfName.setText(cc.getName());
            tfHP.setText(String.valueOf(cc.getLife().getMax()));
            tfATK.setText(String.valueOf(cc.getAttack().getMax()));
        }
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
