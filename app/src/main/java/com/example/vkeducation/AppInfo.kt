package com.example.vkeducation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class AppInfo(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val icon: ImageVector,
    val iconTint: Color,
)