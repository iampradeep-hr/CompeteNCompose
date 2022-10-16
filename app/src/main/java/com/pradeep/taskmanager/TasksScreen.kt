package com.pradeep.taskmanager


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun TasksScreen(

) {
    val context= LocalContext.current

    val list= remember{
        mutableStateListOf<Task>()
    }

    Firebase.db.collection("tasks").get().addOnSuccessListener {
        it.documents.forEach {
            val note=Task(it.get("title").toString(),it.get("content").toString(),it.get("progress").toString())
            list.add(note)

        }
    }.addOnFailureListener{
        Toast.makeText(context,"Something went wrong", Toast.LENGTH_SHORT).show()

    }

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
                items(list){it->
                    TaskItem(task = it) {
                        
                    }
                }
            }
        }
    }
}




