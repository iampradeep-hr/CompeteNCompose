package com.pradeep.taskmanager


import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun TasksScreen(

) {

    val scaffoldState = rememberScaffoldState()
    val showDialog =  remember { mutableStateOf(false) }

    if(showDialog.value)
        CustomDialog(value = "", setShowDialog = {
            showDialog.value = it
        }, collection = "tasks") {
            //do nothing
        }



    Scaffold(
        floatingActionButton = {
            FloatingActionButton(

                shape = RoundedCornerShape(
                    topStart = 30.dp, topEnd = 30.dp, bottomStart = 30.dp, bottomEnd = 2.dp),
                onClick = {
                        showDialog.value=true
                },
                backgroundColor = Color(0xFF1B5E20),
                contentColor = Color(0xFFFFFFFF),

                ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Icon")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Tasks",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {

                    },
                ) {

                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    NoteItem(note = Note("Title","Body")) {

                    }
                    NoteItem(note = Note("Test1","Body")) {

                    }
                    NoteItem(note = Note("Lorem ipsum","orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")) {

                    }
                }
            }
        }
    }
}




