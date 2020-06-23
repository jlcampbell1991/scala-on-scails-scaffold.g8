package scaffold.scripts

object $CamelCase$Table extends $CamelCase$ScaffoldWriter {
  def update = {
    replace(
      s"./$app_name$/src/main/scala/$path$/db/\${CamelCase}Table.scala",
      s"\${UPPER_SNAKE_CASE}_TABLE",
      $CamelCase$Fields.toSql
    )
  }
}
