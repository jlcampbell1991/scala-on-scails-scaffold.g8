package $package$

import cats.implicits._
import cats.effect.Sync
import doobie._
import doobie.implicits._
import org.http4s._
import org.http4s.UrlForm
import play.twirl.api.Html
import java.util.UUID

final case class $CamelCase$Id(value: UUID)
object $CamelCase$Id {
  def apply(id: String): $CamelCase$Id = $CamelCase$Id(UUID.fromString(id))
  def random: $CamelCase$Id = $CamelCase$Id(UUID.randomUUID)
}

final case class $CamelCase$(
    $model_fields$,
    createdAt: Option[Date],
    updatedAt: Option[Date],
    id: Option[$CamelCase$Id],
    userId: Option[UserId]
) {
  def save[F[_]: Sync: Transactor](userId: UserId): F[$CamelCase$] = $CamelCase$.create[F](this, userId)

  def update[F[_]: Sync: Transactor](userId: UserId): F[$CamelCase$] = $CamelCase$.update[F](this, userId)

  def destroy[F[_]: Sync: Transactor](userId: UserId): F[Int] = $CamelCase$.destroy[F](this.id, userId)

  def show: Html = $CamelCase$.show(this)

  def showUrl: String = $CamelCase$.showUrl(this.id)

  def edit: Html = $CamelCase$.edit(this)

  def editUrl: String = $CamelCase$.editUrl(this.id)

  def updateUrl: String = $CamelCase$.updateUrl(this.id)

  def destroyUrl: String = $CamelCase$.destroyUrl(this.id)
}

object $CamelCase$ extends Model with $CamelCase$Queries with $CamelCase$Views {
$UPPER_SNAKE_CASE$_FROM_URL_FORM
}

trait $CamelCase$Queries extends Queries {
  def all[F[_]: Sync](userId: UserId)(implicit XA: Transactor[F]): F[List[$CamelCase$]] =
    sql"""
      select * from $table_name$ where user_id = \${userId}
    """.query[$CamelCase$].to[List].transact(XA)

  def find[F[_]: Sync]($camelCase$Id: $CamelCase$Id, userId: UserId)(implicit XA: Transactor[F]): F[$CamelCase$] =
    sql"""
     select * from $table_name$ where id = \${$camelCase$Id.value} and user_id = \${userId.id}
    """.query[$CamelCase$].unique.transact(XA)
$UPPER_SNAKE_CASE$_CREATE
$UPPER_SNAKE_CASE$_UPDATE

  def destroy[F[_]: Sync](id: Option[$CamelCase$Id], userId: UserId)(implicit XA: Transactor[F]): F[Int] =
    sql"""delete from $table_name$ where id = \${id} and user_id = \${userId.id}""".update.run.transact(XA)
}

trait $CamelCase$Views extends Views {
  def default: String = indexUrl

  def index($camelCase$s: List[$CamelCase$]): Html = views.html.$snake_case$.index($camelCase$s)

  def indexUrl: String = s"""/$normalized$s"""

  def show($camelCase$: $CamelCase$): Html = views.html.$snake_case$.show($camelCase$)

  def showUrl(maybeId: Option[$CamelCase$Id]): String =
    getUrlOrDefault[$CamelCase$Id](maybeId, id => s"""/$normalized$/\${id.value.toString}""")

  def add: Html = views.html.$snake_case$.add()

  def addUrl: String = s"""/$normalized$/add"""

  def createUrl: String = s"""/$normalized$/create"""

  def edit($camelCase$: $CamelCase$): Html = views.html.$snake_case$.edit($camelCase$)

  def editUrl(maybeId: Option[$CamelCase$Id]): String =
    getUrlOrDefault[$CamelCase$Id](maybeId, id => s"""/$normalized$/\${id.value.toString}/edit""")

  def updateUrl(maybeId: Option[$CamelCase$Id]): String =
    getUrlOrDefault[$CamelCase$Id](maybeId, id => s"""/$normalized$/\${id.value.toString}/update""")

  def destroyUrl(maybeId: Option[$CamelCase$Id]): String =
    getUrlOrDefault[$CamelCase$Id](maybeId, id => s"""/$normalized$/\${id.value.toString}/destroy""")
}
