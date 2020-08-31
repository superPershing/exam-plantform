package exam.courseContext.domain.model.course;

import exam.shared.Entity;
import exam.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@EqualsAndHashCode(of = {"courseId"})
public class Course implements Entity<Course> {
    @Getter
    private final CourseId courseId;
    @Getter
    private Examination examination;
    @Getter
    private String videoLink;
    private CourseStatusEnum courseStatus;
    @Getter
    private final LocalDateTime createTime;
    @Getter
    private LocalDateTime publishTime;

    private Course(CourseId courseId, Examination examination, String videoLink) {
        this.courseId = courseId;
        this.examination = examination;
        this.videoLink = videoLink;
        this.courseStatus = CourseStatusEnum.CREATED;
        this.createTime = LocalDateTime.now();
    }

    public static Course create(CourseId courseId, Examination examination, String videoLink) {
        return new Course(courseId, examination, videoLink);
    }

    public boolean isPublished() {
        return courseStatus == CourseStatusEnum.PUBLISHED;
    }

    public void update(Examination examination) {
        this.examination = examination;
    }

    public void update(String videoLink) {
        this.videoLink = videoLink;
    }

    public void publish() {
        this.publishTime = LocalDateTime.now();
        this.courseStatus = CourseStatusEnum.PUBLISHED;
    }

    @Override
    public boolean sameIdentityAs(Course other) {
        return courseId.sameValueAs(other.courseId);
    }


    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    public static class Examination implements ValueObject<Examination> {
        private final String examinationId;
        private final String name;
        private final String description;

        @Override
        public boolean sameValueAs(Examination other) {
            return equals(other);
        }


    }
}
