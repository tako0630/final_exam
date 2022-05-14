package tw.edu.pu.s1092788.final_exam

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import tw.edu.pu.s1092788.final_exam.databinding.ActivityMainBinding


@GlideModule
public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity(),GestureDetector.OnGestureListener,View.OnTouchListener{

    lateinit var binding: ActivityMainBinding
    lateinit var gDetector: GestureDetector

    var check:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var job: Job
        lateinit var switch: Job
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        gDetector = GestureDetector(this, this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img: ImageView = findViewById(R.id.img)
        GlideApp.with(this)
            .load(R.drawable.selfpicture)
            .circleCrop()
            .override(1920, 1080)
            .into(img)

        fly.setOnTouchListener(this)

        binding.img1.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                check = !check


                if(check==false){
                    img1.setImageResource(R.drawable.start)

                }else{
                    img1.setImageResource(R.drawable.stop)

                }


            }

        }
        )

        job = GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                if(check) {
                    val canvas: Canvas = binding.mysv.holder.lockCanvas()
                    binding.mysv.drawSomething(canvas)
                    binding.mysv.holder.unlockCanvasAndPost(canvas)

                }
                delay(25)
            }

        }

        switch = GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                if(check) {
                    fly.setImageResource(R.drawable.fly2)
                    delay(100)
                    fly.setImageResource(R.drawable.fly1)
                    delay(100)
                }
                delay(25)
            }

        }

        //設定螢幕水平顯示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

    }
    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_MOVE && check){
            p0?.y = event.rawY - p0!!.height/2
        }
        return true
    }
    override fun onDown(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {

        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }


}