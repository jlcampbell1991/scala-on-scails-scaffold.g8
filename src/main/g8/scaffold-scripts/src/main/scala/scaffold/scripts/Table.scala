package scaffold.scripts

object Table extends ScaffoldWriter {
  def update = {
    replace(
      s"./$app_name$/src/main/scala/$path$/db/\${CamelCase}Table.scala",
      s"\${UPPER_SNAKE_CASE}_TABLE",
      Fields.toSql
    )
  }
}
