package com.example.sepia.ui.theme

import androidx.compose.ui.graphics.Color

// ── Sepia unified palette ─────────────────────────────────────────────────────
object SepiaColors {
    // Backgrounds
    val ScreenBackground = Color(0xFFF5F0E8)   // warm off-white
    val CardBackground   = Color(0xFFFFFFFF)   // pure white cards
    val HeaderBackground = Color(0xFF1C2333)   // dark navy header

    // Brand / accent
    val Primary          = Color(0xFFE8652A)   // alias used by components
    val Orange           = Primary             // same token, friendlier name
    val OrangeLight      = Color(0xFFFFF0E8)   // soft orange tint

    // Chips
    val ChipUnselected   = Color(0xFFF5F0E8)   // matches screen bg — blends in

    // Text
    val TextPrimary      = Color(0xFF1C2333)
    val TextSecondary    = Color(0xFF888888)

    // UI chrome
    val Divider          = Color(0xFFEEEEEE)
    val BorderColor      = Color(0xFFE0D8CE)   // subtle warm border for cards
    val TrackOff         = Color(0xFFCCCCCC)
    val StorageTrack     = Color(0xFFDDDDDD)
}