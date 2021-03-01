package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /*
        根据课程id查询章节和其对应课时信息
     */
    public List<CourseSection> findSectionAndLesson(Integer courseId);

    /*
        根据课程id查询课程信息信息
     */
    public Course findCourseByCourseId(Integer courseId);

    /*
        新增章节信息
     */
    public void saveSection(CourseSection section);

    /*
        修改章节信息
     */
    public void updateSection(CourseSection section);

    /*
        修改章节状态
     */
    public void updateSectionStatus(CourseSection section);

    /*
        新增章节信息
     */
    public void saveLesson(CourseLesson lesson);
}
