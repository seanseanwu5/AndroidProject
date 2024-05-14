//package com.example.myapplication.auth
//
//import android.content.Context
//import android.content.SharedPreferences
//import androidx.security.crypto.EncryptedSharedPreferences
//import androidx.security.crypto.MasterKeys
//
//object AuthUtils {
//
//    private fun getEncryptedSharedPreferences(context: Context): SharedPreferences {
//        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
//
//        return EncryptedSharedPreferences.create(
//            "encrypted_shared_prefs",
//            masterKeyAlias,
//            context,
//            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//        )
//    }
//
//    fun saveUserCredentials(context: Context, email: String, password: String) {
//        val editor = getEncryptedSharedPreferences(context).edit()
//        editor.putString("email", email)
//        editor.putString("password", password)
//        editor.apply()
//    }
//
//    fun getUserCredentials(context: Context): Pair<String, String>? {
//        val prefs = getEncryptedSharedPreferences(context)
//        val email = prefs.getString("email", null)
//        val password = prefs.getString("password", null)
//
//        if (email != null && password != null) {
//            return Pair(email, password)
//        }
//
//        return null
//    }
//
//    fun clearUserCredentials(context: Context) {
//        val editor = getEncryptedSharedPreferences(context).edit()
//        editor.remove("email")
//        editor.remove("password")
//        editor.apply()
//    }
//}