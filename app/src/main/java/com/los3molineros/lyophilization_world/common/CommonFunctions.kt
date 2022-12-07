package com.los3molineros.lyophilization_world.common

import android.util.Log
import kotlinx.coroutines.coroutineScope
import java.net.InetSocketAddress
import java.net.Socket

suspend fun isNetworkAvailable() = coroutineScope {
    return@coroutineScope try {
        val sock = Socket()
        val socketAddress = InetSocketAddress("8.8.8.8", 53)
        sock.connect(socketAddress, 2000)
        sock.close()
        true
    } catch (e: Exception) {
        Log.e("TAG", "isNetworkAvailable: ${e.message}")
        false
    }
}