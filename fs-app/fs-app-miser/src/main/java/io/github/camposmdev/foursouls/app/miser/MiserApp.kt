package io.github.camposmdev.foursouls.app.miser

import io.github.camposmdev.foursouls.core.card.attribute.CardType.*
import io.github.camposmdev.foursouls.core.util.Timex
import io.github.camposmdev.foursouls.core.util.assets.CardAsset
import io.github.camposmdev.foursouls.core.util.assets.MasterCardAssetsManager
import io.github.camposmdev.foursouls.core.util.logger.Logger
import io.vertx.core.json.Json
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.net.SocketTimeoutException
import java.net.URI
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import javax.imageio.ImageIO
import kotlin.math.roundToInt
import kotlin.system.exitProcess

/* JSoup Class, Tags, Attributes */
const val CARD_GRID_CELL_CLASS = "cardGridCell"
const val PAGE_NUM_CLASS = "page-numbers"
const val CARD_TYPE_HOVER_CLASS = "cardTypeHover"
const val A_TAG = "a"
const val IMG_TAG = "img"
const val SRC_ATTR = "src"
const val HREF_ATTR = "href"
const val DATA_URI = "data:image"
/* Outliar Image */
const val FLIP_CORNER_NOTE_IMG = "FlipCornerNote.png"

const val PROG_NAME = "Miser"

const val CPU_USAGE = 0.75
val N_THREADS = (Runtime.getRuntime().availableProcessors() * CPU_USAGE).roundToInt()
val log = Logger(PROG_NAME).apply {
    debug = true
}

var jsonDes: String = "./cards.json"
var rootDir: String = ""
lateinit var cardDir: String
lateinit var bsoulDir: String
lateinit var charDir: String
lateinit var eternalDir: String
lateinit var treasureDir: String
lateinit var monsterDir: String
lateinit var lootDir: String
lateinit var moneyDir: String
lateinit var roomDir: String
lateinit var outsideDir: String
lateinit var versoDir: String
var skipDownload = false
var useRelativePaths = false

const val BASE_URL = "https://foursouls.com/"
const val CARD_BACKS_URL = "${BASE_URL}cards/"

fun cardQuery(cardType: String): String {
    val query = "card-search/?identical=no&cardstatus=all&card_type="
    return "${BASE_URL}$query$cardType"
}

val CHARACTERS_URL = cardQuery(CHARACTER.key())
val ETERNAL_URLS = arrayOf(
    cardQuery(PETERNAL.key()),      /* Passive Eternals */
    cardQuery(AETERNAL.key()),      /* Active Eternals */
    cardQuery(PAIDETERNAL.key()),   /* Paid Eternals */
    cardQuery(OETERNAL.key()),      /* One-Time Use Eternals */
    cardQuery(SETERNAL.key())       /* Soul Eternals */
)
val TREASURE_URLS = arrayOf(
    cardQuery(PTREASURE.key()),    // Passive Treasure
    cardQuery(ATREASURE.key()),    // Active Treasure
    cardQuery(PAIDTREASURE.key()), // Paid Treasure
    cardQuery(OTREASURE.key()),    // One Use Treasure
    cardQuery(STREASURE.key())     // Soul Treasure
)
val MONSTER_URLS = arrayOf(
    cardQuery(BMONSTER.key()),    // Basic Monsters
    cardQuery(CMONSTER.key()),    // Cursed Monsters
    cardQuery(HMONSTER.key()),    // Holy Monsters
    cardQuery(CHAMONSTER.key()),  // Charmed Monsters
    cardQuery(GEVENT.key()),      // Good Events
    cardQuery(BEVENT.key()),      // Bad Events
    cardQuery(CURSE.key()),       // Curses
    cardQuery(BOSS.key()),        // Bosses
    cardQuery(EPIC.key())         // Epic Bosses
)
val LOOT_URLS = arrayOf(
    cardQuery(CARDS.key()),    // Cards
    cardQuery(TRINKETS.key()), // Trinkets
    cardQuery(PILLS.key()),    // Pills
    cardQuery(RUNES.key()),    // Runes
    cardQuery(BOMBS.key()),    // Bombs
    cardQuery(BUTTER.key()),   // Butter Beans
    cardQuery(BATTERIES.key()),// Batteries
    cardQuery(KEYS.key()),     // Keys
    cardQuery(DICE.key()),     // Dice Shards
    cardQuery(SHEART.key()),   // Soul Hearts
    cardQuery(BHEART.key()),   // Black Hearts
    cardQuery(SACK.key()),     // Sacks
    cardQuery(LSOUL.key()),    // Lost Souls
    cardQuery(WILDCARD.key())  // Wild Cards
)
val MONEY_URLS = arrayOf(
    cardQuery(MONEY1C.key()), // One Pennies
    cardQuery(MONEY2C.key()), // Two Pennies
    cardQuery(MONEY3C.key()), // Three Pennies
    cardQuery(MONEY4C.key()), // Four Pennies
    cardQuery(MONEY5C.key()), // Nickels
    cardQuery(MONEY10C.key()) // Dimes
)
val BSOUL_URL = cardQuery(BSOUL.key())
val ROOM_URL = cardQuery(ROOM.key())
val OUTSIDE_URL = cardQuery(OUTSIDE.key())

