package com.athena.art.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import com.athena.art.textwatcher.AmountTextWatcher

open class AmountEditText : EditText {

    private var textWatcher: TextWatcher? = null
    private lateinit var amountTextWatcher: AmountTextWatcher

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.editTextStyle)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    init {
        setAmountTextWatcher()
    }

    private fun setAmountTextWatcher() {
        amountTextWatcher = object : AmountTextWatcher(this) {
            override fun beforeChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                textWatcher?.beforeTextChanged(s, start, count, after)
            }

            override fun onChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textWatcher?.onTextChanged(s, start, before, count)
            }

            override fun afterChanged(s: Editable) {
                textWatcher?.afterTextChanged(s)
            }
        }

        addTextChangedListener(amountTextWatcher)
    }

    private fun fillZero(text: String): String {
        return when {
            text.isEmpty() -> "0,00"
            text.last() == ',' -> "${text}00"
            else -> text
        }
    }

    fun setTextWatcher(textWatcher: TextWatcher) {
        this.textWatcher = textWatcher
    }

    fun getAmountString(): String {
        val (s1, s2) = getAmountPairString()
        return "$s1$s2"
    }

    fun getAmountDouble(): Double {
        return fillZero(text.toString()).replace(".", "").replace(",", ".").toDouble()
    }

    fun getAmountPairString(): Pair<String, String> {
        val splited = fillZero(text.toString()).split(",").toMutableList()

        if (splited.size == 2) {
            if (splited[1].length == 1) {
                splited[1] += "0"
            }
            return Pair(splited[0], ",${splited[1]}")
        } else {
            return Pair(splited[0], ",00")
        }
    }

    fun getAmountPairInt(): Pair<Int, Int> {
        val splited = fillZero(text.toString()).split(",").toMutableList()
        splited[0] = splited[0].replace(".", "")
        if (splited.size == 2) {
            return Pair(splited[0].toInt(), splited[1].toInt())
        } else {
            return Pair(splited[0].toInt(), 0)
        }
    }

    fun setDecimalPartMaxLength(decimalPartLength: Int) {
        amountTextWatcher.setDecimalPartLength(decimalPartLength)
    }

    fun setFractionalPartMaxLength(fractionalPartLength: Int) {
        amountTextWatcher.setFractionalPartLength(fractionalPartLength)
    }
}