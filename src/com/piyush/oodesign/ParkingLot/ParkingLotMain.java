package com.piyush.oodesign.ParkingLot;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;

public class ParkingLotMain {

  public static void main(String ... args){
    ParkingLot parkingLot = new ParkingLot(3, 10);
    Car car = new Car("6330944", VehicleColor.RED);
    Car bike = new Car("2511251", VehicleColor.BLACK);
    Car bus = new Car("4173989", VehicleColor.GRAY);

    parkingLot.getStatus();

    parkingLot.park(car);
    parkingLot.park(bike);
    parkingLot.unPark(car);
    parkingLot.park(bus);

    parkingLot.getStatus();
  }
}

abstract class Vehicle{
  private Slot slot;

  abstract public VehicleSize getSize();

  abstract public String getLicenseNumber();

  abstract public VehicleColor getVehicleColor();

  public void assignSlot(Slot slot){
    this.slot = slot;
  }

  public Slot getAssignedSlot(){
    return this.slot;
  }
}

class Car extends Vehicle{

  private String licenseNumber;
  private VehicleColor color;

  public Car(String licenseNumber, VehicleColor color){
    this.licenseNumber = licenseNumber;
    this.color = color;
  }

  @Override
  public VehicleSize getSize() {
    return VehicleSize.MEDIUM;
  }

  @Override
  public String getLicenseNumber() {
    return this.licenseNumber;
  }

  @Override
  public VehicleColor getVehicleColor() {
    return this.color;
  }
}

class Bike extends Vehicle{

  private String licenseNumber;
  private VehicleColor color;

  public Bike(String licenseNumber, VehicleColor color){
    this.licenseNumber = licenseNumber;
    this.color = color;
  }

  @Override
  public VehicleSize getSize() {
    return VehicleSize.SMALL;
  }

  @Override
  public String getLicenseNumber() {
    return this.licenseNumber;
  }

  @Override
  public VehicleColor getVehicleColor() {
    return this.color;
  }
}

class Bus extends Vehicle{

  private String licenseNumber;
  private VehicleColor color;

  public Bus(String licenseNumber, VehicleColor color){
    this.licenseNumber = licenseNumber;
    this.color = color;
  }

  @Override
  public VehicleSize getSize() {
    return VehicleSize.LARGE;
  }

  @Override
  public String getLicenseNumber() {
    return this.licenseNumber;
  }

  @Override
  public VehicleColor getVehicleColor() {
    return this.color;
  }
}

class ParkingLot{

  private Level[] levels;

  public ParkingLot(int levels, int slotsPerLevel){
    this.levels = new Level[levels];
    for(int i = 0; i < levels; i++){
      this.levels[i] = new Level(i, slotsPerLevel);
    }
  }

  public Slot park(Vehicle vehicle){
    for(int i = 0; i < levels.length; i++){
      Level level = levels[i];
      Slot slot = level.getNearestSlot(vehicle.getSize());
      if(slot != null){
        slot.assignVehicle(vehicle);
        vehicle.assignSlot(slot);
        System.out.println("Parked:" + slot.toString());
        return slot;
      }
    }

    System.out.println("Looks like the parking lot is full.");
    return null;
  }

  public void unPark(Vehicle vehicle){
    Slot slot = vehicle.getAssignedSlot();
    slot.unAssignVehicle();
    System.out.println("UnParked:" + slot.toString());
  }

  public void getStatus(){
    System.out.println(Arrays.toString(levels));
  }

}

class Level {
  private int number;
  private Slot[] slots;
  private boolean isFull;

  public Level(int number, int slots){
    this.number = number;
    this.slots = new Slot[slots];
    for(int i = 0; i < slots; i++){
      this.slots[i] = new Slot(i, number);
    }
    this.isFull = false;
  }

  public Slot getNearestSlot(VehicleSize size){
    if(this.isFull){
      return null;
    }

    for(int i = 0; i < slots.length; i++){
      Slot slot = slots[i];
      if(slot.getSize() == size && slot.isAvailable()){
        return slot;
      }
    }

    return null;
  }

  public String toString(){
    return "Level:" + number + "\n" + Arrays.toString(slots);
  }
}

class Slot {
  private int number;
  private VehicleSize size;
  private boolean isAvailable;
  private int level;
  private Vehicle vehicle;

  public Slot(int number, int level){
    this.number = number;
    this.size = VehicleSize.class.getEnumConstants()[number%3];
    this.isAvailable = true;
    this.level = level;
  }

  public boolean isAvailable(){
    return this.isAvailable;
  }

  public void assignVehicle(Vehicle vehicle){
    this.vehicle = vehicle;
    this.isAvailable = false;
  }

  public void unAssignVehicle(){
    this.vehicle = null;
    this.isAvailable = true;
  }

  public VehicleSize getSize(){
    return this.size;
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("Level:" + this.level + "; ");
    sb.append("Slot:" + this.number + "; ");
    sb.append("Available:" + this.isAvailable + "; ");

    if(!this.isAvailable){
      sb.append("Vehicle:" + this.vehicle.getVehicleColor() + " " + this.vehicle.getLicenseNumber() + ";");
    }

    return sb.toString();
  }
}

enum VehicleSize {
  SMALL,
  MEDIUM,
  LARGE
}

enum VehicleColor {
  RED,
  BLACK,
  GRAY
}