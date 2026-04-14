package org.example.Demo.controller.Score;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.mapper.ScoreMapper;
import org.example.Demo.server.ScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score/basics")
@Slf4j
@Tag(name = "成绩管理")
@CrossOrigin
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;
    private final ScoreMapper scoreMapper;


}
