package com.example.unieventos.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.enums.CodeError
import com.example.unieventos.ui.components.BackButton
import com.example.unieventos.ui.components.CodeField
import com.example.unieventos.ui.components.PrimaryButton
import com.example.unieventos.ui.components.SecondaryButton
import com.example.unieventos.utils.validateCode

/**
 * ConfirmAccountScreen is a composable screen that allows the user to confirm their account
 * by entering a code sent to their email.
 */
@Composable
fun ConfirmAccountScreen(
    onBack: () -> Unit
) {
    var code by rememberSaveable { mutableStateOf("") }
    var codeError by rememberSaveable { mutableStateOf(CodeError.NONE) }

    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            BackButton(onClick = { onBack() })
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(70.dp))

                Text(text = stringResource(id = R.string.code_description))

                Spacer(modifier = Modifier.height(20.dp))

                CodeField(
                    code = code,
                    onCodeChange = {
                        code = it
                        codeError = validateCode(it)
                    },
                    codeError = codeError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )

                // implementar cuenta regresiva

                Spacer(modifier = Modifier.height(50.dp))

                PrimaryButton(
                    text = stringResource(id = R.string.confirm_btn),
                    onClick = { },
                    enabled = codeError == CodeError.NONE,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                SecondaryButton(
                    text = stringResource(id = R.string.resend_code_btn),
                    onClick = { },
                    enabled = true, // implementar cuenta regresiva
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}