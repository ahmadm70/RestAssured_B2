package io.loopcamp.pojo;

import lombok.Data;

@Data
public class Minion {
    private int id;
    private String gender;
    private String name;
    private String phone;
    private String lastName;

//    public Minion(int id, String gender, String name, String phone) {
//        this.setId(id);
//        this.setGender(gender);
//        this.setName(name);
//        this.setPhone(phone);
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
}