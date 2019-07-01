package com.athena.art.textwatcher

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.widget.EditText

abstract class AmountTextWatcher(
    private val editText: EditText,
    private var decimalPartLength: Int = 9,
    private var fractionalPartLength: Int = 2
) :
    TextWatcher {

    private var separatorForFractional: Char = ','
    private var separatorForDecimal: Char = '.'
    private var programmaticallySetText: Boolean = false
    private var cursorPosition: Int = 0
    private var isDeleted: Boolean = false
    private var isPasted: Boolean = false
    private var previousLength: Int = 0

    init {
        editText.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        editText.keyListener = DigitsKeyListener.getInstance("0123456789.,")
    }

    fun setDecimalPartLength(decimalPartLength: Int) {
        this.decimalPartLength = decimalPartLength
    }

    fun setFractionalPartLength(fractionalPartLength: Int) {
        this.fractionalPartLength = fractionalPartLength
    }

    override fun afterTextChanged(s: Editable) {
        if (programmaticallySetText) {
            onProgrammaticallySetText()
            afterChanged(s)
        } else {
            val text = s.toString()
            when {
                text.isEmpty() -> onTextIsEmpty(s)
                isPasted -> onNumberEntered(text)
                text.last() == ',' || text.last() == '.' -> onFractionalSeparatorEntered(text)
                else -> onNumberEntered(text)
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        if (programmaticallySetText) {
            beforeChanged(s, start, count, after)
        } else {
            isDeleted = previousLength > start + after
            cursorPosition = editText.selectionStart
        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (programmaticallySetText) {
            onChanged(s, start, before, count)
        } else {
            isPasted = count > 1
        }
    }

    private fun onTextIsEmpty(s: Editable) {
        previousLength = 0
        cursorPosition = 0
        afterChanged(s)
    }

    private fun onFractionalSeparatorEntered(s: String) {
        val firstIndexComma = s.indexOf(',')
        val text: String

        if (s == "," || s == ".") {
            text = ""
        } else if (firstIndexComma == 0) {
            text = ""
        } else if (firstIndexComma == -1) {
            text = s.substring(0, s.lastIndex) + separatorForFractional
        } else if (firstIndexComma == s.lastIndex) {
            text = s.substring(0, s.lastIndex) + separatorForFractional
        } else {
            text = s.substring(0, s.lastIndex)
        }
        programmaticallySetText = true
        editText.setText(text)
    }

    private fun onNumberEntered(s: String) {
        var (decimalPart, fractionalPart) = separate(s)
        decimalPart = formatDecimalPart(decimalPart)
        val finalText =
            if (fractionalPart.isEmpty()) decimalPart else decimalPart + separatorForFractional + fractionalPart
        programmaticallySetText = true
        editText.setText(finalText)
    }

    private fun onProgrammaticallySetText() {
        programmaticallySetText = false
        if (isDeleted) {
            var position = cursorPosition - previousLength + editText.text.length
            if (position < 0) position = 0
            editText.setSelection(position)
        } else {
            editText.setSelection(cursorPosition + editText.text.length - previousLength)
        }
        previousLength = editText.text.length
    }

    private fun separate(text: String): Pair<String, String> {
        var decimalValue: String
        var fractionalValue: String
        val filteredText = text.replace(separatorForDecimal.toString(), "")
        val list = filteredText.split(separatorForFractional)

        if (list.size == 1) {
            decimalValue = list[0].trim()
            fractionalValue = ""
        } else {
            decimalValue = list[0].trim()
            fractionalValue = list[1].trim()
            if (fractionalValue.length > fractionalPartLength) {
                fractionalValue = fractionalValue.substring(0, fractionalPartLength)
            }
        }

        if (decimalValue.length > decimalPartLength) {
            decimalValue = decimalValue.substring(0, decimalValue.lastIndex)
        }

        return Pair(decimalValue, fractionalValue)
    }

    private fun formatDecimalPart(decimalPart: String): String {
        val newDecimalPart = StringBuilder(decimalPart)
        var decimalPartLength = decimalPart.length

        if (decimalPartLength > 3) {
            val mod = decimalPartLength % 3
            var i = if (mod == 0) 3 else mod
            while (i < decimalPartLength) {
                newDecimalPart.insert(i, separatorForDecimal)
                decimalPartLength++
                i += 4
            }
        }

        return newDecimalPart.toString()
    }

    abstract fun beforeChanged(s: CharSequence, start: Int, count: Int, after: Int)

    abstract fun onChanged(s: CharSequence, start: Int, before: Int, count: Int)

    abstract fun afterChanged(s: Editable)
}