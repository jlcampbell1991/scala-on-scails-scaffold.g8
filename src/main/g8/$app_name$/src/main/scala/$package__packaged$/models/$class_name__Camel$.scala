package $package$

import cats.implicits._
import cats.effect.Sync
import doobie._
import doobie.implicits._
import org.http4s._
import org.http4s.UrlForm
import java.util.UUID

final case class $class_name;format="Camel"$Id(id: UUID) {
  override def toString = id.toString
}
object $class_name;format="Camel"$Id {
  def apply(id: String): $class_name;format="Camel"$Id = $class_name;format="Camel"$Id(UUID.fromString(id))
  def random: $class_name;format="Camel"$Id = $class_name;format="Camel"$Id(UUID.randomUUID)
}

final case class $class_name;format="Camel"$($model_fields$, createdAt: Option[Date], updatedAt: Option[Date], id: Option[$class_name;format="Camel"$Id], userId: Option[UserId])
