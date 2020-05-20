package $package$

import cats.implicits._
import cats.effect.Sync
import org.http4s._
import org.http4s.UrlForm
import doobie._
import doobie.implicits._
import com.github.t3hnar.bcrypt._
import java.util.UUID

final case class $class_name;format="Camel"$Id(id: UUID) {
  override def toString = id.toString
}
object $class_name;format="Camel"$Id {
  def apply(id: String): $class_name;format="Camel"$Id = $class_name;format="Camel"$Id(UUID.fromString(id))
  def random: $class_name;format="Camel"$Id = $class_name;format="Camel"$Id(UUID.randomUUID)
}

final case class Password(get: String)
object Password {
  def encrypt(p: String): Password = Password(p.bcrypt)
}

final case class $class_name;format="Camel"$(name: String, unencPass: Password, userId: $class_name;format="Camel"$Id) {
  def id: String = userId.toString
  def password: String = unencPass.get
  def isEmpty: Boolean = name == "" && password == ""

  def save[F[_]: Sync: Transactor] =
    if (this.isEmpty) this.pure[F]
    else $class_name;format="Camel"$.create[F](this)

  def update[F[_]: Sync: Transactor] =
    $class_name;format="Camel"$.update[F](this)

  def destroy[F[_]: Sync: Transactor] =
    $class_name;format="Camel"$.destroy[F](this)
}
object $class_name;format="Camel"$ extends Model {
  private def confirmPasswordFromForm[F[_]: Sync](form: UrlForm): F[String] =
    form
      .getFirst("passwordConfirmation")
      .flatMap(pwc => form.getFirst("password").filter(_ == pwc))
      .fold(Sync[F].raiseError[String](MalformedMessageBodyFailure("Password and confirmation don't match")))(
        Sync[F].pure)

  def fromUrlForm[F[_]: Sync](form: UrlForm): F[$class_name;format="Camel"$] =
    for {
      name <- getValueOrRaiseError[F](form, "name")
      password <- confirmPasswordFromForm[F](form)
    } yield $class_name;format="Camel"$(name, Password.encrypt(password), $class_name;format="Camel"$Id.random)

  def find[F[_]: Sync](id: $class_name;format="Camel"$Id)(implicit XA: Transactor[F]): F[$class_name;format="Camel"$] =
    sql"""select * from test_app_user where id = ${id.toString}"""
      .query[$class_name;format="Camel"$]
      .unique
      .transact(XA)

  def find[F[_]: Sync](name: String)(implicit XA: Transactor[F]): F[$class_name;format="Camel"$] =
    sql"""select * from test_app_user where name = ${name}"""
      .query[$class_name;format="Camel"$]
      .unique
      .transact(XA)

  def create[F[_]: Sync](user: $class_name;format="Camel"$)(implicit XA: Transactor[F]): F[$class_name;format="Camel"$] = {
    sql"""
    insert into test_app_user (name, password, id)
    values
    (
      ${user.name},
      ${user.password},
      ${user.id}
    )
    """.update.withUniqueGeneratedKeys[$class_name;format="Camel"$]("name", "password", "id").transact(XA)
  }

  def update[F[_]: Sync](user: $class_name;format="Camel"$): Update0 =
    sql"""
      update test_app_user set
        name = ${user.name},
        password = ${user.password}
      where id = \{user.id}
      """.update

  def destroy[F[_]: Sync](user: $class_name;format="Camel"$): Update0 =
    sql"""delete from test_app_user where id = ${user.id}""".update

  def add = views.html.user.signup()
  def addUrl = "/signup"
}
