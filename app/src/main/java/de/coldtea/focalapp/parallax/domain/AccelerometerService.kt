package de.coldtea.focalapp.parallax.domain

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener2
import android.hardware.SensorManager
import de.coldtea.focalapp.parallax.domain.model.SensorData
import de.coldtea.focalapp.shared.domain.ioCoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccelerometerService(
    context: Context
) : SensorEventListener2 {

    init {
        val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
        val sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        sensorManager.registerListener(this, sensorGravity, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, sensorMagneticField, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private val _sensorData = MutableStateFlow(SensorData(0f, 0f))
    val sensorData: StateFlow<SensorData> = _sensorData

    private var gravity: FloatArray? = null
    private var geomagnetic: FloatArray? = null

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GRAVITY)
            gravity = event.values

        if (event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD)
            geomagnetic = event.values

        if (gravity != null && geomagnetic != null) {
            var r = FloatArray(9)
            var i = FloatArray(9)

            if (SensorManager.getRotationMatrix(r, i, gravity, geomagnetic)) {
                var orientation = FloatArray(3)
                SensorManager.getOrientation(r, orientation)

                ioCoroutineScope.launch {
                    _sensorData.emit(
                        SensorData(
                            pitch = orientation[1],
                            roll = orientation[2]
                        )
                    )
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun onFlushCompleted(p0: Sensor?) {}
}