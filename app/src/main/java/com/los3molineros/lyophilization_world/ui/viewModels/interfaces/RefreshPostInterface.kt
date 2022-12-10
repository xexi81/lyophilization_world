package com.los3molineros.lyophilization_world.ui.viewModels.interfaces

import com.los3molineros.lyophilization_world.data.model.Post

interface RefreshPostInterface {
    fun refreshPost(post: Post?)
}