package com.mupy.soundpy.components.icons

import android.icu.text.ListFormatter.Width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun rememberMotionPhotosPaused(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "motion_photos_paused",
            defaultWidth = 40.dp,
            defaultHeight = 40.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.White),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(16.792f, 24.833f)
                quadToRelative(0.541f, 0f, 0.916f, -0.375f)
                reflectiveQuadToRelative(0.375f, -0.958f)
                verticalLineToRelative(-7.042f)
                quadToRelative(0f, -0.541f, -0.375f, -0.937f)
                reflectiveQuadToRelative(-0.916f, -0.396f)
                quadToRelative(-0.584f, 0f, -0.959f, 0.396f)
                reflectiveQuadToRelative(-0.375f, 0.937f)
                verticalLineTo(23.5f)
                quadToRelative(0f, 0.583f, 0.375f, 0.958f)
                reflectiveQuadToRelative(0.959f, 0.375f)
                close()
                moveToRelative(6.416f, 0f)
                quadToRelative(0.584f, 0f, 0.959f, -0.375f)
                reflectiveQuadToRelative(0.375f, -0.958f)
                verticalLineToRelative(-7.042f)
                quadToRelative(0f, -0.541f, -0.375f, -0.937f)
                reflectiveQuadToRelative(-0.959f, -0.396f)
                quadToRelative(-0.541f, 0f, -0.916f, 0.396f)
                reflectiveQuadToRelative(-0.375f, 0.937f)
                verticalLineTo(23.5f)
                quadToRelative(0f, 0.583f, 0.375f, 0.958f)
                reflectiveQuadToRelative(0.916f, 0.375f)
                close()
                moveTo(20f, 36.375f)
                quadToRelative(-3.417f, 0f, -6.396f, -1.292f)
                quadToRelative(-2.979f, -1.291f, -5.208f, -3.5f)
                quadToRelative(-2.229f, -2.208f, -3.5f, -5.187f)
                reflectiveQuadTo(3.625f, 20f)
                quadToRelative(0f, -1.208f, 0.146f, -2.375f)
                reflectiveQuadToRelative(0.521f, -2.375f)
                quadToRelative(0.166f, -0.5f, 0.666f, -0.729f)
                quadToRelative(0.5f, -0.229f, 1f, -0.021f)
                quadToRelative(0.5f, 0.167f, 0.73f, 0.646f)
                quadToRelative(0.229f, 0.479f, 0.104f, 0.979f)
                quadToRelative(-0.25f, 0.958f, -0.396f, 1.917f)
                quadTo(6.25f, 19f, 6.25f, 20f)
                quadToRelative(0f, 5.75f, 4f, 9.75f)
                reflectiveQuadToRelative(9.75f, 4f)
                quadToRelative(5.75f, 0f, 9.75f, -4f)
                reflectiveQuadToRelative(4f, -9.75f)
                quadToRelative(0f, -5.75f, -4f, -9.75f)
                reflectiveQuadToRelative(-9.75f, -4f)
                quadToRelative(-1f, 0f, -1.958f, 0.167f)
                quadToRelative(-0.959f, 0.166f, -1.917f, 0.375f)
                quadToRelative(-0.5f, 0.166f, -0.979f, -0.063f)
                reflectiveQuadToRelative(-0.688f, -0.687f)
                quadToRelative(-0.25f, -0.584f, 0.021f, -1.084f)
                quadToRelative(0.271f, -0.5f, 0.896f, -0.666f)
                quadToRelative(1.083f, -0.334f, 2.208f, -0.5f)
                quadToRelative(1.125f, -0.167f, 2.292f, -0.167f)
                quadToRelative(3.417f, 0f, 6.437f, 1.292f)
                quadToRelative(3.021f, 1.291f, 5.25f, 3.5f)
                quadToRelative(2.23f, 2.208f, 3.521f, 5.187f)
                quadToRelative(1.292f, 2.979f, 1.292f, 6.396f)
                reflectiveQuadToRelative(-1.292f, 6.396f)
                quadToRelative(-1.291f, 2.979f, -3.5f, 5.208f)
                quadToRelative(-2.208f, 2.229f, -5.187f, 3.5f)
                reflectiveQuadTo(20f, 36.375f)
                close()
                moveTo(9.125f, 11.208f)
                quadToRelative(-0.875f, 0f, -1.5f, -0.625f)
                reflectiveQuadTo(7f, 9.083f)
                quadToRelative(0f, -0.916f, 0.625f, -1.52f)
                quadToRelative(0.625f, -0.605f, 1.5f, -0.605f)
                reflectiveQuadToRelative(1.5f, 0.605f)
                quadToRelative(0.625f, 0.604f, 0.625f, 1.52f)
                quadToRelative(0f, 0.875f, -0.625f, 1.5f)
                reflectiveQuadToRelative(-1.5f, 0.625f)
                close()
                moveTo(20f, 20f)
                close()
            }
        }.build()
    }
}

