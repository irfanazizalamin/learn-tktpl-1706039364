package id.ac.ui.cs.mobileprogramming.irfanazizalamin.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0
        var counterText: TextView = findViewById(R.id.counterText)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            count += 1
            counterText.setText(count.toString());
        }
    }
}