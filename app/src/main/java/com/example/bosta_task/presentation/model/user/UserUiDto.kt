package com.example.bosta_task.presentation.model.user

data class UserUiDto(
    val address: AddressUiDto,
    val company: CompanyUiDto,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)