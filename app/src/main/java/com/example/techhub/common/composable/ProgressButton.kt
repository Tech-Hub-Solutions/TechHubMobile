package com.example.techhub.common.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun ProgressButton(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color,
    textColor: Color = Color.White,
    height: Int, padding: Int, width: Int,
    isLoading: State<Boolean?>,
) {
    ElevatedButton(
        onClick = { onClick() },
        modifier = Modifier
            .padding(padding.dp)
            .width(width.dp)
            .height(height.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
        ),
        border = BorderStroke(
            1.dp, Color(PrimaryBlue.value)
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        if (isLoading.value == true) {
            CircularProgressIndicatorTH(size = 30.0, color = Color.White)
        } else {
            Text(text = text, fontSize = 16.sp, fontWeight = FontWeight(300))
        }
    }
}