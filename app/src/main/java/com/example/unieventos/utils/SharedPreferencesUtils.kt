package com.example.unieventos.utils

import android.content.Context
import com.example.unieventos.dto.UserDTO
import com.example.unieventos.enums.Role

object SharedPreferencesUtils {

    fun savePreferences(context: Context, userId: Int, role: Role) {
        val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("userId", userId)
        editor.putString("role", role.toString())
        editor.apply()
    }

    fun getCurrentUser(context: Context): UserDTO? {
        val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("userId", 0)
        val role = sharedPreferences.getString("role", "")

        return if (userId == 0 || role.isNullOrEmpty()) {
            null
        } else {
            UserDTO(userId, Role.valueOf(role))
        }
    }

    fun clearPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

}