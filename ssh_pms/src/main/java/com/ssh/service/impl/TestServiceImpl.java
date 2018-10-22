package com.ssh.service.impl;

import com.ssh.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by XRog
 * On 2/1/2017.12:58 AM
 */
@Service
public class TestServiceImpl implements TestService {
    public String test() {
        return "test";
    }
}