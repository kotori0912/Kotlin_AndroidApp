package com.example.itoutakumi.stopwatch

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // 1度だけ代入するものは val を使う
    val handler = Handler()

    // 繰り返し代入するものは var を使う
    var timeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // View要素を変数に代入
        val timeText = findViewById<TextView>(R.id.timeText)   // ストップウォッチのテキスト
        val startButton = findViewById<Button>(R.id.start)     // スタートボタン
        val stopButton = findViewById<Button>(R.id.stop)       // ストップボタン
        val resetButton = findViewById<Button>(R.id.reset)     // リセットボタン
        val mediaPlayer = MediaPlayer(this, R.raw.btsound)     // ボタンの音

        // 1秒ごとに処理を実行
        val runnnable = object : Runnable {
            override fun run() {
                timeValue++    // TextViewを更新
                // ?.letを用いてd、nullではない場合のみ更新
                timeToText(timeValue)?.let {
                    // timeToText(timeValue)の値がlet内ではitとして使える
                    timeText.text = it
                }

                handler.postDelayed(this, 1000)
            }
        }

        startButton.setOnClickListener {
            handler.post(runnnable)
        }

        stopButton.setOnClickListener {
            handler.removeCallbacks(runnnable)
        }

        resetButton.setOnClickListener {
            handler.removeCallbacks(runnnable)
            timeValue = 0
            timeToText()?.let {
                timeText.text = it
            }
        }
    }

    // 数値を00:00:00形式の文字列に変換する関数
    // 引数timeにはデフォルト値0を設定、返却する型はnullableなString?型
    private fun timeToText(time: Int = 0): String? {
        // if式は値を返すため、そのままreturnできる
        return if (time < 0) {
            null
        } else if (time == 0) {
            "00:00:00"
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }
}