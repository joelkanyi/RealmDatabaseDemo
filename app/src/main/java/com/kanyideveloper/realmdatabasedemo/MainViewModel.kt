package com.kanyideveloper.realmdatabasedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.kotlin.deleteFromRealm
import java.util.*

class MainViewModel : ViewModel() {
    private var realm: Realm = Realm.getDefaultInstance()

    val allNotes: LiveData<List<Note>>
        get() = getAllNotes()

    fun addNote(noteTitle: String, noteDescription: String) {
        realm.executeTransaction { r: Realm ->
            val note = r.createObject(Note::class.java, UUID.randomUUID().toString())
            note.title = noteTitle
            note.description = noteDescription

            realm.insertOrUpdate(note)
        }
    }

    private fun getAllNotes(): MutableLiveData<List<Note>> {
        val list = MutableLiveData<List<Note>>()
        val notes = realm.where(Note::class.java).findAll()
        list.value = notes?.subList(0, notes.size)
        return list
    }

    fun updateNote(id: String, noteTitle: String, noteDesc: String) {
        val target = realm.where(Note::class.java)
            .equalTo("id", id)
            .findFirst()

        realm.executeTransaction {
            target?.title = noteTitle
            target?.description = noteDesc
            realm.insertOrUpdate(target)
        }
    }

    fun deleteNote(id: String) {
        val notes = realm.where(Note::class.java)
            .equalTo("id", id)
            .findFirst()

        realm.executeTransaction {
            notes!!.deleteFromRealm()
        }
    }

    fun deleteAllNotes() {
        realm.executeTransaction { r: Realm ->
            r.delete(Note::class.java)
        }
    }
}