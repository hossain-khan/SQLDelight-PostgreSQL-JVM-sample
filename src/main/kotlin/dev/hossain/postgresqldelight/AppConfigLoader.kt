package dev.hossain.postgresqldelight

import java.io.File
import java.util.*

class AppConfigLoader {
    /**
     * Loads app config from local properties file.
     */
    fun loadAppConfig(): AppConfig {
        val properties = Properties().apply { load(File("local.properties").inputStream()) }
        return AppConfig(
            dbName = properties.getProperty("db_name"),
            dbUsername = properties.getProperty("db_username"),
            dbPassword = properties.getProperty("db_password"),
        )
    }
}