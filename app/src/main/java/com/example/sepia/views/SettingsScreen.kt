package com.example.sepia.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sepia.ui.theme.SepiaColors
import com.example.sepia.ui.theme.SepiaTheme
import com.example.sepia.ui.theme.components.SepiaTopBar

@Composable
fun SettingsScreen() {
    var autoEnhancement by remember { mutableStateOf(true) }
    var autoFlash       by remember { mutableStateOf(false) }
    var showGrid        by remember { mutableStateOf(true) }

    val usedGb   = 1.2f
    val totalGb  = 2.5f
    val progress = usedGb / totalGb

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SepiaColors.ScreenBackground)
            .verticalScroll(rememberScrollState())
    ) {
        SepiaTopBar(title = "Settings")

        Spacer(modifier = Modifier.height(16.dp))

        SettingsCard(modifier = Modifier.padding(horizontal = 16.dp)) {
            ToggleRow("Auto-Enhancement", autoEnhancement) { autoEnhancement = it }
            RowDivider()
            ToggleRow("Auto-Flash", autoFlash) { autoFlash = it }
            RowDivider()
            ToggleRow("Show Grid", showGrid) { showGrid = it }
        }

        Spacer(modifier = Modifier.height(12.dp))

        SettingsCard(modifier = Modifier.padding(horizontal = 16.dp)) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
                Text(
                    text = "Storage",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = SepiaColors.TextPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(SepiaColors.StorageTrack)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(3.dp))
                            .background(SepiaColors.Primary)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "${usedGb} GB used of ${totalGb} GB",
                    fontSize = 12.sp,
                    color = SepiaColors.TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        SettingsCard(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable { /* handle clear cache */ }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Clear Cache", fontSize = 15.sp, color = SepiaColors.TextPrimary)
                Text("→", fontSize = 18.sp, color = SepiaColors.Primary)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        SettingsCard(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("About", fontSize = 15.sp, color = SepiaColors.TextPrimary)
                Text("v1.0.0", fontSize = 14.sp, color = SepiaColors.TextSecondary)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SettingsCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SepiaColors.CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(content = content)
    }
}

@Composable
private fun ToggleRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontSize = 15.sp, color = SepiaColors.TextPrimary)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor    = Color.White,
                checkedTrackColor    = SepiaColors.Primary,
                uncheckedThumbColor  = Color.White,
                uncheckedTrackColor  = SepiaColors.TrackOff,
                uncheckedBorderColor = SepiaColors.TrackOff
            )
        )
    }
}

@Composable
private fun RowDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        thickness = 0.5.dp,
        color = SepiaColors.Divider
    )
}

@Preview(showBackground = true, widthDp = 375, heightDp = 720)
@Composable
fun SettingsScreenPreview() {
    SepiaTheme { SettingsScreen() }
}