package lese.trainer

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class WordSelectActivity : AppCompatActivity() {
    val checkedWordsIdxs : MutableList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_select)

        val bundle = intent.extras
        val maxWordIdx = bundle!!.getInt("max_word_idx")

        val listview_of_words = findViewById<ListView>(R.id.listviewSelectableWords)
        listview_of_words.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE)

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

        val button1 = findViewById<Button>(R.id.button3)
        button1.setOnClickListener {

            for (idx in listview_of_words.getCheckedItemIds()){
                checkedWordsIdxs.add(idx.toInt())
            }

            val sarr = listview_of_words.getCheckedItemPositions()
            val checklist = get_boolean_list_of_words(sarr)
            if (checklist.count{it==true} > 0) {
                val intent = Intent(this, GetWordsActivity::class.java)
                intent.putExtra("checklist", checklist)
                intent.putExtra("max_word_idx", -1)
                startActivity(intent)
            }
            else {
                AlertDialog()
            }
        }
    }

    private fun get_boolean_list_of_words(checkedItemPositions: SparseBooleanArray): BooleanArray {
        val arr = BooleanArray(list_of_words.size){false}
        for (i in 0 until checkedItemPositions.size()) {
            val key = checkedItemPositions.keyAt(i)
            arr[key] = checkedItemPositions.get(key)
        }
        return arr
    }

    private fun AlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Bitte mindestens ein Wort auswählen")
            .setCancelable(false).setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alert: AlertDialog = alertDialogBuilder.create()
        alert.show()
    }
}