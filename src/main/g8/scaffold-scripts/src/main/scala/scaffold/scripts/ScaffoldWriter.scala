package scaffold.scripts

import java.io.{File, PrintWriter}
import scala.io.Source

trait ScaffoldWriter {
  val table_name: String = "$table_name$"
  val model_fields: String = "$model_fields$"
  val camelCase: String = "$camelCase$"
  val CamelCase: String = "$CamelCase$"
  val UPPER_SNAKE_CASE: String = "$UPPER_SNAKE_CASE$"
  val snake_case: String = "$snake_case$"

  private def update(fileName: String, identifier: String, update: String, replace: Boolean): Unit = {
    val file = new File(fileName)
    val temp = new File("./temp")
    val writer = new PrintWriter(temp)
    Source
      .fromFile(file)
      .getLines
      .map { line =>
        if (line.contains(identifier)) {
          if (replace) update
          else line + update
        } else line
      }
      .foreach(l => writer.println(l))

    writer.close
    temp.renameTo(file)
  }

  def replace(fileName: String, identifier: String, replacement: String): Unit =
    update(fileName, identifier, replacement, true)

  def append(fileName: String, identifier: String, appendage: String): Unit =
    update(fileName, identifier, appendage, false)

  lazy val tripQuotes: String = """""""""
}

object ScaffoldWriter {
  def update = {
    Create.update
    Database.update
    Form.update
    FromUrlForm.update
    Layout.update
    Routes.update
    Table.update
    Test.update
    Update.update
  }
}
