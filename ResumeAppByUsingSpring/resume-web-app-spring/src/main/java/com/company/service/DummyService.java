package com.company.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DummyService {

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public void doSecureStuff(){
        System.out.println("doSecureStuff in Service");
    }
}