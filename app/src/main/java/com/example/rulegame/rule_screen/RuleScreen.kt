package com.example.rulegame.rule_screen

import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rulegame.R
import com.example.rulegame.utils.NumberUtill
import kotlin.math.roundToInt

@Composable
fun RuleScreen() {
    var rotationValue by remember {
        mutableStateOf(0f)
    }
    var number by remember {
        mutableStateOf(0)
    }
    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing
        ),
        finishedListener = {
            val index = (360f - (rotationValue % 360f)) / (360f / NumberUtill.list.size)
            Log.d("MyLog", "index: ${index}")
            number = NumberUtill.list[index.roundToInt()]
        }
    )
    Log.d("MyLog", "angle: $angle")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = number.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentWidth()
                .wrapContentHeight(),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.ruleta),
                contentDescription = "Ruleta",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(angle)
            )
            Image(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "Arrow",
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                rotationValue = ((0..360).random().toFloat() + 720) + angle
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Red)
        ) {
            Text(
                text = "Start",
                color = Color.White
            )
        }
    }
}