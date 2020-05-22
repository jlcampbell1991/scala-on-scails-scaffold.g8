package scaffold.scripts

object Database extends ScaffoldWriter {
  def update = append(
    "./$app_name$/src/main/scala/$path$/db/Database.scala",
    "List(UserTable)",
    s""" ::: List(${CamelCase}Table)"""
  )
}
