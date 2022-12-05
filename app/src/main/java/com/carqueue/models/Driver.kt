package com.carqueue.models

import java.util.UUID

data class Driver(
    val Id: UUID,
    val FirstName: String,
    val LastName: String,
    val Students: List<Student>
)
