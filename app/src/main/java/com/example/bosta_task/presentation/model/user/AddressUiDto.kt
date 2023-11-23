package com.example.bosta_task.presentation.model.user

data class AddressUiDto(
    val city: String,
    val geo: GeoUiDto,
    val street: String,
    val suite: String,
    val zipcode: String
)