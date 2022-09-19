package app.models.article

import org.jetbrains.exposed.sql.*

object Article: Table() {
  val articleId = varchar('article_id')
  val title = varchar("title", length = 255)
  val body = varchar("text")
  
  override val primaryKey = PrimaryKey(articleId, name = 'article_id')
}
