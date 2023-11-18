package dev.hossain.postgresqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

/**
 * Repository for creating database and getting SQLDelight classes needed for testing.
 */
class SportsRepository constructor(

) {

    fun getPlayerQueries(appConfig: AppConfig): PlayerQueries {
        val dataSource: DataSource = getDataSource(appConfig)

        val driver: SqlDriver = dataSource.asJdbcDriver()

        execSchema(driver)

        val database = SportsDatabase(driver)
        val playerQueries: PlayerQueries = database.playerQueries

        return playerQueries
    }

    /**
     * @param driver the [SqlDriver] required to create the database.
     */
    private fun execSchema(driver: SqlDriver) {
        SportsDatabase.Schema.create(driver)
    }


    /**
     * Creates a [DataSource] using [HikariDataSource].
     * @param appConfig the [AppConfig] required to create the [DataSource].
     */
    private fun getDataSource(appConfig: AppConfig): DataSource {
        // https://jdbc.postgresql.org/documentation/use/
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://${appConfig.dbHost}/${appConfig.dbName}"
            driverClassName = "org.postgresql.Driver"
            username = appConfig.dbUsername
            password = appConfig.dbPassword
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        return HikariDataSource(config)
    }
}