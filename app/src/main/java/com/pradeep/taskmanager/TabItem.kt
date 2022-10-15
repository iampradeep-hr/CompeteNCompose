package com.pradeep.taskmanager

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource

typealias ComposableFun = @Composable ()->Unit


sealed class TabItem(val title:String,val icons:Int, val screens:ComposableFun) {

    object Home : TabItem(title = "Tasks",icons= R.drawable.task, screens = { TaskScreen() })
    object Cart: TabItem(title = "Notes",icons = R.drawable.notes, screens={ NoteScreen() })


}

