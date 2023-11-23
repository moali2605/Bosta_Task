package com.example.bosta_task.domain.model.user

data class UserDomainDto(
    val address: AddressDomainDto,
    val company: CompanyDomainDto,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)