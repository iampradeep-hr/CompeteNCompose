package com.pradeep.taskmanager

import android.R
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.firebase.firestore.FirebaseFirestore
import java.security.MessageDigest

@Composable
fun CustomDialog(value: String,collection:String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {

    val txtFieldError = remember { mutableStateOf("") }
    val title = remember { mutableStateOf(value) }
    val body = remember { mutableStateOf(value) }

    val scrollState= rememberScrollState()
    val context= LocalContext.current

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(scrollState)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Add New",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = colorResource(id = if (txtFieldError.value.toString().trim().isEmpty()) R.color.holo_green_light else R.color.holo_red_dark)
                                ),
                                shape = RoundedCornerShape(20)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text(text = "Title goes here...") },
                        value = title.value,
                        maxLines = 20,
                        onValueChange = {
                            title.value = it
                        })

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = colorResource(id = if (txtFieldError.value.trim().isEmpty()) R.color.holo_green_light else R.color.holo_red_dark)
                                ),
                                shape = RoundedCornerShape(20)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text(text = "Body goes here...") },
                        value = body.value,
                        maxLines = 20,
                        onValueChange = {
                            body.value = it
                        })

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                if (title.value.isEmpty()) {
                                    txtFieldError.value = "Field can not be empty"
                                    return@Button
                                }else{
                                    Firebase.db.collection(collection).document().set(Note(title.value.trim().toString(),body.value.trim().toString()))
                                        .addOnSuccessListener {
                                            Toast.makeText(context,"success", Toast.LENGTH_SHORT).show()
                                        }.addOnFailureListener {
                                            Toast.makeText(context,"failed", Toast.LENGTH_SHORT).show()
                                        }
                                }
                                setValue(title.value)
                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "Save")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))


                }
            }
        }
    }
}