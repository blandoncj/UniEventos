package com.example.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.unieventos.enums.EmailError
import com.example.unieventos.enums.NameError
import com.example.unieventos.enums.PasswordError
import com.example.unieventos.ui.components.EmailField
import com.example.unieventos.ui.components.PasswordField
import com.example.unieventos.ui.components.customer.NameField
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.viewmodel.UsersViewModel
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun ProfileScreen(
    usersViewModel: UsersViewModel,
    userId: Int,
    hazeState: HazeState,
    paddingValues: PaddingValues
) {
    val user = usersViewModel.getUserById(userId)

    if (user == null) {
        return
    }

    var name by rememberSaveable { mutableStateOf(user.name) }
    var nameError by rememberSaveable { mutableStateOf(NameError.NONE) }
    var email by rememberSaveable { mutableStateOf(user.email) }
    var emailError by rememberSaveable { mutableStateOf(EmailError.NONE) }
    var password by rememberSaveable { mutableStateOf(user.password) }
    var passwordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }
    var confirmPassword by rememberSaveable { mutableStateOf(user.password) }
    var confirmPasswordError by rememberSaveable { mutableStateOf(PasswordError.NONE) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
            .haze(
                state = hazeState,
                style = HazeDefaults.style(backgroundColor = MaterialTheme.colorScheme.surface)
            ),
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        NameField(
            name = name,
            onNameChange = { name = it },
            nameError = nameError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        EmailField(
            email = email,
            onEmailChange = { email = it },
            emailError = emailError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordField(
            password = password,
            onPasswordChange = { password = it },
            passwordError = passwordError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordField(
            label = stringResource(id = R.string.pass_confirm_lbl),
            password = confirmPassword,
            onPasswordChange = { confirmPassword = it },
            passwordError = confirmPasswordError,
            modifier = Modifier.fillMaxWidth()
        )

//        PrimaryButton(
//            text = stringResource(id = R.string.save_lbl),
//            onClick = {
//                nameError = usersViewModel.validateName(name)
//                emailError = usersViewModel.validateEmail(email)
//                passwordError = usersViewModel.validatePasswordFormat(password)
//                confirmPasswordError = usersViewModel.validatePasswordMatch(password, confirmPassword)
//
//                if (nameError == NameError.NONE && emailError == EmailError.NONE && passwordError == PasswordError.NONE && confirmPasswordError == PasswordError.NONE) {
//                    usersViewModel.updateUser(userId, name, email, password)
//                }
//            },
//            modifier = Modifier.align(Alignment.End)
//        )
    }
}