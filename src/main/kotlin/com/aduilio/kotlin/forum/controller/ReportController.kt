package com.aduilio.kotlin.forum.controller

import com.aduilio.kotlin.forum.service.TopicService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/reports")
class ReportController(private val topicService: TopicService) {

    @GetMapping("/topics")
    fun topicReport(model: Model): String {
        model.addAttribute("items", topicService.report())
        return "topic_report"
    }
}