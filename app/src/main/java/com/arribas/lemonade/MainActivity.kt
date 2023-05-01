package com.arribas.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arribas.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeProcess() {
    LemonadeTheme {
        LemonApp()
    }
}

@Composable
fun LemonApp() {

    var keepCount by remember { mutableStateOf(0) }
    var step by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when(step){
            1-> { PrintLemonApp(
                text = R.string.step_1,
                drawable = R.drawable.lemon_tree,
                description = R.string.step_description_1,

                click = {
                    step = 2
                    keepCount = (2..4).random()
                })
            }

            2-> { PrintLemonApp(
                text = R.string.step_2,
                drawable = R.drawable.lemon_squeeze,
                description = R.string.step_description_2,
                click = {
                    keepCount--

                    if(keepCount == 0) {
                        step = 3
                    }
                })
            }

            3-> { PrintLemonApp(
                text = R.string.step_3,
                drawable = R.drawable.lemon_drink,
                description = R.string.step_description_3,
                click = {step = 4})
            }

            4-> { PrintLemonApp(
                text = R.string.step_4,
                drawable = R.drawable.lemon_restart,
                description = R.string.step_description_4,
                click = {step = 1})
            }
        }

    }
}

@Composable
fun PrintLemonApp(
    text: Int,
    drawable: Int,
    description: Int,
    click: () -> Unit,
    modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()){

        Text(
            text = stringResource(id = text),
            fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = drawable),
            contentDescription = stringResource(id = description),
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize()
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(onClick = click))
    }
}