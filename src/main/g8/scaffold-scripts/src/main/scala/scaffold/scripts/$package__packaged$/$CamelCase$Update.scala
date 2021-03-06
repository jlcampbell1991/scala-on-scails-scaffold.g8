package scaffold.scripts

object $CamelCase$Update extends $CamelCase$ScaffoldWriter {
  def text: String = s"""
  def update[F[_]: Sync](\${camelCase}: \${CamelCase}, userId: UserId)(implicit XA: Transactor[F]): F[\${CamelCase}] =
    sql\${tripQuotes}
      update \${table_name} set
        \${$CamelCase$Fields.toSqlUpdate}
        updated_at = \$\${Date.now}
      where id = \$\${\${camelCase}.id.map(_.value)}
      and user_id = \$\${userId.id}
    \${tripQuotes}.update.withUniqueGeneratedKeys[\${CamelCase}](\${$CamelCase$Fields.toKeysQuoted}, "created_at", "updated_at", "id", "user_id").transact(XA)"""

  def update = {
    replace(
      s"./$app_name$/src/main/scala/$path$/models/\${CamelCase}.scala",
      s"\${UPPER_SNAKE_CASE}_UPDATE",
      text
    )
  }
}
