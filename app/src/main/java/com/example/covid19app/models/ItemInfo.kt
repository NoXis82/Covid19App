package com.example.covid19app.models

import com.google.gson.annotations.SerializedName

data class ItemInfo(
    @SerializedName("Active Cases_text")
    val cases: String = "",
    @SerializedName("Country_text")
    val country: String = "",
    @SerializedName("Last Update")
    val lastUpdate: String = "",
    @SerializedName("New Cases_text")
    val newCases: String = "",
    @SerializedName("New Deaths_text")
    val newDeaths: String = "",
    @SerializedName("Total Cases_text")
    val totalCases: String = "",
    @SerializedName("Total Deaths_text")
    val totalDeaths: String = "",
    @SerializedName("Total Recovered_text")
    val totalRecovered: String = ""
)
