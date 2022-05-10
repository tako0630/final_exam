package tw.edu.pu.s1092788.final_exam

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),SurfaceHolder.Callback {
    lateinit var surfaceHolder: SurfaceHolder
    lateinit var BG: Bitmap
    lateinit var fly1:Bitmap
    init {
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(), R.drawable.background)
        fly1 = BitmapFactory.decodeResource(getResources(), R.drawable.fly1)
        surfaceHolder.addCallback(this)
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        var canvas: Canvas = surfaceHolder.lockCanvas()
        drawSomething(canvas)
        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    fun drawSomething(canvas:Canvas) {
        var bgSrcRect:Rect = Rect(0, 0, BG.width, BG.height) //裁切
        var bgDestRect:Rect = Rect(0, 0, 1920, 1080)
        canvas.drawBitmap(BG, bgSrcRect, bgDestRect,null)

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        //TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        //TODO("Not yet implemented")
    }
}