@Composable
fun rememberPlayCircle(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "play_circle",
            defaultWidth = 40.dp,
            defaultHeight = 40.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.White),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(16f, 15.333f)
                verticalLineToRelative(9.334f)
                quadToRelative(0f, 0.791f, 0.667f, 1.166f)
                quadToRelative(0.666f, 0.375f, 1.333f, -0.041f)
                lineToRelative(7.333f, -4.709f)
                quadToRelative(0.584f, -0.375f, 0.584f, -1.083f)
                reflectiveQuadToRelative(-0.584f, -1.083f)
                lineTo(18f, 14.208f)
                quadToRelative(-0.667f, -0.416f, -1.333f, -0.041f)
                quadToRelative(-0.667f, 0.375f, -0.667f, 1.166f)
                close()
                moveToRelative(4f, 21.042f)
                quadToRelative(-3.375f, 0f, -6.375f, -1.292f)
                quadToRelative(-3f, -1.291f, -5.208f, -3.521f)
                quadToRelative(-2.209f, -2.229f, -3.5f, -5.208f)
                quadTo(3.625f, 23.375f, 3.625f, 20f)
                reflectiveQuadToRelative(1.292f, -6.375f)
                quadToRelative(1.291f, -3f, 3.521f, -5.208f)
                quadToRelative(2.229f, -2.209f, 5.208f, -3.5f)
                quadTo(16.625f, 3.625f, 20f, 3.625f)
                reflectiveQuadToRelative(6.375f, 1.292f)
                quadToRelative(3f, 1.291f, 5.208f, 3.521f)
                quadToRelative(2.209f, 2.229f, 3.5f, 5.208f)
                quadToRelative(1.292f, 2.979f, 1.292f, 6.354f)
                reflectiveQuadToRelative(-1.292f, 6.375f)
                quadToRelative(-1.291f, 3f, -3.521f, 5.208f)
                quadToRelative(-2.229f, 2.209f, -5.208f, 3.5f)
                quadToRelative(-2.979f, 1.292f, -6.354f, 1.292f)
                close()
                moveTo(20f, 20f)
                close()
                moveToRelative(0f, 13.75f)
                quadToRelative(5.667f, 0f, 9.708f, -4.042f)
                quadTo(33.75f, 25.667f, 33.75f, 20f)
                reflectiveQuadToRelative(-4.042f, -9.708f)
                quadTo(25.667f, 6.25f, 20f, 6.25f)
                reflectiveQuadToRelative(-9.708f, 4.042f)
                quadTo(6.25f, 14.333f, 6.25f, 20f)
                reflectiveQuadToRelative(4.042f, 9.708f)
                quadTo(14.333f, 33.75f, 20f, 33.75f)
                close()
            }
        }.build()
    }
}

