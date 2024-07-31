package com.example.spiice.di

import com.example.spiice.utils.securityUtils.DefaultSecurityUtilsImpl
import com.example.spiice.utils.securityUtils.SecurityUtils
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindSecurityUtils(
        securityUtilsImpl: DefaultSecurityUtilsImpl
    ): SecurityUtils
}