package com.example.tic_tok_toi

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var board:Array<Array<Button>>
    var PlAYER = true
    var TURN_COUNT =0
    var boardStatus =Array(3){IntArray(3)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for (i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        initiallizeBoardStatus()
        resetButton.setOnClickListener{
            PlAYER = true
            TURN_COUNT =0;

            initiallizeBoardStatus()
        }
    }

    private fun initiallizeBoardStatus() {
        for (i in 0..2){
            for(j in 0..2){
                boardStatus[i][j]=- 1
            }
        }
        for (i in board){
            for(button in i){
                button.isEnabled = true
                button.text ="     "
            }
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view.id){
                R.id.button1->{
                    updatevalue(row=0,col=0,player=PlAYER)
                }
                R.id.button2->{
                    updatevalue(row=0,col=1,player=PlAYER)
                }
                R.id.button3->{
                    updatevalue(row=0,col=2,player=PlAYER)
                }
                R.id.button4->{
                    updatevalue(row=1,col=0,player=PlAYER)
                }
                R.id.button5->{
                    updatevalue(row=1,col=1,player=PlAYER)
                }
                R.id.button6->{
                    updatevalue(row=1,col=2,player=PlAYER)
                }
                R.id.button7->{
                    updatevalue(row=2,col=0,player=PlAYER)
                }
                R.id.button8->{
                    updatevalue(row=2,col=1,player=PlAYER)
                }
                R.id.button9->{
                    updatevalue(row=2,col=2,player=PlAYER)

                }

            }
            TURN_COUNT++
            PlAYER  = !PlAYER
            if(PlAYER){
                setupdate("Player X turn")
            }
            else{
                setupdate("Player 0 turn")
            }
            if(TURN_COUNT==9){
                setupdate("Game Draw ")
            }
            checkwinner()
        }

    }

    private fun checkwinner() {
       //for the rows
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
                if (boardStatus[i][0]==1){
                    setupdate("Player X is Winner")
                    break
                }
                else if ( boardStatus[i][0]==0){
                    setupdate("Player 0 Winner")
                    break
                }
            }
        }
        //vertical column
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i]){
                if (boardStatus[0][i]==1){
                    setupdate("Player X is Winner")
                    break
                }
                else if ( boardStatus[0][i]==0){
                    setupdate("Player 0 Winner")
                    break
                }
            }
        }
        //for diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][2]){
            if(boardStatus[0][0]==1){
                setupdate("Player X is Winner")
            }
            else if(boardStatus[0][0]==0){
                setupdate("player 0 Winner")
            }
        }

    }

    private fun setupdate(s: String) {
        displaytextView.text = s
        if(s.contains("Winner")){
            disablebutton()
        }

    }

    private fun updatevalue(row: Int, col: Int, player: Boolean) {
        val text:String = if(player)"X" else "0"
        val value:Int = if(player) 1 else 0
        board[row][col].apply {
             isEnabled= false

            setText(text)
        }
        boardStatus[row][col] =value
    }
    private fun disablebutton(){
        for (i in board){
            for(button in i){
               button.isEnabled =false
            }
        }
    }
}