package io.github.camposmdev.foursouls.app.editor.ui.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.app.editor.net.API;
import io.github.camposmdev.foursouls.app.editor.ui.AttributeModifierBox;
import io.github.camposmdev.foursouls.app.editor.ui.NotificationBar;
import io.github.camposmdev.foursouls.app.editor.ui.RewardBox;
import io.github.camposmdev.foursouls.app.editor.ui.RollEventFormController;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.loot.LootOptionFormController;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.loot.PillEventFormController;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.loot.RuneEventFormController;
import io.github.camposmdev.foursouls.app.editor.ui.workspace.monster.MonsterOptionEventFormController;
import io.github.camposmdev.foursouls.model.atlas.MasterCardAtlas;
import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.attribute.*;
import io.github.camposmdev.foursouls.model.card.attribute.loot.LootOption;
import io.github.camposmdev.foursouls.model.card.attribute.loot.PillEvent;
import io.github.camposmdev.foursouls.model.card.attribute.loot.RuneEvent;
import io.github.camposmdev.foursouls.model.card.attribute.monster.MonsterOptionEvent;
import io.github.camposmdev.foursouls.model.fx.DialogBuilder;
import io.github.camposmdev.foursouls.model.fx.FXUtil;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class DialogFactory {
    private static DialogFactory factory;

    public static DialogFactory instance() {
        if (factory == null)
            factory = new DialogFactory();
        return factory;
    }

    public void showErrorBox(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        TextArea ta = new TextArea(exceptionText);
        ta.setPrefSize(500, 500);
        ta.setEditable(false);
        ta.setWrapText(true);

        ta.setMaxWidth(Double.MAX_VALUE);
        ta.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(ta, Priority.ALWAYS);
        GridPane.setHgrow(ta, Priority.ALWAYS);

        GridPane content = new GridPane();
        content.addColumn(0, ta);

        var alert = new DialogBuilder().setTitle("Error")
                .setHeaderText(ex.toString())
                .setContentText(ex.getLocalizedMessage())
                .setDefaultBtn()
                .build();
        alert.getDialogPane().setExpandableContent(content);
        alert.showAndWait();
    }

    public void showExitBox() {
        new DialogBuilder().setAlertType(Alert.AlertType.CONFIRMATION)
                .setTitle("Confirm Exit")
                .setHeaderText("Are you sure you want to exit?")
                .setContentText("""
                        All unsaved changes will be lost.
                        """)
                .setExitConfirmBtns().buildAndShow().ifPresent(x -> {
                    if (x.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                        FXGL.getGameController().exit();
                    }
                });
    }

    public void showRollListenerModifierBox(List<RollListener> lst) {
        var cbRollType = new ComboBox<RollType>();
        cbRollType.setPrefWidth(150);
        cbRollType.setValue(RollType.ANY);
        cbRollType.getItems().addAll(RollType.values());
        var tfRoll = new TextField();
        var rewardBox = new RewardBox();
        var tfLoseCents = new TextField();
        var tfDiscardLoot = new TextField();
        var tfBuffMonsterATK = new TextField();
        var tfHealMonsters = new TextField();
        var tfDamage = new TextField();
        var cbDamageTo = new ComboBox<EntityTarget>();
        cbDamageTo.setValue(EntityTarget.UNDEFINED);
        cbDamageTo.getItems().addAll(EntityTarget.values());
        var checkBoxEndTurn = new CheckBox();
        var tfHeal = new TextField();
        var tfGainCents = new TextField();
        var checkBoxRechargeItem = new CheckBox();
        var cbPeekDeck = new ComboBox<DeckType>();
        cbPeekDeck.setValue(DeckType.UNDEFINED);
        cbPeekDeck.getItems().addAll(DeckType.values());
        var tfPeekDeckAmount = new TextField();
        var tfModMonsterRoll = new TextField();
        var checkBoxIsSatanAlt = new CheckBox();
        var tfModRoll = new TextField();
        FXUtil.initNumberFields(tfRoll, tfLoseCents, tfDiscardLoot, tfBuffMonsterATK, tfHealMonsters, tfDamage, tfHeal, tfGainCents, tfPeekDeckAmount, tfModMonsterRoll, tfModRoll);
        var gridPane = new GridPane(4, 4);
        var btCommit = new Button("Commit");
        gridPane.addColumn(0, new Label("RollType"),
                new Label("Roll Value"), new Label("Reward"), new Label("Lose Cents"),
                new Label("Discard Loot"), new Label("Buff All Monsters Attack"), new Label("Heal All Monsters"),
                new Label("Damage"), new Label("Damage To"), new Label("Cancel Everything"),
                new Label("Heal"), new Label("Gain Cents"), new Label("Recharge Item"),
                new Label("Peek Deck"), new Label("Peek Deck Amount"), new Label("Mod Monster Roll"),
                new Label("Is Satan Alt"), new Label("Mod Roll"));
        gridPane.addColumn(1, cbRollType, tfRoll, rewardBox.getContent(), tfLoseCents,
                tfDiscardLoot, tfBuffMonsterATK, tfHealMonsters,
                tfDamage, cbDamageTo, checkBoxEndTurn,
                tfHeal, tfGainCents, checkBoxRechargeItem,
                cbPeekDeck, tfPeekDeckAmount, tfModMonsterRoll,
                checkBoxIsSatanAlt, tfModRoll);
        gridPane.add(new StackPane(btCommit), 0, 18, 2, 1);
        var lv = new ListView<RollListener>();
        lv.getItems().addAll(lst);
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lv.setPrefSize(400, 400);
        lv.setTooltip(new Tooltip("BACKSPACE or DEL to delete item"));
        lv.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE || e.getCode() == KeyCode.BACK_SPACE
                    && lv.getSelectionModel().getSelectedItem() != null)
                lv.getItems().remove(lv.getSelectionModel().getSelectedItem());
        });
        var root = new HBox(4, lv, gridPane);
        btCommit.setOnAction(e -> {
            try {
                var type = cbRollType.getValue();
                var roll = Byte.parseByte(tfRoll.getText());
                var reward = rewardBox.submit();
                byte byteLoseCents = Byte.parseByte(tfLoseCents.getText());
                byte byteDiscardLoot = Byte.parseByte(tfDiscardLoot.getText());
                byte byteBuffMonsterATK = Byte.parseByte(tfBuffMonsterATK.getText());
                byte byteHealMonsters = Byte.parseByte(tfHealMonsters.getText());
                byte byteDamage = Byte.parseByte(tfDamage.getText());
                byte byteHeal = Byte.parseByte(tfHeal.getText());
                byte byteGainCents = Byte.parseByte(tfGainCents.getText());
                byte bytePeekDeckAmount = Byte.parseByte(tfPeekDeckAmount.getText());
                byte byteModMonsterRoll = Byte.parseByte(tfModMonsterRoll.getText());
                byte byteModRoll = Byte.parseByte(tfModRoll.getText());
                /* get checkbox values */
                boolean endTurn = checkBoxEndTurn.isSelected();
                boolean rechargeItem = checkBoxRechargeItem.isSelected();
                boolean isSatanAlt = checkBoxIsSatanAlt.isSelected();
                /* get combo box values */
                EntityTarget damageTo = cbDamageTo.getValue();
                DeckType selectedPeekDeck = cbPeekDeck.getValue();
                var listener = new RollListener(type, roll, reward, byteLoseCents, byteDiscardLoot, byteBuffMonsterATK,
                        byteHealMonsters, byteDamage, damageTo, endTurn, byteHeal, byteGainCents, rechargeItem,
                        selectedPeekDeck, bytePeekDeckAmount, byteModMonsterRoll, isSatanAlt, byteModRoll);
                lv.getItems().add(listener);
            } catch (Exception ex) {
                DialogFactory.instance().showErrorBox(ex);
            }
        });
        new DialogBuilder().setTitle("Roll Listener Modifier")
                .setHeaderText("Someone rolled. Apply listener.")
                .setContent(root)
                .setDefaultBtn()
                .buildAndShow().ifPresent(e -> {
                    if (e.getButtonData().isDefaultButton()) {
                        lst.clear();
                        lst.addAll(lv.getItems().stream().toList());
                    }
                });
    }

    public Optional<Reward> showRewardModifierBox(Reward reward) {
        var box = new RewardBox();
        if (reward != null)
            box.load(reward);
        return new DialogBuilder().setTitle("Reward Modifier")
                .setDefaultBtn()
                .setContent(box.getContent())
                .buildAndShow().map(x -> {
                    Reward obj = null;
                    if (!x.getButtonData().isDefaultButton()) return null;
                    try {
                        obj = box.submit();
                    } catch (Exception ex) {
                        this.showErrorBox(ex);
                    }
                    return obj;
                });
    }

    public void showRollEventModifierBox(List<RollEvent> lst) {
        UI form = FXUtil.loadUI("workspace/RollEventForm.fxml");
        assert form != null;
        RollEventFormController controller = form.getController();
        controller.load(lst);
        new DialogBuilder().setTitle("Roll Event Modifier")
                .setHeaderText("Roll for an event.")
                .setContent(form.getRoot())
                .setDefaultBtn()
                .buildAndShow().ifPresent(e -> {
                    if (e.getButtonData().isDefaultButton()) {
                        lst.clear();
                        lst.addAll(controller.submit());
                    }
                });
    }

    @Deprecated
    public void showLootOptionEventModifierBox(List<LootOption> lst) {
        UI form = FXUtil.loadUI("workspace/loot/LootOptionForm.fxml");
        assert form != null;
        var controller = (LootOptionFormController) form.getController();
        controller.load(lst);
        new DialogBuilder().setTitle("Loot Option")
                .setHeaderText("You played a loot card, choose one.")
                .setContent(form.getRoot())
                .setDefaultBtn()
                .buildAndShow().ifPresent(e -> {
                    if (e.getButtonData().isDefaultButton()) {
                        try {
                            lst.clear();
                            lst.addAll(controller.submit());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
    }

    public Optional<AttributeModifier> showAttributeModifierBox(AttributeModifier modifier) {
        var box = new AttributeModifierBox();
        if (modifier != null) box.load(modifier);
        return new DialogBuilder().setTitle("Attribute Modifier")
                .setHeaderText("Modifies an attribute when a threshold is reached.")
                .setContent(box.getContent())
                .setDefaultBtn()
                .buildAndShow().map(x -> {
                    AttributeModifier obj = null;
                    if (!x.getButtonData().isDefaultButton()) return null;
                    try {
                        obj = box.submit();
                    } catch (Exception ex) {
                        this.showErrorBox(ex);
                    }
                    return obj;
                });
    }

    public void showPillEventModifierBox(List<PillEvent> lst) {
        UI form = FXUtil.loadUI("workspace/loot/PillEventForm.fxml");
        assert form != null;
        ((PillEventFormController) form.getController()).load(lst.toArray(new PillEvent[]{}));
        new DialogBuilder().setTitle("Pill Event")
                .setHeaderText("You played a pill card. Roll for an event.")
                .setContent(form.getRoot())
                .setDefaultBtn()
                .buildAndShow().ifPresent(e -> {
                    if (e.getButtonData().isDefaultButton()) {
                        lst.clear();
                        try {
                            var arr = ((PillEventFormController) form.getController()).submit();
                            lst.addAll(Arrays.asList(arr));
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
    }

    @Deprecated
    public void showRuneEventModifierBox(List<RuneEvent> lst) {
        UI form = FXUtil.loadUI("workspace/loot/RuneEventForm.fxml");
        assert form != null;
        ((RuneEventFormController) form.getController()).load(lst.toArray(new RuneEvent[]{}));
        new DialogBuilder().setTitle("Rune Event")
                .setHeaderText("You played a rune card.")
                .setContent(form.getRoot())
                .setDefaultBtn()
                .buildAndShow().ifPresent(e -> {
                    if (e.getButtonData().isDefaultButton()) {
                        lst.clear();
                        try {
                            var arr = ((RuneEventFormController) form.getController()).submit();
                            lst.addAll(Arrays.asList(arr));
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
    }

    public void showMonsterOptionEventModifierBox(List<MonsterOptionEvent> lst) {
        UI form = FXUtil.loadUI("workspace/monster/MonsterOptionEventForm.fxml");
        assert form != null;
        ((MonsterOptionEventFormController) form.getController()).load(lst.toArray(new MonsterOptionEvent[]{}));
        new DialogBuilder().setTitle("Monster Option Event")
                .setHeaderText("Choose one.")
                .setContent(form.getRoot())
                .setDefaultBtn()
                .buildAndShow().ifPresent(e -> {
                    if (e.getButtonData().isDefaultButton()) {
                        lst.clear();
                        try {
                            var arr = ((MonsterOptionEventFormController) form.getController()).submit();
                            lst.addAll(Arrays.asList(arr));
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
    }

    public void saveMasterCardAtlas(MasterCardAtlas masterCardAtlas) {
        var filterJSON = new FileChooser.ExtensionFilter("JSON", "*.json");
        var fc = new FileChooser();
        fc.setTitle("Save Master Card Atlas to local machine.");
        fc.getExtensionFilters().add(filterJSON);
        fc.setSelectedExtensionFilter(filterJSON);
        fc.setInitialFileName("cards");
        var f = fc.showSaveDialog(FXGL.getPrimaryStage());
        if (f == null) return;
        /* otherwise save the cards */
        try (var fos = new FileOutputStream(f)) {
            var payload = masterCardAtlas.toString();
            fos.write(payload.getBytes());
            fos.flush();
            NotificationBar.instance().push("Saved " + f);
        } catch (IOException ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }

    public void showPreferences() {
        var root = new GridPane(8, 8);
        var tfHost = new TextField(API.instance().host());
        var tfPort = new TextField(API.instance().port().toString());
        var btPing = new Button("Ping (...)");
        btPing.setMaxWidth(Integer.MAX_VALUE);
        Runnable runnable = () -> {
            try {
                String host = tfHost.getText();
                int port = Integer.parseInt(tfPort.getText());
                API.instance().setHostAndPort(host, port);
                btPing.setText(String.format("Ping (%d ms)", API.instance().ping()));
            } catch (Exception ignored) {
            }
        };
        btPing.setOnAction(e -> FXGL.runOnce(runnable, Duration.ONE));
        root.addRow(0, new Label("Host"), tfHost);
        root.addRow(1, new Label("Port"), tfPort);
        root.add(btPing, 0, 2, 2, 1);
        FXGL.runOnce(runnable, Duration.ONE);
        new DialogBuilder()
                .setTitle("Preferences")
                .setContent(root)
                .setDefaultBtn().buildAndShow();
    }

    public void showAboutBox() {
        UI ui = FXUtil.loadUI("About.fxml");
        assert ui != null : "Failed to load About.fxml";
        new DialogBuilder().setTitle("About")
                .setHeaderText(FXGL.getSettings().getTitle())
                .setContent(ui.getRoot())
                .setDefaultBtn()
                .buildAndShow();
    }


    public void showJSONViewer(BaseCard card) {
        final double WIDTH = 800, HEIGHT = 700;
        final int DEFAULT_FONT_SIZE = 14;
        final int MIN_FONT_SIZE = 12;
        AtomicInteger fontSize = new AtomicInteger(DEFAULT_FONT_SIZE);
        Function<String, TextArea> editor = (content) -> {
            final double ZOOM_FACTOR = 1.1;
            Function<TextArea, Void> increase = x -> {
                double temp = fontSize.get() * ZOOM_FACTOR;
                fontSize.set((int) temp);
                x.setStyle(String.format("-fx-font-family: monospace; -fx-font-size: %d;", fontSize.get()));
                return null;
            };
            Function<TextArea, Void> decrease = x -> {
                double temp = fontSize.get() / ZOOM_FACTOR;
                if (temp < MIN_FONT_SIZE) return null;
                fontSize.set((int) temp);
                x.setStyle(String.format("-fx-font-family: monospace; -fx-font-size: %d;", fontSize.get()));
                return null;
            };
            var ta = new TextArea(content);
            ta.setEditable(false);
            ta.setPrefSize(WIDTH, HEIGHT);
            ta.setStyle(String.format("-fx-font-family: monospace; -fx-font-size: %d;", fontSize.get()));
            ta.setOnKeyPressed(event -> {
                if (!event.isControlDown())
                    return;
                if (event.getCode() == KeyCode.EQUALS)
                    increase.apply(ta);
                else if (event.getCode() == KeyCode.MINUS)
                    decrease.apply(ta);
            });
            ta.setOnScroll(event -> {
                if (!event.isControlDown())
                    return;
                if (event.getDeltaY() > 0) {
                    increase.apply(ta);
                } else {
                    decrease.apply(ta);
                }
            });
            return ta;
        };
        try {
            var mapper = new ObjectMapper();
            var content = mapper.readTree(card.toString()).toPrettyString();
            var ta = editor.apply(content);
            var root = new StackPane(ta);
            new DialogBuilder().setTitle("JSON Viewer")
                    .setHeaderText(card.getId())
                    .setContent(root)
                    .setDefaultBtn()
                    .buildAndShow();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
