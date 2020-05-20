package $package$

object $class_name;format="Camel"$Table extends Table {
  def initialize: Update0 = sql"""
    DROP TABLE IF EXISTS $class_name;format="snake, lower"$;
    CREATE TABLE $class_name;format="snake, lower"$(
      $table_columns$
    )""".update

  def update: Update0 =
    sql"""DROP TABLE IF EXISTS $class_name;format="snake, lower"$"""
    .update
}