@Composable
fun rememberArrowCircleLeft(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "arrow_circle_left",
            defaultWidth = 40.dp,
            defaultHeight = 40.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.White),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(19.125f, 25.292f)
                quadToRelative(0.333f, 0.375f, 0.896f, 0.354f)
                quadToRelative(0.562f, -0.021f, 0.896f, -0.396f)
                quadToRelative(0.416f, -0.417f, 0.416f, -0.938f)
                quadToRelative(0f, -0.52f, -0.416f, -0.895f)
                lineToRelative(-2.084f, -2.125f)
                horizontalLineToRelative(6.209f)
                quadToRelative(0.541f, 0f, 0.916f, -0.375f)
                reflectiveQuadToRelative(0.375f, -0.917f)
                quadToRelative(0f, -0.542f, -0.395f, -0.938f)
                quadToRelative(-0.396f, -0.395f, -0.938f, -0.395f)
                horizontalLineToRelative(-6.167f)
                lineToRelative(2.125f, -2.125f)
                quadToRelative(0.375f, -0.334f, 0.354f, -0.896f)
                quadToRelative(-0.02f, -0.563f, -0.395f, -0.896f)
                quadToRelative(-0.417f, -0.417f, -0.938f, -0.417f)
                quadToRelative(-0.521f, 0f, -0.896f, 0.417f)
                lineToRelative(-4.333f, 4.333f)
                quadToRelative(-0.375f, 0.375f, -0.375f, 0.917f)
                reflectiveQuadToRelative(0.375f, 0.917f)
                close()
                moveTo(20f, 36.375f)
                quadToRelative(-3.458f, 0f, -6.458f, -1.25f)
                reflectiveQuadToRelative(-5.209f, -3.458f)
                quadToRelative(-2.208f, -2.209f, -3.458f, -5.209f)
                quadToRelative(-1.25f, -3f, -1.25f, -6.458f)
                reflectiveQuadToRelative(1.25f, -6.437f)
                quadToRelative(1.25f, -2.98f, 3.458f, -5.188f)
                quadToRelative(2.209f, -2.208f, 5.209f, -3.479f)
                quadToRelative(3f, -1.271f, 6.458f, -1.271f)
                reflectiveQuadToRelative(6.438f, 1.271f)
                quadToRelative(2.979f, 1.271f, 5.187f, 3.479f)
                reflectiveQuadToRelative(3.479f, 5.188f)
                quadToRelative(1.271f, 2.979f, 1.271f, 6.437f)
                reflectiveQuadToRelative(-1.271f, 6.458f)
                quadToRelative(-1.271f, 3f, -3.479f, 5.209f)
                quadToRelative(-2.208f, 2.208f, -5.187f, 3.458f)
                quadToRelative(-2.98f, 1.25f, -6.438f, 1.25f)
                close()
                moveToRelative(0f, -2.625f)
                quadToRelative(5.833f, 0f, 9.792f, -3.958f)
                quadTo(33.75f, 25.833f, 33.75f, 20f)
                reflectiveQuadToRelative(-3.958f, -9.792f)
                quadTo(25.833f, 6.25f, 20f, 6.25f)
                reflectiveQuadToRelative(-9.792f, 3.958f)
                quadTo(6.25f, 14.167f, 6.25f, 20f)
                reflectiveQuadToRelative(3.958f, 9.792f)
                quadTo(14.167f, 33.75f, 20f, 33.75f)
                close()
                moveTo(20f, 20f)
                close()
            }
        }.build()
    }
}

