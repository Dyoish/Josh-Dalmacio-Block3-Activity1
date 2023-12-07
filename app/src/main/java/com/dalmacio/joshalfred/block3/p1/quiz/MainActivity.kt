package com.dalmacio.joshalfred.block3.p1.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.dalmacio.joshalfred.block3.p1.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class Turn {
        Nought,
        Cross
    }

    private var firstTurn = Turn.Cross
    private var currentTurn = Turn.Cross

    private var boardList = mutableListOf<Button>()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardTapped(view: View) {

        if (view !is Button)
            return
        addToBoard(view)

        if(fullboard()){
            result("Draw!")
        }

    }

    private fun result(title: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setPositiveButton("Reset")
            { _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()

    }

    private fun resetBoard() {

        for(button in boardList) {
            button.text = ""
        }
        if(firstTurn == Turn.Nought)
            firstTurn = Turn.Cross
        else if(firstTurn == Turn.Cross)
            firstTurn = Turn.Nought

        currentTurn = firstTurn
        setTurnLabel()
    }

    private fun fullboard(): Boolean {
        for(button in boardList) {
            if(button.text == "")
                return false
        }
        return true
    }

    private fun addToBoard(button: Button) {

        if (button.text != "")
            return

        if (currentTurn == Turn.Nought) {
            button.text = Nought
            currentTurn = Turn.Cross
        }

        else if (currentTurn == Turn.Cross) {
            button.text = Cross
            currentTurn = Turn.Nought
        }

        setTurnLabel()

    }

    private fun setTurnLabel() {
        var turnText = ""
        if(currentTurn == Turn.Cross)
            turnText = "Turn $Cross"
        else if(currentTurn == Turn.Nought)
            turnText = "Turn $Nought"

        binding.turnTV.text = turnText
    }

    companion object{
        const val Nought = "O"
        const val Cross = "X"
    }


}
