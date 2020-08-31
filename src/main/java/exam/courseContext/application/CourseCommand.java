package exam.courseContext.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class CourseCommand {
    private Examination examination;
    private String videoLink;

    @Value
    public static class Examination {
        private String examinationId;
        private String name;
        private String description;
    }

}
