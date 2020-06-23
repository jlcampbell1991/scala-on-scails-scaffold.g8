package scaffold.scripts

object $CamelCase$Layout extends $CamelCase$ScaffoldWriter {
  def update = {
    replace(
      s"./$app_name$/src/main/twirl/views/$snake_case$/layout.scala.html",
      s"\${UPPER_SNAKE_CASE}_LAYOUT_TD",
      $CamelCase$Fields.toLayoutTds
    )

    replace(
      s"./$app_name$/src/main/twirl/views/$snake_case$/layout.scala.html",
      s"\${UPPER_SNAKE_CASE}_LAYOUT_TH",
      $CamelCase$Fields.toLayoutThs
    )
  }
}
