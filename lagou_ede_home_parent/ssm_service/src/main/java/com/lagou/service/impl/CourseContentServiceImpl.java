package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper contentMapper;

    @Override
    public List<CourseSection> findSectionAndLesson(Integer courseId) {
        return contentMapper.findSectionAndLesson(courseId);
    }

    @Override
    public Course findCourseByCourseId(Integer courseId) {
        return contentMapper.findCourseByCourseId(courseId);
    }

    @Override
    public void saveSection(CourseSection section) {

        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);

        contentMapper.saveSection(section);
    }

    @Override
    public void updateSection(CourseSection section) {

        section.setUpdateTime(new Date());

        contentMapper.updateSection(section);
    }

    @Override
    public void updateSectionStatus(int id, int status) {

        CourseSection section = new CourseSection();

        section.setUpdateTime(new Date());
        section.setId(id);
        section.setStatus(status);
        contentMapper.updateSectionStatus(section);
    }

    @Override
    public void saveLesson(CourseLesson lesson) {

        Date date = new Date();

        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);

        contentMapper.saveLesson(lesson);
    }

}
