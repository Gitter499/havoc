/* (C) Robolancers 2024 */
package frc.robolancers

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import com.revrobotics.SparkMaxAbsoluteEncoder

/*
 * The Constants file provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This file should not be used for any other purpose.
 * All String, Boolean, and numeric (Int, Long, Float, Double) constants should use
 * `const` definitions. Other constant types should use `val` definitions.
 */

object Constants {
  object BaseEnums {
    val kEbrushless = CANSparkMaxLowLevel.MotorType.kBrushless
    val kEdutyCycle = SparkMaxAbsoluteEncoder.Type.kDutyCycle
    val kEcoast = CANSparkMax.IdleMode.kCoast
    val kEbrake = CANSparkMax.IdleMode.kBrake
    val kPosition = CANSparkMax.ControlType.kPosition
  }

  object ArmConstants {
    const val kMOTOR_DEVICE_ID_ANCHOR = 0
    const val kMOTOR_DEVICE_ID_FLOATING = 1

    const val kS = 0.0
    const val kG = 0.0
    const val kV = 0.0
    const val kA = 0.0

    val tunables =
        mapOf(
            "kP1" to 0.0,
            "kI1" to 0.0,
            "kD1" to 0.0,
            "kFF1" to 0.0,
            "kP2" to 0.0,
            "kI2" to 0.0,
            "kD2" to 0.0,
            "kFF2" to 0.0)
  }

  object ControllerConstants {
    const val kDRIVER_CONTROLLER_PORT = 0
  }
}
