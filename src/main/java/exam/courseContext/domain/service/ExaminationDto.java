package exam.courseContext.domain.service;

import exam.courseContext.domain.model.course.Course.Examination;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExaminationDto {
    private final String id;
    private final String name;
    private final String description;

    public Examination toExamination() {
        return new Examination(id, name, description);
    }
}
