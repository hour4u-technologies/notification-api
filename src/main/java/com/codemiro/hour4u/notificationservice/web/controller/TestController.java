package com.codemiro.hour4u.notificationservice.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonaws.services.pinpoint.model.MessageType;
import com.codemiro.hour4u.notificationservice.util.SMSUtility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Test controller.
 */
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TestController  {
	
    private final SMSUtility smsUtility;

    /**
     * Home string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/")
    public String home(Map<String,Object> model) {
        smsUtility.sendSMSMessage("345645", "9015418078", MessageType.TRANSACTIONAL);
        return "base";
    }	
}

/**
 * The type Job category preference.
 */
@Getter @Setter
class JobCategoryPreference{

    /**
     * The Level.
     */
    public String level;
    /**
     * The Status.
     */
    public String status;
    /**
     * The Name.
     */
    public String name;
    /**
     * The Type id.
     */
    public String typeId;
}
