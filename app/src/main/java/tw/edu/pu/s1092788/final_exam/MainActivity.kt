package tw.edu.pu.s1092788.final_exam

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import android.media.MediaPlayer
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
import java.util.*


@GlideModule
public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity(),GestureDetector.OnGestureListener,View.OnTouchListener{

    lateinit var binding: ActivityMainBinding
    lateinit var gDetector: GestureDetector
    lateinit var mper: MediaPlayer
    var control:Boolean=false
    var check:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var job: Job
        lateinit var switch: Job
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        gDetector = GestureDetector(this, this)
        mper = MediaPlayer()
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
                if(check && !control) {
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
        gDetector.onTouchEvent(event)
        return true
    }
    override fun onDown(p0: MotionEvent?): Boolean {

        return true
    }

    override fun onShowPress(p0: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        if(check) {
            control = true
            mper.reset()
            mper = MediaPlayer.create(this, R.raw.shoot)
            mper.start()
            GlobalScope.launch(Dispatchers.Main) {
                fly.setImageResource(R.drawable.shoot1)
                delay(25)
                fly.setImageResource(R.drawable.shoot2)
                delay(25)
                fly.setImageResource(R.drawable.shoot3)
                delay(25)
                fly.setImageResource(R.drawable.shoot4)
                delay(25)
                fly.setImageResource(R.drawable.shoot5)
                delay(25)
            }
        }
        control=false
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