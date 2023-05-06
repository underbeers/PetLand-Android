package com.petland.app.data.store

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Store(private val context: Context) {
    val accessToken: Flow<String> = flowOf(KEY_AUTHORIZATION_TOKEN, "")

    suspend fun setAccessToken(accessToken: String) = edit {
        it[KEY_AUTHORIZATION_TOKEN] = accessToken
    }

    private fun <T> flowOf(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> = context.dataStore.data.map { it[key] ?: defaultValue }

    private suspend inline fun edit(
        crossinline transform: suspend (MutablePreferences) -> Unit
    ) {
        context.dataStore.edit { transform(it) }
    }

    companion object {
        private val KEY_AUTHORIZATION_TOKEN = stringPreferencesKey("session_key")
        private const val STORE_NAME = "store"
        private val Context.dataStore by preferencesDataStore(name = STORE_NAME)
    }
}