package com.icecoder.algorithm.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-11 19:04
 */
public class TestCircularQueue {


    @Test
    public void pushAndPoll() {
        CircularQueue<Object> objectCircularQueue = new CircularQueue<>(2);
        Assertions.assertTrue(objectCircularQueue.isEmpty());
        objectCircularQueue.push(new Object());
        Assertions.assertTrue(objectCircularQueue.isFull());
        objectCircularQueue.poll();
        Assertions.assertTrue(objectCircularQueue.isEmpty());
        objectCircularQueue.push(new Object());
        Assertions.assertTrue(objectCircularQueue.isFull());


        CircularQueue<Object> objectCircularQueue2 = new CircularQueue<>(5);
        objectCircularQueue2.push(new Object());
        objectCircularQueue2.push(new Object());
        objectCircularQueue2.push(new Object());
        objectCircularQueue2.push(new Object());
        Assertions.assertTrue(objectCircularQueue2.isFull());
        objectCircularQueue2.poll();
        objectCircularQueue2.poll();
        objectCircularQueue2.poll();
        objectCircularQueue2.poll();
        Assertions.assertTrue(objectCircularQueue2.isEmpty());
    }
}
