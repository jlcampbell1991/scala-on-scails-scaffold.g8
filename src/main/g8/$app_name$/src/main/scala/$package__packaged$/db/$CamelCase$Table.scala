package $package$

import doobie._
import doobie.implicits._

object $CamelCase$Table extends Table {
  def initialize: Update0 = sql"""
    DROP TABLE IF EXISTS $table_name$;
    CREATE TABLE $table_name$(
$UPPER_SNAKE_CASE$_TABLE
      created_at TIMESTAMP,
      updated_at TIMESTAMP,
      id VARCHAR PRIMARY KEY,
      user_id VARCHAR
    )""".update

  def update: Update0 =
    sql"""DROP TABLE IF EXISTS $table_name$"""
    .update
}
