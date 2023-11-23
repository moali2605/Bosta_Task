package com.example.bosta_task.domain.model.user

data class AddressDomainDto(
    val city: String,
    val geo: GeoDomainDto,
    val street: String,
    val suite: String,
    val zipcode: String
)