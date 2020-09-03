package exam.courseContext.domain.model.course;

import exam.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CourseId implements ValueObject<CourseId> {
    private final String id;

    public static CourseId nextId() {
        return new CourseId(UUID.randomUUID().toString());
    }

    @Override
    public boolean sameValueAs(CourseId other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseId courseId = (CourseId) o;
        return Objects.equals(id, courseId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
