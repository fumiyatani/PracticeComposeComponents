package com.ftani.jetpackcomposecomponentspractice.ui.theme

import android.hardware.lights.Light
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

private val LightColorScheme = Colors(
    primary = Purple40,
    primaryVariant = Purple80,
    secondary = Pink40,
    secondaryVariant = Pink40,
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    error = Color.Red,
    onError = Color.White,
    isLight = true,
)

@Composable
fun JetpackComposeComponentsPracticeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorScheme,
        typography = Typography,
        content = content
    )
}

@Immutable
data class ExtendedColors(
    val tertiary: Color,
    val onTertiary: Color
)

@Immutable
data class ExtendedShapes(
    val dialog: Shape,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        tertiary = Color.Unspecified,
        onTertiary = Color.Unspecified
    )
}

val LocalExtendedShapes = staticCompositionLocalOf {
    ExtendedShapes(
        dialog = RoundedCornerShape(ZeroCornerSize)
    )
}

@Composable
fun ExtendedTheme(
    content: @Composable () -> Unit
) {
    val extendedColors = ExtendedColors(
        tertiary = Color(0xFFA8EFF0),
        onTertiary = Color(0xFF002021)
    )
    val extendedShaped = ExtendedShapes(
        dialog = RoundedCornerShape(3.dp)
    )
    CompositionLocalProvider(
        LocalExtendedColors provides extendedColors,
        LocalExtendedShapes provides extendedShaped,
    ) {
        MaterialTheme(
            /* colors = ..., typography = ..., shapes = ... */
            content = content
        )
    }
}

object ExtendedTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
    val shapes: ExtendedShapes
        @Composable
        get() = LocalExtendedShapes.current
}

@Composable
fun ExtendedButton() {

}