package com.binnyatoff.shift.di

import com.binnyatoff.shift.data.network.Api
import com.binnyatoff.shift.ui.mainFragment.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideMainViewModelFactory(api: Api): MainViewModelFactory {
        return MainViewModelFactory(api)
    }
}