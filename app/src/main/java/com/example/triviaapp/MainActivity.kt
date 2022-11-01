package com.example.triviaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var options = arrayOf("a","b","c", "d");
    var score = 0;
    var qindex:Int = 0;
    var max:Int = 0
    var selectedAnswer:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var spinner:Spinner = findViewById(R.id.spinner)
        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        var Qtext:TextView = findViewById(R.id.question)
        var maxText:TextView = findViewById(R.id.maxscore)
        var submit:Button = findViewById(R.id.submit)
        var scoretext:TextView = findViewById(R.id.scoreT)
        var flag = " ";
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = options.get(p2);
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        loadQuestion(Qtext,options,maxText)
        submit.setOnClickListener{
            if (flag.equals(trivia.correct[qindex])){
                score++;
            }
            qindex++;
            loadQuestion(Qtext,options, maxText)
            scoretext.setText("Current Score: " + score.toString());
//            scoretext.setText(qindex.toString());
        }
    }
    private fun loadQuestion(Qtext:TextView,options:Array<String>, maxText:TextView) {
        if(qindex == 3){
            if (score > max){
                max = score
            }
            qindex = 0
            score = 0
            maxText.setText("Max: " + max.toString())
            loadQuestion(Qtext, options,maxText)
            return
        }
        Qtext.setText(trivia.q[qindex]);
        options[0] = trivia.choices[qindex][0]
        options[1] = trivia.choices[qindex][1]
        options[2] = trivia.choices[qindex][2]
        options[3] = trivia.choices[qindex][3]
    }
}
