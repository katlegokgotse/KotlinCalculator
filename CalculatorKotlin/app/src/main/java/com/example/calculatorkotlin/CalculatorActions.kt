package com.example.calculatorkotlin

//For each thing a user will do on the screen
//View model should know what to do in this event
sealed class CalculatorActions {
    data class Number(val number: Int): CalculatorActions()
    data object Clear: CalculatorActions()
    data object Delete: CalculatorActions()
    data object Decimal: CalculatorActions()
    data object Calculate: CalculatorActions()
    class Operations(val operation: CalculatorOperation) : CalculatorActions()
}