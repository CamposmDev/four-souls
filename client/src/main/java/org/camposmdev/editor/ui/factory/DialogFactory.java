package org.camposmdev.editor.ui.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UI;
import io.vertx.core.json.JsonObject;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import org.camposmdev.editor.ui.AttributeModifierBox;
import org.camposmdev.editor.ui.LootOptionEventBox;
import org.camposmdev.editor.ui.RewardBox;
import org.camposmdev.editor.ui.RollEventBox;
import org.camposmdev.editor.ui.workspace.loot.PillEventFormController;
import org.camposmdev.editor.ui.workspace.loot.RuneEventFormController;
import org.camposmdev.editor.ui.workspace.monster.MonsterOptionEventFormController;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.card.attribute.Reward;
import org.camposmdev.model.card.attribute.loot.LootOptionEvent;
import org.camposmdev.model.card.attribute.loot.PillEvent;
import org.camposmdev.model.card.attribute.loot.RuneEvent;
import org.camposmdev.model.card.attribute.monster.MonsterOptionEvent;
import org.camposmdev.util.DialogBuilder;
import org.camposmdev.util.FXUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    public void showPreviewBox(JsonObject payload) {
        Function<JsonObject, TextArea> preview = (obj) -> {
            final double ZOOM_FACTOR = 1.1;
            Function<TextArea, Void> increase = ta -> {
                double currentFontSize = ta.getFont().getSize();
                ta.setStyle("-fx-font-size: " + (currentFontSize * ZOOM_FACTOR));
                return null;
            };
            Function<TextArea, Void> decrease = ta -> {
                double currentFontSize = ta.getFont().getSize();
                ta.setStyle("-fx-font-size: " + (currentFontSize / ZOOM_FACTOR));
                return null;
            };
            var node = new TextArea(obj.encodePrettily());
            node.setPrefSize(800, 700);
            node.setEditable(false);
            node.setStyle("-fx-font-size: 14");
            node.setOnKeyPressed((KeyEvent event) -> {
                if (event.isControlDown()) {
                    if (event.getCode() == KeyCode.EQUALS) {
                        increase.apply(node);
                    } else if (event.getCode() == KeyCode.MINUS) {
                        decrease.apply(node);
                    }
                }
            });
            return node;
        };
        var root = new TabPane();
        root.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        payload.forEach(entry -> {
            var ta = preview.apply((JsonObject) entry.getValue());
            root.getTabs().add(new Tab(entry.getKey(), ta));
        });
        new DialogBuilder()
                .setAlertType(Alert.AlertType.INFORMATION)
                .setTitle("Preview")
                .setHeaderText("Master Card Atlas")
                .setDefaultBtn()
                .setContent(root)
                .buildAndShow();
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
        tfRoll.setPromptText("[1,6]");
        var rewardBox = new RewardBox();
        var tfLoseCents = new TextField("0");
        var tfDiscardLoot = new TextField("0");
        var tfBuffMonsterATK = new TextField("0");
        var tfHealMonsters = new TextField("0");
        var tfDamage = new TextField("0");
        var cbDamageTo = new ComboBox<EntityTarget>();
        cbDamageTo.getItems().addAll(EntityTarget.values());
        var checkBoxCancelEverything = new CheckBox();
        var tfHeal = new TextField("0");
        var tfGainCents = new TextField("0");
        var checkBoxRechargeItem = new CheckBox();
        var cbPeekDeck = new ComboBox<DeckType>();
        cbPeekDeck.getItems().addAll(DeckType.values());
        var tfPeekDeckAmount = new TextField("0");
        var tfModMonsterRoll = new TextField("0");
        var checkBoxIsSatanAlt = new CheckBox();
        var tfModRoll = new TextField("0");
        var gridPane = new GridPane(8,12);
        var btSubmit = new Button("Submit");
        gridPane.addColumn(0, new Label("RollType"),
                new Label("Roll Value"), new Label("Reward"), new Label("Lose Cents"),
                new Label("Discard Loot"), new Label("Buff All Monsters Attack"), new Label("Heal All Monsters"),
                new Label("Damage"), new Label("Damage To"), new Label("Cancel Everything"),
                new Label("Heal"), new Label("Gain Cents"), new Label("Recharge Item"),
                new Label("Peek Deck"), new Label("Peek Deck Amount"), new Label("Mod Monster Roll"),
                new Label("Is Satan Alt"), new Label("Mod Roll"));
        gridPane.addColumn(1, cbRollType, tfRoll, rewardBox.getContent(), tfLoseCents,
                tfDiscardLoot, tfBuffMonsterATK, tfHealMonsters,
                tfDamage, cbDamageTo, checkBoxCancelEverything,
                tfHeal, tfGainCents, checkBoxRechargeItem,
                cbPeekDeck, tfPeekDeckAmount, tfModMonsterRoll,
                checkBoxIsSatanAlt, tfModRoll);
        gridPane.add(new StackPane(btSubmit), 0, 3, 2, 1);
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
        var root = new HBox(12, lv, gridPane);
        btSubmit.setOnAction(e -> {
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
                boolean cancelEverything = checkBoxCancelEverything.isSelected();
                boolean rechargeItem = checkBoxRechargeItem.isSelected();
                boolean isSatanAlt = checkBoxIsSatanAlt.isSelected();
                /* get combo box values */
                EntityTarget damageTo = cbDamageTo.getValue();
                DeckType selectedPeekDeck = cbPeekDeck.getValue();
                var listener = new RollListener(type, roll, reward, byteLoseCents, byteDiscardLoot, byteBuffMonsterATK,
                        byteHealMonsters, byteDamage, damageTo, cancelEverything, byteHeal, byteGainCents, rechargeItem,
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

    public void showDeathListenerModifierBox(List<DeathListener> lst) {
        var cbDeathType = new ComboBox<DeathType>();
        cbDeathType.setPrefWidth(150);
        cbDeathType.setValue(DeathType.ANY);
        cbDeathType.getItems().addAll(DeathType.values());
        var rewardBox = new RewardBox();
        var btSubmit = new Button("Submit");
        var gridPane = new GridPane(8, 12);
        gridPane.addColumn(0, new Label("DeathType"), new Label("Reward"));
        gridPane.addColumn(1, cbDeathType, rewardBox.getContent());
        gridPane.add(new StackPane(btSubmit), 0, 2, 2, 1);
        var lv = new ListView<DeathListener>();
        lv.getItems().setAll(lst);
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lv.setPrefSize(400, 400);
        lv.setTooltip(new Tooltip("BACKSPACE or DEL to delete item"));
        lv.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE || e.getCode() == KeyCode.BACK_SPACE
                    && lv.getSelectionModel().getSelectedItem() != null)
                lv.getItems().remove(lv.getSelectionModel().getSelectedItem());
        });
        var root = new HBox(8, lv, gridPane);

        btSubmit.setOnAction(e -> {
            try {
                var type = cbDeathType.getValue();
                var reward = rewardBox.submit();
                var listener = new DeathListener(type, reward);
                lv.getItems().add(listener);
            } catch (Exception ex) {
                DialogFactory.instance().showErrorBox(ex);
            }
        });

        new DialogBuilder().setTitle("Death Listener Modifier")
                .setHeaderText("Someone died. Apply listener.")
                .setContent(root)
                .setDefaultBtn()
                .buildAndShow().ifPresent(e -> {
                    lst.clear();
                    lst.addAll(lv.getItems().stream().toList());
                });
    }

    public Optional<DamageTakenOptions> showDamageTakenModifierBox(DamageTakenOptions damageTaken) {
        var tfMoney = new TextField();
        tfMoney.setPromptText(FXUtil.getByteRange());
        var tfAtkMod = new TextField();
        tfAtkMod.setPromptText(FXUtil.getByteRange());
        var tfLoot = new TextField();
        tfLoot.setPromptText(FXUtil.getByteRange());
        if (damageTaken != null) {
            tfMoney.setText(damageTaken.money().toString());
            tfAtkMod.setText(damageTaken.attackMod().toString());
            tfLoot.setText(damageTaken.loot().toString());
        }
        var root = new GridPane(8, 12);
        root.addColumn(0, new Label("Money"), new Label("Attack Modifier"), new Label("Loot"));
        root.addColumn(1, tfMoney, tfAtkMod, tfLoot);
        return new DialogBuilder().setTitle("Damage Dealt Options Modifier")
                .setHeaderText("Damage Dealt! Choose One.")
                .setContent(root)
                .setDefaultBtn()
                .buildAndShow().map(x -> {
                    DamageTakenOptions options = null;
                    if (!x.getButtonData().isDefaultButton()) return options;
                    try {
                        var money = Byte.parseByte(tfMoney.getText());
                        var atkMod = Byte.parseByte(tfAtkMod.getText());
                        var loot = Byte.parseByte(tfLoot.getText());
                        options = new DamageTakenOptions(money, atkMod, loot);
                    } catch (Exception ex) {
                        DialogFactory.instance().showErrorBox(ex);
                    }
                    return options;
                });
    }

    public Optional<DamageDealthRollForEffect> showDamageDealtRollForEffectModifierBox(DamageDealthRollForEffect effect) {
        var tfMin = new TextField();
        tfMin.setPromptText("[1,6]");
        var tfMax = new TextField();
        tfMax.setPromptText("[min, 6]");
        var root = new GridPane(8, 12);
        root.addColumn(0, new Label("Min Roll Value"), new Label("Max Roll Value"));
        root.addColumn(1, tfMin, tfMax);
        if (effect != null) {
            tfMin.setText(effect.min().toString());
            tfMax.setText(effect.max().toString());
        }
        return new DialogBuilder().setTitle("Damage Dealt Modifier")
                .setHeaderText("Damage Dealt! Roll for Effect.")
                .setContent(root)
                .setDefaultBtn()
                .buildAndShow().map(x -> {
                    DamageDealthRollForEffect obj = null;
                    if (!x.getButtonData().isDefaultButton()) return obj;
                    try {
                        var min = Byte.parseByte(tfMin.getText());
                        var max = Byte.parseByte(tfMax.getText());
                        obj = new DamageDealthRollForEffect(min, max);
                    } catch (Exception e) {
                        DialogFactory.instance().showErrorBox(e);
                    }
                    return obj;
                });
    }

    public void showKillListenerModifierBox(List<KillListener> lst) {
        var rewardBox = new RewardBox();
        var btSubmit = new Button("Submit");
        var gridPane = new GridPane(4, 4);
        gridPane.addColumn(0, new Label("Reward"));
        gridPane.addColumn(1, rewardBox.getContent());
        gridPane.add(new StackPane(btSubmit), 0, 1, 2, 1);

        var lv = new ListView<KillListener>();
        lv.getItems().setAll(lst);
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lv.setPrefSize(400, 400);
        lv.setTooltip(new Tooltip("BACKSPACE or DEL to delete item"));
        lv.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE || e.getCode() == KeyCode.BACK_SPACE
                    && lv.getSelectionModel().getSelectedItem() != null)
                lv.getItems().remove(lv.getSelectionModel().getSelectedItem());
        });

        btSubmit.setOnAction(e -> {
            Reward reward;
            try {
                reward = rewardBox.submit();
                var listener = new KillListener(reward);
                lv.getItems().add(listener);
            } catch (Exception ex) {
                this.showErrorBox(ex);
            }
        });

        var root = new HBox(8, lv, gridPane);

        new DialogBuilder().setTitle("Kill Listener Modifier")
                .setHeaderText("You killed someone. Apply listener.")
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
        var box = new RollEventBox();
        box.load(lst);
        new DialogBuilder().setTitle("Roll Event Modifier")
                .setHeaderText("Roll for an event.")
                .setContent(box.getContent())
                .setDefaultBtn()
                .buildAndShow().ifPresent(e -> {
                    if (e.getButtonData().isDefaultButton()) {
                        lst.clear();
                        lst.addAll(box.submit());
                    }
                });
    }

    public void showLootOptionEventModifierBox(List<LootOptionEvent> lst) {
        var box = new LootOptionEventBox();
        box.load(lst);
        new DialogBuilder().setTitle("Loot Option Event")
                .setHeaderText("You played a loot card, choose one.")
                .setContent(box.getContent())
                .setDefaultBtn()
                .buildAndShow().ifPresent(e -> {
                    if (e.getButtonData().isDefaultButton()) {
                        try {
                            lst.clear();
                            lst.addAll(box.submit());
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
                       obj = box.build();
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
                .setHeaderText("You played a pill card. Choose one.")
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
}
