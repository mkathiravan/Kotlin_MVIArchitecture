package net.kathir.mviarchitecture.data.api

import io.reactivex.Single
import net.kathir.mviarchitecture.data.model.User

interface ApiService
{
    fun getUsers() : Single<List<User>>
}