package com.ftani.jetpackcomposecomponentspractice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CurrentTextField() {
    var text by remember {
        mutableStateOf("")
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            modifier = Modifier
                .weight(1f),
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            textStyle = TextStyle.Default.copy(fontSize = 24.sp, textAlign = TextAlign.End),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = NumberVisualTransformation(),
            decorationBox = { innerTextField ->
                if (text.isBlank()) {
                    Row(
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "金額を入力",
                            color = Color.Gray,
                            style = TextStyle.Default.copy(fontSize = 24.sp)
                        )
                    }
                }
                innerTextField()
            }
        )
    }
}

class NumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        if (text.isBlank()) {
            return TransformedText(
                text = text,
                offsetMapping = OffsetMapping.Identity,
            )
        }

        val output = "%,d".format(text.text.toIntOrNull() ?: 0)
            .plus("円")

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val totalSeparatorCount = (text.length - 1) / 3
                val rightSeparatorCount = ((text.length - 1 - offset) / 3)
                val leftSeparatorCount = totalSeparatorCount - rightSeparatorCount
                return offset + leftSeparatorCount
            }

            override fun transformedToOriginal(offset: Int): Int {
                val totalSeparatorCount = (text.length - 1) / 3
                // 右側のカンマ分を計算するので、円マーク分を-1する
                val rightSeparatorCount = ((output.length - offset - 1) / 4)
                val leftSeparatorCount = totalSeparatorCount - rightSeparatorCount
                // 変換後のカーソル位置が変換後のテキストサイズと同じ場合、円マークを飛び越えてしまっているので、
                // -1 することで円マークから飛び出さないようにする
                // 例: 1,234円|
                return if (output.length == offset) {
                    (offset - leftSeparatorCount) - 1
                } else {
                    offset - leftSeparatorCount
                }
            }
        }

        return TransformedText(
            text = AnnotatedString(output),
            offsetMapping = offsetMapping
        )
    }
}