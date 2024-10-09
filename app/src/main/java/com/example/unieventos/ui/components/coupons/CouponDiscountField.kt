package com.example.unieventos.ui.components.coupons

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.unieventos.R

/**
 * CouponDiscountField is a composable that displays a text field for the user to input the coupon's discount.
 * @param discount The discount that the user has input.
 * @param modifier The modifier for the CouponDiscountField.
 * @param expanded The state of the dropdown menu.
 * @param onExpandedChange The callback to change the state of the dropdown menu.
 * @param onDiscountSelected The callback to select a discount.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CouponDiscountField(
    discount: String,
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDiscountSelected: (String) -> Unit
) {
    val discounts =
        listOf(
            "5",
            "10",
            "15",
            "20",
            "25",
            "30",
            "35",
            "40",
            "45",
            "50"
        )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onExpandedChange(!expanded) },
    ) {
        OutlinedTextField(
            value = discount,
            onValueChange = { },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            label = { Text(text = stringResource(id = R.string.disc_lbl)) },
            placeholder = { Text(text = stringResource(id = R.string.cpn_disc_ph)) },
            modifier = Modifier
                .menuAnchor()
                .then(modifier)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            discounts.forEach() { discount ->
                DropdownMenuItem(
                    text = { Text(text = discount) },
                    onClick = {
                        onDiscountSelected(discount)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}