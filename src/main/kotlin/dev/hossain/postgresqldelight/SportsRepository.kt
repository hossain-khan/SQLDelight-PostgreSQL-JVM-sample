package dev.hossain.postgresqldelight

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

class SportsRepository constructor(

) {

    fun getSource(appConfig: AppConfig): DataSource {
        val hikariConfig = HikariConfig()
        // https://jdbc.postgresql.org/documentation/use/
        hikariConfig.setJdbcUrl("jdbc:postgresql://192.168.2.32:5432/${appConfig.dbName}")
        hikariConfig.driverClassName = "org.postgresql.Driver"
        hikariConfig.username = appConfig.dbUsername
        hikariConfig.password = appConfig.dbPassword

        return HikariDataSource(hikariConfig)
    }
}