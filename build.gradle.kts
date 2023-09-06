plugins {
    kotlin("jvm") version "1.9.0"

    // SQLDelight - Generates typesafe Kotlin APIs from SQL
    // https://cashapp.github.io/sqldelight/2.0.0/
    id("app.cash.sqldelight") version "2.0.0"

    application
}

group = "dev.hossain.postgresqldelight"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

// SQLDelight - Generates typesafe Kotlin APIs from SQL
// https://github.com/cashapp/sqldelight
sqldelight {
    databases {
        create("SportsDatabase") {
            packageName.set("dev.hossain.postgresqldelight")
            // https://cashapp.github.io/sqldelight/2.0.0/jvm_postgresql/
            dialect("app.cash.sqldelight:postgresql-dialect:2.0.0")
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(19))
}

dependencies {
    // PostgreSQL is a powerful object-relational database system
    // https://www.postgresql.org/
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.6.0")

    // 光 HikariCP・A solid, high-performance, JDBC connection pool at last.
    // https://github.com/brettwooldridge/HikariCP#artifacts
    // https://www.baeldung.com/hikaricp
    implementation("com.zaxxer:HikariCP:5.0.1")

    // SQLDelight - Generates typesafe Kotlin APIs from SQL
    // https://cashapp.github.io/sqldelight/2.0.0/jvm_postgresql
    implementation("app.cash.sqldelight:jdbc-driver:2.0.0")

    // Faker - Generates fake data for testing or populating a development database.
    // https://github.com/blocoio/faker
    implementation("io.github.serpro69:kotlin-faker:1.14.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}