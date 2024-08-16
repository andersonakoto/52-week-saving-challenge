package com.example.a52weeksaver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a52weeksaver.db.entities.SavingsData
import com.example.a52weeksaver.viewModels.SavingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var savingsData = SavingsData()

    private val savingsViewModel : SavingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeekSavingsApp(savingsViewModel, savingsData)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeekSavingsApp(savingsViewModel: SavingsViewModel, savingsData: SavingsData) {
    val savingsList by savingsViewModel.allSavingsData.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var weekNumber by remember { mutableStateOf("") }
        var amount by remember { mutableStateOf("") }
        var mpesaRef by remember { mutableStateOf("") }

        var selectedWeek by remember { mutableStateOf(1) }
        var expanded by remember { mutableStateOf(false) }
        val weeks = (1..52).toList()

//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = !expanded }
//        ) {
//            TextField(
//                readOnly = true,
//                value = "Week $selectedWeek",
//                onValueChange = {},
//                label = { Text("Select Week") },
//                modifier = Modifier.fillMaxWidth(),
//                trailingIcon = {
//                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
//                }
//            )
//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false }
//            ) {
//                weeks.forEach { week ->
//                    DropdownMenuItem(
//                        onClick = {
//                            selectedWeek = week
//                            expanded = false
//                        },
//                        text = { Text(text = "Week $week")})
//                    }
//                }
//            }
//
//        LazyColumn(
//            modifier = Modifier.height(height = 100.dp)
//        ) {
//            items(52) { index ->
//                val week = index + 1
//                val amount = 50.0 * week
//                SavingsData(weekNumber = week, savedAmount = amount)
//            }
//        }

        TextField(
            value = weekNumber,
            onValueChange = { weekNumber = it },
            label = { Text("Week Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = mpesaRef,
            onValueChange = { mpesaRef = it },
            label = { Text("MPESA REF CODE") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val weekNum = weekNumber.toIntOrNull()
                val amt = amount.toDoubleOrNull()
                val mpesaref = mpesaRef

                val df: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                val timestamp = df.format(Calendar.getInstance().time).toString()

                savingsData.savedAmount = amt
                savingsData.weekNumber = weekNum
                savingsData.mpesaRef = mpesaref
                savingsData.timeStamp = timestamp

                if (weekNum != null && amt != null) {
                    savingsViewModel.insertSavingsData(savingsData)
                    weekNumber = ""
                    amount = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(savingsList.size) { index ->
                val saving = savingsList[index]
                WeekSavingItem(savingsData = saving)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WeekSavingItem(savingsData: SavingsData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Week ${savingsData.weekNumber}")
        Text(text = "$${savingsData.savedAmount}")
    }
}

