package com.binnyatoff.shift.di

import com.binnyatoff.shift.ui.mainFragment.MainFragment
import dagger.Component

@Component(modules = [DataModule::class, AppModule::class])
interface AppComponent {
    fun inject(mainFragment: MainFragment)
}