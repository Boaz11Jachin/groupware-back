package org.codenova.groupware.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.codenova.groupware.entity.Department;
import org.codenova.groupware.repository.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController // <- 이걸로하면 Responsebody 안붙여도 된다 (????)
@RequestMapping("/api/department")
@RequiredArgsConstructor // <- final 붙은 애들만 주입함 (올아그도 가능)
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @GetMapping
    public ResponseEntity<List<Department>> getDepartmentHandle() {
        List<Department> list = departmentRepository.findAll();

        return ResponseEntity.status(200).body(list);       //200코드는 OK. 요청이 성공했음을 나타내는 성공 응답 상태 코드
    }
}
