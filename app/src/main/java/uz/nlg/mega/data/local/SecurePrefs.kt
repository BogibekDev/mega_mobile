package uz.nlg.mega.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class SecurePrefs @Inject constructor(
    private val securePref: SharedPreferences
) {

    fun saveString(name: String, data: String?) {
        securePref.edit()
            .putString(name, data)
            .apply()
    }

    fun saveBoolean(name: String, data: Boolean) {
        securePref.edit()
            .putBoolean(name, data)
            .apply()
    }

    fun saveLong(name: String, data: Long) {
        securePref.edit()
            .putLong(name, data)
            .apply()
    }

    fun saveFloat(name: String, data: Float) {
        securePref.edit()
            .putFloat(name, data)
            .apply()
    }

    fun saveInt(name: String, data: Int) {
        securePref.edit()
            .putInt(name, data)
            .apply()
    }

    fun getString(name: String): String? {
        return securePref
            .getString(name, null)
    }

    fun getBoolean(name: String): Boolean {
        return securePref
            .getBoolean(name, false)
    }

    fun getLong(name: String): Long {
        return securePref
            .getLong(name, 0L)
    }

    fun getFloat(name: String): Float {
        return securePref
            .getFloat(name, 0F)
    }

    fun getInt(name: String): Int {
        return securePref
            .getInt(name, 0)
    }

}