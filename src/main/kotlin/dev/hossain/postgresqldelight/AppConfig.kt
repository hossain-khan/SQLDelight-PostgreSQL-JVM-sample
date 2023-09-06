package dev.hossain.postgresqldelight

/**
 * App configuration data.
 */
data class AppConfig(
    /**
     * Database host with port.
     * See https://jdbc.postgresql.org/documentation/use/
     */
    val dbHost: String,
    val dbName: String,
    val dbUsername: String,
    val dbPassword: String,
)
