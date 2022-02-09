package lese.trainer

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.AbsListView.CHOICE_MODE_MULTIPLE
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SelectWordsFromListActivity : AppCompatActivity() {
    lateinit var buttonStart : Button

    val checkedWordsIdxs : MutableList<Int> = arrayListOf()
//    var checkedWordsIdxs = IntArray(list_of_words.size)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_words_list)

        val bundle = intent.extras
        val maxWordIdx = bundle!!.getInt("max_word_idx")

        val listview_of_words = findViewById<ListView>(R.id.listViewOfWords)
        listview_of_words.setChoiceMode(CHOICE_MODE_MULTIPLE)

        val max_items_shown = list_of_words.size // maxWordIdx
        val listItems = arrayOfNulls<String>(max_items_shown)
        for (i in 0 until listItems.size){
            listItems[i] = list_of_words[i]
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, listItems)
        listview_of_words.adapter = adapter

        // show maxWordIdx items as selected by default
        for (i in 0 until maxWordIdx) {
            listview_of_words.setItemChecked(i,true)
        }

        for (idx in listview_of_words.getCheckedItemIds()){
            checkedWordsIdxs.add(idx.toInt())
        }
//        checkedWordsIdxs = listview_of_words.getCheckedItemPositions()

        buttonStart = findViewById<Button>(R.id.button_start_with_selected)
        /** Define what happens when the user taps the Weiter button */
        buttonStart.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent =Intent(this@SelectWordsFromListActivity, GetWordsActivity::class.java)
                intent.putExtra("checked_words_idx", checkedWordsIdxs.toIntArray());
                startActivity(intent);
            }
        })
    }
    fun startWithSelectedWords(view: View){
        val intent = Intent(this@SelectWordsFromListActivity, GetWordsActivity::class.java)
        intent.putExtra("checked_words_idx", checkedWordsIdxs.toIntArray())
        intent.putExtra("max_word_idx", 1)
        startActivity(intent)
    }
}