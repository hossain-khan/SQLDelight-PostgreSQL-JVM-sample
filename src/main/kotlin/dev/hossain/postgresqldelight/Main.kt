package dev.hossain.postgresqldelight

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.faker
import kotlin.math.absoluteValue
import kotlin.random.Random


/**
 * Test application entry point. Run the app with ▶️ icon from left gutter.
 */
fun main(args: Array<String>) {
    println("Begin SQLDelight 2.0 with PostgreSQL Sample!")
    val appConfig: AppConfig = AppConfigLoader().loadAppConfig()
    val sportsRepository = SportsRepository()
    // ℹ️ NOTE:
    // =================
    // If your `PlayerQueries` is marked red, that means the generated class is not available.
    // You have to run the following gradle task to generate the class.
    //   - generateSqlDelightInterface: Aggregation task which runs every interface generation task for every given source
    // Run `./gradlew generateSqlDelightInterface` from terminal or from IntelliJ IDEA Gradle tool window.
    val playerQueries: PlayerQueries = sportsRepository.getPlayerQueries(appConfig)

    testDriveDatabase(playerQueries, faker { })
}


/**
 * Test drives different functions from [PlayerQueries].
 *
 * @param playerQueries SQLDelight class for doing player queries
 * @param faker Fake data generator.
 */
fun testDriveDatabase(playerQueries: PlayerQueries, faker: Faker) {
    // Show all players
    val hockeyPlayers = playerQueries.selectAll().executeAsList()
    println("Existing ${hockeyPlayers.size} records: $hockeyPlayers")


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

    val totalRecords = playerQueries.totalRecords().executeAsOne()
    println("Total players: $totalRecords.")
}