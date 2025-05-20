package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.Address;
import com.korit.springboot_study.dto.PostData;
import com.korit.springboot_study.dto.Student;
import com.korit.springboot_study.dto.param.SearchStudyDto;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HttpStudyController3 {

    /*
        주소에 값을 입력한다.
        파라미터를 통해 값을 입력한다.
        파라미터는 주소의 ? 뒤에 따라오는 key value 값을 의미한다.
        http://localhost:8080/api/~~~~?파라미터(여러개의 데이터를 요청시 &로 구분)
     */
    @GetMapping("/study/get")
    public String get(
            @RequestParam String name,
            @RequestParam Integer age
    ) {
        return "get 요청입니다.";
    }

    /*
        가능한 json 으로 값을 입력한다.
        "{
            "key1": "value1",
            "key2": "value2,
            "key3": {
                key4: value3,
                key5: [ "a", "b", "c", "d" ]
            }
        }"
        데이터를 받을려면 @RequestBody를 붙여줘야함
    */
    @PostMapping("/study/post")
    public String post(@RequestBody Map<String, Object> data) {
        System.out.println(data);
        System.out.println(data.get("age"));
        System.out.println(data.get("address"));
        Object data2 = data.get("address");
        Map<String, Object> map = (Map<String, Object>) data2;
        Object data3 = map.get("address3");
        List<String> list = (List<String>) data3;

        System.out.println(((Map<String, Object>) data.get("address")).get("address4"));
        return "post 요청입니다.";
    }

    @PostMapping("/study/post2")
    public String post2(@RequestBody PostData postData) {
        System.out.println(postData);
        System.out.println(postData.getAddress());
        Address add = postData.getAddress();
        System.out.println(add.getAddress4());
        return "post2 요청입니다.";
    }
    /*
        Class Student {name, age}


        get 요청
        /api/students
        응답: [{Student객체1}, {Student객체2}]
        post 요청
        /api/students
        응답: {status: "성공", data: {Student객체}}
     */
    @GetMapping("/study/students")
    public List<Student> getStudents(SearchStudyDto searchStudyDto) {
//        System.out.println(searchValue);
//        List<Student> students = List.of(
//                new Student("김준일", 32),
//                new Student("김명규", 28),
//                new Student("정선유", 26),
//                new Student("염진우", 30),
//                new Student("이수원", 25)
//        );
        List<Student> students = new ArrayList<>();
        Random random = new Random();
        int startCode = 0xAC00;     // 가
        int endCode = 0xD7A3;       // 힇

        for(int i = 0;  i < searchStudyDto.getCount(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append((char)(startCode + random.nextInt(endCode - startCode + 1)));
            builder.append((char)(startCode + random.nextInt(endCode - startCode + 1)));
            builder.append((char)(startCode + random.nextInt(endCode - startCode + 1)));
            students.add(new Student(builder.toString(), random.nextInt(100) + 1));
        }

        if(searchStudyDto.getPage() < 1) {
           searchStudyDto.setPage(1);
        }

        /*
            page = 1 -> 0
            page = 2 -> 10
            page = 3 -> 20
         */

        List<Student> foundStudents = students;
        if(searchStudyDto.getSearchValue() != null && !searchStudyDto.getSearchValue().isBlank()) {
            students.stream().filter(s-> s.getName().contains(searchStudyDto.getSearchValue())).collect(Collectors.toList());
        }

        int startIndex = (searchStudyDto.getPage() - 1) * searchStudyDto.getCount();
        List<Student> newStudents = new ArrayList<>();

        for(int i = startIndex; i < (foundStudents.size() < searchStudyDto.getCount() ? foundStudents.size() : searchStudyDto.getCount()); i++) {
            try {
                newStudents.add(students.get(startIndex + i));
            } catch (Exception e) {
                break;
            }

        }
        return students;
    }



    @PostMapping("/study/students")
    public Map<String, Object> StudentPost(@RequestBody Student student) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "성공");
        map.put("data", student);
        return map;
//        return Map.of("status", "성공", "data", student);
    }


    /*
        json 으로 값을 입력한다.
     */
    @PutMapping("/study/put")
    public String put() {
        return "put 요청입니다.";
    }

    /*
        경로에 값을 입력한다.
        /api/study/delet/10
     */
    @DeleteMapping("/study/delete/{category}/{id}")
    public String delete(@PathVariable String category, @PathVariable Long id) {
        return "delete 요청입니다.";
    }
}
