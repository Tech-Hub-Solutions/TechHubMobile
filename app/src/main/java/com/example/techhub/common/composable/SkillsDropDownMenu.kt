package com.example.techhub.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assistant
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techhub.R
import com.example.techhub.common.utils.UiText
import com.example.techhub.common.utils.showToastError
import com.example.techhub.common.utils.verificarCorFlag
import com.example.techhub.domain.model.flag.FlagData
import com.example.techhub.presentation.editarUsuario.EditarUsuarioViewModel
import com.example.techhub.presentation.ui.theme.PrimaryBlue


@Composable
fun SkillsDropDownMenu(
    viewModel: EditarUsuarioViewModel,
    categoria: String,
    flagsSkills: SnapshotStateList<FlagData>,
) {
    val flags = viewModel.flags.observeAsState()
    var skill by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current
    val toastErrorMessage = UiText.StringResource(
        R.string.toast_error_skills_dropdown_menu
    ).asString(context = context)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    value = skill,
                    onValueChange = {
                        skill = it
                        expanded = true
                    },
                    label = {
                        Text(
                            UiText.StringResource(
                                R.string.label_text_search_country
                            ).asString(context = context)
                        )
                    },
                    placeholder = {
                        Text(
                            UiText.StringResource(
                                R.string.placeholder_text_skill
                            ).asString(context = context)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color(PrimaryBlue.value),
                        errorCursorColor = Color(PrimaryBlue.value),
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        errorSupportingTextColor = Color.Red.copy(alpha = 0.6f),
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Assistant,
                            contentDescription = UiText.StringResource(
                                R.string.description_image_nome
                            ).asString(context = context),
                            tint = Color(PrimaryBlue.value)
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowDropDown,
                                contentDescription = null,
                            )
                        }
                    }
                )
            }

            AnimatedVisibility(visible = expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .shadow(8.dp),
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = 200.dp)
                            .background(Color.White)
                    ) {
                        items(
                            flags.value!!.filter {
                                it.categoria == categoria && !flagsSkills.contains(it) && it.nome!!.contains(
                                    skill,
                                    ignoreCase = true
                                )
                            }
                        ) {
                            val underlineColor = if (it.categoria != "soft-skill") {
                                verificarCorFlag(it.area!!)
                            } else {
                                Color.Transparent
                            }
                            ItemSelecionavel(
                                title = it.nome!!,
                                underlineColor = underlineColor,
                                underlineHeight = 2
                            ) { title ->
                                if (flagsSkills.size < 10) {
                                    flagsSkills.add(it)
                                    skill = title
                                    expanded = false
                                    skill = ""
                                } else {
                                    showToastError(context = context, message = toastErrorMessage)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




