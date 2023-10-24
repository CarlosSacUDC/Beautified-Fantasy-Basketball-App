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

class MainActivity : AppCompatActivity() {
    var tkn = "cc1eIk6k59pzcdBdZg6TE2vuVBeD18YzY3OujmNlgn1PBcPswVCzUUU2USQM"

    private lateinit var playerList: MutableList<PlayerDetails>
    private lateinit var rvPlayer: RecyclerView
    private val teamNames = mapOf(
        "ATL" to "Atlanta Hawks",
        "BOS" to "Boston Celtics",
        "BKN" to "Brooklyn Nets",
        "CHA" to "Charlotte Hornets",
        "CHI" to "Chicago Bulls",
        "CLE" to "Cleveland Cavaliers",
        "DAL" to "Dallas Mavericks",
        "DEN" to "Denver Nuggets",
        "DET" to "Detroit Pistons",
        "GS" to "Golden State Warriors",
        "HOU" to "Houston Rockets",
        "IND" to "Indiana Pacers",
        "LAC" to "Los Angeles Clippers",
        "LAL" to "Los Angeles Lakers",
        "MEM" to "Memphis Grizzlies",
        "MIA" to "Miami Heat",
        "MIL" to "Milwaukee Bucks",
        "MIN" to "Minnesota Timberwolves",
        "NO" to "New Orleans Pelicans",
        "NYK" to "New York Knicks",
        "OKC" to "Oklahoma City Thunder",
        "ORL" to "Orlando Magic",
        "PHI" to "Philadelphia 76ers",
        "PHO" to "Phoenix Suns",
        "POR" to "Portland Trail Blazers",
        "SAC" to "Sacramento Kings",
        "SA" to "San Antonio Spurs",
        "TOR" to "Toronto Raptors",
        "UTA" to "Utah Jazz",
        "WAS" to "Washington Wizards"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPlayer = findViewById(R.id.player_list)
        playerList = mutableListOf()

        getPlayerImageURL()

    }
    private fun getPlayerImageURL() {

        val client = AsyncHttpClient()

        client["https://api.sportsdata.io/v3/nba/scores/json/Players?key=b32947d1bc404b5db05091ad3cc81dd3", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Player", "response successful$json")


                for (i in 0 until 20) {
                    val playerImageArray = json.jsonArray.getJSONObject(i).getString("PhotoUrl")
                    val name = json.jsonArray.getJSONObject(i).getString("DraftKingsName")
                    val team = json.jsonArray.getJSONObject(i).getString("Team")
                    val fullTeamName = teamNames[team]
                    val player = PlayerDetails(name,fullTeamName,playerImageArray)
                    playerList.add(player)
                }

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
