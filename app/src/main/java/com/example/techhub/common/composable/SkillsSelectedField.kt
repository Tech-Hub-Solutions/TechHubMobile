package com.example.techhub.common.composable

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.presentation.editarUsuario.composables.ChipSkill

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsSelectedField(
    flags: SnapshotStateList<FlagData>,
    context: Context
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp))
            .wrapContentHeight()
    )
    {
        FlowRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            if (flags.isEmpty()) {
                Text(
                    text = UiText.StringResource(
                        R.string.text_no_skill
                    ).asString(context = context),
                    color = Color(0xFF9E9E9E),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            flags.forEach {
                ChipSkill(flag = it, onDismiss = { flags.remove(it) })
            }

        }
    }
}