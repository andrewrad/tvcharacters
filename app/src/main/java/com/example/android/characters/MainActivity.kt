package com.example.android.characters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.android.characters.api.CharacterApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        test()
    }

//    // TODO: remove
    fun test(){

        lifecycleScope.launch{
            val chars = CharacterApi.retrofitService.getSimpsonsCharacters()
            Log.i("yep", "yep chars: $chars")

            val pic = CharacterApi.retrofitService.getImage("f0eb79ee.png") //todo:remove
            Log.i("yep2", "yep pic: $pic")
        }

        Log.i("test end", "test end: ")
    }
}