package id.ac.ui.cs.mobileprogramming.irfanazizalamin.helloworld

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


class MainActivity : AppCompatActivity() {

    private lateinit var wifiManager: WifiManager
    private lateinit var resultList: List<ScanResult>
    private var deviceList: ArrayList<String> = ArrayList() // arrayList
    private lateinit var arrayAdapter: ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main);
        scanBtn.setOnClickListener {
            scanWifi()
        }

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (!wifiManager.isWifiEnabled) {
            Toast.makeText(this, "WiFi is disabled ... Enabling the WiFi", Toast.LENGTH_LONG).show();
            wifiManager.isWifiEnabled = true
        }

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, deviceList)
        listWifi.setAdapter(arrayAdapter)

        scanWifi()
    }

    private fun scanWifi() {
        registerReceiver(wifiReceiver, IntentFilter (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan()
        Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_LONG).show();
        postData(this, deviceList)
    }

    var wifiReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            resultList = wifiManager.scanResults
            unregisterReceiver(this)
            for (scanResult in resultList) {
                if (!deviceList.contains(scanResult.SSID)) {
                    deviceList.add(scanResult.SSID)
                    arrayAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun postData(context: Context, deviceList: ArrayList<String>) {
        val url = "https://d9d18a85a6ba9dc27b916910af48142a.m.pipedream.net"
        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        val postData = JSONObject()
        try {
            postData.put("wifi", deviceList)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjectRequest =
            JsonObjectRequest(Request.Method.POST, url, postData, {
                    response -> {
                Log.d("Response: ", response.toString())
            }
            }, {
                    error -> {
                Log.d("Response: ", "error")
                error.printStackTrace()
            }
            })
        requestQueue.add(jsonObjectRequest)
    }
}