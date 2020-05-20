package $package$

import cats.implicits._
import cats.effect.Sync
import doobie._
import doobie.implicits._
import org.http4s._
import org.http4s.UrlForm
import play.twirl.api.Html
import java.util.UUID

final case class $class_name;format="Camel"$Id(value: UUID)
object $class_name;format="Camel"$Id {
  def apply(id: String): $class_name;format="Camel"$Id = $class_name;format="Camel"$Id(UUID.fromString(id))
  def random: $class_name;format="Camel"$Id = $class_name;format="Camel"$Id(UUID.randomUUID)
}

final case class $class_name;format="Camel"$($model_fields$, createdAt: Option[Date], updatedAt: Option[Date], id: Option[$class_name;format="Camel"$Id], userId: Option[UserId]) {
  def save[F[_]: Sync : Transactor](implicit userId: UserId): F[$class_name;format="Camel"$] = $class_name;format="Camel"$.create[F](this)
  def update[F[_]: Sync : Transactor](implicit userId: UserId): F[$class_name;format="Camel"$] = $class_name;format="Camel"$.update[F](this)
  def destroy[F[_]: Sync : Transactor](implicit userId: UserId): F[Int] = $class_name;format="Camel"$.destroy[F](this.id)

  def show: Html = $class_name;format="Camel"$.show(this)
  def showUrl: String = $class_name;format="Camel"$.showUrl(this.id)
  def edit: Html = $class_name;format="Camel"$.edit(this)
  def editUrl: String = $class_name;format="Camel"$.showUrl(this.id)
  def destroyUrl: String = $class_name;format="Camel"$.destroyUrl(this.id)
}

object $class_name;format="Camel"$
  extends $class_name;format="Camel"$Queries
  with $class_name;format="Camel"$Views {
    ATTENTION
    def fromUrlForm[F[_]: Sync](form: UrlForm): F[$class_name;format="Camel"$] =
      for {
        $model_fields$
        // name <- getValueOrRaiseError[F](form, "name")
      } yield $class_name;format="Camel"$($model_fields$)
  }

trait $class_name;format="Camel"$Queries extends Model {
  def all[F[_]: Sync](implicit userId: UserId, XA: Transactor[F]): F[List[$class_name;format="Camel"$]] =
    sql"""
      select * from $app_name;format="snake, lower"$_$class_name;format="snake, lower"$ where user_id = \${userId}
    """.query[$class_name;format="Camel"$].to[List].transact(XA)

  def find[F[_]: Sync]($class_name;format="camel"$Id: $class_name;format="Camel"$Id)(implicit userId: UserId, XA: Transactor[F]): F[$class_name;format="Camel"$] =
    sql"""
     select * from $app_name;format="snake, lower"$_$class_name;format="snake, lower"$ where id = \${$class_name;format="camel"$Id.toString} and user_id = \${userId.id}
    """.query[$class_name;format="Camel"$].unique.transact(XA)

  ATTENTION
  def create[F[_]: Sync]($class_name;format="camel"$: $class_name;format="Camel"$)(implicit userId: UserId, XA: Transactor[F]): F[$class_name;format="Camel"$] =
    sql"""
      insert into $app_name;format="snake, lower"$_$class_name;format="snake, lower"$ ($model_fields$, created_at, id, user_id)
      values
      (
        /*
        \${$model_fields$},
        */
        \${Date.now},
        \${$class_name;format="Camel"$Id.random},
        \${userId.id}
      );
    """.update.withUniqueGeneratedKeys[$class_name;format="Camel"$]($model_fields$, "created_at", "updated_at", "id", "user_id").transact(XA)

  ATTENTION
  def update[F[_]: Sync]($class_name;format="camel"$: $class_name;format="Camel"$)(implicit userId: UserId, XA: Transactor[F]): F[$class_name;format="Camel"$] =
    sql"""
      update $app_name;format="snake, lower"$_$class_name;format="snake, lower"$ set
        /*
          columns = \${$model_fields$}
        */
        updated_at = \${Date.now}
      where id = \${$class_name;format="camel"$.id}
      and user_id = \${userId.id}
    """.update.withUniqueGeneratedKeys[$class_name;format="Camel"$]($model_fields$, "created_at", "updated_at", "id", "user_id").transact(XA)

  def destroy[F[_]: Sync](id: Option[$class_name;format="Camel"$Id])(implicit userId: UserId, XA: Transactor[F]): F[Int] =
    sql"""delete from $app_name;format="snake, lower"$_$class_name;format="snake, lower"$ where id = \${id} and user_id = \${userId}""".update.run.transact(XA)
}

trait $class_name;format="Camel"$Views {
  private def getUrlOrIndex(id: Option[$class_name;format="Camel"$Id], s: $class_name;format="Camel"$Id => String) =
    id.map(s).getOrElse(indexUrl)

  def index($class_name;format="camel"$s: List[$class_name;format="Camel"$]): Html = views.html.$class_name;format="snake, lower"$.index($class_name;format="camel"$s)
  def indexUrl: String = s"""/$class_name;format="normalize"$s"""
  def show($class_name;format="camel"$: $class_name;format="Camel"$): Html = views.html.$class_name;format="snake, lower"$.show($class_name;format="camel"$)
  def showUrl(maybeId: Option[$class_name;format="Camel"$Id]): String =
    getUrlOrIndex(maybeId, id => s"""/$class_name;format="normalize"$/\${id.value.toString}""")
  def add: Html = views.html.$class_name;format="snake, lower"$.add()
  def addUrl: String = s"""/$class_name;format="normalize"$/add"""
  def createUrl: String = s"""/$class_name;format="normalize"$/create"""
  def edit($class_name;format="camel"$: $class_name;format="Camel"$): Html = views.html.$class_name;format="snake, lower"$.edit($class_name;format="camel"$)
  def editUrl(maybeId: Option[$class_name;format="Camel"$Id]): String =
    getUrlOrIndex(maybeId, id => s"""/$class_name;format="normalize"$/\${id.value.toString}/edit""")
  def updateUrl: String = s"""/$class_name;format="normalize"$/update"""
  def destroyUrl(maybeId: Option[$class_name;format="Camel"$Id]): String = showUrl(maybeId)
}
