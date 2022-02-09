package lese.trainer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class ViewWordsListActivity : AppCompatActivity() {
    var maxWordIdx: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_words_list)

        val bundle = intent.extras
        maxWordIdx = bundle!!.getInt("max_word_idx")

        val listview_of_words = findViewById<ListView>(R.id.listViewOfWords)
        //    listview_of_words.setAdapter{adapter}

        val listItems = arrayOfNulls<String>(maxWordIdx)
        for (i in 0 until maxWordIdx){
            listItems[i] = list_of_words[i]
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listview_of_words.adapter = adapter
    }

    fun selectWords(view: View){
        // WordSelectActivity SelectWordsFromListActivity
        val intent = Intent(this, WordSelectActivity::class.java)
        intent.putExtra("max_word_idx", maxWordIdx)
        startActivity(intent)
    }
}