fun parseArgs(args: Array<String>) {
    fun showUsage() {
        println("""
        Usage: Miser [options]
        
        Options:
          -j, --json <file>        JSON file path (default: ./cards.json)
          -o, --out <directory>    Image directory path (default: ./)
          -s, --skip-download      Skip downloading images
          --relative-paths         Use relative paths from 'cards/' directory
          -h, --help               Show this help message and exit
    """.trimIndent())
    }
    var i = 0
    while (i < args.size) {
        when (args[i]) {
            "-j", "--json" -> {
                if (i + 1 < args.size) {
                    jsonDes = args[i + 1] /* overwrite jsonDes */
                    i += 2
                } else {
                    println("Error: Missing argument for JSON file path.")
                    showUsage()
                    exitProcess(1)
                }
            }
            "-o", "--out" -> {
                if (i + 1 < args.size) {
                    val temp = args[i + 1]
                    rootDir = if (temp.endsWith("/")) temp else "$temp/"
                    i += 2
                } else {
                    println("Error: Missing argument for image directory path.")
                    showUsage()
                    exitProcess(1)
                }
            }
            "-s", "--skip-download" -> {
                skipDownload = true
                i++
            }
            "--relative-paths" -> {
                useRelativePaths = true
                i++
            }
            "-h", "--help" -> {
                showUsage()
                exitProcess(0)
            }
            else -> {
                println("Error: Unknown option '${args[i]}'.")
                showUsage()
                exitProcess(1)
            }
        }
    }
    cardDir = rootDir + "cards/"
    bsoulDir = cardDir + "bsoul/"
    charDir = cardDir + "character/"
    eternalDir = cardDir + "eternal/"
    treasureDir = cardDir + "treasure/"
    monsterDir = cardDir + "monster/"
    lootDir = cardDir + "loot/"
    moneyDir = cardDir + "loot/money/"
    roomDir = cardDir + "room/"
    outsideDir = cardDir + "outside/"
    versoDir = cardDir + "verso/"
}

fun fetch(src: String): Document {
    val ua = "'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'"
    val maxRetries = 10
    val conn = Jsoup.connect(src).userAgent(ua)
    var retries = 0
    while (retries < maxRetries) {
        try {
            val doc = conn.get()
            log.debug("Raiding $src")
            return doc
        } catch (ex: SocketTimeoutException) {
            log.warn("Retrying[${++retries}/$maxRetries] $src")
        } catch (ex: IOException) {
            ex.printStackTrace()
        } catch (ex: InterruptedException) {
            ex.printStackTrace()
        }
    }
    log.error("Failed to raid $src")
    throw RuntimeException()
}

