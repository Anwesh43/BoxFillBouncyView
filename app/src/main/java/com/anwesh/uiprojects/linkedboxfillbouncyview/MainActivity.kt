package com.anwesh.uiprojects.linkedboxfillbouncyview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.boxfillbouncyview.BoxFillBouncyView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BoxFillBouncyView.create(this)
    }
}
