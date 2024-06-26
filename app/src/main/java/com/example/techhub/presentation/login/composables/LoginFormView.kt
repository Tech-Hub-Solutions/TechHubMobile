package com.example.techhub.presentation.login.composables

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.common.composable.CenteredImageSection
import com.example.techhub.common.composable.TopBar
import com.example.techhub.domain.model.usuario.UsuarioLoginData
import com.example.techhub.presentation.index.IndexActivity
import com.example.techhub.R
import com.example.techhub.common.composable.CircularProgressIndicatorTH
import com.example.techhub.common.composable.ElevatedButtonTH
import com.example.techhub.common.composable.EmailTextField
import com.example.techhub.common.composable.PasswordTextField
import com.example.techhub.common.composable.ProgressButton
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.presentation.cadastro.CadastroActivity
import com.example.techhub.presentation.login.LoginViewModel
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.PrimaryBlue

@Composable
fun LoginFormView(
    viewModel: LoginViewModel = LoginViewModel(),
    onAuthSucess: (UsuarioLoginData) -> Unit
) {
    val context = LocalContext.current
    val isLoading = viewModel.isLoading.observeAsState();
    val (user, userSetter) = remember {
        mutableStateOf(UsuarioLoginData())
    }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                activity = IndexActivity::class.java,
                context = context,
                title = UiText.StringResource(
                    R.string.title_login
                ).asString(context = context),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(12.dp))

            CenteredImageSection(
                imagePath = R.mipmap.login_image,
                contentDescription = UiText.StringResource(
                    R.string.description_image_login
                ).asString(context = context),
                width = 252,
                height = 300,
            )

            Spacer(modifier = Modifier.padding(24.dp))

            Row {
                Text(
                    text = UiText.StringResource(
                        R.string.text_login
                    ).asString(context = context),
                    color = Color(GrayText.value),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.padding(12.dp))

            Column(
                modifier = Modifier
                    .width(350.dp)
            ) {
                EmailTextField(
                    onValueChanged = { userSetter(user.copy(email = it)) },
                    context = context,
                    modifier = Modifier.focusRequester(focusRequester)
                )

                PasswordTextField(
                    context = context,
                    onValueChanged = { userSetter(user.copy(senha = it)) }
                )
            }

            Spacer(modifier = Modifier.padding(12.dp))

            ProgressButton(
                onClick = { viewModel.loginUser(user, context, onAuthSucess) },
                text = UiText.StringResource(
                    R.string.btn_text_entrar
                ).asString(context = context),
                backgroundColor = Color(PrimaryBlue.value),
                height = (60),
                width = (350),
                padding = (10),
                isLoading = isLoading
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = UiText.StringResource(
                        R.string.text_login_redirect_cadastro
                    ).asString(context = context),
                    color = Color.Black,
                    fontWeight = FontWeight.Thin,
                    fontSize = 14.sp
                )

                TextButton(onClick = {
                    startNewActivity(
                        context = context,
                        CadastroActivity::class.java
                    )
                }) {
                    Text(
                        text = UiText.StringResource(
                            R.string.btn_text_cadastre_se
                        ).asString(context = context),
                        color = Color(PrimaryBlue.value),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Thin,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}
