package scaffold.scripts

object Table extends ScaffoldWriter {
  def update = {
    replace(
      "./$app_name$/src/main/scala/$path$/db/Table.scala",
      s"\${UPPER_SNAKE_CASE}_TABLE",
      Fields.toSql
    )
  }
}
