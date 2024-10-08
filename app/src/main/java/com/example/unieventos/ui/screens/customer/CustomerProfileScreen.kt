package com.example.unieventos.ui.screens.customer

import android.widget.Toast
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.enums.CedulaError
import com.example.unieventos.enums.EmailError
import com.example.unieventos.enums.NameError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.enums.PhoneError
import com.example.unieventos.models.Customer
import com.example.unieventos.ui.components.customer.CustomerForm
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.ui.components.utils.SecondaryButton
import com.example.unieventos.utils.validateCedula
import com.example.unieventos.utils.validateEmail
import com.example.unieventos.utils.validateFields
import com.example.unieventos.utils.validateName
import com.example.unieventos.utils.validatePasswordFormat
import com.example.unieventos.utils.validatePasswordsMatch
import com.example.unieventos.utils.validatePhone
import com.example.unieventos.viewmodel.UsersViewModel
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun CustomerProfileScreen(
    usersViewModel: UsersViewModel,
    userId: Int,
    paddingValues: PaddingValues,
    hazeState: HazeState,
    onLogout: () -> Unit
) {
    val user = usersViewModel.getUserById(userId)

    if (user == null) {
        return
    }

    val customer = user as? Customer
    if (customer == null) {
        return
    }

    val context = LocalContext.current

    var cedula by rememberSaveable { mutableStateOf(customer.cedula) }
    var cedulaError by rememberSaveable { mutableStateOf(CedulaError.NONE) }
    var name by rememberSaveable { mutableStateOf(customer.name) }
    var nameError by rememberSaveable { mutableStateOf(NameError.NONE) }
    var city by rememberSaveable { mutableStateOf(customer.city) }
    var expandedCity by rememberSaveable { mutableStateOf(false) }
    var phone by rememberSaveable { mutableStateOf(customer.phone) }
    var phoneError by rememberSaveable { mutableStateOf(PhoneError.NONE) }
    var email by rememberSaveable { mutableStateOf(customer.email) }
    var emailError by rememberSaveable { mutableStateOf(EmailError.NONE) }
    var password by rememberSaveable { mutableStateOf(customer.password) }
    var passwordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }
    var confirmPassword by rememberSaveable { mutableStateOf(customer.password) }
    var confirmPasswordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }

    var showDialog by rememberSaveable { mutableStateOf(false) }

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
                nameError = usersViewModel.validateName(it)
            },
            nameError = nameError,
            phone = phone,
            onPhoneChange = {
                phone = it
                if (phone != customer.phone) {
                    phoneError = usersViewModel.validatePhone(it)
                }
            },
            phoneError = phoneError,
            city = city,
            expandedCity = expandedCity,
            onExpandedChange = { expandedCity = it },
            onCitySelected = { city = it },
            email = email,
            onEmailChange = {
                email = it
                if (email != customer.email) {
                    emailError = usersViewModel.validateEmail(it)
                }
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
            onClick = {
                if (cedulaError == CedulaError.NONE &&
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
                    )
                ) {
                    val updatedCustomer = Customer(
                        id = userId,
                        cedula = cedula,
                        name = name,
                        phone = phone,
                        city = city,
                        email = email,
                        password = password
                    )
                    usersViewModel.updateUser(updatedCustomer)
                    Toast.makeText(context, R.string.account_updated, Toast.LENGTH_SHORT).show()
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        SecondaryButton(
            text = stringResource(id = R.string.delete_account_btn),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                showDialog = true
            }
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        usersViewModel.deleteUser(customer)
                        showDialog = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.account_deleted),
                            Toast.LENGTH_SHORT
                        ).show()
                        onLogout()
                    }
                ) {
                    Text(stringResource(id = R.string.confirm_btn))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(stringResource(id = R.string.dismiss_btn))
                }
            },
            title = {
                Text(stringResource(id = R.string.delete_account_title))
            },
            text = {
                Text(stringResource(id = R.string.delete_account_msg))
            }
        )
    }
}
