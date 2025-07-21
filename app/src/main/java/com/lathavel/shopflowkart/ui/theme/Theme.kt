package com.lathavel.shopflowkart.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val LightPrimary = Color(0xFF4CAF50)
val LightOnPrimary = Color.White
val LightPrimaryContainer = Color(0xFFC8E6C9)
val LightOnPrimaryContainer = Color(0xFF1B5E20)
val LightSecondary = Color(0xFFFF9800)
val LightOnSecondary = Color.Black
val LightBackground = Color(0xFFF9F9F9)
val LightSurface = Color(0xFFFFFFFF)
val LightError = Color(0xFFB00020)
val LightOnError = Color.White

val ElegantLightColorScheme: ColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    background = LightBackground,
    surface = LightSurface,
    error = LightError,
    onError = LightOnError
)
// Dark Pink Theme
val PinkDarkPrimary = Color(0xFFFFB1C8) // A lighter, softer pink for primary in dark mode (more accessible)
val PinkDarkOnPrimary = Color(0xFF5F002E) // Dark maroon/purple for text on primary
val PinkDarkPrimaryContainer = Color(0xFF2D3B1D) // A deeper pink for containers in dark mode
val PinkDarkOnPrimaryContainer = Color(0xFF8BC34A) // Light pink for text on primary container

val PinkDarkSecondary = Color(0xFFFFB0C9) // A light pink for secondary actions
val PinkDarkOnSecondary = Color(0xFF5F002F) // Dark maroon for text on secondary
val PinkDarkSecondaryContainer = Color(0xFF7D2948) // Deeper pink for secondary containers
val PinkDarkOnSecondaryContainer = Color(0xFFFFD9E3) // Light pink for text on secondary container

val PinkDarkTertiary = Color(0xFFFFB0C8) // Light accent pink for dark mode
val PinkDarkOnTertiary = Color(0xFF5F002E) // Dark maroon for text on tertiary
val PinkDarkTertiaryContainer = Color(0xFF7D2947) // Deeper pink for tertiary containers
val PinkDarkOnTertiaryContainer = Color(0xFFFFD9E2) // Light pink for text on tertiary container

val PinkDarkError = Color(0xFFFFB4AB) // Lighter error red for dark mode
val PinkDarkOnError = Color(0xFF690005) // Dark red for text on error
val PinkDarkErrorContainer = Color(0xFF93000A) // Deep red error container
val PinkDarkOnErrorContainer = Color(0xFFFFB4AB) // Light red text on error container

val PinkDarkBackground = Color(0xFF201A1B) // Dark grey, slightly pinkish/brownish tint
val PinkDarkOnBackground = Color(0xFFECE0E1) // Light grey/pinkish white for text on background

val PinkDarkSurface = Color(0xFF201A1B) // Surface can be same as background
val PinkDarkOnSurface = Color(0xFFECE0E1) // Light grey/pinkish white for text on surface
val PinkDarkSurfaceVariant = Color(0xFF534345) // Muted dark pink/grey for cards, dividers
val PinkDarkOnSurfaceVariant = Color(0xFFD8C2C3) // Lighter muted color for text on surface variant
val PinkDarkOutline = Color(0xFFA08C8E) // Muted light pink/grey for outlines


private val DarkPinkColorScheme = darkColorScheme(
    primary = PinkDarkPrimary,
    onPrimary = PinkDarkOnPrimary,
    primaryContainer = PinkDarkPrimaryContainer,
    onPrimaryContainer = PinkDarkOnPrimaryContainer,
    secondary = PinkDarkSecondary,
    onSecondary = PinkDarkOnSecondary,
    secondaryContainer = PinkDarkSecondaryContainer,
    onSecondaryContainer = PinkDarkOnSecondaryContainer,
    tertiary = PinkDarkTertiary,
    onTertiary = PinkDarkOnTertiary,
    tertiaryContainer = PinkDarkTertiaryContainer,
    onTertiaryContainer = PinkDarkOnTertiaryContainer,
    error = PinkDarkError,
    onError = PinkDarkOnError,
    errorContainer = PinkDarkErrorContainer,
    onErrorContainer = PinkDarkOnErrorContainer,
    background = PinkDarkBackground,
    onBackground = PinkDarkOnBackground,
    surface = PinkDarkSurface,
    onSurface = PinkDarkOnSurface,
    surfaceVariant = PinkDarkSurfaceVariant,
    onSurfaceVariant = PinkDarkOnSurfaceVariant,
    outline = PinkDarkOutline
)


@Composable
fun ShopFlowKartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkPinkColorScheme
        else -> ElegantLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}