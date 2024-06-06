package io.github.camposmdev.foursouls.server.chest.spi

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default

data class ChestOpts(
    val chestPort: Int,
    val momHost: String,
    val momPort: Int
) {
    companion object {
        private const val PROG_NAME = "Chest"
        private const val DEFAULT_CHEST_PORT = 6000
        private const val DEFAULT_MOM_HOST = "localhost"
        private const val DEFAULT_MOM_PORT = 8000

        @JvmStatic
        fun parse(args: Array<String>): ChestOpts {
            val parser = ArgParser(PROG_NAME)
            val chestPort by parser.option(
                ArgType.Int,
                "port",
                "p",
                "Port Number of Chest Server"
            ).default(DEFAULT_CHEST_PORT)
            val momHost by parser.option(
                ArgType.String,
                fullName = "mom-host",
                shortName = "mh",
                description = "Host Address of Mom Server"
            ).default(DEFAULT_MOM_HOST)
            val momPort by parser.option(
                ArgType.Int,
                fullName = "mom-port",
                shortName = "mp",
                description = "Port Number of Mom Server"
            ).default(DEFAULT_MOM_PORT)
            parser.parse(args)
            return ChestOpts(chestPort, momHost, momPort)
        }
    }
}