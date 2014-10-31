package com.example.testintro.network;

import java.util.Random;

public class RequestPerformer {

    public int getStatusCode() {
        return 200 + new Random().nextInt(300);
    }
}
