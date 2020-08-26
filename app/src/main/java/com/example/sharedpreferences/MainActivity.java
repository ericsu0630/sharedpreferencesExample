//Code to serialize an array list and save it to shared preferences
package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Rich");
        friends.add("Dan");
        friends.add("Tsu");

        ArrayList<String> friends2 = new ArrayList<>();
        friends2.add("Bob");
        friends2.add("Tony");
        friends2.add("Paul");

        try {
            String serializedFriends = ObjectSerializer.serialize(friends); //ObjectSerializer is a self defined class
            sharedPreferences.edit().putString("friends",serializedFriends).apply(); //adds the encoded data to shared preferences
            serializedFriends = ObjectSerializer.serialize(friends2);
            sharedPreferences.edit().putString("friends",serializedFriends).apply(); //replaces the data if key is the same
            Log.i("serialized friends",serializedFriends); //display encoded data
            serializedFriends = sharedPreferences.getString("friends","empty"); //gets the encoded data
            ArrayList<String> newFriends = (ArrayList<String>) ObjectSerializer.deserialize(serializedFriends); //decodes the data into a new array list
            Log.i("newFriends",newFriends.toString()); //display the decoded data
            sharedPreferences.edit().remove("friends").apply(); //removes the data from shared preferences
            sharedPreferences.edit().remove("friends2").apply();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}