@Composable
fun rememberArrowCircleRight(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "arrow_circle_right",
            defaultWidth = 40.dp,
            defaultHeight = 40.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.White),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(20.917f, 25.25f)
                lineToRelative(4.333f, -4.333f)
                quadToRelative(0.375f, -0.375f, 0.375f, -0.917f)
                reflectiveQuadToRelative(-0.375f, -0.917f)
                lineToRelative(-4.375f, -4.375f)
                quadToRelative(-0.333f, -0.375f, -0.875f, -0.354f)
                quadToRelative(-0.542f, 0.021f, -0.917f, 0.396f)
                quadToRelative(-0.416f, 0.375f, -0.416f, 0.896f)
                reflectiveQuadToRelative(0.416f, 0.937f)
                lineToRelative(2.084f, 2.084f)
                horizontalLineToRelative(-6.209f)
                quadToRelative(-0.541f, 0f, -0.896f, 0.395f)
                quadToRelative(-0.354f, 0.396f, -0.354f, 0.938f)
                quadToRelative(0f, 0.542f, 0.375f, 0.917f)
                reflectiveQuadToRelative(0.917f, 0.375f)
                horizontalLineToRelative(6.167f)
                lineToRelative(-2.125f, 2.166f)
                quadToRelative(-0.375f, 0.334f, -0.354f, 0.875f)
                quadToRelative(0.02f, 0.542f, 0.395f, 0.917f)
                quadToRelative(0.417f, 0.417f, 0.938f, 0.417f)
                quadToRelative(0.521f, 0f, 0.896f, -0.417f)
                close()
                moveTo(20f, 36.375f)
                quadToRelative(-3.458f, 0f, -6.458f, -1.25f)
                reflectiveQuadToRelative(-5.209f, -3.458f)
                quadToRelative(-2.208f, -2.209f, -3.458f, -5.209f)
                quadToRelative(-1.25f, -3f, -1.25f, -6.458f)
                reflectiveQuadToRelative(1.25f, -6.437f)
                quadToRelative(1.25f, -2.98f, 3.458f, -5.188f)
                quadToRelative(2.209f, -2.208f, 5.209f, -3.479f)
                quadToRelative(3f, -1.271f, 6.458f, -1.271f)
                reflectiveQuadToRelative(6.438f, 1.271f)
                quadToRelative(2.979f, 1.271f, 5.187f, 3.479f)
                reflectiveQuadToRelative(3.479f, 5.188f)
                quadToRelative(1.271f, 2.979f, 1.271f, 6.437f)
                reflectiveQuadToRelative(-1.271f, 6.458f)
                quadToRelative(-1.271f, 3f, -3.479f, 5.209f)
                quadToRelative(-2.208f, 2.208f, -5.187f, 3.458f)
                quadToRelative(-2.98f, 1.25f, -6.438f, 1.25f)
                close()
                moveToRelative(0f, -2.625f)
                quadToRelative(5.833f, 0f, 9.792f, -3.958f)
                quadTo(33.75f, 25.833f, 33.75f, 20f)
                reflectiveQuadToRelative(-3.958f, -9.792f)
                quadTo(25.833f, 6.25f, 20f, 6.25f)
                reflectiveQuadToRelative(-9.792f, 3.958f)
                quadTo(6.25f, 14.167f, 6.25f, 20f)
                reflectiveQuadToRelative(3.958f, 9.792f)
                quadTo(14.167f, 33.75f, 20f, 33.75f)
                close()
                moveTo(20f, 20f)
                close()
            }
        }.build()
    }
}

@Composable
fun rememberMusicNote(color: Color): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "music_note",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(color),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(16.5f, 34.708f)
                quadToRelative(-2.625f, 0f, -4.417f, -1.791f)
                quadToRelative(-1.791f, -1.792f, -1.791f, -4.417f)
                reflectiveQuadToRelative(1.791f, -4.417f)
                quadToRelative(1.792f, -1.791f, 4.417f, -1.791f)
                quadToRelative(1.083f, 0f, 2f, 0.291f)
                quadToRelative(0.917f, 0.292f, 1.625f, 0.875f)
                verticalLineTo(7.917f)
                quadToRelative(0f, -1.084f, 0.75f, -1.854f)
                quadToRelative(0.75f, -0.771f, 1.875f, -0.771f)
                horizontalLineToRelative(4.125f)
                quadToRelative(1.167f, 0f, 2f, 0.833f)
                reflectiveQuadToRelative(0.833f, 2f)
                quadToRelative(0f, 1.208f, -0.833f, 2.063f)
                quadToRelative(-0.833f, 0.854f, -2f, 0.854f)
                horizontalLineTo(22.75f)
                verticalLineTo(28.5f)
                quadToRelative(0f, 2.625f, -1.812f, 4.417f)
                quadToRelative(-1.813f, 1.791f, -4.438f, 1.791f)
                close()
            }
        }.build()
    }
}

