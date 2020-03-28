package net.kathir.mviarchitecture.data.api

class ApiHelper(private val apiService: ApiService)
{
    fun getUsers() = apiService.getUsers()
}