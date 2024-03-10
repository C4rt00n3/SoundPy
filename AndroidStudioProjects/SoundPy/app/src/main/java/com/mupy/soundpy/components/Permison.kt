package com.mupy.soundpy.components

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mupy.soundpy.ContextMain

@SuppressLint("InlinedApi")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(
    viewModel: ContextMain,
    // context: Context
) {
    val permissionStateRead =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    val permissionStateWrite =
        rememberPermissionState(permission = Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val permissionStateManager =
        rememberPermissionState(permission = Manifest.permission.MANAGE_EXTERNAL_STORAGE)
    val postNotificationPermission =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val postForegroundPermission =
        rememberPermissionState(permission = Manifest.permission.FOREGROUND_SERVICE)

    val permissionRead = permissionStateRead.status.isGranted
    val permissionWrite = permissionStateWrite.status.isGranted
    val permissionManager = permissionStateManager.status.isGranted
    val permissionForegorund = permissionStateManager.status.isGranted

    LaunchedEffect(Unit) {
        if (!permissionRead) {
            permissionStateRead.launchPermissionRequest()
        }
        if (!permissionForegorund) {
            postForegroundPermission.launchPermissionRequest()
        }
        if (!postNotificationPermission.status.isGranted) {
            postNotificationPermission.launchPermissionRequest()
        }
        if (!permissionWrite) {
            permissionStateWrite.launchPermissionRequest()
        }
        if (!permissionManager) {
            permissionStateManager.launchPermissionRequest()
        }

        println("permissionRead = $permissionRead / permissionWrite = $permissionWrite")

        if (permissionRead && permissionWrite) {
            // Both read and write permissions are granted, execute your function here
            viewModel.getFilesSaved()
        }
    }
}
