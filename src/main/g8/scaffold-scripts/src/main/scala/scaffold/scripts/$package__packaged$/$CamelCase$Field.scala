package scaffold.scripts

object $CamelCase$Fields extends $CamelCase$ScaffoldWriter {
  private def asList: List[Field] =
    model_fields.split(",").toList.map { s =>
      val _s = s.replaceAll(" ", "").split(":").toList
      Field(_s(0), FieldType.fromString(_s(1)))
    }

  private def foldLeft(addend: Field => String)(replace: String): String =
    asList.foldLeft("")((str, field) => str ++ addend(field)).replaceFirst(replace, "")

  def toSql: String =
    foldLeft(field => s"""\n      \${field.toSql},""")("\n")

  def toSqlCreate: String =
    foldLeft(field => s"""\n        \$\${\${camelCase}.\${field.key}},""")("\n        ")

  def toSqlUpdate: String =
    foldLeft(field => s"""\n        \${field.key} = \$\${\${camelCase}.\${field.key}},""")("\n        ")

  def toUrlForComp: String =
    foldLeft(field =>
      s"""\n      \${field.key} <- getValueOrRaiseError[F](form, "\${field.key}")"""
    )("\n")

  def toKeys: String =
    foldLeft(field => s""", \${field.key}""")(", ")

  def toKeysQuoted: String =
    foldLeft(field => s""", "\${field.key}"""")(", ")

  def toLayoutTds: String =
    foldLeft(field => s"""\n      <td>@\${camelCase}.\${field.key}</td>""")("\n")

  def toLayoutThs: String =
    foldLeft(field => s"""\n      <th>\${field.key.capitalize}</th>""")("\n")

  def toFormElements: String =
    foldLeft(field => s"""\n    \${field.toFormElement}""")("\n")

  def toTestDefaults: String =
    foldLeft(field => s""", \${field.value.toTestDefault}""")(", ")

  def toUrlFormDefaults: String =
    foldLeft(field => s""", ("\${field.key}", \${field.value.toTestDefault})""")(", ")
}
