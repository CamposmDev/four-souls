package io.github.camposmdev.foursouls.core.util

import io.github.camposmdev.foursouls.core.util.assets.CardAsset
import io.vertx.core.json.JsonObject
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CardAssetTest {
    @Test
    fun testBsoulOfGluttony() {
        val str = "{\"url\":\"https://foursouls.com/cards/b-soul_of_gluttony/\",\"hiResUrl\":\"https://foursouls.com/wp-content/uploads/2022/02/b-soul_of_gluttony.png\",\"loResUrl\":\"https://foursouls.com/wp-content/uploads/2022/02/b-soul_of_gluttony-308x420.png\",\"dir\":\"cards/bsoul/\"}"
        val job = JsonObject(str)
        val asset = job.mapTo(CardAsset::class.java)
        assertEquals("b-soul_of_gluttony", asset.id())
    }

    @Test
    fun testLilDelirium() {
        val str = "{\"url\":\"https://foursouls.com/cards/box-lil_delirium/\",\"hiResUrl\":\"https://foursouls.com/wp-content/uploads/2022/11/LilDelirium.png\",\"loResUrl\":\"https://foursouls.com/wp-content/uploads/2022/11/LilDelirium-308x420.png\",\"dir\":\"cards/treasure/ptreasure/\"}"
        val job = JsonObject(str)
        val asset = job.mapTo(CardAsset::class.java)
        assertEquals("box-lil_delirium", asset.id())
    }

    @Test
    fun testLumpofCoal() {
        val str = "{\"url\":\"https://foursouls.com/cards/p-lump_of_coal/\",\"hiResUrl\":\"https://foursouls.com/wp-content/uploads/2022/11/LumpofCoal.png\",\"loResUrl\":\"https://foursouls.com/wp-content/uploads/2022/11/LumpofCoal-308x420.png\",\"dir\":\"cards/treasure/ptreasure/\"}"
        val job = JsonObject(str)
        val asset = job.mapTo(CardAsset::class.java)
        assertEquals("p-lump_of_coal", asset.id())
    }

    @Test
    fun testDeliriousDukeOfFlies() {
        val str = "\"cardType\": \"BOSS\",{\"url\":\"https://foursouls.com/cards/box-delirium/\",\"hiResUrl\":\"https://foursouls.com/wp-content/uploads/2022/11/DeliriousDukeOfFlies.png\",\"loResUrl\":\"https://foursouls.com/wp-content/uploads/2022/11/DeliriousDukeOfFlies-308x420.png\",\"dir\":\"cards/monster/boss/\"}"
        val job = JsonObject(str)
        val asset = job.mapTo(CardAsset::class.java)
        assertEquals("box-delirium", asset.id())
    }

    @Test
    fun testDeliriousMonstro() {
        val str = "\"cardType\": \"BOSS\",{\"url\":\"https://foursouls.com/cards/box-delirium_2/\",\"hiResUrl\":\"https://foursouls.com/wp-content/uploads/2022/11/DeliriousMonstro.png\",\"loResUrl\":\"https://foursouls.com/wp-content/uploads/2022/11/DeliriousMonstro-308x420.png\",\"dir\":\"cards/monster/boss/\"}\n"
        val job = JsonObject(str)
        val asset = job.mapTo(CardAsset::class.java)
        assertEquals("box-delirium_2", asset.id())
    }

    @Test
    fun testTheHarbingers() {
        val str = "{\"cardType\": \"OUTSIDE\",\"url\":\"https://foursouls.com/cards/r-the_harbingers/\",\"hiResUrl\":\"https://foursouls.com/wp-content/uploads/2022/01/r-the_harbingers.png\",\"loResUrl\":\"https://foursouls.com/wp-content/uploads/2022/01/r-the_harbingers-308x420.png\",\"dir\":\"cards/outside/\"}"
        val job = JsonObject(str)
        val asset = job.mapTo(CardAsset::class.java)
        assertEquals("r-the_harbingers", asset.id())
    }

    @Test
    fun testTheBeast() {
        val str = "{\"cardType\": \"OUTSIDE\",\"url\":\"https://foursouls.com/cards/r-the_harbingers/\",\"hiResUrl\":\"https://foursouls.com/wp-content/uploads/2022/01/r-the_beast.png\",\"loResUrl\":\"https://foursouls.com/wp-content/uploads/2022/01/r-the_beast-308x420.png\",\"dir\":\"cards/outside/\"}"
        val job = JsonObject(str)
        val asset = job.mapTo(CardAsset::class.java)
        assertEquals("r-the_beast", asset.id())
    }

    @Test
    fun testCharacterCardBack() {
        val str = "{\"cardType\": \"VERSO\",\"url\":\"https://foursouls.com/cards/\",\"hiResUrl\":\"https://foursouls.com//wp-content/uploads/2021/10/CharacterCardBack.png\",\"loResUrl\":\"https://foursouls.com//wp-content/uploads/2021/10/CharacterCardBack-110x150.png\",\"dir\":\"cards/verso/\"}"
        val job = JsonObject(str)
        val asset = job.mapTo(CardAsset::class.java)
        assertEquals("CharacterCardBack", asset.id())
    }
}