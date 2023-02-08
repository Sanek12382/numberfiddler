package com.practicething.numberfiddler.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class FileEntry implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String date;

    private String name;

    private int contentSum;

    public FileEntry(){};

    public int getContentSum() {
        return contentSum;
    }

    public void setContentSum(int contentSum) {
        this.contentSum = contentSum;
    }

    public FileEntry(String date, int contentSum, String name){
        this.contentSum=contentSum ;
        this.date=date;
        this.name=name;

    };


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", contentSum='" + contentSum + '\'' +
                '}';
    }
}
