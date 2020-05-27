package scaffold.scripts

object Table extends ScaffoldWriter {
  def update = {
    replace(
      s"./test-app/src/main/scala/test/app/db/\${CamelCase}Table.scala",
      s"\${UPPER_SNAKE_CASE}_TABLE",
      Fields.toSql
    )
  }
}
