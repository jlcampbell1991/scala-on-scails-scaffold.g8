package $package$

import doobie._
import doobie.implicits._

ATTENTION
/**
  After generating scaffold, append this Table Object
  to `tables` List in /db/Database.scala
*/

object $class_name;format="Camel"$Table extends Table {
  def initialize: Update0 = sql"""
    DROP TABLE IF EXISTS $app_name;format="snake, lower"$_$class_name;format="snake, lower"$;
    CREATE TABLE $app_name;format="snake, lower"$_$class_name;format="snake, lower"$(
      $table_columns$,
      created_at TIMESTAMP,
      updated_at TIMESTAMP,
      id VARCHAR PRIMARY KEY,
      user_id VARCHAR
    )""".update

  def update: Update0 =
    sql"""DROP TABLE IF EXISTS $app_name;format="snake, lower"$_$class_name;format="snake, lower"$"""
    .update
}
