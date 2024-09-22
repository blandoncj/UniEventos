package com.example.unieventos.ui.components.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.unieventos.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    date: String,
    onDateChange: (String) -> Unit,
    isDatePicked: Boolean,
    onDatePickedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val datePickerState = rememberDatePickerState()

    OutlinedTextField(
        value = date,
        onValueChange = { },
        readOnly = true,
        label = { Text(text = stringResource(id = R.string.date_lbl)) },
        placeholder = { Text(text = stringResource(id = R.string.date_ph)) },
        trailingIcon = {
            IconButton(
                onClick = { onDatePickedChange(true) }
            ) {
                Icon(
                    imageVector = Icons.Rounded.DateRange,
                    contentDescription = "Date"
                )
            }
        },
        modifier = modifier
    )

    if (isDatePicked) {
        DatePickerDialog(
            onDismissRequest = { onDatePickedChange(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDate = datePickerState.selectedDateMillis
                        if (selectedDate != null) {
                            val formattedDate =
                                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                                    Date(selectedDate)
                                )
                            onDateChange(formattedDate)
                        }
                        onDatePickedChange(false)
                    }
                ) {
                    Text(text = stringResource(id = R.string.confirm_btn))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onDatePickedChange(false) }
                ) {
                    Text(text = stringResource(id = R.string.cancel_btn))
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}