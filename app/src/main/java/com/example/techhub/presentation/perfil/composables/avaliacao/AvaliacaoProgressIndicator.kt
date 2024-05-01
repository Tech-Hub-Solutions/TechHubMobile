package com.example.techhub.presentation.perfil.composables.avaliacao

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun AvaliacaoProgressIndicator(totalProgress: Float) {
    // TODO - Caso o Text do AvaliacaoCountRow tiver dois digitos, ele desalinha o LinearProgressIndicator
    LinearProgressIndicator(
        progress = totalProgress,
        color = PrimaryBlue,
        modifier = Modifier
            .height(16.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(8.dp)),
        trackColor = Color.LightGray,
        strokeCap = StrokeCap.Round
    )
}