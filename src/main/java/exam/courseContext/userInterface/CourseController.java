package exam.courseContext.userInterface;

import exam.courseContext.application.CourseApplicationService;
import exam.courseContext.application.CourseCommand;
import exam.courseContext.domain.model.course.Course;
import exam.courseContext.domain.model.course.CourseId;
import exam.courseContext.domain.model.course.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {
    private final CourseRepository courseRepository;
    private final CourseApplicationService courseApplicationService;

    public CourseController(CourseRepository courseRepository, CourseApplicationService courseApplicationService) {
        this.courseRepository = courseRepository;
        this.courseApplicationService = courseApplicationService;
    }

    @PostMapping("/courses")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO create(@RequestBody CourseCommand command) {
        final CourseId courseId = courseApplicationService.createCourse(command);
        return CourseDTO.from(courseId);
    }

    @GetMapping("/courses")
    public List<Course> getAllPublishedCourse() {
        return courseRepository.getAllPublishedCourse();
    }

    @PutMapping("/courses/{courseId}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String courseId, @RequestBody CourseCommand command) {
        courseApplicationService.updateCourse(courseId, command);
    }

    @PutMapping("/courses/{courseId}/publish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publish(@PathVariable String courseId) {
        courseApplicationService.publishCourse(courseId);
    }
}
