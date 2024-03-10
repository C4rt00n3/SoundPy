package com.mupy.soundpy.models

import androidx.compose.runtime.Composable

class Menu(val open: Boolean, val menu: @Composable () -> Unit) {}