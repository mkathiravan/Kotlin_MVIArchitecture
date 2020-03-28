package net.kathir.mviarchitecture.ui.main.dataholder

import net.kathir.mviarchitecture.data.model.User

data class MainDataHolder(var users: List<User>? = null)
{
    override fun toString(): String {
        return "MainDataHolder(users=$users)"
    }
}