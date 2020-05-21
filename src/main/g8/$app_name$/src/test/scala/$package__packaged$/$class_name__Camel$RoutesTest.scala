package $package$

import cats.effect.IO
import org.http4s._
import org.http4s.circe.CirceEntityCodec._
import org.http4s.implicits._
import org.http4s.twirl._
import org.http4s.UrlForm

final class $CamelCase$RoutesTest extends BaseTest {
  ATTENTION
  import DBDriver.XA
  val userId: UserId = UserId.random
  val $camelCase$Form: UrlForm = UrlForm($model_fields$)
  val $camelCase$: $CamelCase$ = $CamelCase$($model_fields$).save[IO](userId).unsafeRunSync

  """GET -> Root / "$normal-name$s"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.unsafeFromString($CamelCase$.indexUrl))
        ),
      Status.Ok,
      None
    )
  }
  """GET -> Root / "$normal-name$/ id"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.unsafeFromString($camelCase$.showUrl))
        ),
      Status.Ok,
      None
    )
  }
  """GET -> Root / "$normal-name$" / "add"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.unsafeFromString($CamelCase$.addUrl))
        ),
      Status.Ok,
      None
    )
  }
  """POST -> Root / "$normal-name$" / "create"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.POST, uri = Uri.unsafeFromString($CamelCase$.createUrl)).withEntity(
            $camelCase$Form
          )
        ),
      Status.SeeOther,
      None
    )
  }
  """GET -> Root / "$normal-name$" / id / "edit"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.unsafeFromString($camelCase$.editUrl))
        ),
      Status.Ok,
      None
    )
  }
  """PUT  -> Root / "$normal-name$" / id / "update"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.PUT, uri = Uri.unsafeFromString($CamelCase$.updateUrl)).withEntity(
            $camelCase$Form
          )
        ),
      Status.SeeOther,
      None
    )
  }
  """DELETE -> Root / "$normal-name$" / id""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.DELETE, uri = Uri.unsafeFromString($camelCase$.destroyUrl)).withEntity(
            $camelCase$Form
          )
        ),
      Status.SeeOther,
      None
    )
  }

}
