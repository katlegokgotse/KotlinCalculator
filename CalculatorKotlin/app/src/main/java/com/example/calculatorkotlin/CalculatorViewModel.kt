package com.example.calculatorkotlin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

    var numberState by mutableStateOf(CalculatorState())
    private set

    fun onAction(calculatorActions: CalculatorActions) {
        when (calculatorActions) {
            is CalculatorActions.Number -> enterNumbers(calculatorActions.number)
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Clear -> numberState = CalculatorState()
            is CalculatorActions.Operations -> enterOperations(calculatorActions.operation)
            is CalculatorActions.Calculate -> performCalculation()
            is CalculatorActions.Delete -> performDeletion()
        }
    }

    private fun performDeletion() {
        when{
            numberState.number2.isNotBlank() -> numberState = numberState.copy(
                number2 = numberState.number2.dropLast(1)
            )
            numberState.operation != null -> numberState = numberState.copy(
                operation = null
            )
            numberState.number1.isNotBlank() -> numberState = numberState.copy(
                number1 = numberState.number1.dropLast(1)
            )
        }
    }

    private fun enterOperations(operation: CalculatorOperation) {
        if(numberState.number1.isNotBlank()) {
            numberState= numberState.copy(operation = operation)
        }
    }

    private fun performCalculation() {
        val number1 = numberState.number1.toDoubleOrNull()
        val number2 = numberState.number2.toDoubleOrNull()
        if(number1 != null && number2 != null){
            val result = when(numberState.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                null -> return
            }
            numberState = numberState.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }

    }

    private fun enterDecimal() {
        if(numberState.operation == null &&
            !numberState.number1.contains(".") &&
            numberState.number1.isNotBlank()) {
            numberState = numberState.copy(
                number1 = numberState.number1 + "."
            )
            return
        }
        if(numberState.operation == null && !numberState.number2.contains(".") &&
            numberState.number2.isNotBlank()) {
            numberState = numberState.copy(
                number2 = numberState.number2 + "."
            )
            return
        }
    }

    private fun enterNumbers(num: Int) {
       if (numberState.operation == null){
           if (numberState.number1.length >= MAX_NUM_LENGTH) {
                return
           }
            numberState = numberState.copy(
                number1 = numberState.number1 + num
            )
           return
           }
        if (numberState.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        numberState = numberState.copy(
            number2 = numberState.number2 + num
        )
       }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}