fun raidCardBacks(manager: MasterCardAssetsManager) {
    val cardType = VERSO.key()
    val doc = fetch(CARD_BACKS_URL)
    val nil = ""
    val regex = "-110x150|-150x110"
    val divs = doc.getElementsByClass(CARD_TYPE_HOVER_CLASS)
    for (div in divs) {
        val imgTags = div.getElementsByTag(IMG_TAG)
        for (imgTag in imgTags) {
            val src = imgTag.attributes().get(SRC_ATTR)
            if (src.startsWith(DATA_URI)) {
                log.debug("Skipping <img>: $SRC_ATTR attribute starts with $DATA_URI")
                continue
            }
            val pattern = Pattern.compile(regex)
            val loResUrl = BASE_URL.plus(src)
            val hiResUrl = BASE_URL.plus(pattern.matcher(src).replaceAll(nil))
            val cardAsset = buildCardAsset(cardType, CARD_BACKS_URL, hiResUrl, loResUrl)
            allocateCardAsset(cardType, cardAsset, manager)
        }
    }
}

fun raidCards(urls: Array<String>, manager: MasterCardAssetsManager) {
    urls.forEach { raidCards(it, manager) }
}

fun raidCards(url: String, manager: MasterCardAssetsManager) {
    fun pillage(src: String, recursive: Boolean = false) {
        fun burn(elements: Elements) {
            val nil = ""
            val delimiter = "="
            val regex = "-308x420|-420x308|-420x300"
            for (div in elements) {
                val aTags = div.getElementsByTag(A_TAG)
                if (aTags.isEmpty()) {
                    log.debug("Empty tags in: $div")
                    return
                }
                val originUrl = aTags.first()!!.attributes().get(HREF_ATTR)
                val imgTags = div.getElementsByTag(IMG_TAG)
                for (imgTag in imgTags) {
                    val imgUrl = imgTag.attributes().get(SRC_ATTR)
                    if (imgUrl.startsWith(DATA_URI)) {
                        log.debug("Skipping <img>: $SRC_ATTR attribute starts with $DATA_URI")
                        continue
                    }
                    val pattern = Pattern.compile(regex)
                    val matcher = pattern.matcher(imgUrl)
                    if (matcher.find()) {
                        val hiResUrl = matcher.replaceAll(nil)
                        val cardType = url.split(delimiter).last()
                        val cardAsset = buildCardAsset(cardType, originUrl, hiResUrl, imgUrl)
                        allocateCardAsset(cardType, cardAsset, manager)
                    } else if (imgUrl.contains(FLIP_CORNER_NOTE_IMG)) {
                        log.debug("Skipping: $imgUrl")
                    } else {
                        log.debug("Outliar: $imgUrl")
                    }
                }
            }
        }

        val doc = fetch(src)
        val cards = doc.getElementsByClass(CARD_GRID_CELL_CLASS)
        if (!cards.isEmpty())
            burn(cards)
        val pageNumbers = doc.getElementsByClass(PAGE_NUM_CLASS)
        if (recursive) {
            for (idx in 1 until pageNumbers.size) {
                val href = pageNumbers[idx].attributes().get(HREF_ATTR)
                pillage(href)
            }
        }
    }
    pillage(url, true)
}

