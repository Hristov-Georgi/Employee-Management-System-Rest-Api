package com.sirmaacademy.employeemanagementsystemrestapi.mapper;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Employee;
import com.sirmaacademy.employeemanagementsystemrestapi.model.response.EmployeeEntityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    List<EmployeeEntityResponse> employeeToEmployeeResponseList(List<Employee> employees);

    @Mapping(target = "departmentName", source = "department.name")
    @Mapping(target = "position", source = "position.name")
    EmployeeEntityResponse employeeToEmployeeEntityResponse(Employee employee);

}
