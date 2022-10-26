package com.pradeep.taskmanager

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pradeep.taskmanager.ui.theme.RedOrange

@Composable
fun TaskItem(
    task:Task,
    modifier: Modifier = Modifier.padding(8.dp),
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDeleteClick: () -> Unit
) {

    val context= LocalContext.current
    Box(
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = RedOrange,
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )

            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text =task.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            CircularProgressBar(percentage = task.progress.toFloat(), number = 100)


        }
        IconButton(
            onClick = {
                Firebase.db.collection("tasks").whereEqualTo("title",task.title).get().addOnSuccessListener {
                    it.documents.forEach {
                        val docID=it.id
                        Firebase.db.collection("notes").document(docID).delete().addOnSuccessListener {
                            Toast.makeText(context,"Deleted", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener{
                            Toast.makeText(context,"Something went wrong!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }.addOnFailureListener{
                    Toast.makeText(context,"Something went wrong!", Toast.LENGTH_SHORT).show()
                }


            },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete note",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}


@Composable
fun CircularProgressBar(
    percentage:Float,
    number:Int,
    fontSize: TextUnit =28.sp,
    radius: Dp =50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp =8.dp,
    animDuration:Int=2000,
    animDelay:Int=0
) {

    var animationPlayed by remember{
        mutableStateOf(false)
    }

    val currPercentage = animateFloatAsState(
        targetValue = if (animationPlayed)percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true){
        animationPlayed=true
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius*2f)){
            drawArc(
                color=color,
                startAngle = -90f,
                sweepAngle = 360*currPercentage.value,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(
            modifier = Modifier.clickable {
                animationPlayed=!animationPlayed
            },
            text =(currPercentage.value*number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold

        )

    }

}