package com.los3molineros.lyophilization_world.ui

import android.app.Activity
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.los3molineros.lyophilization_world.data.model.AssetParamType
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.ui.screens.CommentsActivity
import com.los3molineros.lyophilization_world.ui.screens.LoginWithEmailActivity
import com.los3molineros.lyophilization_world.ui.screens.PostActivity
import com.los3molineros.lyophilization_world.ui.screens.SplashActivity

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    val activity = (LocalContext.current as? Activity)

    NavHost(
        navController = navController,
        startDestination = "splashScreen") {

        composable( route = "splashScreen") {
            SplashActivity(
                onLoginWithEmailClick = { navController.navigate("firebaseEmailScreen")},
                continueToPost = { navController.navigate("postScreen") }
            )
        }

        composable( route = "firebaseEmailScreen") {
            LoginWithEmailActivity(
                onBackPressed = { navController.popBackStack() },
                onLoginSuccessfully = { navController.navigate( "postScreen")}
            )
        }

        composable( route = "postScreen") {
            PostActivity(
                onBackClick = { activity?.finish() },
                onCommentClick = { post ->
                    val json = Uri.encode(Gson().toJson(post))
                    navController.navigate("commentsScreen/$json")
                }
            )
        }

        composable(
            route = "commentsScreen/{post}",
            arguments = listOf(navArgument("post") {type = AssetParamType() })
        ) {
            val post = it.arguments?.getParcelable<Post>("post")
            CommentsActivity(
                post = post,
                onBackClick = {
                    navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate("postScreen")
                }
            )
        }
    }
}