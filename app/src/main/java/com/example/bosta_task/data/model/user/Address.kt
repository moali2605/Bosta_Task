package com.example.bosta_task.data.model.user

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)