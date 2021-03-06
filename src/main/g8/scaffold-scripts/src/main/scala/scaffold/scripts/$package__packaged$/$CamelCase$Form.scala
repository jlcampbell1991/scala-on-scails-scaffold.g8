package scaffold.scripts

object $CamelCase$Form extends $CamelCase$ScaffoldWriter {
  def update = {
    replace(
      s"./$app_name$/src/main/twirl/views/$snake_case$/form.scala.html",
      s"\${UPPER_SNAKE_CASE}_FORM",
      $CamelCase$Fields.toFormElements
    )
  }
}
