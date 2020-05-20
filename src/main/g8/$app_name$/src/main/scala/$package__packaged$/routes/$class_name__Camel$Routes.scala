package $package$

import cats.implicits._
import cats.effect.Sync
import org.http4s.dsl.Http4sDsl
import org.http4s._
import org.http4s.twirl._
import doobie._

object $class_name;format="Camel"$Routes extends Routes {
  def publicRoutes[F[_]: Sync: Transactor](implicit dsl: Http4sDsl[F]): HttpRoutes[F] =
    HttpRoutes.empty

  def authedRoutes[F[_]: Sync: Transactor](implicit dsl: Http4sDsl[F]): HttpRoutes[F] = {
    import dsl._
    authedService(
      (userId: UserId) => HttpRoutes.of {
        case GET -> Root / "$class_name;format="normalize"$" / id => for {
          $class_name;format="camel"$ <- $class_name;format="Camel"$.find($class_name;format="Camel"$Id(id))
          response <- $class_name;format="camel"$.show
        } yield Ok(response)
        case GET -> Root / "$class_name;format="normalize"$" / "new"
        case req @ POST -> Root / "$class_name;format="normalize"$" / "create"
        case GET -> Root / "$class_name;format="normalize"$" / id / "edit"
        case req @ PUT  -> Root / "$class_name;format="normalize"$" / id / "update"
        case DELETE -> Root / "$class_name;format="normalize"$" / id
      }
    )
  }
}
