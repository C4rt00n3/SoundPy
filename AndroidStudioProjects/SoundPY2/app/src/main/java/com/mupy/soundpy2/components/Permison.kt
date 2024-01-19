package com.mupy.soundpy2.components

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mupy.soundpy2.ContextMain
import com.mupy.soundpy2.notification.WaterNotificationService

@SuppressLint("InlinedApi")
@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Permission(viewModel: ContextMain) {
    val context = LocalContext.current
    val permissionStateRead =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    val permissionStateWrite =
        rememberPermissionState(permission = Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val permissionStateManager =
        rememberPermissionState(permission = Manifest.permission.MANAGE_EXTERNAL_STORAGE)
    val postNotificationPermission =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    val permissionRead = permissionStateRead.status.isGranted
    val permissionWrite = permissionStateWrite.status.isGranted
    val permissionManager = permissionStateManager.status.isGranted

    val waterNotificationService = WaterNotificationService(context)

    LaunchedEffect(Unit) {
        if (!permissionRead) {
            permissionStateRead.launchPermissionRequest()
        }
        if (!postNotificationPermission.status.isGranted) {
            postNotificationPermission.launchPermissionRequest()
        } else
            waterNotificationService.showExpandableNotification()

        if (!permissionWrite) {
            permissionStateWrite.launchPermissionRequest()
        }

        if (!permissionManager) {
            permissionStateManager.launchPermissionRequest()
        }

        if (permissionRead && permissionWrite) {
            // Both read and write permissions are granted, execute your function here
            viewModel.getFiles(context)
        }
    }

    // You can also check for permissions without using LaunchedEffect
    if (permissionRead && permissionWrite) {
        // Both read and write permissions are granted, execute your function here
        viewModel.getFiles(context)
    }
}
