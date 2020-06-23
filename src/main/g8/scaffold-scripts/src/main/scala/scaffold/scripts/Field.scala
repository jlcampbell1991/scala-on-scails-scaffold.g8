package scaffold.scripts

// TODO: have this extends $CamelCase$ScaffoldWriter so we can have class name and then
// insert default value for toFormElement
sealed trait FieldType {
  def toScala: String
  def toSql: String
  def toFormElement(key: String): String
  def toTestDefault: String
}
object FieldType {
  private def regex(s: String): String = {
    val _regex = s"""option\\\\[(.*)\\\\]""".r
    _regex.findFirstIn(s).map(_.replaceAll("\\\\[", "").replaceAll("\\\\]", "")).getOrElse("string")
  }

  def fromString(s: String): FieldType = s.toLowerCase match {
    case "int" => FieldInt
    case "string" => FieldString
    case "boolean" => FieldBoolean
    case "bool" => FieldBoolean
    case "double" => FieldDouble
    case "uuid" => FieldUUID
    case "date" => FieldDate
    case _s: String if (_s.contains("option")) => FieldOption(fromString(regex(_s)))
  }
}
case object FieldInt extends FieldType {
  def toScala: String = "Int"
  def toSql: String = "INTEGER"
  def toFormElement(key: String): String =
    s"""\${key}: <input type="number" name="\${key}">"""
  def toTestDefault: String = "1"
}
case object FieldString extends FieldType {
  def toScala: String = "String"
  def toSql: String = "VARCHAR"
  def toFormElement(key: String): String =
    s"""\${key}: <input type="text" name="\${key}">"""
  def toTestDefault: String = s""""\${java.util.UUID.randomUUID.toString}""""
}
case object FieldBoolean extends FieldType {
  def toScala: String = "Boolean"
  def toSql: String = "BOOLEAN"
  def toFormElement(key: String): String =
    s"""\${key}: <input type="checkbox" name="\${key}">"""
  def toTestDefault: String = "true"
}
case object FieldDouble extends FieldType {
  def toScala: String = "Double"
  def toSql: String = "DOUBLE PRECISION"
  def toFormElement(key: String): String =
    s"""\${key}: <input type="number" name="\${key}" step="0.01">"""
  def toTestDefault: String = "1.0"
}
case object FieldUUID extends FieldType {
  def toScala: String = "UUID"
  def toSql: String = "VARCHAR"
  def toFormElement(key: String): String =
    s"""\${key}: <input type="text" name="\${key}">"""
  def toTestDefault: String = "java.util.UUID.randomUUID"
}
case object FieldDate extends FieldType {
  def toScala: String = "Date"
  def toSql: String = "TIMESTAMP"
  def toFormElement(key: String): String =
    s"""\${key}: <input type="date" name="\${key}">"""
  def toTestDefault: String = "Date.now"
}
case class FieldOption(fieldType: FieldType) extends FieldType {
  def toScala: String = s"""Option[\${fieldType.toScala}]"""
  def toSql: String = fieldType.toSql
  def toFormElement(key: String): String = fieldType.toFormElement(key)
  def toTestDefault: String = s"""Some(\${fieldType.toTestDefault})"""
}
final case class Field(key: String, value: FieldType) {
  def toScala: String = s"""\${key}: \${value.toScala}"""
  def toSql: String = s"""\${key} \${value.toSql}"""
  def toFormElement: String = value.toFormElement(key)
}
object Fields extends $CamelCase$ScaffoldWriter {
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
