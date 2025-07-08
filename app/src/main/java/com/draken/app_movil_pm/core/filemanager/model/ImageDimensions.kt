package com.draken.app_movil_pm.core.filemanager.model

data class ImageDimensions(
    val width: Int,
    val height: Int
) {
    val aspectRatio: Float get() = width.toFloat() / height.toFloat()
    val isLandscape: Boolean get() = width > height
    val isPortrait: Boolean get() = height > width
    val isSquare: Boolean get() = width == height
}
