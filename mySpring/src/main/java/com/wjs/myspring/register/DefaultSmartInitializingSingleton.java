package com.wjs.myspring.register;

import org.springframework.beans.factory.SmartInitializingSingleton;

public class DefaultSmartInitializingSingleton implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {

    }
}
