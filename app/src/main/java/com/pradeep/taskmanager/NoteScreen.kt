package com.pradeep.taskmanager


import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun NotesScreen(

) {

    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    val showDialog =  remember { mutableStateOf(false) }
    val list= remember{
        mutableStateListOf<Note>()
    }

    Firebase.db.collection("notes").get().addOnSuccessListener {
        it.documents.forEach {
            val note=Note(it.get("title").toString(),it.get("content").toString())
            list.add(note)

        }
    }.addOnFailureListener{
        Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show()

    }

    if(showDialog.value)
        CustomDialog(value = "", setShowDialog = { showDialog.value = it }, collection = "notes") {
            //do nothing
        }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(

                shape = RoundedCornerShape(
                    topStart = 30.dp, topEnd = 30.dp, bottomStart = 30.dp, bottomEnd = 2.dp),
                onClick = { showDialog.value=true  },
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
                    text = "Your notes",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {

                    },
                ) {

                }
            }
            AnimatedVisibility(
                visible =true,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)) {


                items(list){
                    it->
                    NoteItem(Note(it.title,it.content)) {

                    }
                }
            }
        }
    }
}

