package com.teamvoy.task.exception;

//exception when user ordering good which out of stock
public class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}
