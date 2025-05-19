package com.korit.springboot_study.controller;

// 요청 URL : /api/age 응답 데이터 : { name : 이훈렬, age : 25 }

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HttpStudyController2 {

    // 요청 URL: /api/age   응답 데이터 : { name : 김준일, age : 32 }
    // # 방법 1           (Map 상의 데이터에 변화가 있는 경우)
    @GetMapping("/age")
    public Map<String, Object> get() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "이훈렬");
        map.put("age", 25);
        return map;
    }
    // # 방법 2           (Map 상의 데이터에 변화가 없는 경우)
//    @GetMapping("/age")
//    public Map<String, Object> get() {
//        return Map.of("name", "김준일", "age", 32);
//    }

    // 요청 URL: /api/names   응답 데이터 : ["김준일", "김준이", "김준삼"]

    @GetMapping("/names")
    public String[] getName() {
       String [] names = {"김준일", "김준이", "김준삼"};
       return names;
    }

    // 요청 URL: /api/students   응답 데이터 : [{ name : 김준일, age : 32 }, { name: 김준이, age: 33 }]
    // # 방법 1
    @GetMapping("/students")
    public List<Map<String, Object>> getStudents() {
        List<Map<String, Object>> studentList = new ArrayList<>();
        Map<String, Object> student1 = new HashMap<>();
        Map<String, Object> student2 = new HashMap<>();
        student1.put("name", "김준일");
        student1.put("age", 32);
        studentList.add(student1);
        student2.put("name", "김준이");
        student2.put("age", 33);
        studentList.add(student2);
        return studentList;
    }
    // # 방법 2
//    @GetMapping("/students")
//    public List<Map<String, Object>> getStudents() {
//        return List.of(
//                Map.of("name", "김준일", "age", 32),
//                Map.of("name", "김준이", "age", 33)
//        );
//    }
    /*
        [
            {
                name: 김준일,
                age: 32,
                hobby: [축구, 농구]
            },
            {
                name: 김준이,
                age: 33,
                hobby: [골프, 낚시]
            }
        ]
     */

    @GetMapping("/students2")
    public List<Map<String, Object>> getStudents2() {
        List<Map<String, Object>> studentList2 = new ArrayList<>();
        Map<String, Object> student1 = new HashMap<>();
        Map<String, Object> student2 = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        student1.put("name", "김준일");
        student1.put("age", 32);
        list1.add("축구");
        list1.add("농구");
        student1.put("hobby", list1);
        studentList2.add(student1);

        student2.put("name", "김준이");
        student2.put("age", 33);
        list2.add("골프");
        list2.add("낚시");
        student2.put("hobby", list2);
        studentList2.add(student2);


        return studentList2;
    }
}
