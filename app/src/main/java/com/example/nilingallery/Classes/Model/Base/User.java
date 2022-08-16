package com.example.nilingallery.Classes.Model.Base;


import com.example.nilingallery.Classes.Model.Base.Address;
import com.example.nilingallery.Classes.Model.Base.Company;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("address")
    @Expose
    public Address address;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("website")
    @Expose
    public String website;
    @SerializedName("company")
    @Expose
    public Company company;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param website
     * @param address
     * @param phone
     * @param name
     * @param company
     * @param id
     * @param email
     * @param username
     */
    public User(Integer id, String name, String username, String email, Address address, String phone, String website, Company company) {
        super();
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

}