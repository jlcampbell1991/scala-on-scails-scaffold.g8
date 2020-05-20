package $package$

import cats.effect.IO
import org.http4s._
import org.http4s.circe.CirceEntityCodec._
import org.http4s.implicits._
import org.http4s.twirl._

final class $class_name;format="Camel"$RoutesTest extends BaseTest {
  ATTENTION
  val $class_name;format="camel"$Form: UrlForm = Urlform($model_fields$)
  val $class_name;format="camel"$: $class_name;format="Camel"$ = $class_name;format="Camel"$($model_fields$).save[IO].unsafeRunSync

  """GET -> Root / "$class_name;format="normalize"$s"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.uri($class_name;format="Camel"$.indexUrl))
        ),
      Status.Ok,
      None
    )
  }
  """GET -> Root / "$class_name;format="normalize"$""" / id" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.uri($class_name;format="camel"$.showUrl))
        ),
      Status.Ok,
      None
    )
  }
  """GET -> Root / "$class_name;format="normalize"$" / "add"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.uri($class_name;format="Camel"$.addUrl))
        ),
      Status.Ok,
      None
    )
  }
  """POST -> Root / "$class_name;format="normalize"$" / "create"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.POST, uri = Uri.uri($class_name;format="Camel"$.createUrl)).withEntity(
            $class_name;format="camel"$Form
          )
        ),
      Status.Ok,
      None
    )
  }
  """GET -> Root / "$class_name;format="normalize"$" / id / "edit"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.GET, uri = Uri.uri($class_name;format="Camel"$.editUrl))
        ),
      Status.Ok,
      None
    )
  }
  """PUT  -> Root / "$class_name;format="normalize"$" / id / "update"""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.PUT, uri = Uri.uri($class_name;format="Camel"$.updateUrl)).withEntity(
            $class_name;format="camel"$Form
          )
        ),
      Status.Ok,
      None
    )
  }
  """DELETE -> Root / "$class_name;format="normalize"$" / id""" in {
    check[String](
      service.orNotFound
        .run(
          Request(method = Method.DELETE, uri = Uri.uri($class_name;format="camel"$.destroyUrl)).withEntity(
            $class_name;format="camel"$Form
          )
        ),
      Status.Ok,
      None
    )
  }

}
