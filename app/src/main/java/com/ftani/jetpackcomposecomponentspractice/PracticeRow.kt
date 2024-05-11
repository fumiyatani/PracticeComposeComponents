package com.ftani.jetpackcomposecomponentspractice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PracticeRow() {
    // 全ての Box を均等に表示する
    Row(
        modifier = Modifier.height(100.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Blue)
                .weight(1f)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Red)
                .weight(1f)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Green)
                .weight(1f)
        )
    }
}

@Composable
fun PracticeRow2() {
    // height や width が固定の Composable 以外の箇所を埋めながら Composable を表示する
    Row(
        modifier = Modifier
            .height(100.dp)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp) // 100dp の Box を作成
                .padding(8.dp) // 100dp の Box に対して 8dp の padding を設定 (実質 84*84の Box になる)
                .background(Color.Cyan) // 84 * 84 の Box を水色で塗りつぶす
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween // 端のスペースは 0 にし、間のスペースを均等に分割し内部の Composable を配置する
        ) {
            Text(text = "テキスト1")
            Text(text = "テキスト2")
            Text(text = "テキスト3")
        }
        Spacer(modifier = Modifier.fillMaxHeight().size(8.dp))
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Blue)
                .weight(1f)
        )
        Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Account Box")

    }
}

@Preview
@Composable
fun PracticeRowPreview() {
    Column {
        PracticeRow()
        Spacer(modifier = Modifier.size(24.dp))
        PracticeRow2()
    }
}