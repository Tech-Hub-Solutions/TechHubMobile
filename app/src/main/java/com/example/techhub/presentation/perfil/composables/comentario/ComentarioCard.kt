package com.example.techhub.presentation.perfil.composables.comentario

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.techhub.common.composable.StarRatingBarFixed
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun ComentarioCard(
    nome: String,
    description: String,
    urlFoto: String,
    rating: Double,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 16.dp,
                        top = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
            ) {
                if (urlFoto.isNullOrBlank()) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "@string/btn_description_profile",
                        tint = PrimaryBlue,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(45.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE4E4E4))
                            .padding(2.dp)
                            .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
                    )
                } else {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(urlFoto)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        contentDescription = "Angular Icon",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(45.dp)
                            .clip(CircleShape)
                    )
                }

                Column(
                    modifier = Modifier,
                    Arrangement.spacedBy(6.dp)
                ) {
                    TextButton(
                        onClick = { /* TODO - Redirecionar para o perfil do usuário */ },
                        shape = RoundedCornerShape(0.dp),
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        content = {
                            Column {
                                Text(
                                    text = nome,
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight(600),
                                    modifier = Modifier.padding(0.dp),
                                )

                                Row(
                                    modifier = Modifier,
                                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                                ) {
                                    Text(
                                        text = "Brasil",
                                        color = Color.Black,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight(200),
                                    )

                                    StarRatingBarFixed(rating = rating, starSize = 8)
                                }
                            }
                        }
                    )
                }
            }

            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight(200),
                textAlign = TextAlign.Justify,
                lineHeight = 24.sp,
                modifier = Modifier.padding(
                    bottom = 16.dp,
                    top = 0.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            )
        }
    )
}