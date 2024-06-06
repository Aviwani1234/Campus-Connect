package com.example.campus_connect_app

import GeofenceBroadcastReceiver
import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import com.google.android.gms.location.*
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest

class HostelTrackingActivity : AppCompatActivity() {
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private lateinit var geofencingClient: GeofencingClient
//
//    private val GEOFENCE_RADIUS_IN_METERS = 10F
//    private val GEOFENCE_ID = "PERSON_1_GEOFENCE"
//    private val NOTIFICATION_CHANNEL_ID = "PERSON_2_NOTIFICATION_CHANNEL"
//    private val NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        enableEdgeToEdge()
        setContentView(R.layout.activity_hostel_tracking)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        geofencingClient = LocationServices.getGeofencingClient(this)
//
//        createNotificationChannel()
//
//        requestLocationUpdates()
//
//        startGeofencing()
    }

//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                NOTIFICATION_CHANNEL_ID,
//                "Person 2 Notification Channel",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            val notificationManager =
//                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }


//    private fun requestLocationUpdates() {
//        if (checkPermissions()) {
//            if (ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return
//            }
//            fusedLocationClient.requestLocationUpdates(
//                getLocationRequest(),
//                object : LocationCallback() {
//                    override fun onLocationResult(p0: LocationResult) {
//                        p0 ?: return
//                        for (location in p0.locations) {
//                            checkDistance(location)
//                        }
//                    }
//                },
//                null
//            )
//        } else {
//            Toast.makeText(
//                this,
//                "Location permission not granted.",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }

//    private fun getLocationRequest(): LocationRequest {
//        return com.google.android.gms.location.LocationRequest.create().apply {
//            interval = 10000
//            fastestInterval = 5000
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        }
//    }
//
//    private fun checkDistance(location: Location) {
//        // Calculate distance between Person 1 and Person 2
//        val distance = calculateDistance(location.latitude, location.longitude, PERSON_2_LATITUDE, PERSON_2_LONGITUDE)
//
//        // If distance is greater than 10 meters, send notification to Person 2
//        if (distance > GEOFENCE_RADIUS_IN_METERS) {
//            sendNotification()
//        }
//    }
//
//    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
//        val results = FloatArray(1)
//        Location.distanceBetween(lat1, lon1, lat2, lon2, results)
//        return results[0]
//    }
//
//
//    private fun startGeofencing() {
//        val geofence = Geofence.Builder()
//            .setRequestId(GEOFENCE_ID)
//            .setCircularRegion(PERSON_1_LATITUDE, PERSON_1_LONGITUDE, GEOFENCE_RADIUS_IN_METERS)
//            .setExpirationDuration(Geofence.NEVER_EXPIRE)
//            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_EXIT)
//            .build()
//
//        val geofenceRequest = GeofencingRequest.Builder()
//            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
//            .addGeofence(geofence)
//            .build()
//
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        geofencingClient.addGeofences(geofenceRequest, geofencePendingIntent)?.run {
//            addOnSuccessListener {
//                // Geofences added
//            }
//            addOnFailureListener {
//                // Failed to add geofences
//            }
//        }
//    }
//
//    private val geofencePendingIntent: PendingIntent by lazy {
//        val intent = Intent(this, GeofenceBroadcastReceiver::class.java)
//        PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//    }
//
//    private fun checkPermissions(): Boolean {
//        // Check location permission
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                REQUEST_LOCATION_PERMISSION
//            )
//            return false
//        }
//        return true
//    }
//
//    private fun sendNotification() {
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        val intent = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(
//            this, 0, intent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
//            .setContentTitle("Person 1 is Beyond 10 Meters")
//            .setContentText("Person 1 is outside the safe zone.")
//            .setSmallIcon(R.drawable.logocampus)
//            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
//            .build()
//
//        notificationManager.notify(NOTIFICATION_ID, notification)
//    }
//
//    companion object {
//        const val PERSON_1_LATITUDE = 0.0 // Person 1 latitude
//        const val PERSON_1_LONGITUDE = 0.0 // Person 1 longitude
//        const val PERSON_2_LATITUDE = 0.0 // Person 2 latitude
//        const val PERSON_2_LONGITUDE = 0.0 // Person 2 longitude
//        const val REQUEST_LOCATION_PERMISSION = 1001
//    }
}