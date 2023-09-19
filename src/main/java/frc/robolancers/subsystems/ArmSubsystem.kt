/* (C) Robolancers 2024 */
package frc.robolancers.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.SparkMaxAbsoluteEncoder
import com.revrobotics.SparkMaxPIDController
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robolancers.Constants
import frc.robolancers.Constants.BaseEnums.brake
import frc.robolancers.Constants.BaseEnums.brushless
import frc.robolancers.Constants.BaseEnums.coast
import frc.robolancers.Constants.BaseEnums.dutyCycle

// By making a subsystem a Kotlin object, we ensure there is only ever one instance of it.
// It also reduces the need to have reference variables for the subsystems to be passed around.
object ArmSubsystem : SubsystemBase() {

  private val m_motor_1: CANSparkMax
  private val m_motor_2: CANSparkMax

  private val m_absEncoder_1: SparkMaxAbsoluteEncoder
  private val m_absEncoder_2: SparkMaxAbsoluteEncoder

  private val m_pidController_1: SparkMaxPIDController
  private val m_pidController_2: SparkMaxPIDController

  init {

    with(Constants.ArmConstants) {
      m_motor_1 = CANSparkMax(kMOTOR_DEVICE_ID_L0, brushless)
      m_motor_2 = CANSparkMax(kMOTOR_DEVICE_ID_L1, brushless)

      m_motor_1.apply {
        restoreFactoryDefaults()
        setIdleMode(brake)
        m_absEncoder_1 = getAbsoluteEncoder(dutyCycle)
        m_pidController_1 = pidController
      }

      m_motor_2.apply {
        restoreFactoryDefaults()
        setIdleMode(brake)
        m_absEncoder_2 = getAbsoluteEncoder(dutyCycle)
        m_pidController_2 = pidController
      }
    }

    m_pidController_1.setFeedbackDevice(m_absEncoder_1)
    m_pidController_2.setFeedbackDevice(m_absEncoder_2)
  }

  override fun periodic() {
    m_motor_1.setIdleMode(coast)

    m_pidController_1.setReference(m_absEncoder_1.position, CANSparkMax.ControlType.kPosition)
    m_pidController_2.setReference(m_absEncoder_1.position, CANSparkMax.ControlType.kPosition)
  }
  override fun simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
