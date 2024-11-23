package com.example.spiice.di

import com.example.spiice.utils.securityUtils.DefaultSecurityUtilsImpl
import com.example.spiice.utils.securityUtils.SecurityUtils
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindSecurityUtils(
        securityUtilsImpl: DefaultSecurityUtilsImpl
    ): SecurityUtils
}