package com.draken.app_movil_pm.core.domain.model

import android.net.Uri

data class CharacterIcon(
    val characterIconNumber: Int = 0,
    val characterIconUri: Uri? = null,
    val characterIconUrl: CharacterIconUrl? = null
)
