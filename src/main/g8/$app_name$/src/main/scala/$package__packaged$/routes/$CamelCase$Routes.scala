package $package$

import cats.implicits._
import cats.effect.Sync
import org.http4s.dsl.Http4sDsl
import org.http4s._
import org.http4s.twirl._
import doobie._

object $CamelCase$Routes extends Routes {
  def publicRoutes[F[_]: Sync: Transactor](implicit dsl: Http4sDsl[F]): HttpRoutes[F] =
    HttpRoutes.empty

  def authedRoutes[F[_]: Sync: Transactor](implicit dsl: Http4sDsl[F]): HttpRoutes[F] = {
    import dsl._
    authedService((userId: UserId) =>
      HttpRoutes.of {
        case GET -> Root / "$normalized$s" =>
          for {
            $camelCase$s <- $CamelCase$.all(userId)
            response <- Ok($CamelCase$.index($camelCase$s))
          } yield response
        case GET -> Root / "$normalized$" / "add" => Ok($CamelCase$.add)
        case GET -> Root / "$normalized$" / id =>
          for {
            $camelCase$ <- $CamelCase$.find($CamelCase$Id(id), userId)
            response <- Ok($camelCase$.show)
          } yield response

        case req @ POST -> Root / "$normalized$" / "create" =>
          for {
            form <- req.as[UrlForm]
            $camelCase$ <- $CamelCase$.fromUrlForm(form).flatMap(_.save(userId))
            response <- Redirect($camelCase$.showUrl)
          } yield response
        case GET -> Root / "$normalized$" / id / "edit" =>
          for {
            $camelCase$ <- $CamelCase$.find($CamelCase$Id(id), userId)
            response <- Ok($camelCase$.edit)
          } yield response
        case req @ PUT -> Root / "$normalized$" / id / "update" =>
          for {
            form <- req.as[UrlForm]
            $camelCase$ <- $CamelCase$
              .fromUrlForm(form)
              .map(_.copy(id = Some($CamelCase$Id(id))))
              .flatMap(_.update(userId))
            response <- Redirect($camelCase$.showUrl)
          } yield response
        case DELETE -> Root / "$normalized$" / id =>
          for {
            _ <- $CamelCase$.destroy(Some($CamelCase$Id(id)), userId)
            response <- Redirect($CamelCase$.indexUrl)
          } yield response
      }
    )
  }
}
