package com.example.spiice.di

import android.content.Context
import com.example.spiice.roomDB.AutoMigrationSpec1To2
import com.example.spiice.ui.MainActivity
import com.example.spiice.ui.addNoteScreen.AddNoteFragment
import com.example.spiice.ui.logInScreen.LogInFragment
import com.example.spiice.ui.navigationContainer.NavigationFragment
import com.example.spiice.ui.notesListScreen.NotesListFragment
import com.example.spiice.ui.profileScreen.ProfileFragment
import com.example.spiice.ui.searchScreen.SearchFragment
import com.example.spiice.ui.signUpScreen.SignUpFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataBaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(fragment: AddNoteFragment)
    fun inject(fragment: LogInFragment)
    fun inject(fragment: NotesListFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: NavigationFragment)
    fun inject(activity: MainActivity)
    fun inject(autoMigrationSpec1To2: AutoMigrationSpec1To2)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun provideContext(context: Context): Builder
    }
}