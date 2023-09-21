/* (C) Robolancers 2024 */
package frc.robolancers.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.SparkMaxAbsoluteEncoder
import com.revrobotics.SparkMaxPIDController
import edu.wpi.first.math.controller.ArmFeedforward
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robolancers.Constants
import frc.robolancers.Constants.BaseEnums.kEbrake
import frc.robolancers.Constants.BaseEnums.kEbrushless
import frc.robolancers.Constants.BaseEnums.kEdutyCycle
import frc.robolancers.Constants.BaseEnums.kPosition

// By making a subsystem a Kotlin object, we ensure there is only ever one instance of it.
// It also reduces the need to have reference variables for the subsystems to be passed around.
object ArmSubsystem : SubsystemBase() {

  private val m_motor_1: CANSparkMax
  private val m_motor_2: CANSparkMax

  private val m_absEncoder_1: SparkMaxAbsoluteEncoder
  private val m_absEncoder_2: SparkMaxAbsoluteEncoder

  private val m_pidController_1: SparkMaxPIDController
  private val m_pidController_2: SparkMaxPIDController

  private val m_armFeedForward: ArmFeedforward

  init {

    with(Constants.ArmConstants) {
      m_motor_1 = CANSparkMax(kMOTOR_DEVICE_ID_ANCHOR, kEbrushless)
      m_motor_2 = CANSparkMax(kMOTOR_DEVICE_ID_FLOATING, kEbrushless)

      m_motor_1.apply {
        restoreFactoryDefaults()
        setIdleMode(kEbrake)
        m_absEncoder_1 = getAbsoluteEncoder(kEdutyCycle)
        m_pidController_1 = pidController
      }

      m_motor_2.apply {
        restoreFactoryDefaults()
        setIdleMode(kEbrake)
        m_absEncoder_2 = getAbsoluteEncoder(kEdutyCycle)
        m_pidController_2 = pidController
      }

      m_pidController_1.setFeedbackDevice(m_absEncoder_1)
      m_pidController_2.setFeedbackDevice(m_absEncoder_2)

      tunables.forEach { (key, value) -> SmartDashboard.putNumber(key, value) }

      m_armFeedForward = ArmFeedforward(kS, kG, kV, kA)

      configurePID()
    }
  }

  fun configurePID() {

    with(Constants.ArmConstants.tunables) {
      m_pidController_1.apply {
        p = SmartDashboard.getNumber("kP1", getValue("kP1"))
        i = SmartDashboard.getNumber("kI1", getValue("kI1"))
        d = SmartDashboard.getNumber("kD1", getValue("kD1"))
        ff = SmartDashboard.getNumber("kFF1", getValue("kFF1"))
      }
      m_pidController_2.apply {
        p = SmartDashboard.getNumber("kP2", getValue("kP2"))
        i = SmartDashboard.getNumber("kI2", getValue("kI2"))
        d = SmartDashboard.getNumber("kD2", getValue("kD2"))
        ff = SmartDashboard.getNumber("kFF2", getValue("kFF2"))
      }
    }
  }

  fun setM1Position(setpoint: Double) = m_pidController_1.setReference(setpoint, kPosition)

  fun setM2Position(setpoint: Double) = m_pidController_2.setReference(setpoint, kPosition)
}
