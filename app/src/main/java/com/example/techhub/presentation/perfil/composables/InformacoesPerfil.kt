package com.example.techhub.presentation.perfil.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.techhub.domain.model.perfil.PerfilGeralDetalhadoData
import com.example.techhub.presentation.perfil.composables.avaliacao.AvaliacaoSection
import com.example.techhub.presentation.perfil.composables.informacoesAdicionais.InformacoesAdicionaisSection

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InformacoesPerfil(
    userInfo: State<PerfilGeralDetalhadoData?>,
    isEmpresa: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        TextContainer(
            title = "Experiência",
            description = "${userInfo.value!!.experiencia}"
        )

        TextContainer(
            title = if (isEmpresa) "Quem procuramos" else "Sobre mim",
            description = userInfo.value!!.sobreMim ?: "sem descrição"
        )

        TagsSection(
            title = if (isEmpresa) "Valores" else "Soft Skills",
            flags = userInfo.value!!.flags!!.filter {
                it.categoria == "soft-skill"
            }
        )

        Divider(
            color = Color.LightGray.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        if (!isEmpresa) {
            TagsSection(
                title = "Hard Skills",
                flags = userInfo.value!!.flags!!.filter {
                    it.categoria == "hard-skill"
                }
            )
            Divider(
                color = Color.LightGray.copy(alpha = 0.4f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }


        if (!isEmpresa) {
            Column(modifier = Modifier.fillMaxSize()) {
                SectionTitle(title = "Projetos desenvolvidos", isCentered = false)

                FlowRow(
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    GitHubProjectCard()
                    GitHubProjectCard()
                    GitHubProjectCard()
                }
            }

            Divider(
                color = Color.LightGray.copy(alpha = 0.4f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }

        // Seção de Avaliações
        AvaliacaoSection(totalRating = 4.0)

        Divider(
            color = Color.LightGray.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        // Seção de Informações Adicionais
        InformacoesAdicionaisSection(
            projetosFinalizados = 10,
            empresasInteressadas = 5,
            recomendacoes = 3
        )
    }
}