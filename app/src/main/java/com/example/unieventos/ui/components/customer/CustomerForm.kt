package com.example.unieventos.ui.components.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.enums.CedulaError
import com.example.unieventos.enums.EmailError
import com.example.unieventos.enums.NameError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.enums.PhoneError
import com.example.unieventos.ui.components.CityField
import com.example.unieventos.ui.components.EmailField
import com.example.unieventos.ui.components.PasswordField

/**
 * A composable that displays a form for the user to input their information.
 * @param cedula The cedula that the user has input.
 * @param cedulaError The error state of the cedula field.
 * @param onCedulaChange A lambda that is called when the user changes the cedula.
 * @param name The name that the user has input.
 * @param nameError The error state of the name field.
 * @param onNameChange A lambda that is called when the user changes the name.
 * @param city The city that the user has selected.
 * @param expandedCity The state of the city field.
 * @param onCitySelected A lambda that is called when the user selects a city.
 * @param onExpandedChange A lambda that is called when the user expands the city field.
 * @param phone The phone that the user has input.
 * @param phoneError The error state of the phone field.
 * @param onPhoneChange A lambda that is called when the user changes the phone.
 * @param email The email that the user has input.
 * @param emailError The error state of the email field.
 * @param onEmailChange A lambda that is called when the user changes the email.
 * @param password The password that the user has input.
 * @param passwordError The error state of the password field.
 * @param onPasswordChange A lambda that is called when the user changes the password.
 * @param confirmPassword The password that the user has input to confirm.
 * @param confirmPasswordError The error state of the confirm password field.
 * @param onConfirmPasswordChange A lambda that is called when the user changes the confirm password.
 */
@Composable
fun CustomerForm(
    cedula: String,
    cedulaError: CedulaError,
    onCedulaChange: (String) -> Unit,
    name: String,
    nameError: NameError,
    onNameChange: (String) -> Unit,
    city: String,
    expandedCity: Boolean,
    onCitySelected: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    phone: String,
    phoneError: PhoneError,
    onPhoneChange: (String) -> Unit,
    email: String,
    emailError: EmailError,
    onEmailChange: (String) -> Unit,
    password: String,
    passwordError: PasswordError,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    confirmPasswordError: PasswordError,
    onConfirmPasswordChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        CedulaField(
            cedula = cedula,
            onCedulaChange = onCedulaChange,
            cedulaError = cedulaError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        NameField(
            name = name,
            onNameChange = onNameChange,
            nameError = nameError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        PhoneField(
            phone = phone,
            onPhoneChange = onPhoneChange,
            phoneError = phoneError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        CityField(
            city = city,
            expanded = expandedCity,
            onExpandedChange = { onExpandedChange(it) },
            onCitySelected = onCitySelected,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        EmailField(
            email = email,
            onEmailChange = onEmailChange,
            emailError = emailError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordField(
            password = password,
            onPasswordChange = onPasswordChange,
            passwordError = passwordError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordField(
            label = stringResource(id = R.string.pass_confirm_lbl),
            password = confirmPassword,
            onPasswordChange = onConfirmPasswordChange,
            passwordError = confirmPasswordError,
            modifier = Modifier.fillMaxWidth()
        )
    }
}