package $package$

import cats.effect.IO
import org.http4s._
import org.http4s.circe.CirceEntityCodec._
import org.http4s.implicits._
import org.http4s.twirl._
import org.http4s.UrlForm

final class $CamelCase$RoutesTest extends BaseTest {
  import DBDriver.XA

  val userId: UserId = UserId.random

$UPPER_SNAKE_CASE$_TEST

  """GET -> Root / "$normalized$s"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.unsafeFromString($CamelCase$.indexUrl)).addCookie(cookie)
        ),
      Status.Ok,
      None
    )
  }
  """GET -> Root / "$normalized$/ id"""" in {
    println($camelCase$.updateUrl)
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.unsafeFromString($camelCase$.showUrl)).addCookie(cookie)
        ),
      Status.Ok,
      None
    )
  }
  """GET -> Root / "$normalized$" / "add"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.unsafeFromString($CamelCase$.addUrl)).addCookie(cookie)
        ),
      Status.Ok,
      None
    )
  }
  """POST -> Root / "$normalized$" / "create"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.POST, uri = Uri.unsafeFromString($CamelCase$.createUrl))
            .addCookie(cookie)
            .withEntity(
              $camelCase$Form
            )
        ),
      Status.SeeOther,
      None
    )
  }
  """GET -> Root / "$normalized$" / id / "edit"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.unsafeFromString($camelCase$.editUrl)).addCookie(cookie)
        ),
      Status.Ok,
      None
    )
  }
  """POST  -> Root / "$normalized$" / id / "update"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.POST, uri = Uri.unsafeFromString($camelCase$.updateUrl))
            .addCookie(cookie)
            .withEntity(
              $camelCase$Form
            )
        ),
      Status.SeeOther,
      None
    )
  }
  """DELETE -> Root / "$normalized$" / id""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.DELETE, uri = Uri.unsafeFromString($camelCase$.destroyUrl))
            .addCookie(cookie)
            .withEntity(
              $camelCase$Form
            )
        ),
      Status.SeeOther,
      None
    )
  }
}
