package $package$

object $class_name;format="Camel"$Table extends Table {
  def initialize: Update0 = sql"""
    DROP TABLE IF EXISTS test_app_user;
    CREATE TABLE test_app_user(
      name VARCHAR UNIQUE,
      password VARCHAR,
      id VARCHAR PRIMARY KEY
    )""".update

  def update: Update0 =
    sql"""DROP TABLE IF EXISTS test_app_user"""
    .update
}
