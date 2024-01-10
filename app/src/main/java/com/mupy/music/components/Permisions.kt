package com.mupy.music.components

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.mupy.music.screen.ContextMain
import kotlin.concurrent.thread

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(viewModel: ContextMain){
    val permissionStateRead = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    val permissionStateWri = rememberPermissionState(permission = Manifest.permission.WRITE_EXTERNAL_STORAGE)

    LaunchedEffect(Unit) {
        if (!permissionStateRead.hasPermission) {
            thread {
                permissionStateRead.launchPermissionRequest()
            }
        } else if (!permissionStateWri.hasPermission) {
            thread {
                permissionStateWri.launchPermissionRequest()
            }
        }

        if (permissionStateRead.hasPermission && permissionStateWri.hasPermission) {
            // A permissão foi concedida, execute sua função aqui
            viewModel.getFiles()
        }
    }

    // Verifica se a permissão foi solicitada
    if (permissionStateRead.permissionRequested || permissionStateWri.permissionRequested) {
        viewModel.getFiles()
        viewModel.setPlayer()
    }
}