package com.example.techhub.presentation.editarUsuario

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.composable.AboutMeTextField
import com.example.techhub.common.composable.DescriptionTextField
import com.example.techhub.common.composable.ExperienceTextField
import com.example.techhub.common.composable.GitHubTextField
import com.example.techhub.common.composable.LinkedinTextField
import com.example.techhub.common.composable.NameTextField
import com.example.techhub.common.composable.PriceTextField
import com.example.techhub.common.composable.TopBar
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.presentation.perfil.PerfilActivity
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun EditarUsuarioView(
    userInfo: PerfilGeralDetalhadoData
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf(userInfo.nome) }
    var descricao by remember { mutableStateOf(userInfo.descricao) }
    var experiencia by remember { mutableStateOf(userInfo.experiencia) }
    var sobreMim by remember { mutableStateOf(userInfo.sobreMim) }
    var preco by remember { mutableStateOf(userInfo.precoMedio) }
    var linkLinkedin by remember { mutableStateOf(userInfo.linkLinkedin) }
    var linkGithub by remember { mutableStateOf(userInfo.linkGithub) }

    val toastErrorMessage = "Ops! Algo deu errado.\n Tente novamente."


    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                activity = PerfilActivity::class.java,
                context = LocalContext.current,
                title = "Editar Perfil",
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 24.dp,
                    start = 24.dp,
                    end = 24.dp
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Spacer(modifier = Modifier.padding(0.dp))

                NameTextField(initialValue = userInfo.descricao ?: "", onValueChanged = { name = it })

                DescriptionTextField (initialValue = userInfo.descricao ?: "", onValueChanged = { descricao = it })

                Spacer(modifier = Modifier.padding(0.dp))

                ExperienceTextField (initialValue = userInfo.experiencia ?: "", onValueChanged = { experiencia = it })

                Spacer(modifier = Modifier.padding(0.dp))

                AboutMeTextField (initialValue = userInfo.sobreMim ?: "", onValueChanged = { sobreMim = it })

                Spacer(modifier = Modifier.padding(0.dp))

                PriceTextField (initialValue = userInfo.precoMedio.toString(), onValueChanged = { preco = it.toDouble() })
                // Necessário fazer máscara

                Spacer(modifier = Modifier.padding(2.dp))

                Row {
                    Text(
                        text = "Formas de Contato",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                    )
                }
                Spacer(modifier = Modifier.padding(2.dp))

                LinkedinTextField(initialValue = userInfo.linkLinkedin ?: "", onValueChanged = { linkLinkedin = it })

                Spacer(modifier = Modifier.padding(2.dp))

                GitHubTextField(initialValue = userInfo.linkGithub ?: "", onValueChanged = { linkGithub = it })

                ElevatedButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(PrimaryBlue.value),
                        contentColor = Color.White,
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(text = "Salvar", fontSize = 16.sp, fontWeight = FontWeight(500))
                }




                Spacer(modifier = Modifier.padding(12.dp))

                ElevatedButton(
                    onClick = {
                        val extras = Bundle()
                        extras.putInt("id", CurrentUser.userProfile!!.id!!)
                        startNewActivity(context, PerfilActivity::class.java, extras)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(PrimaryBlue.value),
                    ),
                    border = BorderStroke(
                        1.dp, Color(PrimaryBlue.value)
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(text = "Cancelar", fontSize = 16.sp, fontWeight = FontWeight(500))
                }

            }
        }
    }
}