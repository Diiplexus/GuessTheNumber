package com.example.bemyguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var answer : Int = 0
    var isGameOver : Boolean = false
    var nbrOfAttempts = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startOver()
    }
    fun generateAnswer(){
        answer = Random.nextInt(1,25)
        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = answer.toString()
    }

    fun startOver(){
        isGameOver = false
        generateAnswer()

        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "??"
        findViewById<Button>(R.id.buttonSubmit).isEnabled = true
        findViewById<EditText>(R.id.editTextGuess).text.clear()
        findViewById<TextView>(R.id.textView).text = "Guess 1 to 25"
    }

    fun btnStartOverTapped(view: View){
        startOver()
    }

    fun btnSubmitTapped(view: View){
        val ug =  getUsersGuess()
        if (ug != null) {
                isGameOver = validateUserGuess(ug)
        }
    }

    fun getUsersGuess(): Int?{
        val editTexGuess = findViewById<EditText>(R.id.editTextGuess)
        val userGuess = editTexGuess.text.toString().toInt()
       return userGuess
    }

    fun validateUserGuess(userGuess: Int): Boolean{
        val answerTextView = findViewById<TextView>(R.id.answer)
        val textView =  findViewById<TextView>(R.id.textView)
        nbrOfAttempts++
        if (userGuess == answer){
            textView.text = "You won, on the $nbrOfAttempts guess"
            answerTextView.text = answer.toString()
            findViewById< Button>(R.id.buttonSubmit).isEnabled = false
           return true
        }else{
            if (userGuess > answer)  textView.text = "hot" else if((userGuess < answer)) textView.text = "cold"
        }
        return false
    }
}