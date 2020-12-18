package kg.nurik.auth.di

import kg.nurik.auth.BuildConfig.BASE_URL
import kg.nurik.auth.data.intecartor.RetrofitInteractor
import kg.nurik.auth.data.intecartor.RetrofitInteractorImpl
import kg.nurik.auth.data.remote.RetrofitBuilder
import kg.nurik.auth.data.repository.RetrofitRepositories
import kg.nurik.auth.data.repository.RetrofitRepositoriesImpl
import kg.nurik.auth.ui.NewActivtiy.NewVieModel
import kg.nurik.auth.ui.main.AuthViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { NewVieModel(get()) }
}
val repositoryModule = module {
    single { RetrofitBuilder.initRetrofit(BASE_URL) } //сам будет создавать single ton
    single<RetrofitInteractor> { RetrofitInteractorImpl(get()) }
    single<RetrofitRepositories> { RetrofitRepositoriesImpl(get()) }
}
val appModules = listOf(viewModelModule, repositoryModule)