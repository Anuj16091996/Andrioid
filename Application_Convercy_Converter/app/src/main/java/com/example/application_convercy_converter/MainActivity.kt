package com.example.application_convercy_converter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.application_convercy_converter.Network.UsDollarAPI
import com.example.application_convercy_converter.adapter.CustomSpinnerAdapter
import com.example.application_convercy_converter.entities.CountryDetails
import com.example.application_convercy_converter.recycleView.CountriesAdapter
import com.example.application_convercy_converter.recycleView.Reciving_Currency_Activity
import com.example.myapplication_discorg_album.Network.RestCountryAPI
import com.example.myapplication_discorg_album.entities.ResultOfRestCountries
import com.example.application_convercy_converter.db.AppDatabase
import com.example.application_convercy_converter.entities.ResultofUSDollar
import com.reza.roomapplication.db.entities.CurrencyBook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), retrofit2.Callback<ArrayList<ResultOfRestCountries>>,
    AdapterView.OnItemSelectedListener {
    private lateinit var cardView: CardView
    private lateinit var rotateImage: ImageView
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinerTo: Spinner
    private lateinit var amountFromUser: EditText
    private val detailsOfCountries = ArrayList<CountryDetails>()
    private val detailsOfUSDollar = ArrayList<CountryDetails>()
    private lateinit var fromCurrencyCode: TextView
    private lateinit var toCurreicesCode: TextView
    private lateinit var swapButton: Button
    private lateinit var convertButton: Button
    private var fromCurrencyUserSelect = CountryDetails(null, null, null, null)
    private var toCurrencyUserSelect = CountryDetails(null, null, null, null)
    private val countryAdapter = CountriesAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataBase: AppDatabase
    private var toastDisplayValues = true
    private lateinit var favoriteButton: Button

    companion object {
        const val FROM_USER_CHOICE = "User"
        const val TO_USER_CHOICE = "To"
        const val USER_Amount = "amount"
        const val FAVROITE_DATA = "Favroites"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinnerFrom = findViewById(R.id.main_spinner)
        spinerTo = findViewById(R.id.main_spinnerTo)
        fromCurrencyCode = findViewById(R.id.main_fromTextCurrency)
        toCurreicesCode = findViewById(R.id.main_toTextCurrency)
        swapButton = findViewById(R.id.main_swap)
        dataBase = AppDatabase.getDatabaseInstance(this)
        amountFromUser = findViewById(R.id.main_amount)
        convertButton = findViewById(R.id.main_convertButton)
        recyclerView = findViewById<RecyclerView>(R.id.main_recycle)
        val checkAPI = RestCountryAPI.RETROFIT_SERVICE.getSearchResults()
        checkAPI.enqueue(this)
        rotateImage = findViewById(R.id.main_swap_image)
        cardView = findViewById(R.id.main_cardView)
        favoriteButton = findViewById(R.id.main_Favorites)
        convertButton.setOnClickListener(this::convertCurreinces)
        swapButton.setOnClickListener(this::swapValuesOfCurrencies)
        cardView.setOnClickListener(this::changeBolleanValues)
        recyclerView.adapter = countryAdapter
        favoriteButton.setOnClickListener(this::favroiteDataBase)
        val usDollar = UsDollarAPI.RETROFIT_SERVICE.getSearchResults()
        usDollar.enqueue(object : Callback<ResultofUSDollar> {
            override fun onResponse(
                call: Call<ResultofUSDollar>,
                response: Response<ResultofUSDollar>
            ) {
                val usDollarRates = response.body()
                if (usDollarRates != null) {
                    for ((key, value) in usDollarRates.conversion_rates) {
                        detailsOfUSDollar.add(CountryDetails(null, null, key, value.toString()))
                    }
                }
                detailsOfUSDollar.removeAt(0)
                countryAdapter.changeData(detailsOfUSDollar)
            }

            override fun onFailure(call: Call<ResultofUSDollar>, t: Throwable) {
                println(t)
            }

        })
    }


    override fun onResponse(
        call: Call<ArrayList<ResultOfRestCountries>>,
        response: Response<ArrayList<ResultOfRestCountries>>
    ) {
        val Tempdata = response.body()
        if (Tempdata != null) {
            for (pos in Tempdata) {
                if (pos.currencies != null) {
                    detailsOfCountries.add(
                        CountryDetails(
                            pos.countryName,
                            pos.flags.flagPNG,
                            pos.currencies[0].currencyCode,
                            pos.currencies[0].currencyName
                        )
                    )
                }


            }
            val adapter = CustomSpinnerAdapter(
                this,
                detailsOfCountries
            )
            callSpinnerToRenderDropDown(adapter)
        }
    }


    private fun favroiteDataBase(view: View) {
        var customersDeatils = mutableListOf<CurrencyBook>()
        AppDatabase.databaseWriteExecutor.execute {
            customersDeatils = dataBase.CurrencyDAO().getAllUser()
        }

        val intent = Intent(this, MainActivityFavroite::class.java)
        startActivity(intent)

    }

    private fun convertCurreinces(view: View) {

        if (amountFromUser.text.toString() == "") {
            Toast.makeText(this, "Enter The Amount", Toast.LENGTH_SHORT)
                .show()
        } else {
            val intent = Intent(this, Reciving_Currency_Activity::class.java)
            intent.putExtra(FROM_USER_CHOICE, fromCurrencyUserSelect)
            intent.putExtra(TO_USER_CHOICE, toCurrencyUserSelect)
            intent.putExtra(USER_Amount, amountFromUser.text.toString())
            amountFromUser.setText("0")
            startActivity(intent)
        }

    }

    private fun callSpinnerToRenderDropDown(adapter: CustomSpinnerAdapter) {
        spinerTo.adapter = adapter
        spinnerFrom.adapter = adapter
        spinnerFrom.setOnItemSelectedListener(this)
        spinerTo.setOnItemSelectedListener(this)
    }

    private fun swapValuesOfCurrencies(view: View) {
        val rotate =
            RotateAnimation(
                0F,
                180F,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            );
        rotate.setDuration(100)
        rotateImage.startAnimation(rotate)
        val Tempdata = fromCurrencyCode.text
        fromCurrencyCode.text = toCurreicesCode.text
        toCurreicesCode.text = Tempdata

        val TempClassData = fromCurrencyUserSelect
        fromCurrencyUserSelect = toCurrencyUserSelect
        toCurrencyUserSelect = TempClassData
    }

    private fun changeBolleanValues(view: View) {
        toastDisplayValues = false
    }

    override fun onFailure(call: Call<ArrayList<ResultOfRestCountries>>, t: Throwable) {
        println(t)
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
        when (parent!!.id) {
            2131231202 -> {
                val currencyCodeFrom =
                    (parent?.getItemAtPosition(pos) as CountryDetails).currencyCode
                fromCurrencyCode.text = currencyCodeFrom
                fromCurrencyUserSelect = (parent?.getItemAtPosition(pos) as CountryDetails)

            }
            2131231205 -> {
                val currencyCode =
                    (parent?.getItemAtPosition(pos) as CountryDetails).currencyCode
                toCurreicesCode.text = currencyCode
                toCurrencyUserSelect = (parent?.getItemAtPosition(pos) as CountryDetails)
            }
        }

        if (fromCurrencyCode.text == toCurreicesCode.text) {
            val currencyCode =
                (parent?.getItemAtPosition(pos + 1) as CountryDetails).currencyCode
            toCurrencyUserSelect = (parent?.getItemAtPosition(pos + 1) as CountryDetails)
            toCurreicesCode.text = currencyCode
            if (!toastDisplayValues) {
                Toast.makeText(this, "Both The Currencies Can not Be same", Toast.LENGTH_SHORT)
                    .show()
            }
            toastDisplayValues = false
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        println(p0)
    }
}



