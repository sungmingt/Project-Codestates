package com.codestates;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @GetMapping
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(400, "400 오류!");
    }
}
