package exam.courseContext.application;

import exam.courseContext.domain.model.course.Course;
import exam.courseContext.domain.model.course.CourseId;
import exam.courseContext.domain.model.course.CourseRepository;
import exam.courseContext.domain.service.ExaminationClient;
import exam.courseContext.domain.service.ExaminationDto;
import org.springframework.stereotype.Service;

@Service
public class CourseApplicationService {
    private final CourseRepository courseRepository;
    private ExaminationClient examinationClient;

    public CourseApplicationService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseId createCourse(CourseCommand courseCommand) {
        final Course.Examination examination = examinationDtoFrom(courseCommand).toExamination();
        String videoLink = courseCommand.getVideoLink();
        CourseId courseId = CourseId.nextId();
        Course course = Course.create(courseId, examination, videoLink);
        courseRepository.save(course);
        return courseId;
    }

    public void updateCourse(String courseId, CourseCommand courseCommand) {
        final Course.Examination examination = examinationDtoFrom(courseCommand).toExamination();
        String videoLink = courseCommand.getVideoLink();
        Course course = courseRepository.find(new CourseId(courseId));
        course.update(videoLink);
        course.update(examination);
        courseRepository.update(course);
    }

    public void publishCourse(String courseId) {
        Course course = courseRepository.find(new CourseId(courseId));
        course.publish();
        courseRepository.update(course);
    }

    private ExaminationDto examinationDtoFrom(CourseCommand courseCommand) {
        return new ExaminationDto(courseCommand.getExamination().getExaminationId(), courseCommand.getExamination().getName(), courseCommand.getExamination().getDescription());
    }

}
