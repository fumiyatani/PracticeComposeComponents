package com.ftani.jetpackcomposecomponentspractice.ui.theme

import android.hardware.lights.Light
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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