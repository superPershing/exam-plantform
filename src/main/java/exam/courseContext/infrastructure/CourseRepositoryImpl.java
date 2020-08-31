package exam.courseContext.infrastructure;

import exam.courseContext.domain.model.course.Course;
import exam.courseContext.domain.model.course.CourseId;
import exam.courseContext.domain.model.course.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseRepositoryImpl implements CourseRepository {
    private final Set<Course> courseSet = new HashSet<>();

    @Override
    public Course find(CourseId courseId) {
        return courseSet.stream().filter(course -> course.getCourseId().equals(courseId)).findFirst().orElseThrow(NullPointerException::new);
    }

    @Override
    public void save(Course course) {
        courseSet.add(course);
    }

    @Override
    public List<Course> getAll() {
        return new ArrayList<>(courseSet);
    }

    @Override
    public void update(Course course) {
        Course courseToBeUpdate = find(course.getCourseId());
        courseSet.remove(courseToBeUpdate);
        courseSet.add(course);
    }

    @Override
    public List<Course> getAllPublishedCourse() {
        return getAll().stream().filter(Course::isPublished).collect(Collectors.toList());
    }
}
