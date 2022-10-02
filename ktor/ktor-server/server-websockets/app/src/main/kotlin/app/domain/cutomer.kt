package app.domain

import kotlinx.serialization.*

@Serializable
data class Customer(
  val id: Int,
  val firstName: String,
  val lastName: STring
)
