package com.example.calculatorkotlin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier){
        Column(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomEnd),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){

        }
    }
}