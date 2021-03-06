package scaffold.scripts

object $CamelCase$Test extends $CamelCase$ScaffoldWriter {
  def text = s"""
  val user: User = User(\${FieldString.toTestDefault}, Password(\${FieldString.toTestDefault}), userId).save[IO].unsafeRunSync

  val cookie: RequestCookie = Session.requestCookie(user)

  val \${camelCase}Form: UrlForm = UrlForm(\${$CamelCase$Fields.toUrlFormDefaults})

  val \${camelCase}: \${CamelCase} = \${CamelCase}(\${$CamelCase$Fields.toTestDefaults}, None, None, Some(\${CamelCase}Id.random), Some(userId)).save[IO](userId).unsafeRunSync"""

  def update = {
    replace(
      s"./$app_name$/src/test/scala/$path$/\${CamelCase}RoutesTest.scala",
      s"\${UPPER_SNAKE_CASE}_TEST",
      text
    )
  }
}
