package com.example.unieventos.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unieventos.R
import com.example.unieventos.enums.CedulaError
import com.example.unieventos.enums.EmailError
import com.example.unieventos.enums.NameError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.enums.PhoneError
import com.example.unieventos.ui.components.BackButton
import com.example.unieventos.ui.components.CedulaField
import com.example.unieventos.ui.components.CityField
import com.example.unieventos.ui.components.EmailField
import com.example.unieventos.ui.components.NameField
import com.example.unieventos.ui.components.PasswordField
import com.example.unieventos.ui.components.PhoneField
import com.example.unieventos.ui.components.PrimaryButton
import com.example.unieventos.utils.validateCedula
import com.example.unieventos.utils.validateEmail
import com.example.unieventos.utils.validateEmailFormat
import com.example.unieventos.utils.validateFields
import com.example.unieventos.utils.validateName
import com.example.unieventos.utils.validatePasswordFormat
import com.example.unieventos.utils.validatePasswordsMatch
import com.example.unieventos.utils.validatePhone

/**
 * Signup screen composable function.
 * This composable function displays the signup screen.
 */
@Composable
fun SignupScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToConfirmAccount: () -> Unit
) {
    var cedula by rememberSaveable { mutableStateOf("") }
    var cedulaError by rememberSaveable { mutableStateOf(CedulaError.NONE) }
    var name by rememberSaveable { mutableStateOf("") }
    var nameError by rememberSaveable { mutableStateOf(NameError.NONE) }
    var city by rememberSaveable { mutableStateOf("") }
    var expandedCity by rememberSaveable { mutableStateOf(false) }
    var phone by rememberSaveable { mutableStateOf("") }
    var phoneError by rememberSaveable { mutableStateOf(PhoneError.NONE) }
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(EmailError.NONE) }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var confirmPasswordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }

    val scrollState = rememberScrollState()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                BackButton(
                    onClick = {
                        onNavigateToLogin()
                    }
                )
            }

            Text(
                text = stringResource(id = R.string.about),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            CedulaField(
                cedula = cedula,
                onCedulaChange = {
                    cedula = it
                    cedulaError = validateCedula(it)
                },
                cedulaError = cedulaError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            NameField(
                name = name,
                onNameChange = {
                    name = it
                    nameError = validateName(it)
                },
                nameError = nameError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            PhoneField(
                phone = phone,
                onPhoneChange = {
                    phone = it
                    phoneError = validatePhone(it)
                },
                phoneError = phoneError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            CityField(
                city = city,
                expanded = expandedCity,
                onExpandedChange = { expandedCity = it },
                onCitySelected = { city = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            EmailField(
                email = email,
                onEmailChange = {
                    email = it
                    emailError = validateEmail(it)
                },
                emailError = emailError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            PasswordField(
                password = password,
                onPasswordChange = {
                    password = it
                    passwordError = validatePasswordFormat(it)
                },
                passwordError = passwordError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            PasswordField(
                label = stringResource(id = R.string.password_confirm_lbl),
                password = confirmPassword,
                onPasswordChange = {
                    confirmPassword = it
                    confirmPasswordError = validatePasswordsMatch(password, it)
                },
                passwordError = confirmPasswordError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            PrimaryButton(
                text = stringResource(id = R.string.create_account_btn),
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
                onClick = { onNavigateToConfirmAccount() }
            )
        }
    }
}