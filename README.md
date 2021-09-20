## RealmDatabaseDemo

### Creating a Realm Model Class
```kotlin
@RealmClass
open class Note() : RealmModel {
    @PrimaryKey
    var id: String = ""

    @Required
    var title: String? = ""

    @Required
    var description: String? = ""
}

```
### Adding Data Into Realm
```kotlin
fun addNote(noteTitle: String, noteDescription: String) {
        realm.executeTransaction { r: Realm ->
            val note = r.createObject(Note::class.java, UUID.randomUUID().toString())
            note.title = noteTitle
            note.description = noteDescription

            realm.insertOrUpdate(note)
        }
    }
```

### Query data from the Realm database
```kotlin
private fun getAllNotes(): MutableLiveData<List<Note>> {
        val list = MutableLiveData<List<Note>>()
        val notes = realm.where(Note::class.java).findAll()
        list.value = notes?.subList(0, notes.size)
        return list
    }
```

#### Updating data
```kotlin
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
```

### Deleting A Single data of a paricular Realm object
```kotlin
fun deleteNote(id: String) {
        val notes = realm.where(Note::class.java)
            .equalTo("id", id)
            .findFirst()

        realm.executeTransaction {
            notes!!.deleteFromRealm()
        }
    }
```

### Deleting all data from a Realm object
```kotlin
fun deleteAllNotes() {
        realm.executeTransaction { r: Realm ->
            r.delete(Note::class.java)
        }
    }
``` 

### Demo

<p float="left">
<img src="screenshots/Screenshot_20210818-091028.png" width=250/>
<img src="screenshots/Screenshot_20210818-091130.png" width=250/>
<img src="screenshots/Screenshot_20210818-091204.png" width=250/>
  </p>
