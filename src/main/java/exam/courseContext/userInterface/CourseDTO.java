package exam.courseContext.userInterface;

import exam.courseContext.domain.model.course.CourseId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseDTO {
    private final String uri;

    public static CourseDTO from(CourseId courseId) {
        return new CourseDTO("courses/" + courseId);
    }
}
