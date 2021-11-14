package com.cookandroid.project8_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.File

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var btnPrev : Button
    lateinit var btnNext : Button
    lateinit var myPicture : myPictureView
    lateinit var slide_num : TextView
    var curNum : Int = 0
    var imageFiles : Array<File>? = null
    lateinit var imageFname : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "간단 이미지 뷰어"
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), Context.MODE_PRIVATE)

        btnPrev = findViewById<Button>(R.id.btnPrev)
        btnNext = findViewById<Button>(R.id.btnNext)
        myPicture = findViewById<myPictureView>(R.id.myPictureView1)
        slide_num = findViewById<TextView>(R.id.slide)

        imageFiles = File(Environment.getExternalStorageDirectory().absolutePath + "/NewPictures").listFiles()
        imageFname = imageFiles!![0].toString()
        myPicture.imagePath=imageFname

        slide_num.text = (curNum+1).toString() + "/" + (imageFiles!!.size).toString()

        btnPrev.setOnClickListener {
            if (curNum <= 0) {
                //Toast.makeText(applicationContext, "첫번째 그림입니다", Toast.LENGTH_SHORT).show()
                //이전 버튼 누르면 마지막 사진으로 이동
                curNum = imageFiles!!.size - 1
                imageFname = imageFiles!![curNum].toString()
                myPicture.imagePath=imageFname
                myPicture.invalidate()
            } else {
                curNum--
                imageFname = imageFiles!![curNum].toString()
                myPicture.imagePath=imageFname
                myPicture.invalidate()
            }
            slide_num.text = (curNum + 1).toString() + "/" + (imageFiles!!.size).toString()

        }

        btnNext.setOnClickListener {
            if (curNum >= imageFiles!!.size - 1) {
                //Toast.makeText(applicationContext, "마지막 그림입니다", Toast.LENGTH_SHORT).show()
                //다음 버튼 누르면 처음 사진으로 이동
                curNum = 0
                imageFname = imageFiles!![curNum].toString()
                myPicture.imagePath=imageFname
                myPicture.invalidate()
            } else {
                curNum++
                imageFname = imageFiles!![curNum].toString()
                myPicture.imagePath=imageFname
                myPicture.invalidate()
            }
            slide_num.text = (curNum + 1).toString() + "/" + (imageFiles!!.size).toString()
        }
    }
}
