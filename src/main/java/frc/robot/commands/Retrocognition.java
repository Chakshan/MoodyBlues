// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Retrocognition extends CommandBase {
  /** Creates a new Retrocognition. */

  private Drivetrain drive;
  private JSONArray xList;
  private JSONArray yList;
  private int index = 0;
  private int finalIndex;

  public Retrocognition(Drivetrain dt, String xJSON, String yJSON) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = dt;
    addRequirements(drive);

    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader(String.format("/home/lvuser/deploy/%s", xJSON))) {
      Object obj = jsonParser.parse(reader);
      JSONObject json = (JSONObject) obj;
      xList = (JSONArray) json.get("x");
      yList = (JSONArray) json.get("y");
      System.out.println("Reading successful!!!");

    } catch (FileNotFoundException e)  {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    finalIndex = yList.size();

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.arcadeDrive(yList.indexOf(index), xList.indexOf(index));
    index++;

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (index >= finalIndex) {
      return true;
    }
    return false;
  }
}
