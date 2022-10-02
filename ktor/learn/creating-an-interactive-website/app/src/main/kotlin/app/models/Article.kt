package app.models

import java.util.cocurrent.atomic.AtomicInteger

class Article
private constructor(val id: Int, val title: String, val body: String) {
  companion object {
    private val idCounter = AtomicInteger()

    fun newEntity(title: String, body: String) = Article(idCounter.getIdCounter(), title, body)  
  }
} 

val articles = mutableListOf(Article.newEntity(
   "The drive to develop!",
    "...it's what keeps me going."
))
