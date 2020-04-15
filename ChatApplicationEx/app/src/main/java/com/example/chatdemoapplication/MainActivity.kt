package com.example.chatdemoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.chatsdk.core.session.ChatSDK
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_chat.setOnClickListener {
            ChatSDK.ui().startSplashScreenActivity(this)

               // loginbynumber()
        }

    }

    private fun loginbynumber() {

        val phoneNumber = "+919766771918"
        val smsCode = "123456"

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseAuthSettings = firebaseAuth.firebaseAuthSettings

// Configure faking the auto-retrieval with the whitelisted numbers.
        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, smsCode)

        val phoneAuthProvider = PhoneAuthProvider.getInstance()
        phoneAuthProvider.verifyPhoneNumber(
            phoneNumber,
            60L,
            TimeUnit.SECONDS,
            this,
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Instant verification is applied and a credential is directly returned.
                    // ...
                    Log.d("",""+credential.smsCode)
                }

                override fun onVerificationFailed(p0: FirebaseException) {

                }

                // ...
            })



    }


    //auth-current-user-id -> 73udyiYpvVcLkjL2vQ2kOgjuxrr1
}
