package com.example.td2

import android.os.Bundle
import android.preference.PreferenceFragment
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class MySettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.preferences, rootKey)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun onDisplayPreferenceDialog(preference: Preference?) {

        val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(context)
        val appStyleColorPreference : ListPreference?= findPreference("TaskBckgColor")
        val color= appStyleColorPreference?.value
        if (color!=null){

            sharedpreferences.edit().putString("TaskBckgColor", color)?.apply()

        }


        super.onDisplayPreferenceDialog(preference)



    }

}