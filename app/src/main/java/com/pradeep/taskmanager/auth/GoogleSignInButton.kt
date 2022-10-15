package com.pradeep.taskmanager.auth

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pradeep.taskmanager.R
import com.pradeep.taskmanager.ui.theme.Shapes
import com.pradeep.taskmanager.ui.theme.background


@ExperimentalMaterialApi
@Composable
fun GoogleSignInButtonUi(
    text : String = "",
    loadingText: String = "" ,
    onClicked:() -> Unit){

    var clicked by remember { mutableStateOf(false)}
    
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = background),
    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Column(modifier = Modifier
            .fillMaxWidth().padding(8.dp)
            .wrapContentHeight()) {
            Box(modifier = Modifier
                .wrapContentSize()

            ){
                Text(
                    text = buildAnnotatedString {
                        append("Take ")
                        withStyle(
                            SpanStyle(
                                color = Color.Black,
                                fontSize = 40.sp,
                            )
                        ){
                            append("Notes ")
                        }
                        append("Take ")

                        withStyle(
                            SpanStyle(color = Color.Black, fontSize = 40.sp, textDecoration = TextDecoration.Underline)
                        ){
                            append("Action")
                        }
                        append("& ")
                        withStyle(
                            SpanStyle(color = Color.Black, fontSize = 40.sp, textDecoration = TextDecoration.Underline)
                        ){
                            append("Hit Every Deadline")
                        }


                    },
                    color = Color.White,
                    fontSize = 30.sp,
                    fontStyle = FontStyle.Italic,

                )

            }
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, modifier =Modifier.align(Alignment.CenterHorizontally))
        }
        
        
        
        Surface(
            onClick = {clicked = !clicked},
            shape = Shapes.medium,
            border = BorderStroke(width = 1.dp,color = Color.LightGray),
            color = MaterialTheme.colors.surface
        ) {

            Row (modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
                ),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
                Icon(painter = painterResource(id = R.drawable.google), contentDescription = "Google sign button", tint = Color.Unspecified)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = if (clicked) loadingText else text)

                if (clicked){
                    Spacer(modifier = Modifier.width(16.dp))
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colors.primary
                    )
                    onClicked()
                }

            }
        }
        
        
        
    }
    



}
