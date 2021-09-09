package com.pubsub.subscriberserver;

import org.springframework.web.bind.annotation.*;

@RequestMapping("")
@RestController
public class Controller {


    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ResponseBody
    public String receiveMessageTest1(@RequestBody Object data) {
        System.out.print("Data at test1: ");
        System.out.println(data);
        return "Data received at test1";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    @ResponseBody
    public String receiveMessageTest2(@RequestBody Object data) {
        System.out.print("Data at test2: ");
        System.out.println(data);
        return "Data received at test2";
    }

}
