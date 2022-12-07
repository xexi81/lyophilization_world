package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme

@Composable
fun DrawerContentApp(
    onContactClick: () -> Unit = {},
    onAboutUsClick: () -> Unit = {},
    onRateClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {}
) {
    Lyophilization_worldTheme {
        Column(
            Modifier.padding(all = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.height(20.dp))

            DrawerRow(iconResource = R.drawable.ic_contact_us, stringResource = R.string.contact_us, onClick = {onContactClick()})
            DrawerRow(iconResource = R.drawable.ic_about_us, stringResource = R.string.about_us, onClick = { onAboutUsClick()})

            Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 20.dp))

            DrawerRow(iconResource = R.drawable.ic_rate_us, stringResource = R.string.rate_us, onClick = {onRateClick()})

            Spacer(modifier = Modifier.height(20.dp))

            DrawerRow(iconResource = R.drawable.ic_logout, stringResource = R.string.sign_out, onClick = {onSignOutClick()})
        }
    }
}

@Composable
private fun DrawerRow(
    iconResource: Int? = null,
    stringResource: Int = 0,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconApp(
            resource = iconResource,
            size = 30.dp,
            tint = Color.Black
        )
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = stringResource(stringResource),
            style = MaterialTheme.typography.body1
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DrawerContentPreview() {
    DrawerContentApp()
}