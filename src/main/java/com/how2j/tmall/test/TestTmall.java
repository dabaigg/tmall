package com.how2j.tmall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTmall {

    public static void main(String args[]){
        System.out.println(sum(3));
        System.out.println(sum(5));
    }
    public static int sum(int a){
        if (0<a){
            return sum(a-2)+3;
        }
        return 1;
    }
}
