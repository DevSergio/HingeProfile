package com.hinge.profile.app.persistence.model

import com.hinge.profile.app.domain.model.User
import com.hinge.profile.app.domain.util.DomainMapper

class UserEntityMapper : DomainMapper<UserEntity, User> {
    override fun mapToDomainModel(model: UserEntity): User {
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

    override fun mapFromDomainModel(domainModel: User): UserEntity {
        return UserEntity(
            id = domainModel.id,
            name = domainModel.name,
            photo = domainModel.photo,
            gender = domainModel.gender,
            school = domainModel.school,
            about = domainModel.about,
            hobbies = domainModel.hobbies
        )
    }

    fun fromEntityList(initial: List<UserEntity>): List<User> {
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<User>): List<UserEntity> {
        return initial.map { mapFromDomainModel(it) }
    }

}