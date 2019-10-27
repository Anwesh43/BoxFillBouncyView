package com.anwesh.uiprojects.boxfillbouncyview

/**
 * Created by anweshmishra on 27/10/19.
 */

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

val nodes : Int = 5
val scGap : Float = 0.02f
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val delay : Long = 30
val foreColor : Int = Color.parseColor("#0D47A1")
val backColor : Int = Color.parseColor("#BDBDBD")