fun buildCardAsset(type: String, origin: String, hiResUrl: String, loResUrl: String): CardAsset {
    var dir: String
    val cardType = parse(type)
    when (cardType) {
        BSOUL -> dir = bsoulDir
        CHARACTER -> dir = charDir
        AETERNAL, PETERNAL, PAIDETERNAL, OETERNAL, SETERNAL ->
            dir = "${eternalDir}${cardType.key()}/"

        PTREASURE, ATREASURE, PAIDTREASURE, OTREASURE, STREASURE ->
            dir = "${treasureDir}${cardType.key()}/"

        BMONSTER, CMONSTER, HMONSTER, CHAMONSTER, GEVENT, BEVENT, CURSE, BOSS, EPIC ->
            dir = "${monsterDir}${cardType.key()}/"

        CARDS, TRINKETS, PILLS, RUNES, BOMBS, BUTTER, BATTERIES, KEYS, DICE, SHEART, BHEART, SACK, LSOUL, WILDCARD ->
            dir = "${lootDir}${cardType.key()}/"

        MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C ->
            dir = "${moneyDir}${cardType.key()}/"

        ROOM -> dir = roomDir
        OUTSIDE -> dir = outsideDir
        VERSO -> dir = versoDir
        else -> throw IllegalArgumentException("Invalid cardType '$type'")
    }
    if (useRelativePaths) {
        val idx = dir.indexOf(cardDir) + cardDir.length - "cards/".length
        dir = dir.substring(idx)
    }
    return CardAsset(cardType, origin, hiResUrl, loResUrl, dir)
}

fun allocateCardAsset(type: String, asset: CardAsset, manager: MasterCardAssetsManager) {
    when (parse(type)) {
        BSOUL -> manager.bsoul[asset.id()] = asset
        CHARACTER -> manager.character[asset.id()] = asset
        AETERNAL -> manager.eternal.aeternal[asset.id()] = asset
        PETERNAL -> manager.eternal.peternal[asset.id()] = asset
        PAIDETERNAL -> manager.eternal.paideternal[asset.id()] = asset
        OETERNAL -> manager.eternal.oeternal[asset.id()] = asset
        SETERNAL -> manager.eternal.seternal[asset.id()] = asset
        PTREASURE -> manager.treasure.ptreasure[asset.id()] = asset
        ATREASURE -> manager.treasure.atreasure[asset.id()] = asset
        PAIDTREASURE -> manager.treasure.paidtreasure[asset.id()] = asset
        OTREASURE -> manager.treasure.otreasure[asset.id()] = asset
        STREASURE -> manager.treasure.streasure[asset.id()] = asset
        BMONSTER -> manager.monster.bmonster[asset.id()] = asset
        CMONSTER -> manager.monster.cmonster[asset.id()] = asset
        HMONSTER -> manager.monster.hmonster[asset.id()] = asset
        CHAMONSTER -> manager.monster.chamonster[asset.id()] = asset
        GEVENT -> manager.monster.gevent[asset.id()] = asset
        BEVENT -> manager.monster.bevent[asset.id()] = asset
        CURSE -> manager.monster.curse[asset.id()] = asset
        BOSS -> manager.monster.boss[asset.id()] = asset
        EPIC -> manager.monster.epic[asset.id()] = asset
        CARDS -> manager.loot.cards[asset.id()] = asset
        TRINKETS -> manager.loot.trinkets[asset.id()] = asset
        PILLS -> manager.loot.pills[asset.id()] = asset
        RUNES -> manager.loot.runes[asset.id()] = asset
        BOMBS -> manager.loot.bombs[asset.id()] = asset
        BUTTER -> manager.loot.butter[asset.id()] = asset
        BATTERIES -> manager.loot.batteries[asset.id()] = asset
        KEYS -> manager.loot.keys[asset.id()] = asset
        DICE -> manager.loot.dice[asset.id()] = asset
        SHEART -> manager.loot.sheart[asset.id()] = asset
        BHEART -> manager.loot.bheart[asset.id()] = asset
        SACK -> manager.loot.sack[asset.id()] = asset
        LSOUL -> manager.loot.lsoul[asset.id()] = asset
        WILDCARD -> manager.loot.wildcard[asset.id()] = asset
        MONEY1C -> manager.loot.money.money1c[asset.id()] = asset
        MONEY2C -> manager.loot.money.money2c[asset.id()] = asset
        MONEY3C -> manager.loot.money.money3c[asset.id()] = asset
        MONEY4C -> manager.loot.money.money4c[asset.id()] = asset
        MONEY5C -> manager.loot.money.money5c[asset.id()] = asset
        MONEY10C -> manager.loot.money.money10c[asset.id()] = asset
        ROOM -> manager.room[asset.id()] = asset
        OUTSIDE -> manager.outside[asset.id()] = asset
        VERSO -> manager.verso[asset.id()] = asset
        else -> throw IllegalArgumentException("Invalid cardType '$type'")
    }
    log.info("Allocated ${asset.id()} -> $type")
}

