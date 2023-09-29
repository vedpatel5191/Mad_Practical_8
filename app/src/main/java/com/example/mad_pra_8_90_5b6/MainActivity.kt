package com.example.mad_pra_8_90_5b6

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import com.example.mad_pra_8_90_5b6.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window,false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.cardAlarmcreate.visibility = View.GONE
        binding.btncreatealarm.setOnClickListener(){
            showTimerDialog()
        }
    }

    private fun showTimerDialog(){
        val cldr : Calendar = Calendar.getInstance()
        val hour: Int = cldr.get(Calendar.HOUR_OF_DAY)
        val minutes : Int = cldr.get(Calendar.MINUTE)
        val picker = TimePickerDialog(this,{tp, sHour, sMinute -> sendDialogDataToActivity(sHour,sMinute)},
            hour,
            minutes,
            false)
        picker.show()
    }
}
private fun sendDialogDataToActivity(hour:Int, minute:Int ){
    val alarmCalendar = Calendar.getInstance()
    val year: Int = alarmCalendar.get(Calendar.YEAR)
    val month: Int = alarmCalendar.get(Calendar.MONTH)
    val day : Int = alarmCalendar.get(Calendar.DATE)
    alarmCalendar.set(year, month, day, hour, minute, 0)
    binding.textAlarmTime.text = SimpleDateFormate("hh:mm:ss a").format(alarmCalendar)
    binding.cardAlarmcreate.visibility = View.VISIBLE
    setAlarm(alarmCalendar.timeInMillis, "Start")
}