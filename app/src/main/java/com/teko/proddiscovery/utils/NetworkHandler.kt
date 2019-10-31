/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.teko.proddiscovery.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Injectable class which returns information about the network connection state.
 */
class NetworkHandler
constructor(private val context: Context) {
    val isConnected get() = run {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT < 23) {
            val networkInfo = cm.activeNetworkInfo

            if (networkInfo != null) {
                (networkInfo.isConnected() && (networkInfo.getType() == ConnectivityManager.TYPE_WIFI || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE));
            } else {
                false
            }
        } else {
            val network = cm.activeNetwork
            if (network != null) {
                val networkCapacity = cm.getNetworkCapabilities(network)
                (networkCapacity?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false || networkCapacity?.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI) ?: false)
            } else {
                false
            }
        }
    }
}