fun raidImages(manager: MasterCardAssetsManager) {
    if (skipDownload) {
        log.info("Skipping image downloads")
        return
    }
    fun pillageImage(cardAsset: CardAsset) {
        fun burnImage(src: String, des: String) {
            var f = File(des)
            if (useRelativePaths) f = File(rootDir + des);
            val parent = f.parentFile
            if (!parent.exists())
                parent.mkdirs()
            if (f.exists())  { /* file already exists, abort pillage */
                log.debug("Image already exists $f")
                return
            }
            var retries = 0
            val maxRetries = 10
            while (retries < maxRetries) {
                try {
                    val url = URI.create(src).toURL()
                    val img = ImageIO.read(url.openStream())
                    ImageIO.write(img, "png", f)
                    log.info("Pillaged $url")
                    break
                } catch (ex: IOException) {
                    log.warn("Retrying[${++retries}/${maxRetries}] $f")
                } catch (ex: InterruptedException) {
                    ex.printStackTrace()
                }
            }
            if (retries > maxRetries)
                log.error("Failed to pillage $src")
        }
        burnImage(cardAsset.hiResUrl, cardAsset.hiResSrc())
        burnImage(cardAsset.loResUrl, cardAsset.loResSrc())
    }
    fun verifyImages(manager: MasterCardAssetsManager) {
        var path = ""
        if (useRelativePaths)
            path = rootDir
        for (entry in manager) {
            val f1 = File(path.plus(entry.value.hiResSrc()))
            val f2 = File(path.plus(entry.value.loResSrc()))
            if (!f1.exists())
                log.error("Failed to pillage $f1")
            if (!f2.exists())
                log.error("Failed to pillage $f2")
        }
    }
    val timeout = 5L
    val executor = Executors.newFixedThreadPool(N_THREADS)
    for (entry in manager) {
        executor.execute { pillageImage(entry.value) }
    }
    try {
        executor.shutdown()
        val rc = executor.awaitTermination(timeout, TimeUnit.MINUTES)
        if (!rc) {
            log.error("Downloading images took too long!")
            executor.shutdownNow()
        }
    } catch (ex: InterruptedException) {
        log.error(ex.message)
    } finally {
        verifyImages(manager)
    }
}

fun save(manager: MasterCardAssetsManager) {
    val str = Json.encodePrettily(manager)
    try {
        val f = File(jsonDes)
        val parent = f.parentFile
        if (parent != null && !parent.exists())
            parent.mkdirs()
        val pw = PrintWriter(f)
        pw.print(str)
        pw.close()
        log.info("Saved $f")
    } catch (ex: IOException) {
        log.error(ex.message)
    }
}

fun main(args: Array<String>) {
    val timer = Timex().start()
    val manager = MasterCardAssetsManager()
    log.info("Utilizing $N_THREADS CPUs")
    parseArgs(args)
    /* first come raid, then pillage, then burn */
    raidCardBacks(manager)
    raidCards(BSOUL_URL, manager)
    raidCards(CHARACTERS_URL, manager)
    raidCards(ETERNAL_URLS, manager)
    raidCards(TREASURE_URLS, manager)
    raidCards(MONSTER_URLS, manager)
    raidCards(LOOT_URLS, manager)
    raidCards(MONEY_URLS, manager)
    raidCards(ROOM_URL, manager)
    raidCards(OUTSIDE_URL, manager)
    raidImages(manager)
    save(manager)
    timer.stop()
    log.info("Finished Raiding ${timer.stop()}")
}