package scaffold.scripts

object Create extends ScaffoldWriter {
  val text: String = s"""
  def create[F[_]: Sync](\${camelCase}: \${CamelCase}, userId: UserId)(implicit XA: Transactor[F]): F[\${CamelCase}] =
    sql\${tripQuotes}
      insert into \${table_name} (\${Fields.toKeys}, created_at, id, user_id)
      values
      (
        \${Fields.toSqlCreate}""" + """
        \${Date.now},
        \${""" + CamelCase + """Id .random},
        \${userId.id}""" + s"""
      );
    \${tripQuotes}.update.withUniqueGeneratedKeys[\${CamelCase}](\${Fields.toKeysQuoted}, "created_at", "updated_at", "id", "user_id").transact(XA)"""

  def update = {
    replace(
      s"./$app_name$/src/main/scala/$path$/models/\${CamelCase}.scala",
      s"\${UPPER_SNAKE_CASE}_CREATE",
      text
    )
  }
}
