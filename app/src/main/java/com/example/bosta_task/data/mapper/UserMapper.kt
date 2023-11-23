package com.example.bosta_task.data.mapper

import com.example.bosta_task.data.model.user.Address
import com.example.bosta_task.data.model.user.Company
import com.example.bosta_task.data.model.user.Geo
import com.example.bosta_task.data.model.user.UserDto
import com.example.bosta_task.domain.model.user.AddressDomainDto
import com.example.bosta_task.domain.model.user.CompanyDomainDto
import com.example.bosta_task.domain.model.user.GeoDomainDto
import com.example.bosta_task.domain.model.user.UserDomainDto

fun UserDto.toUserDomainModel(): UserDomainDto =
    UserDomainDto(
        name = name,
        address = address.toAddressDomainDto(),
        company = company.toCompanyDomainDto(),
        email = email,
        id = id,
        phone = phone,
        username = username,
        website = website
    )

fun Address.toAddressDomainDto(): AddressDomainDto =
    AddressDomainDto(
        city = this.city,
        street = this.street,
        suite = this.suite,
        zipcode = this.zipcode,
        geo = geo.toGeoDomainDto()
    )

fun Company.toCompanyDomainDto(): CompanyDomainDto =
    CompanyDomainDto(bs = bs, catchPhrase = catchPhrase, name = name)


fun Geo.toGeoDomainDto(): GeoDomainDto = GeoDomainDto(lat = lat, lng = lng)