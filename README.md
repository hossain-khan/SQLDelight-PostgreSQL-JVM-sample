# SQLDelight 2.0 with PostgreSQL Sample
<img width="420" align="right" src="https://github.com/hossain-khan/SQLDelight-PostgreSQL-JVM-sample/assets/99822/a09a822d-443c-4153-8f62-679b701f59a6"/>
A sample project exercising PostgreSQL with SQLDelight 2.0 on JVM

This is an exploration of the official guide at https://cashapp.github.io/sqldelight/2.0.0/jvm_postgresql/ with the intention of filling in missing pieces. 

## ✍️ Blog
See [blog post](https://medium.com/@hossainkhan/using-sqldelight-2-0-with-postgresql-for-jvm-10e749093a82) on medium for quick guide and explanation. 

## 🚗 Test Drive
To run the sample app, you need to do the following
1. Git clone the repo
1. Rename/copy `sample-local.properties` into `local.properties`  
  i. Update all app configurations like `db_host`, `db_password`, and so on
1. Create the database with the name that matches `db_name` config
1. Run app from [`Main.kt`](https://github.com/hossain-khan/SQLDelight-PostgreSQL-JVM-sample/blob/main/src/main/kotlin/dev/hossain/postgresqldelight/Main.kt)  

## 📚 Resources
* https://cashapp.github.io/sqldelight
* https://jdbc.postgresql.org/documentation/use/
