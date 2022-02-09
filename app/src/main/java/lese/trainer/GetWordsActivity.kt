package lese.trainer

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.stream.Collectors.toSet


//const val EXTRA_MESSAGE = "lese.trainer.NEW_WORD"

class GetWordsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_words)
        /**
        val bundle = intent.extras
//        val maxWordIdx = bundle!!.getInt("max_word_idx")

        if (bundle!!.getObject("max_word_idx") != null){
            val maxWordIdx = bundle!!.getInt("max_word_idx")
        }
        else {
            val checkedWordsIdxs = bundle!!.getObject("checked_words_idx")
        }
        **/

        val maxWordIdx = intent.getIntExtra("max_word_idx", -1)
        val checklist = intent.getBooleanArrayExtra("checklist")


        var randomWord: String = getNewWord(maxWordIdx, checklist)
        // get random word from list of words


        // Capture the layout's TextView and set the string as its text
        val textViewWords = findViewById<TextView>(R.id.textViewWords).apply {
            text = randomWord
        }

        val buttonNewWord = findViewById<Button>(R.id.buttonNewWord)
        /** Define what happens when the user taps the Weiter button */
        buttonNewWord.setOnClickListener{
            textViewWords.text = getNewWord(maxWordIdx, checklist)
            val fonts = listOf(Typeface.MONOSPACE, Typeface.SERIF, Typeface.SANS_SERIF)
            val bold_or_italics = listOf(Typeface.NORMAL, Typeface.BOLD, Typeface.BOLD_ITALIC, Typeface.ITALIC)
            textViewWords.setTypeface(fonts.random(), bold_or_italics.random())
        }
    }

    fun getNewWord(maxIdx: Int, checklist: BooleanArray?): String{
        if (maxIdx != -1) {
            return list_of_words.slice(0..(maxIdx - 1)).random()
        }
        val list_of_words_reduced: MutableList<String> = ArrayList()
        var cnt = 0
        for (i in 0 until checklist!!.size){
            if (checklist[i]){
                list_of_words_reduced.add(list_of_words[i])
                cnt++
            }
        }
        return list_of_words_reduced.random()
    }

//    fun getNewWord(maxIdx: Int): String{
//        return list_of_words.slice(0..(maxIdx-1)).random()
//    }
//
//    fun getNewWord(idxList: Set<Int>): String{
//        return list_of_words.slice(idxList).random()
//    }

//    /** Called when the user taps the Weiter button */
//    fun putNewWord(view: View) {
//        val newWord = getNewWord()
//        val intent = Intent(this, GetWordsActivity::class.java).apply {
//            putExtra(EXTRA_MESSAGE, newWord)
//        }
//        startActivity(intent)
//    }
}