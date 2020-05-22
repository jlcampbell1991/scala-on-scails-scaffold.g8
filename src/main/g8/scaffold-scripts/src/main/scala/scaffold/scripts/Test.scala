package scaffold.scripts

object Test extends ScaffoldWriter {
  def text = s"""
  val user: User = User(\${FieldString.toTestDefault}, Password(\${FieldString.toTestDefault}), userId).save[IO].unsafeRunSync

  val cookie: RequestCookie = Session.requestCookie(user)

  val \${camelCase}Form: UrlForm = UrlForm(\${Fields.toUrlFormDefaults})

  val \${camelCase}: \${CamelCase} = \${CamelCase}(\${Fields.toTestDefaults}, None, None, Some(\${CamelCase}.Id.random), Some(userId)).save[IO](userId).unsafeRunSync"""

  def update = {
    replace(
      s"./$app_name$/src/test/scala/$path$/RoutesTest.scala",
      s"\${UPPER_SNAKE_CASE}_TEST",
      text
    )
  }
}
