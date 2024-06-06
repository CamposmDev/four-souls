package io.github.camposmdev.foursouls.server.basement.spi

import io.github.camposmdev.foursouls.server.basement.BasementServer
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default

data class BasementOpts(
    val basementPort: Int,
    val momHost: String,
    val momPort: Int
) {
    companion object {
        private const val DEFAULT_BASEMENT_PORT = 7000
        private const val DEFAULT_MOM_HOST = "localhost"
        private const val DEFAULT_MOM_PORT = 8000

        fun parse(args: Array<String>): BasementOpts {
            val parser = ArgParser(BasementServer.NAME)
            val basementPort by parser.option(
                ArgType.Int,
                "port",
                "p",
                "Port Number of Basement Server"
            ).default(DEFAULT_BASEMENT_PORT)
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
            return BasementOpts(basementPort, momHost, momPort)
        }
    }
}