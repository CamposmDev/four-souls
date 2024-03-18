package org.camposmdev.util;


import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DialogBuilder {
    private String title, headerText, contentText;
    private Alert.AlertType alertType;
    private List<ButtonType> btList;
    private Node content;

    public DialogBuilder() {
        this.alertType = Alert.AlertType.NONE;
    }

    public DialogBuilder setAlertType(Alert.AlertType alertType) {
        this.alertType = alertType;
        return this;
    }

    public DialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public DialogBuilder setHeaderText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    public DialogBuilder setHeaderTextInfo() {
        this.headerText = "Please fill out the following information";
        return this;
    }

    public DialogBuilder setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    /**
     * Adds a button type to the button bar of the alert
     *
     * @param s    Text displayed on the button
     * @param data Type of button
     * @return AlertBuilder
     */
    public DialogBuilder addBtnType(String s, ButtonBar.ButtonData data) {
        ButtonType type = new ButtonType(s, data);
        if (btList == null) btList = new LinkedList<>();
        btList.add(type);
        return this;
    }

    public DialogBuilder setExitConfirmBtns() {
        ButtonType bt1 = new ButtonType("Exit", ButtonBar.ButtonData.OK_DONE);
        ButtonType bt2 = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        btList = new LinkedList<>();
        btList.add(bt1);
        btList.add(bt2);
        return this;
    }

    /**
     * @return Instance of the caller's object
     * @brief Replaces the default
     */
    public DialogBuilder setConfirmBtns() {
        ButtonType bt1 = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType bt2 = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        btList = new LinkedList<>();
        btList.add(bt1);
        btList.add(bt2);
        return this;
    }

    public DialogBuilder setDefaultBtn() {
        ButtonType bt = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        btList = new LinkedList<>();
        btList.add(bt);
        return this;
    }

    public DialogBuilder addApplyBtn() {
        ButtonType bt = ButtonType.APPLY;
        if (btList == null) btList = new LinkedList<>();
        btList.add(bt);
        return this;
    }

    public DialogBuilder addBtn(String s, ButtonBar.ButtonData data) {
        ButtonType bt = new ButtonType(s, data);
        if (btList == null) btList = new LinkedList<>();
        btList.add(bt);
        return this;
    }

    public DialogBuilder setContent(Node content) {
        this.content = content;
        return this;
    }

    public Alert build() {
        Alert alert = new Alert(alertType);
        alert.initOwner(FXGL.getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        if (btList != null)
            alert.getButtonTypes().setAll(btList);
        if (contentText != null) {
            alert.setContentText(contentText);
        } else {
            alert.getDialogPane().setContent(content);
        }
        return alert;
    }

    public Optional<ButtonType> buildAndShow() {
        return build().showAndWait();
    }
}
