package com.gabutmen.ingetwoy.di

import android.content.Context
import androidx.room.Room
import com.gabutmen.ingetwoy.data.database.AppDatabase
import com.gabutmen.ingetwoy.data.database.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "ingetwoy_db").build()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.getProductDao()
    }
}