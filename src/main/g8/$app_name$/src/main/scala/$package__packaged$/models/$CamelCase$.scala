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

  def editUrl: String = $CamelCase$.showUrl(this.id)

  def updateUrl: String = $CamelCase$.updateUrl(this.id)

  def destroyUrl: String = $CamelCase$.destroyUrl(this.id)
}

object $CamelCase$ extends $CamelCase$Queries with $CamelCase$Views {
  def fromUrlForm[F[_]: Sync](form: UrlForm): F[$CamelCase$] =
    for {
      name <- getValueOrRaiseError[F](form, "name")
    } yield $CamelCase$(name, None, None, None, None)
}

trait $CamelCase$Queries extends Model {
  def all[F[_]: Sync](userId: UserId)(implicit XA: Transactor[F]): F[List[$CamelCase$]] =
    sql"""
      select * from test_app_round_spiral where user_id = \${userId}
    """.query[$CamelCase$].to[List].transact(XA)

  def find[F[_]: Sync]($camelCase$Id: $CamelCase$Id, userId: UserId)(implicit XA: Transactor[F]): F[$CamelCase$] =
    sql"""
     select * from test_app_round_spiral where id = \${$camelCase$Id.value} and user_id = \${userId.id}
    """.query[$CamelCase$].unique.transact(XA)

  ATTENTION
  def create[F[_]: Sync]($camelCase$: $CamelCase$, userId: UserId)(implicit XA: Transactor[F]): F[$CamelCase$] =
    sql"""
      insert into test_app_round_spiral (name, created_at, id, user_id)
      values
      (
        \${$model_fields$},
        \${Date.now},
        \${$CamelCase$Id.random},
        \${userId.id}
      );
    """.update.withUniqueGeneratedKeys[$CamelCase$]("name", "created_at", "updated_at", "id", "user_id").transact(XA)

  ATTENTION
  def update[F[_]: Sync]($camelCase$: $CamelCase$, userId: UserId)(implicit XA: Transactor[F]): F[$CamelCase$] =
    sql"""
      update test_app_round_spiral set
          column = \${$model_fields$},
        updated_at = \${Date.now}
      where id = \${$camelCase$.id.map(_.value)}
      and user_id = \${userId.id}
    """.update.withUniqueGeneratedKeys[$CamelCase$]("name", "created_at", "updated_at", "id", "user_id").transact(XA)

  def destroy[F[_]: Sync](id: Option[$CamelCase$Id], userId: UserId)(implicit XA: Transactor[F]): F[Int] =
    sql"""delete from test_app_round_spiral where id = \${id} and user_id = \${userId.id}""".update.run.transact(XA)
}

trait $CamelCase$Views {
  private def getUrlOrIndex(id: Option[$CamelCase$Id], s: $CamelCase$Id => String) =
    id.map(s).getOrElse(indexUrl)

  def index($camelCase$s: List[$CamelCase$]): Html = views.html.round_spiral.index($camelCase$s)

  def indexUrl: String = s"""/$normalized$s"""

  def show($camelCase$: $CamelCase$): Html = views.html.round_spiral.show($camelCase$)

  def showUrl(maybeId: Option[$CamelCase$Id]): String =
    getUrlOrIndex(maybeId, id => s"""/$normalized$/\${id.value.toString}""")

  def add: Html = views.html.round_spiral.add()

  def addUrl: String = s"""/$normalized$/add"""

  def createUrl: String = s"""/$normalized$/create"""

  def edit($camelCase$: $CamelCase$): Html = views.html.round_spiral.edit($camelCase$)

  def editUrl(maybeId: Option[$CamelCase$Id]): String =
    getUrlOrIndex(maybeId, id => s"""/$normalized$/\${id.value.toString}/edit""")

  def updateUrl(maybeId: Option[$CamelCase$Id]): String =
    getUrlOrIndex(maybeId, id => s"""/$normalized$/\${id.value.toString}/update""")

  def destroyUrl(maybeId: Option[$CamelCase$Id]): String = showUrl(maybeId)
}
