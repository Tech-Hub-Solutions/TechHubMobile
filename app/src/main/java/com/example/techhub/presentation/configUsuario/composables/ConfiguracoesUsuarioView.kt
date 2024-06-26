package com.example.techhub.presentation.configUsuario.composables

import android.app.Activity
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.composable.CompanyNameTextField
import com.example.techhub.common.composable.EmailTextField
import com.example.techhub.common.composable.FlagDropDownMenu
import com.example.techhub.common.composable.NameTextField
import com.example.techhub.common.composable.PasswordTextField
import com.example.techhub.common.composable.ProgressButton
import com.example.techhub.common.composable.Switch2FALeft
import com.example.techhub.common.composable.TopBar
import com.example.techhub.common.enums.UsuarioFuncao
import com.example.techhub.common.objects.countryFlagsList
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.base64Images.encodeBase64
import com.example.techhub.common.utils.showToastError
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.domain.model.updateCurrentUser
import com.example.techhub.domain.model.usuario.UsuarioAtualizacaoData
import com.example.techhub.domain.model.usuario.UsuarioSimpleVerifyData
import com.example.techhub.presentation.configUsuario.ConfiguracoesUsuarioViewModel
import com.example.techhub.presentation.login.LoginActivity
import com.example.techhub.presentation.perfil.PerfilActivity
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ConfiguracoesUsuarioView(
    redirectToAuth: (UsuarioSimpleVerifyData) -> Unit,
    viewModel: ConfiguracoesUsuarioViewModel,
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf(CurrentUser.userProfile!!.nome!!) }
    val nacionalidade = remember {
        mutableStateOf(
            countryFlagsList.find { it.acronym == CurrentUser.userProfile!!.pais!! }?.name ?: ""
        )
    }
    var email by remember { mutableStateOf(CurrentUser.email!!) }
    var password by remember { mutableStateOf("") }
    var isUsing2FA by remember { mutableStateOf(CurrentUser.isUsing2FA) }
    val errorApi = viewModel.errorApi.observeAsState().value!!
    val usuarioTokenData = viewModel.usuarioTokenData.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(usuarioTokenData.value) {
        if (!usuarioTokenData.value!!.secret.isNullOrBlank()) {
            redirectToAuth(
                UsuarioSimpleVerifyData(
                    email = email,
                    senha = password,
                    encodedUrl = encodeBase64(usuarioTokenData.value!!.secretQrCodeUrl!!),
                    secretKey = usuarioTokenData.value!!.secret!!
                )
            )
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                activity = PerfilActivity::class.java,
                context = context,
                title = UiText.StringResource(
                    R.string.title_edit_config_usuario,
                ).asString(context = context),
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

            Row {
                Text(
                    text = UiText.StringResource(
                        R.string.text_edit_config_usuario
                    ).asString(context = context),
                    color = Color(GrayText.value),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Spacer(modifier = Modifier.padding(0.dp))

//                NameTextField(
//                    initialValue = CurrentUser.userProfile!!.nome!!,
//                    onValueChanged = { name = it },
//                    context = context,
//                    modifier = Modifier.focusRequester(focusRequester)
//                )

                if (!CurrentUser.isEmpresa) {
                    NameTextField(
                        initialValue = CurrentUser.userProfile!!.nome!!,
                        onValueChanged = { name = it },
                        context = context,
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                } else {
                    CompanyNameTextField(
                        initialValue = CurrentUser.userProfile!!.nome!!,
                        onValueChanged = { name = it },
                        context = context,
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                }

                FlagDropDownMenu(flag = nacionalidade, context = context)

                Spacer(modifier = Modifier.padding(0.dp))

                EmailTextField(
                    initialValue = CurrentUser.email!!,
                    onValueChanged = { email = it },
                    context = context
                )

                PasswordTextField(onValueChanged = { password = it }, context = context)

                Switch2FALeft(
                    initialValue = CurrentUser.isUsing2FA,
                    onValueChanged = { isUsing2FA = it },
                    context = context
                )

                Spacer(modifier = Modifier.padding(0.dp))

            }

            ProgressButton(
                onClick = {
                    val countryCode = countryFlagsList.find { it.name == nacionalidade.value }
                    val usuarioAtualizacaoData =
                        UsuarioAtualizacaoData(
                            name,
                            email,
                            countryCode?.acronym,
                            password,
                            isUsing2FA
                        )

                    if (usuarioAtualizacaoData.nome.isNullOrBlank() || usuarioAtualizacaoData.email.isNullOrBlank() ||
                        usuarioAtualizacaoData.pais.isNullOrBlank() || usuarioAtualizacaoData.senha.isNullOrBlank()
                    ) {
                        (context as Activity).runOnUiThread {
                            showToastError(
                                context,
                                UiText.StringResource(
                                    R.string.toast_text_error_campos_obrigatorios,
                                ).asString(context = context),
                            )
                        }
                    } else {
                        viewModel.atualizarConfigUsuario(
                            usuarioAtualizacaoData,
                            context
                        )

                        if (errorApi.isNotBlank()) {
                            Log.e("Error", "Erro ao atualizar")
                        } else {
                            if (!isUsing2FA) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    delay(1000L)
                                    (context as Activity).runOnUiThread {
                                        showToastError(
                                            context,
                                            UiText.StringResource(
                                                R.string.toast_text_success_atualizar_config_usuario,
                                            ).asString(context = context),
                                        )
                                    }
                                    delay(3000L)
                                    updateCurrentUser(context, usuarioTokenData.value!!, email)
                                    startNewActivity(context, LoginActivity::class.java)
                                }
                            }
                        }
                    }
                },
                text = UiText.StringResource(
                    R.string.btn_text_salvar,
                ).asString(context = context),
                backgroundColor = Color(PrimaryBlue.value),
                height = (60),
                padding = (0),
                width = (385),
                isLoading = isLoading
            )

            Spacer(modifier = Modifier.padding(12.dp))

            ElevatedButton(
                onClick = {
                    val extras = Bundle()
                    extras.putInt("id", CurrentUser.userProfile!!.id!!)
                    startNewActivity(context, PerfilActivity::class.java, extras)
                },
                modifier = Modifier
                    .width(385.dp)
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
                Text(
                    text = UiText.StringResource(
                        R.string.btn_text_cancelar
                    ).asString(context = context),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            }
        }
    }
}
