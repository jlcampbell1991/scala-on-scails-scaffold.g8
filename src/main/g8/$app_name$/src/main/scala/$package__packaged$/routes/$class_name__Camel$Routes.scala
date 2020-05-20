package $package$

import cats.implicits._
import cats.effect.Sync
import org.http4s.dsl.Http4sDsl
import org.http4s._
import org.http4s.twirl._
import doobie._

ATTENTION
/**
  After generating scaffold, combine these routes to
  `PublicRoutes` or `AuthRoutes` in /routes/Routes.scala
*/

object $class_name;format="Camel"$Routes extends Routes {
  def publicRoutes[F[_]: Sync: Transactor](implicit dsl: Http4sDsl[F]): HttpRoutes[F] =
    HttpRoutes.empty

  def authedRoutes[F[_]: Sync: Transactor](implicit dsl: Http4sDsl[F]): HttpRoutes[F] = {
    import dsl._
    authedService(
      (userId: UserId) => HttpRoutes.of {
        case GET -> Root / "$class_name;format="normalize"$s" => for {
          $class_name;format="camel"$s <- $class_name;format="Camel"$.all
          response <- $class_name;format="Camel"$.index($class_name;format="camel"$s)
        } yield response
        case GET -> Root / "$class_name;format="normalize"$" / id => for {
          $class_name;format="camel"$ <- $class_name;format="Camel"$.find($class_name;format="Camel"$Id(id))
          response <- $class_name;format="camel"$.show
        } yield Ok(response)
        case GET -> Root / "$class_name;format="normalize"$" / "add" => Ok($class_name;format="Camel"$.add)
        case req @ POST -> Root / "$class_name;format="normalize"$" / "create" => for {
          form <- req.as[UrlForm]
          $class_name;format="camel"$ <- $class_name;format="Camel"$.fromUrlForm(form).map(_.save)
          response <- Redirect($class_name;format="camel"$.showUrl)
        } yield response
        case GET -> Root / "$class_name;format="normalize"$" / id / "edit" => for {
          $class_name;format="camel"$ <- $class_name;format="Camel"$.find($class_name;format="Camel"$Id(id))
          response <- Ok($class_name;format="camel"$.edit)
        } yield response
        case req @ PUT  -> Root / "$class_name;format="normalize"$" / id / "update" => for {
          form <- req.as[UrlForm]
          $class_name;format="camel"$ <- $class_name;format="Camel"$.fromUrlForm(form).map(_.update)
          response <- Redirect($class_name;format="camel"$.showUrl)
        } yield response
        case DELETE -> Root / "$class_name;format="normalize"$" / id => for {
          _ <- $class_name;format="Camel"$.destroy($class_name;format="Camel"$Id(id))
          response <- Redirect($class_name;format="Camel"$.indexUrl)
        } yield response
      }
    )
  }
}
