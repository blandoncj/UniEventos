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
import com.example.unieventos.models.Customer
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.customer.CustomerForm
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.utils.validateCedula
import com.example.unieventos.utils.validateEmail
import com.example.unieventos.utils.validateFields
import com.example.unieventos.utils.validateName
import com.example.unieventos.utils.validatePasswordFormat
import com.example.unieventos.utils.validatePasswordsMatch
import com.example.unieventos.utils.validatePhone
import com.example.unieventos.viewmodel.UsersViewModel

/**
 * Signup screen composable function.
 *
 * @param usersViewModel ViewModel that contains the users data.
 * @param onBack Function that navigates back.
 * @param onNavigateToConfirmAccount Function that navigates to the confirm account screen.
 */
@Composable
fun SignupScreen(
    usersViewModel: UsersViewModel,
    onBack: () -> Unit,
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

    Scaffold(
        topBar = {
            CustomTopAppBar(title = stringResource(id = R.string.about), onBack = onBack)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            CustomerForm(
                cedula = cedula,
                onCedulaChange = {
                    cedula = it
                    cedulaError = usersViewModel.validateCedula(it)
                },
                cedulaError = cedulaError,
                name = name,
                onNameChange = {
                    name = it
                    nameError = usersViewModel.validateName(it)
                },
                nameError = nameError,
                phone = phone,
                onPhoneChange = {
                    phone = it
                    phoneError = usersViewModel.validatePhone(it)
                },
                phoneError = phoneError,
                city = city,
                expandedCity = expandedCity,
                onExpandedChange = { expandedCity = it },
                onCitySelected = { city = it },
                email = email,
                onEmailChange = {
                    email = it
                    emailError = usersViewModel.validateEmail(it)
                },
                emailError = emailError,
                password = password,
                onPasswordChange = {
                    password = it
                    passwordError = usersViewModel.validatePasswordFormat(it)
                },
                passwordError = passwordError,
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = {
                    confirmPassword = it
                    confirmPasswordError = usersViewModel.validatePasswordsMatch(password, it)
                },
                confirmPasswordError = confirmPasswordError
            )

            Spacer(modifier = Modifier.height(10.dp))

            PrimaryButton(
                text = stringResource(id = R.string.register_btn),
                modifier = Modifier.fillMaxWidth(),
                enabled = cedulaError == CedulaError.NONE &&
                        nameError == NameError.NONE &&
                        phoneError == PhoneError.NONE &&
                        emailError == EmailError.NONE &&
                        passwordError == PasswordError.NONE &&
                        confirmPasswordError == PasswordError.NONE,
                onClick = {
//                    onNavigateToConfirmAccount()
                    if (cedulaError == CedulaError.NONE &&
                        nameError == NameError.NONE &&
                        phoneError == PhoneError.NONE &&
                        emailError == EmailError.NONE &&
                        passwordError == PasswordError.NONE &&
                        confirmPasswordError == PasswordError.NONE
                    ) {

                        val customer = Customer(
                            id = 0,
                            cedula = cedula,
                            name = name,
                            phone = phone,
                            city = city,
                            email = email,
                            password = password
                        )

                        usersViewModel.createUser(customer)
                        onNavigateToConfirmAccount()
                    }
                }
            )
        }
    }
}
