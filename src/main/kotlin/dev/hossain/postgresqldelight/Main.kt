package dev.hossain.postgresqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.faker
import javax.sql.DataSource
import kotlin.math.absoluteValue
import kotlin.random.Random


fun main(args: Array<String>) {
    println("Begin SQLDelight 2.0 with PostgreSQL Sample!")
    val appConfigLoader = AppConfigLoader()
    val sportsRepository = SportsRepository()
    val dataSource: DataSource = sportsRepository.getSource(appConfigLoader.loadAppConfig())


    val driver: SqlDriver = dataSource.asJdbcDriver()
    SportsDatabase.Schema.create(driver)

    testDriveDatabase(driver, faker { })
}


/**
 * @param driver the [SqlDriver] required to create the database.
 * @param faker Fake data generator.
 */
fun testDriveDatabase(driver: SqlDriver, faker: Faker) {
    val database = SportsDatabase(driver)

    val playerQueries: PlayerQueries = database.playerQueries

    // Show all players
    println(playerQueries.selectAll().executeAsList())


    // Uses query param to insert data
    playerQueries.insert(
        player_number = Random.nextInt().absoluteValue,
        full_name = faker.name.name()
    )
    println(playerQueries.selectAll().executeAsList())

    // Uses full data object to insert data
    val player = HockeyPlayer(
        player_number = Random.nextInt().absoluteValue,
        full_name = faker.name.name()
    )
    playerQueries.insertFullPlayerObject(player)
}