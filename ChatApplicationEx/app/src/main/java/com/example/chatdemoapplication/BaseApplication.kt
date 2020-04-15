package com.example.chatdemoapplication

import androidx.multidex.MultiDexApplication
import co.chatsdk.core.session.ChatSDK
import co.chatsdk.core.session.Configuration
import co.chatsdk.firebase.FirebaseNetworkAdapter
import co.chatsdk.firebase.file_storage.FirebaseFileStorageModule
import co.chatsdk.ui.manager.BaseInterfaceAdapter

class BaseApplication:MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initChatSDK()
    }

    private fun initChatSDK() {

        val context = getApplicationContext()
        try
        {
            // Create a new configuration
            val builder = Configuration.Builder()
            // Perform any other configuration steps (optional)
            builder.firebaseRootPath("prod")
            // Initialize the Chat SDK
            val config =  Configuration.Builder()
            ChatSDK.initialize(context, config.build(), FirebaseNetworkAdapter::class.java, BaseInterfaceAdapter::class.java)
            // File storage is needed for profile image upload and image messages
            FirebaseFileStorageModule.activate()
            // Push notification module
            ///FirebasePushModule.activate()
            // Activate any other modules you need.
            // ...

        }
        catch (e:Exception) {
            // Handle any exceptions
            e.printStackTrace()
        }


    }
}