package exam.courseContext.domain.model.course;

import java.util.List;

public interface CourseRepository {
    Course find(CourseId courseId);

    void save(Course course);

    List<Course> getAll();

    void update(Course course);

    List<Course> getAllPublishedCourse();
}
