package exam.courseContext.application;

import exam.courseContext.domain.model.course.Course;
import exam.courseContext.domain.model.course.CourseId;
import exam.courseContext.domain.model.course.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseApplicationServiceTest {
    @Autowired
    private CourseApplicationService courseApplicationService;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void should_crud_course() {

        CourseCommand courseCommand = new CourseCommand(new CourseCommand.Examination("exId1", "cs", "Computer Science"), "https://videoLink1");
        CourseId courseId = courseApplicationService.createCourse(courseCommand);

        assertThat(courseRepository.getAllPublishedCourse().size()).isEqualTo(0);

        courseApplicationService.publishCourse(courseId.getId());

        List<Course> publishedCourse = courseRepository.getAllPublishedCourse();

        assertThat(publishedCourse.size()).isEqualTo(1);
        assertThat(publishedCourse.get(0).getExamination()).isEqualTo(new Course.Examination("exId1", "cs", "Computer Science"));
        assertThat(publishedCourse.get(0).getVideoLink()).isEqualTo("https://videoLink1");
        assertThat(publishedCourse.get(0).isPublished()).isEqualTo(true);

        CourseCommand updateCourseCommand = new CourseCommand(new CourseCommand.Examination("exId2", "se", "Software Engineering"), "https://videoLink2");
        courseApplicationService.updateCourse(courseId.getId(), updateCourseCommand);

        assertThat(publishedCourse.size()).isEqualTo(1);
        assertThat(publishedCourse.get(0).getExamination()).isEqualTo(new Course.Examination("exId2", "se", "Software Engineering"));
        assertThat(publishedCourse.get(0).getVideoLink()).isEqualTo("https://videoLink2");
        assertThat(publishedCourse.get(0).isPublished()).isEqualTo(true);
    }

}