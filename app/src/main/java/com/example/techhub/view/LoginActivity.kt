package com.example.techhub.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.IndexActivity
import com.example.techhub.composable.CenteredImageSection
import com.example.techhub.R
import com.example.techhub.composable.ElevatedButtonTH
import com.example.techhub.composable.EmailTextField
import com.example.techhub.composable.PasswordTextField
import com.example.techhub.composable.SetBarColor
import com.example.techhub.composable.StartNewActivity
import com.example.techhub.composable.TopBar
import com.example.techhub.service.RetrofitService
import com.example.techhub.service.usuario.dto.UsuarioLoginData
import com.example.techhub.service.usuario.dto.UsuarioTokenData
import com.example.techhub.ui.theme.GrayText
import com.example.techhub.ui.theme.PrimaryBlue
import com.example.techhub.ui.theme.TechHubTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TechHubTheme {
                SetBarColor(color = Color.White)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginContent(context = this)
                }
            }
        }
    }
}

@Composable
fun LoginContent(context: Context) {
    var email = remember { mutableStateOf("") }
    var senha = remember { mutableStateOf("") }

    fun loginUser() {
        val user = UsuarioLoginData(
            email = email.value,
            senha = senha.value
        )
        val usuarioService = RetrofitService.getUsuarioService()

        usuarioService.loginUser(user).enqueue(object : Callback<UsuarioTokenData> {
            override fun onResponse(
                call: Call<UsuarioTokenData>,
                response: Response<UsuarioTokenData>
            ) {
                val responseBody = response.body()

                Log.d("LoginActivity => ", "Response: $responseBody")
                if (responseBody != null) {
                    response.body()?.token?.let {
                        StartNewActivity(context, LoginAuthActivity::class.java)
                    } ?: showError(context)
                    // TODO - encontrar forma de usar DataStore p/ storeData(token)
                    // val token = response.body()!!.token
                } else {
                    Toast.makeText(
                        context,
                        "Ops! Algo deu errado." +
                                "\n Tente novamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // TODO - Retirar print
                    println(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<UsuarioTokenData>, t: Throwable) {
                showError(context)
                // TODO - Retirar print
                println("Erro ao logar: ${t.message}")
            }
        })
    }


    Scaffold(
        topBar = {
            TopBar(
                willRedirectToActivity = true,
                activity = IndexActivity::class.java,
                context = context,
                title = "Login",
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
                    start = 16.dp,
                    end = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(12.dp))

            CenteredImageSection(
                imagePath = R.mipmap.login_image,
                contentDescription = "@string/description_image_login",
                width = 252,
                height = 300,
            )

            Spacer(modifier = Modifier.padding(24.dp))

            Row {
                Text(
                    text = "Entre com sua conta para uma \n" +
                            "experiência melhor!",
                    color = Color(GrayText.value),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.padding(12.dp))

            Column(
                modifier = Modifier
                    .width(350.dp)
            ) {
                EmailTextField { email.value = it }

                PasswordTextField { senha.value = it }
            }

            Spacer(modifier = Modifier.padding(12.dp))

            ElevatedButtonTH(
                onClick = { loginUser() },
                text = "Entrar",
                backgroundColor = Color(PrimaryBlue.value),
                width = (350),
                height = (60),
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Não tem uma conta?",
                    color = Color.Black,
                    fontWeight = FontWeight.Thin,
                    fontSize = 14.sp
                )

                TextButton(onClick = {
                    StartNewActivity(
                        context = context,
                        CadastroActivity::class.java
                    )
                }) {
                    Text(
                        text = "Cadastre-se.",
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

fun showError(context: Context) {
    Toast.makeText(
        context,
        "Ops! Algo deu errado.\n Tente novamente.",
        Toast.LENGTH_SHORT
    ).show()
}
