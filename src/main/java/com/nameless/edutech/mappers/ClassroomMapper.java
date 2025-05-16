package com.nameless.edutech.mappers;

import com.nameless.edutech.DTO.Classroom.ClassroomRequest;
import com.nameless.edutech.DTO.Classroom.ClassroomResponse;
import com.nameless.edutech.models.Classroom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ClassroomMapper {

    public abstract ClassroomResponse toClassroomResponse(Classroom classroom);

    public abstract Classroom toClassroom(ClassroomResponse classroomResponse);

    public abstract ClassroomRequest toClassroomRequest(Classroom classroom);

    public abstract Classroom toClassroom(ClassroomRequest classroomRequest);
}
