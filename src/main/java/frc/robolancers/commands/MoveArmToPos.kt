/* (C) Robolancers 2024 */
package frc.robolancers.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robolancers.subsystems.ArmSubsystem

/** An example command that uses an example subsystem. */
class MoveArmToPos(val anchorSetpoint: Double, val armSetpoint: Double) : CommandBase() {

  init {

    addRequirements(ArmSubsystem)
  }

  override fun initialize() {}

  override fun execute() {
    ArmSubsystem.apply {
      setM1Position(anchorSetpoint)
      setM2Position(armSetpoint)
    }
  }

  override fun end(interrupted: Boolean) {
    // Called once the command ends or is interrupted.
  }

  override fun isFinished(): Boolean {
    // Returns true when the command should end.
    return false
  }
}
