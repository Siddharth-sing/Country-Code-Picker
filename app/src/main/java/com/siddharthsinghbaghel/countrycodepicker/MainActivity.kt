package com.siddharthsinghbaghel.countrycodepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.rilixtech.widget.countrycodepicker.CountryCodePicker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Declaring ccp in .kt file
        val ccp:CountryCodePicker = findViewById(R.id.ccp)

        //Connecting edit text with country code picker (ccp)
        val edtPhoneNumber: EditText = findViewById(R.id.edtPhone)
        ccp.registerPhoneNumberTextView(edtPhoneNumber)

        //Button OTP
        val btnOTP: Button = findViewById(R.id.btnSendOTP)
        btnOTP.setOnClickListener{



            if(checkValidity(ccp,edtPhoneNumber))
            {
                //Change with your own functionality
                Toast.makeText(this, "OTP Sent", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun checkValidity(ccp: CountryCodePicker, edtPhoneNumber: EditText): Boolean {

        /* Checking validity for Indian Phone number */
        /* You can create it the same way for your country or multiple countries */
        val numberString = edtPhoneNumber.text.toString()

        return if(ccp.isValid) {
            Toast.makeText(this, "number " + ccp.fullNumber + " is valid.", Toast.LENGTH_SHORT).show()
            true
        } else {
            false
        }

    }
}