package com.antin.es.logstash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/4/20.
 */
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1; i++) {
            LOGGER.info("Info log [" + i + "].你好，中国");
            Thread.sleep(500);
        }
    }
}

