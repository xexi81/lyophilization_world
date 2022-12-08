package com.los3molineros.lyophilization_world.di



import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.los3molineros.lyophilization_world.data.implementation.FirebaseAuthImpl
import com.los3molineros.lyophilization_world.data.implementation.FirestoreUserImpl
import com.los3molineros.lyophilization_world.data.repositories.FirebaseAuthRepository
import com.los3molineros.lyophilization_world.data.repositories.FirestoreUserRepository
import com.los3molineros.lyophilization_world.domain.FirebaseLoginUseCase
import com.los3molineros.lyophilization_world.domain.SplashScreenUseCase
import com.los3molineros.lyophilization_world.ui.viewModels.LoginWithEmailViewModel
import com.los3molineros.lyophilization_world.ui.viewModels.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }

    single<FirebaseAuthRepository>{FirebaseAuthImpl(get())}
    single<FirestoreUserRepository>{FirestoreUserImpl(get())}

    single { SplashScreenUseCase(get()) }
    single { FirebaseLoginUseCase(get(), get()) }

    viewModel { SplashScreenViewModel(get()) }
    viewModel { LoginWithEmailViewModel(get())}
}
