package com.janita.mq.consumer.one.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Janita on 2017-04-26 10:22
 */
@Data
public class User implements Serializable {

    private String userId;

    private String username;
}
