package com.example.ittalian.todo

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

open class ToDo : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var dateTime: Date = Date()
    var toDo: String = ""
    var deadLine: String = ""
}