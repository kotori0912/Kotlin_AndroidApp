package com.example.itoutakumi.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // View要素を変数に代入
        val timeText = findViewById(R.id.timeText) as TextView   // ストップウォッチのテキスト
        val startButton = findViewById(R.id.start) as Button     // スタートボタン
        val stopButton = findViewById(R.id.stop) as Button       // ストップボタン
        val resetButton = findViewById(R.id.reset) as Button     // リセットボタン

        // 1度だけ代入するものは val を使う
        val handler = Handler()

        // 繰り返し代入するものは var を使う
        var timeValue = 0

        override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
            super.onCreate(savedInstanceState, persistentState)

            // 1秒ごとに処理を実行
            val runnnable = object : Runnable {
                override fun run() {
                    timeValue++
                    handler.postDelayed(this, 1000)
                }
            }
        }
    }
}