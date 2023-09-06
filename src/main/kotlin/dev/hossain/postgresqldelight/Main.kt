package dev.hossain.postgresqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import javax.sql.DataSource


fun main(args: Array<String>) {
    println("Begin SQLDelight 2.0 with PostgreSQL Sample!")
    val appConfigLoader = AppConfigLoader()
    val sportsRepository = SportsRepository()
    val dataSource: DataSource = sportsRepository.getSource(appConfigLoader.loadAppConfig())


    val driver: SqlDriver = dataSource.asJdbcDriver()

    doDatabaseThings(driver)
}



fun doDatabaseThings(driver: SqlDriver) {
    val database = SportsDatabase(driver)
    val playerQueries: PlayerQueries = database.playerQueries

    println(playerQueries.selectAll().executeAsList())
    // [HockeyPlayer(15, "Ryan Getzlaf")]

    playerQueries.insert(player_number = 10, full_name = "Corey Perry")
    println(playerQueries.selectAll().executeAsList())
    // [HockeyPlayer(15, "Ryan Getzlaf"), HockeyPlayer(10, "Corey Perry")]

    val player = HockeyPlayer(10, "Ronald McDonald")
    playerQueries.insertFullPlayerObject(player)
}