package org.example.Demo.controller.Course;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course/basics")
@Slf4j
@Tag(name = "课程管理")
@CrossOrigin
@RequiredArgsConstructor
public class CourseController {

}
