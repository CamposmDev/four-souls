package org.camposmdev.client

import com.almasb.fxgl.app.ApplicationMode
import com.almasb.fxgl.app.CursorInfo
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.GameSettings
import com.almasb.fxgl.dsl.*
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import org.camposmdev.client.entity.factory.FSEntityFactory
import org.camposmdev.client.service.BoardPosition
import org.camposmdev.client.service.EntityService
import org.camposmdev.client.ui.scene.FSSceneFactory
import org.camposmdev.client.ui.view.ActionDrawerView
import org.camposmdev.client.ui.view.PlayMatView
import org.camposmdev.model.atlas.MasterCardAtlas
import org.camposmdev.model.game.LocalGameManager
import org.camposmdev.util.FXUtil

class FourSoulsApp : GameApplication() {
    companion object {
        private const val TITLE = "Four Souls"
        private const val VERSION = "1.0.0-alpha"
        private const val APP_WIDTH = 1600
        private const val APP_HEIGHT = 900

        @JvmStatic
        fun main(args: Array<String>) {
            launch(FourSoulsApp::class.java, args)
        }
    }

    override fun initSettings(settings: GameSettings) {
        settings.title = TITLE
        settings.version = VERSION
        settings.width = APP_WIDTH
        settings.height = APP_HEIGHT
        settings.appIcon = "icons/soul_circle.png"
        settings.isPreserveResizeRatio = true
        settings.isScaleAffectedOnResize = true
        settings.isIntroEnabled = false
        settings.isMainMenuEnabled = false
        settings.isGameMenuEnabled = true
        settings.sceneFactory = FSSceneFactory()
        settings.isManualResizeEnabled = true
        settings.isFullScreenAllowed = true
        settings.isPauseMusicWhenMinimized = true
        settings.defaultCursor = CursorInfo("cursor.png", 0.0, 0.0)
        settings.fontGame = "EdmundMcMillen_v2.ttf"
        settings.fontUI = "EdmundMcMillen_v2.ttf"
        settings.fontText = "EdmundMcMillen_v2.ttf"
        settings.cssList += "main.css"
        settings.applicationMode = ApplicationMode.DEVELOPER
        settings.addEngineService(EntityService::class.java)
    }

    override fun onPreInit() {
        val volume = 0.1
        getSettings().globalMusicVolume = volume
        getSettings().globalSoundVolume = volume
        getNotificationService().backgroundColor = Color.web("#2D2D30")
        getNotificationService().textColor = Color.WHITE
        /* load texture and store in cache */
        getAssetLoader().loadTexture("board.jpg")
    }

    override fun initUI() {
        ActionDrawerView.instance().render()
    }

    override fun initGameVars(vars: MutableMap<String, Any>) {
        val atlas = FXUtil.loadJSON("cards.json", MasterCardAtlas::class.java)
        val game = LocalGameManager(atlas)
        /* shuffle the cards */
        game.shuffle()
        /* add the game to game variables*/
        vars["game"] = game
        /* otherwise send request to server */
    }

    override fun initGame() {
        /* start the music */
        // loopBGM("The Binding of Isaac - 11 Repentant.mp3");
        /* add the four souls entity factory to spawn the game entities */
        getGameWorld().addEntityFactory(FSEntityFactory())
        val es = EntityService.get()
        /* render in the play mat (background) */
        val playMat = PlayMatView(-500)
        playMat.render()
        /* add treasure deck to game world */
        val treasureDeck = es.spawn().treasure_deck()
        es.mapper().position(treasureDeck, BoardPosition.CENTER_LEFT)
        /* add loot deck to game world */
        val lootDeck = es.spawn().loot_deck()
        es.mapper().position(lootDeck, BoardPosition.CENTER)
        /* add monster deck to game world */
        val monsterDeck = es.spawn().monster_deck()
        es.mapper().position(monsterDeck, BoardPosition.CENTER_RIGHT)
        /* add player to game world */
        val characterId = "b-isaac"
        es.spawn().player(characterId)
        //        ItemShopView itemShop = new ItemShopView();
//        itemShop.render();
//        var m1 = es.spawn_monster();
//        var c = m1.getComponent(MonsterCardComponent.class);
//        var root = (TilePane) itemShop.content();
//        root.setTranslateX(getAppWidth()/4d - root.getPrefWidth()/2d);
//        root.setTranslateY(getAppHeight()/2d - root.getPrefHeight()/2d);
//        root.getChildren().add(c.texture());
    }

    override fun initInput() {
        /* set key listener to fullscreen game */
        onKeyDown(KeyCode.F11) {
            val fullScreen: Boolean = getSettings().fullScreen.get()
            getSettings().fullScreen.set(!fullScreen)
        }
    }
}


