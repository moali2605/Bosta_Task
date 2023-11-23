package com.example.bosta_task.presentation.mapper

import com.example.bosta_task.domain.model.user.AddressDomainDto
import com.example.bosta_task.domain.model.user.CompanyDomainDto
import com.example.bosta_task.domain.model.user.GeoDomainDto
import com.example.bosta_task.domain.model.user.UserDomainDto
import com.example.bosta_task.presentation.model.user.AddressUiDto
import com.example.bosta_task.presentation.model.user.CompanyUiDto
import com.example.bosta_task.presentation.model.user.GeoUiDto
import com.example.bosta_task.presentation.model.user.UserUiDto

fun UserDomainDto.toUserUiDto(): UserUiDto =
    UserUiDto(
        name = name,
        address = address.toAddressUiDto(),
        company = company.toCompanyUiDto(),
        email = email,
        id = id,
        phone = phone,
        username = username,
        website = website
    )

private fun CompanyDomainDto.toCompanyUiDto(): CompanyUiDto = CompanyUiDto(
    bs = bs, catchPhrase = catchPhrase, name = name
)


private fun AddressDomainDto.toAddressUiDto(): AddressUiDto = AddressUiDto(
    city = this.city,
    street = this.street,
    suite = this.suite,
    zipcode = this.zipcode,
    geo = geo.toGeoUiDto()
)

private fun GeoDomainDto.toGeoUiDto(): GeoUiDto = GeoUiDto(
    lat = lat, lng = lng
)
