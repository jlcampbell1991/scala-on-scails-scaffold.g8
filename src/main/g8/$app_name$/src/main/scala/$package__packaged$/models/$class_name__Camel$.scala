package $package$

import cats.implicits._
import cats.effect.Sync
import doobie._
import doobie.implicits._
import org.http4s._
import org.http4s.UrlForm
import play.twil.api.Html
import java.util.UUID

final case class $class_name;format="Camel"$Id(value: UUID) {
  override def toString = id
}
object $class_name;format="Camel"$Id {
  def apply(id: String): $class_name;format="Camel"$Id = $class_name;format="Camel"$Id(UUID.fromString(id))
  def random: $class_name;format="Camel"$Id = $class_name;format="Camel"$Id(UUID.randomUUID)
}

final case class $class_name;format="Camel"$($model_fields$, createdAt: Option[Date], updatedAt: Option[Date], id: Option[$class_name;format="Camel"$Id], userId: Option[UserId]) {
  def show: Html = $class_name;format="Camel"$.show(this)
  def showUrl: String = $class_name;format="Camel"$.show(this.id)
  def edit: Html = $class_name;format="Camel"$.edit(this)
  def editUrl: String = $class_name;format="Camel"$.showUrl(this.id)
}

object $class_name;format="Camel"$ extends $class_name;format="Camel"$Queries
  with $class_name;format="Camel"$Views

trait $class_name;format="Camel"$Queries {
  def all[F[_]: Sync](implicit userId: UserId, XA: Transactor[F]): F[List[$class_name;format="Camel"$]] =
    sql"""
      select * from $app_name;format="snake, lower"$_$class_name;format="camel"$ where user_id = \${userId}
    """.query[$class_name;format="Camel"$].to[List].transact(XA)

  def find[F[_]: Sync]($class_name;format="camel"$Id: $class_name;format="Camel"$Id)(implicit userId: UserId, XA: Transactor[F]): F[$class_name;format="Camel"$] =
    sql"""
     select * from $app_name;format="snake, lower"$_$class_name;format="camel"$ where id = \${$class_name;format="camel"$Id.toString} and user_id = \${userId.toString}
    """.query[$class_name;format="Camel"$].unique.transact(XA)

  def create[F[_]: Sync]($class_name;format="camel"$: $class_name;format="Camel"$)(implicit userId: UserId, XA: Transactor[F]): F[$class_name;format="Camel"$] =
    sql"""
      insert into $app_name;format="snake, lower"$_$class_name;format="camel"$ ($model_columns$, created_at, id, user_id)
      values
      (
        /*
        \${$model_columns$},
        */
        \${Date.now},
        \${$class_name;format="Camel"$Id.random},
        \${userId.toString}
      );
    """.update.withUniqueGeneratedKeys[$class_name;format="Camel"$]($model_columns$, "created_at", "updated_at", "id", "user_id").transact(XA)

  def update[F[_]: Sync]($class_name;format="camel"$: $class_name;format="Camel"$)(implicit userId: UserId, XA: Transactor[F]): F[$class_name;format="Camel"$] =
    sql"""
      update $app_name;format="snake, lower"$_$class_name;format="camel"$ set
        /*
          columns = \${$model_columns$}
        */
        updated_at = \${Date.now}
      where id = \${$class_name;format="camel"$.id}
      and user_id = \${userId.toString}
    """.update.withUniqueGeneratedKeys[$class_name;format="Camel"$]($model_columns$, "created_at", "updated_at", "id", "user_id").transact(XA)

  def destroy[F[_]: Sync](id: Option[$class_name;format="Camel"$Id])(implicit userId: UserId, XA: Transactor[F]): F[Int] =
    sql"""delete from $app_name;format="snake, lower"$_$class_name;format="camel"$ where id = \${id} and user_id = \${userId}""".update.run.transact(XA)
}

trait $class_name;format="Camel"$Views {
  def index($class_name;format="camel"$s: List[$class_name;format="Camel"$]): Html = views.$class_name;format="normalize"$.index($class_name;format="camel"$s)
  def indexUrl: String = s"""/\${$class_name;format="normaliz"$s}"""
  def show($class_name;format="camel"$: $class_name;format="Camel"$): Html = views.$class_name;format="normalize"$.show($class_name;format="camel"$)
  def showUrl(id: $class_name;format="Camel"Id): String = s"""/\${$class_name;format="normaliz"$s}/\${id.value.toString}"""
  def edit($class_name;format="camel"$: $class_name;format="Camel"$): Html = views.$class_name;format="normalize"$.edit($class_name;format="camel"$)
  def editUrl(id: $class_name;format="Camel"Id): String = s"""/\${$class_name;format="normaliz"$s}/\${id.value.toString}/edit"""
}
