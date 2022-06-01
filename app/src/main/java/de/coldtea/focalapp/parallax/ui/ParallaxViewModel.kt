package de.coldtea.focalapp.parallax.ui

import android.hardware.SensorManager
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.coldtea.focalapp.parallax.domain.AccelerometerService
import de.coldtea.focalapp.parallax.domain.model.SensorData
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ParallaxViewModel @Inject constructor(
    val accelerometerService: AccelerometerService
) : ViewModel() {

    val sensorData: StateFlow<SensorData>
        get() = accelerometerService.sensorData
}