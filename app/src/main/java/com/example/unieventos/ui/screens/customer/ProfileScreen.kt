package com.example.unieventos.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.unieventos.ui.components.customer.CustomerForm
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.ui.components.utils.SecondaryButton
import com.example.unieventos.utils.validateCedula
import com.example.unieventos.utils.validateEmail
import com.example.unieventos.utils.validateFields
import com.example.unieventos.utils.validateName
import com.example.unieventos.utils.validatePasswordFormat
import com.example.unieventos.utils.validatePasswordsMatch
import com.example.unieventos.utils.validatePhone
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

/**
 * ProfileScreen composable is a screen that displays the profile form.
 * @param paddingValues: Padding values
 * @param hazeState: Haze state
 */
@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    hazeState: HazeState
) {
    var cedula by rememberSaveable { mutableStateOf("1234567890") }
    var cedulaError by rememberSaveable { mutableStateOf(CedulaError.NONE) }
    var name by rememberSaveable { mutableStateOf("Jacobo") }
    var nameError by rememberSaveable { mutableStateOf(NameError.NONE) }
    var city by rememberSaveable { mutableStateOf("Armenia") }
    var expandedCity by rememberSaveable { mutableStateOf(false) }
    var phone by rememberSaveable { mutableStateOf("3226843150") }
    var phoneError by rememberSaveable { mutableStateOf(PhoneError.NONE) }
    var email by rememberSaveable { mutableStateOf("jbc@gmail.com") }
    var emailError by rememberSaveable { mutableStateOf(EmailError.NONE) }
    var password by rememberSaveable { mutableStateOf("1234567") }
    var passwordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }
    var confirmPassword by rememberSaveable { mutableStateOf("1234567") }
    var confirmPasswordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .haze(
                state = hazeState,
                style = HazeDefaults.style(backgroundColor = MaterialTheme.colorScheme.surface)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        CustomerForm(
            cedula = cedula,
            onCedulaChange = {
                cedula = it
                cedulaError = validateCedula(it)
            },
            cedulaError = cedulaError,
            name = name,
            onNameChange = {
                name = it
                nameError = validateName(it)
            },
            nameError = nameError,
            phone = phone,
            onPhoneChange = {
                phone = it
                phoneError = validatePhone(it)
            },
            phoneError = phoneError,
            city = city,
            expandedCity = expandedCity,
            onExpandedChange = { expandedCity = it },
            onCitySelected = { city = it },
            email = email,
            onEmailChange = {
                email = it
                emailError = validateEmail(it)
            },
            emailError = emailError,
            password = password,
            onPasswordChange = {
                password = it
                passwordError = validatePasswordFormat(it)
            },
            passwordError = passwordError,
            confirmPassword = confirmPassword,
            onConfirmPasswordChange = {
                confirmPassword = it
                confirmPasswordError = validatePasswordsMatch(password, it)
            },
            confirmPasswordError = confirmPasswordError
        )

        Spacer(modifier = Modifier.height(10.dp))

        PrimaryButton(
            text = stringResource(id = R.string.update_account_btn),
            enabled = cedulaError == CedulaError.NONE &&
                    nameError == NameError.NONE &&
                    phoneError == PhoneError.NONE &&
                    emailError == EmailError.NONE &&
                    passwordError == PasswordError.NONE &&
                    confirmPasswordError == PasswordError.NONE &&
                    validateFields(
                        listOf(
                            cedula,
                            name,
                            phone,
                            city,
                            email,
                            password,
                            confirmPassword
                        )
                    ),
            modifier = Modifier.fillMaxWidth(),
            onClick = { }
        )

        Spacer(modifier = Modifier.height(10.dp))

        SecondaryButton(
            text = stringResource(id = R.string.delete_account_btn),
            modifier = Modifier.fillMaxWidth(),
            onClick = { }
        )
    }
}
