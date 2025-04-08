package pt.ipg.diceroller

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pt.ipg.diceroller.ui.theme.DiceRollerTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }

    @Preview
    @Composable
    fun DiceRollerApp() {
        DiceWithButtonAndImage(
            modifier = Modifier
                //preencher o layout com a tela inteira
                .fillMaxSize()
                //centralizar o conteudo na vertical e na horizontal
                .wrapContentSize(Alignment.Center)
        )
    }

    @Composable
    fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
        //varivel para manter o valor do dado durante recomposiçoes
        var result by remember { mutableStateOf(1) }
        //imagem dependendo do valor do dado
        val imageResource= when (result) {
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        //coluna
        Column(
            //modifier modifica a aparencia e o comportamento como tamanho, cor alinhamento
            modifier = modifier,
            //centralizar o conteudo na horizontal
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                //carregar uma imagem do diretorio res/drawable
                // vai mudar o dado e vai mudar a imagem
                painter = painterResource(imageResource),
                //descrição da imagem
                contentDescription = "1"
            )
            //espaçamento entre os elementos imagem e botão
            Spacer(modifier = Modifier.height(16.dp))
            //Criar um botão, e faz  um numero aleatorio
            Button(onClick = {result = (1..6).random() }) {
                //Cria um texto
                Text(stringResource(R.string.roll))
            }
        }
    }
}
