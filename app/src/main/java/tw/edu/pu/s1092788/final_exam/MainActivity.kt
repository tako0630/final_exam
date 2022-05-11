package tw.edu.pu.s1092788.final_exam

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import tw.edu.pu.s1092788.final_exam.databinding.ActivityMainBinding

@GlideModule
public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding

    var check:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var job: Job
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img: ImageView = findViewById(R.id.img)
        GlideApp.with(this)
            .load(R.drawable.selfpicture)
            .circleCrop()
            .override(1920, 1080)
            .into(img)

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

        //設定螢幕水平顯示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

    }


}