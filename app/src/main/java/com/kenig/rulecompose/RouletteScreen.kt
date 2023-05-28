package com.kenig.rulecompose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kenig.rulecompose.ui.theme.GreenBackground
import com.kenig.rulecompose.ui.theme.RedButton
import kotlin.math.roundToInt


@Preview(showBackground = true)
@Composable
fun RuleScreen() {
    var rotationValue by remember { //(переменная на поворот картинки со значением float)
        mutableStateOf(0f)
    }

    var number by remember{
        mutableStateOf(0)
    }

    val angle: Float by animateFloatAsState( //(анимирует поворот картинки)
        targetValue = rotationValue,
        animationSpec = tween(durationMillis = 5000),
        finishedListener = {//(it - это и есть rotationValue)
            val index = (360f - (it % 360)) / (360f / NumberUtil.list.size)
            number = NumberUtil.list[index.roundToInt()]
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenBackground),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .wrapContentWidth(),
            text = number.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = Color.White
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Image(
                painter = painterResource(R.drawable.roulette),
                contentDescription = "roulette",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(angle) //(из-за angle картинка с рулеткой будет крутиться плавно)
            )
            Image(
                painter = painterResource(R.drawable.arrow),
                contentDescription = "arrow",
                modifier = Modifier.fillMaxSize()
            )
        }

        Button(
            onClick = { rotationValue = (720 .. 1080).random().toFloat() + angle },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, bottom = 100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = RedButton)
        ) {
            Text(
                text = "Spin",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}