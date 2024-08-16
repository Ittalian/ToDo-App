package com.example.ittalian.todo

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        //カラムを変更して、再マイグレーションする時に使用
        //.deleteRealmIfMigrationNeeded()
        val config = RealmConfiguration.Builder().allowWritesOnUiThread(true).build()
        Realm.setDefaultConfiguration(config)
        //データの削除
//        Realm.deleteRealm(config)
    }
}