@Composable
fun rememberMusicOff(color: Color): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "music_off",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(color),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(32.292f, 36.417f)
                lineTo(3.583f, 7.75f)
                quadToRelative(-0.375f, -0.375f, -0.375f, -0.917f)
                quadToRelative(0f, -0.541f, 0.375f, -0.958f)
                quadTo(4f, 5.5f, 4.542f, 5.5f)
                quadToRelative(0.541f, 0f, 0.958f, 0.375f)
                lineToRelative(28.667f, 28.667f)
                quadToRelative(0.375f, 0.416f, 0.375f, 0.937f)
                quadToRelative(0f, 0.521f, -0.375f, 0.938f)
                quadToRelative(-0.417f, 0.375f, -0.959f, 0.375f)
                quadToRelative(-0.541f, 0f, -0.916f, -0.375f)
                close()
                moveToRelative(-9.417f, -16.875f)
                lineToRelative(-2.625f, -2.625f)
                verticalLineToRelative(-9f)
                quadToRelative(0f, -1.084f, 0.771f, -1.854f)
                quadToRelative(0.771f, -0.771f, 1.854f, -0.771f)
                horizontalLineTo(27f)
                quadToRelative(1.208f, 0f, 2.042f, 0.833f)
                quadToRelative(0.833f, 0.833f, 0.833f, 2f)
                quadToRelative(0f, 1.208f, -0.833f, 2.063f)
                quadToRelative(-0.834f, 0.854f, -2.042f, 0.854f)
                horizontalLineToRelative(-4.125f)
                close()
                moveToRelative(-6.208f, 15.166f)
                quadToRelative(-2.625f, 0f, -4.417f, -1.791f)
                quadToRelative(-1.792f, -1.792f, -1.792f, -4.417f)
                reflectiveQuadToRelative(1.792f, -4.417f)
                quadToRelative(1.792f, -1.791f, 4.417f, -1.791f)
                quadToRelative(1.083f, 0f, 1.979f, 0.291f)
                quadToRelative(0.896f, 0.292f, 1.604f, 0.875f)
                verticalLineToRelative(-2.833f)
                lineToRelative(2.625f, 2.667f)
                verticalLineTo(28.5f)
                quadToRelative(0f, 2.625f, -1.792f, 4.417f)
                quadToRelative(-1.791f, 1.791f, -4.416f, 1.791f)
                close()
            }
        }.build()
    }
}

@Composable
fun rememberScreenRotationUp(color: Color): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "screen_rotation_up",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(color),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(592.334f, -166f)
                horizontalLineTo(316.53f)
                quadToRelative(-29.447f, 0f, -50.405f, -21.085f)
                quadToRelative(-20.958f, -21.086f, -20.958f, -50.748f)
                verticalLineToRelative(-404.5f)
                lineTo(308.5f, -579f)
                verticalLineToRelative(349.667f)
                horizontalLineToRelative(283.834f)
                lineToRelative(-59.167f, -59.5f)
                quadToRelative(-9.667f, -9.333f, -9.583f, -22.105f)
                quadToRelative(0.083f, -12.772f, 9.322f, -22.167f)
                quadToRelative(9.761f, -9.395f, 22.578f, -9.395f)
                quadToRelative(12.816f, 0f, 22.183f, 9.5f)
                lineToRelative(113.666f, 113f)
                quadToRelative(9.5f, 9.5f, 9.5f, 22.334f)
                quadToRelative(0f, 12.833f, -9.5f, 22.333f)
                lineTo(577.833f, -62.5f)
                quadToRelative(-9.833f, 9.167f, -22.499f, 9.417f)
                quadToRelative(-12.667f, 0.25f, -22.428f, -9.25f)
                quadToRelative(-9.239f, -9.5f, -9.156f, -22.333f)
                quadToRelative(0.084f, -12.834f, 9.584f, -22.334f)
                lineToRelative(59f, -59f)
                close()
                moveToRelative(122.833f, -156.167f)
                lineTo(651.834f, -385.5f)
                verticalLineToRelative(-345.167f)
                horizontalLineTo(366.999f)
                lineTo(426.5f, -671.5f)
                quadToRelative(9.333f, 9.667f, 9.25f, 22.438f)
                quadToRelative(-0.084f, 12.772f, -9.149f, 22.167f)
                quadToRelative(-9.935f, 9.395f, -22.751f, 9.395f)
                quadToRelative(-12.817f, 0f, -22.184f, -9.5f)
                lineTo(268f, -740f)
                quadToRelative(-9.5f, -9.5f, -9.5f, -22.334f)
                quadToRelative(0f, -12.833f, 9.529f, -22.362f)
                lineToRelative(113.775f, -113.109f)
                quadToRelative(9.529f, -8.862f, 22.196f, -9.112f)
                quadToRelative(12.666f, -0.25f, 22.601f, 9.25f)
                quadToRelative(9.065f, 9.5f, 8.982f, 22.333f)
                quadTo(435.499f, -862.5f, 426f, -853f)
                lineToRelative(-59.001f, 59f)
                horizontalLineToRelative(276.472f)
                quadToRelative(29.446f, 0f, 50.571f, 20.987f)
                quadToRelative(21.125f, 20.988f, 21.125f, 50.513f)
                verticalLineToRelative(400.333f)
                close()
            }
        }.build()
    }
}