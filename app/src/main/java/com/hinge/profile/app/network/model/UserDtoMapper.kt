package com.hinge.profile.app.network.model

import com.hinge.profile.app.domain.model.User
import com.hinge.profile.app.domain.util.DomainMapper

class UserDtoMapper : DomainMapper<UserDto, User> {

    override fun mapToDomainModel(model: UserDto): User {
        return User(
            id = model.id,
            name = model.name,
            photo = model.photo,
            gender = model.gender,
            school = model.school,
            about = model.about,
            hobbies = model.hobbies
        )
    }

    override fun mapFromDomainModel(domainModel: User): UserDto {
        return UserDto(
            id = domainModel.id,
            name = domainModel.name,
            photo = domainModel.photo,
            gender = domainModel.gender,
            school = domainModel.school,
            about = domainModel.about,
            hobbies = domainModel.hobbies
        )
    }

    fun toDomainList(initial: List<UserDto>): List<User> {
        return initial.map { mapToDomainModel(it) }
    }
}