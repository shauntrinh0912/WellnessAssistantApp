package com.example.wellnessassistant.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SimpleBarChart(
    data: List<Pair<String, Float>>,
    maxValue: Float,
    modifier: Modifier = Modifier,
    barColor: Color = MaterialTheme.colorScheme.primary
) {
    val barWidth = 24.dp
    val spacing = 8.dp

    Column(modifier = modifier) {
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val totalBars = data.size
            val totalSpacing = spacing.toPx() * (totalBars + 1)
            val totalBarWidth = barWidth.toPx() * totalBars
            val scaleFactor = (canvasHeight - 16.dp.toPx()) / maxValue

            data.forEachIndexed { index, pair ->
                val label = pair.first
                val value = pair.second
                val left = spacing.toPx() * (index + 1) + barWidth.toPx() * index
                val barHeight = value * scaleFactor

                drawRect(
                    color = barColor,
                    topLeft = Offset(left, canvasHeight - barHeight),
                    size = androidx.compose.ui.geometry.Size(barWidth.toPx(), barHeight)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            data.forEach { (label, _) ->
                Text(text = label, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
