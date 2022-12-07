package com.los3molineros.lyophilization_world.di



import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.los3molineros.lyophilization_world.data.implementation.FirebaseAuthImpl
import com.los3molineros.lyophilization_world.data.repositories.FirebaseAuthRepository
import com.los3molineros.lyophilization_world.domain.SplashScreenUseCase
import com.los3molineros.lyophilization_world.ui.viewModels.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { Firebase.auth }
    single<FirebaseAuthRepository>{FirebaseAuthImpl(get())}
    single { SplashScreenUseCase(get()) }
    viewModel { SplashScreenViewModel(get()) }

}
