package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.techhub.common.composable.BottomBar
import com.example.techhub.common.composable.CircularProgressIndicatorTH
import com.example.techhub.common.composable.FloatingActionButtonScroll
import com.example.techhub.common.enums.UsuarioFuncao
import com.example.techhub.common.utils.startNewActivity
import com.example.techhub.domain.model.CurrentUser
import com.example.techhub.presentation.explorarTalentos.ExplorarTalentosActivity
import com.example.techhub.presentation.perfil.GitHubViewModel
import com.example.techhub.presentation.perfil.PerfilViewModel
import com.example.techhub.presentation.perfil.composables.comentario.ComentariosSection
import com.example.techhub.presentation.perfil.composables.shimmerEffect.ShimmerEffectPerfil

@Composable
fun PerfilView(
    id: Int?,
    viewModel: PerfilViewModel,
    gitHubViewModel: GitHubViewModel = GitHubViewModel()
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val isOwnProfile = id == CurrentUser.userProfile?.id
    val context = LocalContext.current
    val userInfo = viewModel.usuario.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState();
    val isLoadingPerfil = viewModel.isLoadingPerfil.observeAsState()
    val isLoadingWallpaper = viewModel.isLoadingWallpaper.observeAsState()
    val isLoadingCurriculo = viewModel.isLoadingCurriculo.observeAsState()
    val urlCurriculo = viewModel.urlCurriculo.observeAsState()
    val isEmpresa = userInfo.value!!.funcao == UsuarioFuncao.EMPRESA
    val hasError = viewModel.hasError.observeAsState()

    LaunchedEffect(Unit) {
        if (id != null && id != 0) {
            viewModel.getInfosUsuario(context = context, userId = id)
        }
    }

    if (hasError.value!!) {
        startNewActivity(context, ExplorarTalentosActivity::class.java)
    } else {
    Scaffold(
        bottomBar = {
            BottomBar()
        },
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        if (isLoading.value!!) {
            /* Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicatorTH()
            } */
            ShimmerEffectPerfil()
        } else {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(bottom = 60.dp)
            ) {
                // Banner e imagem de perfil
                TopoDoPerfil(
                    userInfo = userInfo,
                    isLoadingPerfil = isLoadingPerfil,
                    isLoadingWallpaper = isLoadingWallpaper,
                    isOwnProfile = isOwnProfile,
                    isLoadingCurriculo = isLoadingCurriculo,
                    isEmpresa = isEmpresa,
                    viewModel = viewModel,
                    context = context,
                    urlCurriculo = urlCurriculo.value ?: ""
                )
                    // Nome e Infos
                    DetalhesUsuario(
                        userInfo = userInfo,
                        context = context
                    )

                    Divider(
                        color = Color.LightGray.copy(alpha = 0.4f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )

                    // Column das informações pós header/banner
                    InformacoesPerfil(
                        userInfo = userInfo,
                        isOwnProfile = isOwnProfile,
                        isEmpresa = isEmpresa,
                        viewModel = viewModel,
                        gitHubViewModel = gitHubViewModel,
                        context = context
                    )

                    // Seção de Comentários
                    ComentariosSection(
                        userInfo = userInfo,
                        viewModel = viewModel,
                        context = context,
                        isOwnProfile = isOwnProfile
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    FloatingActionButtonScroll(
                        isScrolled = scrollState.value > 0,
                        scrollState = scrollState,
                        scope = scope,
                        context = context
                    )
                }
            }
        }
    }
}