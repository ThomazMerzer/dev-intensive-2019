package ru.skillbranch.devintensive

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEdt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj: Bender

    /**
     * Тестовая документация
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("M_MainActivity", "OnCreate")

        benderImage = iv_bender
        textTxt = tv_text
        messageEdt = edt_message
        sendBtn = iv_send_button

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val(r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy")
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.iv_send_button) {
            val(phrase, color) = benderObj.listenAnswer(messageEdt.text.toString().toLowerCase())
            messageEdt.setText("")
            val(r,g,b) = color
            benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase

            //Closes keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("STATUS", benderObj.questiion.name)
        Log.d("M_MainActivity", "OnSaveInstanceState")
    }
}
