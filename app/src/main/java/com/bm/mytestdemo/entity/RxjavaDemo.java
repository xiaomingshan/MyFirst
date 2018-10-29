package com.bm.mytestdemo.entity;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by xiao on 2018/10/15.
 */

public class RxjavaDemo {

    public static RxjavaDemo instans;
    private Subject<String> subject = PublishSubject.create();

    private RxjavaDemo() {
    }

    public static RxjavaDemo getInstans() {
        if (instans == null) {
            synchronized (RxjavaDemo.class) {
                if (instans == null) {
                    instans = new RxjavaDemo();
                }
            }
        }
        return instans;
    }

    public Observable<String> getDate() {
        return subject;
    }

    public void setDate(){
        subject.onNext("11111111");
    }


}
