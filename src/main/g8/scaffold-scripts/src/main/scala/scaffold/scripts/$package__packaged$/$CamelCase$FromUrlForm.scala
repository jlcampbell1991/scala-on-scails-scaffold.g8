package scaffold.scripts

object $CamelCase$FromUrlForm extends $CamelCase$ScaffoldWriter {
  val text: String =
    s"""
  def fromUrlForm[F[_]: Sync](form: UrlForm): F[\${CamelCase}] =
    for {
\${$CamelCase$Fields.toUrlForComp}
} yield \${CamelCase}(\${$CamelCase$Fields.toKeys}, None, None, None, None)
"""
  def update = {
    replace(
      s"./$app_name$/src/main/scala/$path$/models/\${CamelCase}.scala",
      s"\${UPPER_SNAKE_CASE}_FROM_URL_FORM",
      text
    )
  }
}
