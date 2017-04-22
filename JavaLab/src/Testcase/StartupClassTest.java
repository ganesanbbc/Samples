package Testcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartupClassTest {
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }


    @Test
    void testThatDemo(){

        boolean condition = 1 == 1;
        assertTrue(condition);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

}