package org.example;

import javafx.beans.property.*;

import java.util.ArrayList;

public class InternetSalePack {
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastNAme", "");
    private final StringProperty address = new SimpleStringProperty(this, "address", "");
    private final StringProperty speed = new SimpleStringProperty(this, "speed", "");
    private final StringProperty bandwidth = new SimpleStringProperty(this, "bandwidth", "");
    private final StringProperty duration = new SimpleStringProperty(this, "duation", "");

    public InternetSalePack() {
    }

    public InternetSalePack(String firstName, String lastName, String address, String speed, String bandwidth, String duration) {

        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.address.set(address);
        this.speed.set(speed);
        this.bandwidth.set(bandwidth);
        this.duration.set(duration);
    }

    public InternetSalePack(String firstName, String lastName) {

        this.firstName.set(firstName);
        this.lastName.set(lastName);

    }

    public InternetSalePack(String firstName) {

        this.firstName.set(firstName);

    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getSpeed() {
        return speed.get();
    }

    public StringProperty speedProperty() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed.set(speed);
    }

    public String getBandwidth() {
        return bandwidth.get();
    }

    public StringProperty bandwidthProperty() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth.set(bandwidth);
    }

    public String getDuration() {
        return duration.get();
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());

    public ObjectProperty<ArrayList<String>> errorsProperty() {
        return errorList;
    }

    public boolean isValid() {
        boolean isValid = true;
        if (firstName.get() != null && firstName.get().equals("")) {
            errorList.getValue().add("First name can't be empty!");
            isValid = false;
        }
        if (lastName.get().equals("")) {
            errorList.getValue().add("Last name can't be empty!");
            isValid = false;
        }
        if (address.get().equals("")) {
            errorList.getValue().add("Address can't be empty!");
            isValid = false;
        }
        if (speed.get().equals("")) {
            errorList.getValue().add("Speed must be selected!");
            isValid = false;
        }
        if (bandwidth.get().equals("")) {
            errorList.getValue().add("Bandwidth must be selected!");
            isValid = false;
        }
        if (duration.get().equals("")) {
            errorList.getValue().add("Duration must be selected!");
            isValid = false;
        }
        return isValid;
    }

    public boolean save() {
        if (isValid()) {
            System.out.println("Internet sale package: \n" + this + "\nsaved!");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "First name: " + firstName.get() + "\n" +
                "Last name: " + lastName.get() + "\n" +
                "Address: " + address.get() + "\n" +
                "Speed: " + speed.get() + "\n" +
                "Bandwidth: " + bandwidth.get() + "\n" +
                "Duration: " + duration.get();
    }
}
