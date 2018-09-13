package com.longhai.playground.controller;

import com.longhai.playground.config.SecurityConfig;
import com.longhai.playground.model.Employee;
import com.longhai.playground.model.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Import(SecurityConfig.class)
@WebMvcTest(EmployeesController.class)

public class EmployeesControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {
        when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Employee(1L, "Employee", 24, null)
                ));

    }

    @Test
    public void testAdminEmployee_RoleIsMANAGER() throws Exception {

        mvc.perform(get("/admin/employees")
                .with(user("user").roles("MANAGER"))
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"Employee\",\"salary\":24,\"managerId\":null}]"));
    }

    @Test
    public void testAdminEmployee_RoleIsUSER() throws Exception {

        mvc.perform(get("/admin/employees")
                .with(user("user").roles("EMPLOYEE"))
                .accept(MediaType.ALL))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAdminEmployee_Anonymous() throws Exception {

        mvc.perform(get("/admin/employees")
                .accept(MediaType.ALL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testEmployee_Anonymous() throws Exception {

        mvc.perform(get("/employees")
                .accept(MediaType.ALL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testEmployee_NotShowingSalary() throws Exception {

        mvc.perform(get("/employees")
                .with(user("user").roles("MANAGER", "EMPLOYEE", "OTHER"))
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"Employee\",\"managerId\":null}]"));

    }

}