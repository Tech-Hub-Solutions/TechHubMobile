package com.example.techhub.presentation.comparar.composables.accordion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techhub.domain.model.accordion.AccordionModel

@Composable
fun Accordion(model: AccordionModel) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        Modifier
            .padding(horizontal = 0.dp, vertical = 1.dp)
    ) {
        AccordionHeader(title = model.area, isExpanded = expanded) {
            expanded = !expanded
        }

        AnimatedVisibility(visible = expanded) {
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .background(Color.White)
            ) {
                Column {
                    model.rows.forEach { row ->
                        AccordionRow(row)
                        Divider(color = Color(0xFFDDDDDD), thickness = 1.dp)
                    }
                }
            }
        }
    }
}