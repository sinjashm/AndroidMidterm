package ca.sheridancollege.midterm.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/* ---------- palette ---------- */
private val LightColors = lightColorScheme(
    primary       = Color(0xFF0068A1),
    onPrimary     = Color.White,
    primaryContainer = Color(0xFFCCE5FF),
    onPrimaryContainer = Color(0xFF001E30),

    secondary     = Color(0xFF33665E),
    onSecondary   = Color.White,
    secondaryContainer = Color(0xFFB8CCC7),
    onSecondaryContainer = Color(0xFF001F1B),

    background    = Color(0xFFFDFDFD),
    onBackground  = Color(0xFF1A1C1E),

    surface       = Color(0xFFFFFFFF),
    onSurface     = Color(0xFF1A1C1E),

    error         = Color(0xFFBA1A1A),
    onError       = Color.White,
)

private val DarkColors = darkColorScheme(
    primary       = Color(0xFF7CC9FF),
    onPrimary     = Color(0xFF003353),
    primaryContainer = Color(0xFF004A75),
    onPrimaryContainer = Color(0xFFCCE5FF),

    secondary     = Color(0xFFA0D0C9),
    onSecondary   = Color(0xFF013731),
    secondaryContainer = Color(0xFF1B4E47),
    onSecondaryContainer = Color(0xFFBDECE4),

    background    = Color(0xFF101213),
    onBackground  = Color(0xFFE2E2E6),

    surface       = Color(0xFF181A1B),
    onSurface     = Color(0xFFE2E2E6),

    error         = Color(0xFFFFB4AB),
    onError       = Color(0xFF690005),
)

/* ---------- typography tweaks (optional) ---------- */
private val AppTypography = Typography(
    titleLarge = Typography().titleLarge.copy(letterSpacing = 0.2.em),
    bodyMedium = Typography().bodyMedium.copy(lineHeight = 20.sp)
)

/* ---------- shapes (optional) ---------- */
private val AppShapes = Shapes(
    small  = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large  = RoundedCornerShape(16.dp)
)

/* ---------- theme wrapper ---------- */
@Composable
fun MidtermTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography  = AppTypography,
        shapes      = AppShapes,
        content     = content
    )
}