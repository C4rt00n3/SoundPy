package com.mupy.music.components

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlin.concurrent.thread

@OptIn(ExperimentalPermissionsApi::class)
@Composable
inline fun Permission(){
    val permissionStateRead =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    val permissionStateWri =
        rememberPermissionState(permission = Manifest.permission.WRITE_EXTERNAL_STORAGE)
    LaunchedEffect(Unit) {
        if (!permissionStateRead.hasPermission) thread {
            permissionStateRead.launchPermissionRequest()
        }
        else if (!permissionStateWri.hasPermission) thread {
            permissionStateWri.launchPermissionRequest()
        }
    }
}