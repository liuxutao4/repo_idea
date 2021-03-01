package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId) {

        List<CourseSection> sectionAndLesson = courseContentService.findSectionAndLesson(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询章节和课时信息成功", sectionAndLesson);

        return responseResult;
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId) {

        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", course);

        return responseResult;
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {

        if (section.getId() == null) {
            courseContentService.saveSection(section);
            return new ResponseResult(true, 200, "新增章节成功", null);
        } else {
            courseContentService.updateSection(section);
            return new ResponseResult(true, 200, "修改章节成功", null);
        }

    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status) {

        courseContentService.updateSectionStatus(id, status);

        HashMap<String, Object> map = new HashMap<>();
        map.put("status", status);

        return new ResponseResult(true, 200, "修改章节状态成功", map);

    }

    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson lesson) {

        courseContentService.saveLesson(lesson);

        return new ResponseResult(true, 200, "新增课时成功", null);
    }
}
