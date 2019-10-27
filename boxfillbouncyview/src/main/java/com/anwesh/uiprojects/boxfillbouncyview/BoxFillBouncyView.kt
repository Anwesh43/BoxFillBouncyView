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
val deg : Float = 180f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawLid(scale : Float, size : Float, paint : Paint) {
    save()
    rotate(-180f * scale.sinify())
    drawLine(0f, 0f, size, 0f, paint)
    restore()
}

fun Canvas.drawBoxFill(scale : Float, size : Float, paint : Paint) {
    for (j in 0..1) {
        save()
        translate(size * j, size)
        drawLine(0f, 0f, 0f, -size, paint)
        if (j == 0) {
            drawLine(0f, 0f, size, 0f, paint)
        }
        restore()
    }
    drawRect(RectF(0f, size - size * scale.divideScale(0, 2), size, size), paint)
}

fun Canvas.drawBoxFillBouncy(scale : Float, size : Float, paint : Paint) {
    drawBoxFill(scale, size, paint)
    drawLid(scale, size, paint)
}

fun Canvas.drawBFBNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(w / 2, gap * (i + 1))
    drawBoxFillBouncy(scale, size, paint)
    restore()
}

class BoxFillBouncyView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += scGap * dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}