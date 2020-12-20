package kg.nurik.auth.di

import androidx.room.Room
import kg.nurik.auth.BuildConfig.BASE_URL
import kg.nurik.auth.data.intecartor.RetrofitInteractor
import kg.nurik.auth.data.intecartor.RetrofitInteractorImpl
import kg.nurik.auth.data.local.AppDatabase
import kg.nurik.auth.data.local.AppDatabase.Companion.MIGRATION_1_2
import kg.nurik.auth.data.local.DATABASE_NAME
import kg.nurik.auth.data.remote.RetrofitBuilder
import kg.nurik.auth.data.repository.RetrofitRepositories
import kg.nurik.auth.data.repository.RetrofitRepositoriesImpl
import kg.nurik.auth.ui.NewActivtiy.NewViewModel
import kg.nurik.auth.ui.main.AuthViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { NewViewModel(get()) }
}
val repositoryModule = module {
    single { RetrofitBuilder.initRetrofit(BASE_URL) } //сам будет создавать single ton
    single<RetrofitInteractor> { RetrofitInteractorImpl(get()) }
    single<RetrofitRepositories> { RetrofitRepositoriesImpl(get(), get()) } //второй get() потому что там 2парам в конструкторе

    single { Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME)
      // .fallbackToDestructiveMigration() //1 вар , если у нас схемы не совпадают то грохай бд и занова пересоздается
       // .addMigrations(MIGRATION_1_2) при миграции добав успешно , потом можем его отключить после успешного
        .build() } //room
    single { get<AppDatabase>().profileDao() }
}
val appModules = listOf(viewModelModule, repositoryModule)