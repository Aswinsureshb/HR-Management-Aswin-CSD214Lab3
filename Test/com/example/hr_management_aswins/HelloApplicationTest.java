package com.example.hr_management_aswins;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloApplicationTest {

    @Test
    void calculateYearlySalary() {

        HelloApplication x= new HelloApplication();
        assertEquals(x.CalculateYearlySalary(4000),48000);
    }
}