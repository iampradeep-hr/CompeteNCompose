package com.pradeep.taskmanager.auth


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pradeep.taskmanager.MainContent
import com.pradeep.taskmanager.ui.theme.background


@Composable
fun HomeScreen(user: User){
    Toast.makeText(LocalContext.current,"Hello, ${user.displayName}",Toast.LENGTH_SHORT).show()
    MainContent()
}