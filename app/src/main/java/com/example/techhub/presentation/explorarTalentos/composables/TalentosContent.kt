package com.example.techhub.presentation.explorarTalentos.composables

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.techhub.R
import com.example.techhub.common.composable.CircularProgressIndicatorTH
import com.example.techhub.common.composable.CustomizedElevatedButton
import com.example.techhub.common.composable.FloatingActionButtonScrollLazyColumn
import com.example.techhub.common.composable.UserCard
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.shadowCustom
import com.example.techhub.common.utils.showToastError
import com.example.techhub.composable.OrderDropDownMenu
import com.example.techhub.domain.model.usuario.UsuarioFiltroData
import com.example.techhub.presentation.explorarTalentos.ExplorarTalentosViewModel
import com.example.techhub.presentation.explorarTalentos.composables.shimmerEffect.ShimmerEffectExplorarTalentos
import com.example.techhub.presentation.ui.theme.GrayLoadButton
import com.example.techhub.presentation.ui.theme.GrayText
import com.example.techhub.presentation.ui.theme.GrayTinyButton
import com.example.techhub.presentation.ui.theme.PrimaryBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun TalentosContent(
    viewModel: ExplorarTalentosViewModel,
    filtro: UsuarioFiltroData,
    context: Context,
    ordem: MutableState<String>,
    drawerState: DrawerState,
) {
    val talentos = viewModel.talentos.observeAsState().value!!
    val erroApi = viewModel.erroApi.observeAsState().value!!
    val isLoading = viewModel.isLoading.observeAsState()
    val isLoadingMoreTalents = viewModel.isloadingMoreTalents.observeAsState()
    val isLastPage = viewModel.isLastPage.observeAsState().value!!
    val totalElements = viewModel.totalElements.observeAsState().value!!
    val page = remember { mutableStateOf(0) }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var isScrolled by remember { mutableStateOf(false) }


    if (erroApi.isNotEmpty()) {
        showToastError(context = context, message = erroApi)
    }

    LaunchedEffect(Unit) {
        viewModel.getFlags()
    }

    LaunchedEffect(filtro, ordem.value) {
        page.value = 0
        viewModel.getTalentos(0, 10, ordem.value, filtro)
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex > 0 }
            .distinctUntilChanged()
            .collect { scrolled ->
                Log.d("SCROLL STATE", "isScrolled: $scrolled")
                isScrolled = scrolled
            }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = UiText.StringResource(
                R.string.text_total_talentos,
                totalElements.toString()
            ).asString(context = context),
            modifier = Modifier.fillMaxWidth(0.4f),
            color = GrayText
        )

        OrderDropDownMenu(ordem, context)

        IconButton(
            onClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            },
            modifier = Modifier
                .size(86.dp, 25.dp)
                .background(Color(GrayTinyButton.value))
                .padding(0.dp),
        ) {
            Row(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val color = if (
                    filtro.tecnologiasIds?.isEmpty() == true
                    && filtro.area.isNullOrEmpty()
                    && filtro.nome.isNullOrEmpty()
                    && filtro.precoMax == null
                    && filtro.precoMin == null
                ) {
                    GrayText
                } else {
                    PrimaryBlue
                }
                Text(
                    text = UiText.StringResource(
                        R.string.text_filter
                    ).asString(context = context),
                    fontSize = 14.sp,
                    color = color
                )
                Image(
                    painter = painterResource(id = R.mipmap.filter),
                    contentDescription = UiText.StringResource(
                        R.string.text_filter
                    ).asString(context = context),
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }

    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    if (erroApi.isNotEmpty()) {
        Text(erroApi, color = Color.Red, fontSize = 16.sp)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
    ) { innerPadding ->
        if (isLoading.value!!) {
            ShimmerEffectExplorarTalentos()
        } else {
            if (talentos.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.85f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.search_rafiki),
                        contentDescription = UiText.StringResource(
                            R.string.text_no_talents_found
                        ).asString(context = context),
                    )
                    Text(
                        text = UiText.StringResource(R.string.text_no_talents_found)
                            .asString(context = context),
                        color = GrayText,
                        fontSize = 20.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val subLists = talentos.chunked(2)
                    var count = 0
                    itemsIndexed(subLists) { index, subLista ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start)
                        ) {
                            subLista.forEachIndexed { index, item ->
                                count++;
                                UserCard(
                                    item,
                                    talentos,
                                    isAbleToFavorite = false,
                                    modifier = Modifier
                                        .weight(1f, false)
                                )

                                if (index == subLista.size - 1 && subLista.size % 2 != 0) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                    item {
                        if (!isLastPage && talentos.isNotEmpty()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CustomizedElevatedButton(
                                    onClick = {
                                        viewModel.getTalentos(++page.value, 10, ordem.value, filtro)
                                    },
                                    horizontalPadding = 16,
                                    verticalPadding = 8,
                                    defaultElevation = 0,
                                    pressedElevation = 0,
                                    containerColor = Color(GrayLoadButton.value),
                                    contentColor = Color(0xFF505050),
                                    shape = RoundedCornerShape(50),
                                    horizontalArrangement = Arrangement.spacedBy(
                                        8.dp,
                                        Alignment.CenterHorizontally
                                    ),
                                    text = UiText.StringResource(
                                        R.string.btn_text_load_more_talents
                                    ).asString(context = context),
                                    fontSize = 16,
                                    fontWeight = FontWeight.Medium,
                                    contentDescription = UiText.StringResource(
                                        R.string.btn_description_load_more_talents
                                    ).asString(context = context),
                                    isLoading = isLoadingMoreTalents.value!!
                                )
                            }
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd,
        ) {
            FloatingActionButtonScrollLazyColumn(
                isScrolled = isScrolled,
                listState = listState,
                scope = scope,
                context = context
            )
        }
    }
}
