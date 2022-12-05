package com.carqueue.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carqueue.models.Driver
import com.carqueue.models.Student
import kotlinx.coroutines.delay
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverCard(
    driver: Driver,
    onQueue: (driver: Driver) -> Unit ) {

    var expandedState by remember { mutableStateOf(false) }

    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                delayMillis = 5,
                easing = LinearOutSlowInEasing
            )
        )
        .padding(10.dp, 5.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = { expandedState = !expandedState })
        {
            Column(
                modifier = Modifier.padding(5.dp).fillMaxWidth()) {
                Text(
                    text = "${driver.LastName}, ${driver.FirstName}",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                driver.Students.forEach { item ->
                    StudentItem( student = item )}
                if(expandedState){
                        Button(
                            modifier = Modifier
                                .align(alignment = Alignment.End).size(200.dp, 60.dp),
                            onClick = {
                                expandedState = false
                                onQueue(driver)},
                            content = { Text(text = "Queue")})
                }
        }
    }
}

@Composable
fun StudentItem(student: Student) {
    Text(
        modifier = Modifier.padding(start = 50.dp),
        text = "${student.FirstName} ${student.LastName}",
        fontSize = 25.sp)
}


@Preview(showBackground = true)
@Composable
fun PriviewDriverCard() {
    DriverCard(driver =
    Driver(
        UUID.randomUUID(),
        "Andrew",
        "VanderToorn",
        listOf(
            Student(
                UUID.randomUUID(),
                "Eveleyn",
                "VanderToorn"),
            Student(
                UUID.randomUUID(),
                "Sam",
                "VanderToorn"))),
        {driver -> Unit} )
}