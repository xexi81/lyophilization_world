package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun EditTextApp(
    placeHolder: String = "",
    onValueChanged: (String) -> Unit = {}
) {
    val inputValue = remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            value = inputValue.value,
            onValueChange = {
                inputValue.value = it
                onValueChanged(it.text) },
            placeholder = { Text(placeHolder, style = MaterialTheme.typography.body1) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Text,
            ),
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Gray,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                disabledIndicatorColor = Color.LightGray,
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