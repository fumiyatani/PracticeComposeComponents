package com.ftani.jetpackcomposecomponentspractice

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PracticeTextFields() {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        CustomBasicTextFieldWithIcon(
            textFieldAlign = TextAlign.End
        )
    }
}

/**
 * 入力した時に
 */
@Composable
fun CustomBasicTextFieldWithIcon(
    modifier: Modifier = Modifier,
    textFieldAlign: TextAlign = TextAlign.Start,

    ) {
    // 入力値を保持
    var textState by remember { mutableStateOf(TextFieldValue()) }
    // フォーカス状態の監視
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(color = Color.Black, textAlign = textFieldAlign),
            cursorBrush = SolidColor(Color.Black), // カーソルの色
            interactionSource = interactionSource, // フォーカス状態の監視
            visualTransformation = VisualTransformation.None, // 入力値のマスク処理 (今回は無し)
            decorationBox = { innerTextField -> // TextField に追加の Composable を設定する。
                Box(
                    modifier = Modifier,
                    contentAlignment = if (textFieldAlign == TextAlign.End) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    if (textState.text.isEmpty()) {
                        Text(
                            text = "Enter text here...",
                            style = TextStyle(color = Color.Gray),
                            textAlign = textFieldAlign
                        )
                    }
                    innerTextField()
                }
            }
        )

        if (isFocused) {
            Spacer(modifier = Modifier.size(8.dp))
            Icon(
                modifier = Modifier
                    .clickable { // クリック処理を設定
                        textState = TextFieldValue("")
                    },
                imageVector = Icons.Default.Clear,
                contentDescription = "Clear"
            )
        }
    }
}

@Preview
@Composable
fun CustomBasicTextFieldWithIconPreview() {
    CustomBasicTextFieldWithIcon(
        textFieldAlign = TextAlign.End
    )
}