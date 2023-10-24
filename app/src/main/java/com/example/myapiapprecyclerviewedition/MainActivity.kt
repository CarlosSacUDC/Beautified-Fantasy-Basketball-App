package com.example.myapiapprecyclerviewedition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var tkn = "cc1eIk6k59pzcdBdZg6TE2vuVBeD18YzY3OujmNlgn1PBcPswVCzUUU2USQM"

    private lateinit var playerList: MutableList<String>
    private lateinit var rvPlayer: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPlayer = findViewById(R.id.player_list)
        playerList = mutableListOf()

        getPlayerImageURL()

    }
    private fun getPlayerImageURL() {

        val client = AsyncHttpClient()
        val random = Random.nextInt(1,100)
        val playerJSON = "https://soccer.sportmonks.com/api/v2.0/players/16?api_token=$tkn"

        client[playerJSON, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Player", "response successful$json")

                val playerImageArray = json.jsonObject.getJSONObject("data").getString("image_path")

                playerList.add(playerImageArray)
                val adapter = PlayerAdapter(playerList)
                rvPlayer.adapter = adapter
                rvPlayer.layoutManager = LinearLayoutManager(this@MainActivity)
            }
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Player Error", errorResponse)
            }
        }]

    }




    private fun getNextImage(button: Button, imageView: ImageView) {

    }

}
