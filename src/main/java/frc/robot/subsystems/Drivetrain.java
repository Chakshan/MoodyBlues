// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  private final SpeedControllerGroup m_leftMotors = 
      new SpeedControllerGroup(
        new WPI_TalonFX(Constants.DriveConstants.kLeftMaster), 
        new WPI_TalonFX(Constants.DriveConstants.kLeftFollower));
    
  private final SpeedControllerGroup m_rightMotors = 
      new SpeedControllerGroup(
        new WPI_TalonFX(Constants.DriveConstants.kRightMaster), 
        new WPI_TalonFX(Constants.DriveConstants.kRightFollower));
    
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  public Drivetrain() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  public void stop() {
    m_drive.stopMotor();
  }

}
