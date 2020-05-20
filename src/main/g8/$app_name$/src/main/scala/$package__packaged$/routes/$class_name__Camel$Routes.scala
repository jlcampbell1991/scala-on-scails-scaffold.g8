package $package$

import cats.implicits._
import cats.effect.Sync
import org.http4s.dsl.Http4sDsl
import org.http4s._
import org.http4s.twirl._
import doobie._

object $class_name;format="Camel"$Routes extends Routes {
  def publicRoutes[F[_]: Sync: Transactor](implicit dsl: Http4sDsl[F]): HttpRoutes[F] = {
    import dsl._

    HttpRoutes.of {
      case GET -> Root / "login" => Ok(Session.login)
      case params @ POST -> Root / "login" => {
          for {
            form <- params.as[UrlForm]
            session <- Session.fromUrlForm(form)
            user <- session.findUser
            response <- user.fold(BadRequest(Session.login))(_ => Ok(Session.login))
          } yield response
        }.handleErrorWith { case _: MalformedMessageBodyFailure => BadRequest(Session.login) }
      case GET -> Root / "logout" =>
        for {
          response <- Redirect(Session.loginUrl)
        } yield response.removeCookie(Session.COOKIE_NAME)
    }
  }

  def authedRoutes[F[_]: Sync: Transactor](implicit dsl: Http4sDsl[F]): HttpRoutes[F] = {
    import dsl._
    authedService((_: UserId) => HttpRoutes.empty)
  }
}