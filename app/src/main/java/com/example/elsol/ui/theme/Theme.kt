package com.example.elsol.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/*private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    *//* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    *//*
)*/

private val DarkColorPersonal = darkColorScheme(
    primary = Primary_D,
    secondary = Secondary_D,
    onPrimary = PVariant_D,
    onSecondary = SVariant_D,
    tertiary = fondoDatos,
    background = Background_D,
    error = Error_D,
    surface = Surface_D

)

private val LightColorPersonal = lightColorScheme(
    primary = Primary_L,
    secondary = Secondary_L,
    onPrimary = PVariant_L,
    tertiary = fondoDatos,
    background = Background_L,
    error = Error_L,
    surface = Surface_L
)

@Composable
fun ElSolTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    /*val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )*/
    val coloresApp = when {
        /*dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }*/

        darkTheme -> DarkColorPersonal
        else -> LightColorPersonal
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = coloresApp.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                darkTheme
        }
    }

    MaterialTheme(
        colorScheme = coloresApp,
        typography = Typography,
        content = content
    )
}