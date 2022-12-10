package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun EditTextApp(
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    onValueChanged: (String) -> Unit = {},
    focusedIndicatorColor: Color = Color.Gray,
    unfocusedIndicatorColor: Color = Color.LightGray,
    disabledIndicatorColor: Color = Color.LightGray,
    maxLines: Int = 1,
    textColor: Color = Color.Black,
    text: String = ""
) {
    val inputValue = remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            modifier = modifier,
            value = text,
            maxLines = maxLines,
            onValueChange = { onValueChanged(it) },
            placeholder = { Text(placeHolder, style = MaterialTheme.typography.body1) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Text,
            ),
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = textColor,
                disabledTextColor = Color.Gray,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = focusedIndicatorColor,
                unfocusedIndicatorColor = unfocusedIndicatorColor,
                disabledIndicatorColor = disabledIndicatorColor,
                cursorColor = Color.LightGray,
                placeholderColor = Color.LightGray
            )
        )
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditTextAppPreview() {
    EditTextApp(
        placeHolder = "eMail"
    )
}