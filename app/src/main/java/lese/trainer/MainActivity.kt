package lese.trainer

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val picker = findViewById<NumberPicker>(R.id.numberPickerMaxIdx)
        picker.setMinValue(1);
        picker.setMaxValue(list_of_words.size);
        picker.value = 5
    }

    fun parseMaxWordIdx(): Int{
//        val editText = findViewById<EditText>(R.id.editTextNumberTopWords)
////        isInputValidNumber(editText)
//        val maxWordIdx = editText.text.toString().toInt()
//        val maxWordIdxCorrected = minOf(maxWordIdx, 100)
//        return maxWordIdxCorrected
        val picker = findViewById<NumberPicker>(R.id.numberPickerMaxIdx)
        return picker.value
    }

    /** Called when the user taps the Los! button */
    fun sendMessage(view: View) {
        val maxWordIdx = parseMaxWordIdx()
        val intent = Intent(this, GetWordsActivity::class.java)
        intent.putExtra("max_word_idx", maxWordIdx);
        startActivity(intent)
    }

    fun inspectWords(view: View){
        val maxWordIdx = parseMaxWordIdx()
        val intent = Intent(this, ViewWordsListActivity::class.java)
        intent.putExtra("max_word_idx", maxWordIdx);
        startActivity(intent)
    }

//    fun isInputValidNumber(s: EditText): Boolean {
//        try {
//            val value = s.toString().toInt()
//            if (value > 100) {
//                return false
//            } else if (value < 1) {
//                return false
//            }
//        } catch (ex: NumberFormatException) {
//            return false
//        }
//        return true
//    }
}