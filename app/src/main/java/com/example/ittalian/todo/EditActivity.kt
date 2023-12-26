package com.example.ittalian.todo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ittalian.todo.databinding.ActivityEditBinding
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.Date

class EditActivity : AppCompatActivity() {
    private val tag = "ToDo"
    private lateinit var realm: Realm
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        realm = Realm.getDefaultInstance()

        val bpId = intent.getLongExtra("id", 0L)

        if (bpId > 0){
            val todo = realm.where<ToDo>().equalTo("id", bpId).findFirst()
            binding.todoEdit.setText(todo?.toDo.toString())
            binding.deadLineEdit.setText(todo?.deadLine.toString())
            binding.deleteBtn.visibility = View.VISIBLE
        }
        else {
            binding.deleteBtn.visibility = View.INVISIBLE
        }

        binding.saveBtn.setOnClickListener {
            var max: String = ""
            var min: String = ""

            if (!binding.todoEdit.text.isNullOrEmpty())
                max = binding.todoEdit.text.toString()
            if (!binding.deadLineEdit.text.isNullOrEmpty())
                min = binding.deadLineEdit.text.toString()

            when (bpId){
                0L -> {
                    realm.executeTransaction {
                        val maxId = realm.where<ToDo>().max("id")
                        val nextId = (maxId?.toLong() ?: 0L) + 1L
                        Log.v("nextId", "$nextId")
                        val todo = realm.createObject<ToDo>(nextId)
                        todo.dateTime = Date()
                        todo.toDo = max
                        todo.deadLine = min
                    }
                }
                else -> {
                    realm.executeTransaction{
                        val todo = realm.where<ToDo>().equalTo("id", bpId).findFirst()
                        todo?.toDo = max
                        todo?.deadLine = min
                    }
                }
            }
            Toast.makeText(applicationContext, "保存しました", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.deleteBtn.setOnClickListener {
            realm.executeTransaction {
                val todo = realm.where<ToDo>().equalTo("id", bpId)?.findFirst()?.deleteFromRealm()
            }
            Toast.makeText(applicationContext, "削除しました", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}