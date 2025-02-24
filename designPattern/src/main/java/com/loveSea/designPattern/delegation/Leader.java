package com.loveSea.designPattern.delegation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiahaifeng
 */

public class Leader implements IEmployee {
    private Map<String, IEmployee> employeeMap = new HashMap<>();

    public Leader() {
        employeeMap.put("加密", new EmployeeA());
        employeeMap.put("登陆", new EmployeeB());
    }

    @Override
    public void doing(String command) {
        Optional.ofNullable(employeeMap.get(command)).ifPresent(employee -> employee.doing(command));
    }
}