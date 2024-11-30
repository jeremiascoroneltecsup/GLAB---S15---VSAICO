package com.lopez.johan.poketinder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lopez.johan.poketinder.data.database.dao.PokemonDao
import com.lopez.johan.poketinder.data.database.entities.MyPokemonEntity

@Database(entities = [MyPokemonEntity::class], exportSchema = false, version = 1)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
}
