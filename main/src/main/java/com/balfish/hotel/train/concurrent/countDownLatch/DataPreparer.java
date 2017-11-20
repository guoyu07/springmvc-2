package com.balfish.hotel.train.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by yhm on 2017/11/9 AM10:50.
 */
public class DataPreparer extends BasePreparer {

    public DataPreparer(CountDownLatch countDownLatch) {
        super("data prepare", countDownLatch);
    }

    @Override
    protected void prepare() {
        System.out.println(this.getPrepareName() + "is doing");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getPrepareName() + "is done");
    }
}
