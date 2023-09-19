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
    val brushless = CANSparkMaxLowLevel.MotorType.kBrushless
    val dutyCycle = SparkMaxAbsoluteEncoder.Type.kDutyCycle
    val coast = CANSparkMax.IdleMode.kCoast
    val brake = CANSparkMax.IdleMode.kBrake
  }
  object ArmConstants {
    const val kMOTOR_DEVICE_ID_L0 = 0
    const val kMOTOR_DEVICE_ID_L1 = 1
  }

  object ControllerConstants {
    const val kDRIVER_CONTROLLER_PORT = 0
  }
}
