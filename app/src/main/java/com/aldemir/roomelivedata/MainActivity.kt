package com.aldemir.roomelivedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aldemir.roomelivedata.adapter.Adapter
import com.aldemir.roomelivedata.database.NotesDatabase
import com.aldemir.roomelivedata.model.NotesEntity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    //Instância do Banco de Dados que iremos usar
    lateinit var database : NotesDatabase

    //Nosso Adapter customizado para exibição das anotações
    val adapter = Adapter()

    lateinit var notesList : LiveData<List<NotesEntity>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupDatabase()
        setupRecyclerView()

        notesList.observe(this, Observer {
            it?.let {
                    lista -> adapter.updateList(lista)
            }
        })


        fab_new_note.setOnClickListener {
            val newNoteActivity = Intent(this, NewNoteActivity::class.java)
            startActivity(newNoteActivity)
        }
    }

    //Criação do Banco de Dados
    fun setupDatabase() {
        database = NotesDatabase.getInstance(this)
        notesList = database.DAO().getAll()
    }

    //Configuração do RecyclerView
    fun setupRecyclerView() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.refresh -> {
                //Caso a opção refresh seja acionada, o Adapter deverá
                //consultar as anotações no banco e exibir corretamente.
                resetData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun resetData() {

        //Fazendo a Query em background e Setando o Adapter novamente
        doAsync {
            val result = queryData()
            uiThread {
                adapter.updateList(result)
            }
        }

    }

    private fun queryData(): List<NotesEntity> {
        return database.DAO().getAllNotes()
    }
}
