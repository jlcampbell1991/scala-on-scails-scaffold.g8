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

object $CamelCase$Routes extends Routes {
  def publicRoutes[F[_]: Sync: Transactor](implicit dsl: Http4sDsl[F]): HttpRoutes[F] =
    HttpRoutes.empty

  def authedRoutes[F[_]: Sync: Transactor](implicit dsl: Http4sDsl[F]): HttpRoutes[F] = {
    import dsl._
    authedService(
      (userId: UserId) => HttpRoutes.of {
        case GET -> Root / "$normal-name$s" => for {
          $camelCase$s <- $CamelCase$.all(userId)
          response <- Ok($CamelCase$.index($camelCase$s))
        } yield response
        case GET -> Root / "$normal-name$" / id => for {
          $camelCase$ <- $CamelCase$.find($CamelCase$Id(id), userId)
          response <- Ok($camelCase$.show)
        } yield response
        case GET -> Root / "$normal-name$" / "add" => Ok($CamelCase$.add)
        case req @ POST -> Root / "$normal-name$" / "create" => for {
          form <- req.as[UrlForm]
          $camelCase$ <- $CamelCase$.fromUrlForm(form).flaMap(_.save(userId))
          response <- Redirect($camelCase$.showUrl)
        } yield response
        case GET -> Root / "$normal-name$" / id / "edit" => for {
          $camelCase$ <- $CamelCase$.find($CamelCase$Id(id), userId)
          response <- Ok($camelCase$.edit)
        } yield response
        case req @ PUT  -> Root / "$normal-name$" / "update" => for {
          form <- req.as[UrlForm]
          $camelCase$ <- $CamelCase$.fromUrlForm(form).flatMap(_.update(userId))
          response <- Redirect($camelCase$.showUrl)
        } yield response
        case DELETE -> Root / "$normal-name$" / id => for {
          _ <- $CamelCase$.destroy(Some($CamelCase$Id(id)), userId)
          response <- Redirect($CamelCase$.indexUrl)
        } yield response
      }
    )
  }
}
