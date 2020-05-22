package scaffold.scripts

object Routes extends ScaffoldWriter {
  def update = append(
    "./$app_name$/src/main/scala/$path$/routes/Routes.scala",
    "SessionRoutes.authedRoutes[F]",
    s""" <+> \${CamelCase}Routes.authedRoutes[F]"""
  )
}
