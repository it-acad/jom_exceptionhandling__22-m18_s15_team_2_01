package com.softserve.itacademy.controller;

import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class ExceptionTestController {
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    public ExceptionTestController(RoleService roleService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/400")
    public void test400() {
        roleService.create(null);
    }

    @GetMapping("/404")
    public void test404() {
        roleService.readById(100);
    }

    @GetMapping("/500")
    public void test500() {
        roleRepository.save(null);
    }
}
