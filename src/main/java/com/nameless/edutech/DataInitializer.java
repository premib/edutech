package com.nameless.edutech;

import com.github.javafaker.Faker;
import com.nameless.edutech.models.*;
import com.nameless.edutech.models.base.Human;
import com.nameless.edutech.models.base.Staff;
import com.nameless.edutech.models.embeddable.Contact;
import com.nameless.edutech.models.enums.ActivityStatus;
import com.nameless.edutech.repositories.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StaffRepository staffRepository;
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;
    private final ExternalHumanRepository externalHumanRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    private static final List<String> RELATIONSHIPS = List.of(
            "Father", "Mother", "Brother", "Sister", "Guardian", "Uncle", "Aunt", "Cousin", "Grandfather", "Grandmother"
    );

    private static final List<String> GENDERS = List.of("male", "female");
    private static final List<String> BLOOD_TYPES = List.of(
            "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    );
    private static final List<String> ROLES = List.of(
            "driver", "kitchen", "nurse", "coach", "counsellor", "registrar", "librarian"
    );
    private static final String TEACHER = "teacher";
    private static final String STUDENT = "student";
    public static final String CO_ORDINATOR = "co_ordinator";
    public static final String PRINCIPAL = "principal";

    private static final List<String> TEACHER_ROLES = List.of(
            TEACHER, CO_ORDINATOR, PRINCIPAL
    );
    private static final List<ExternalHuman> EXTERNAL_HUMANS = new ArrayList<>();
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;


    public DataInitializer(StaffRepository staffRepository,
                           StudentRepository studentRepository,
                           ClassroomRepository classroomRepository,
                           RoleRepository roleRepository,
                           ExternalHumanRepository externalHumanRepository,
                           ModelMapper modelMapper, SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.staffRepository = staffRepository;
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
        this.roleRepository = roleRepository;
        this.externalHumanRepository = externalHumanRepository;
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        createRoles();
        createSubjects();
        createClassrooms(3, 2, 15);
    }

    List<Subject> allSubjects = new ArrayList<>();
    private void createSubjects() {
        for (int i = 0; i < 15; i++) {
            Subject subject = new Subject();
            subject.setName("Subject " + i);
            subject.setActive(true);
            subject.setDescription("Subject Description " + i);
            subject.setCode("schi-00" + i);
            subject.setDepartment("subject department " + i);
            allSubjects.add(subject);
        }
        subjectRepository.saveAll(allSubjects);
    }

    private void createRoles() {
        List<Role> roles = new ArrayList<>();

        for (String roleName : ROLES) {
            Role role = new Role();
            role.setName(roleName);
            role.setDescription(roleName + " description");
            roles.add(role);
        }

        for (String roleName : TEACHER_ROLES) {
            Role role = new Role();
            role.setName(roleName);
            role.setDescription(roleName + " description");
            roles.add(role);
        }

        roleRepository.saveAll(roles);
    }

    private Human createHuman(String firstname, int number) {
        Faker faker = new Faker();
        Human externalHuman = new ExternalHuman();
        externalHuman.setFirstName(firstname + " " + number);
        externalHuman.setLastName("human");
        externalHuman.setDob(LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault()));
        externalHuman.setGender(GENDERS.get(new Random().nextInt(GENDERS.size())));
        externalHuman.setBloodType(BLOOD_TYPES.get(new Random().nextInt(BLOOD_TYPES.size())));
        externalHuman.setContact(Contact.builder()
                .address(faker.address().fullAddress())
                .city(faker.address().city())
                .zip(faker.address().zipCode())
                .state(faker.address().state())
                .country(faker.address().country())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().phoneNumber())
                .build());
        externalHuman.setPhotoUrl(faker.avatar().image());

        return externalHuman;
    }

    private final List<ExternalHuman> allExternalHumans = new ArrayList<>();
    private int assignedExternalHumans = 0;
    private ExternalHuman getAssignedExternalHumans() {
        if (assignedExternalHumans + 1 > allExternalHumans.size())
            assignedExternalHumans = 0;

        return allExternalHumans.get(assignedExternalHumans++);
    }
    private void createExternalHumans(int numberOfExternalHumans) {
        for (int i = 1; i <= numberOfExternalHumans; i++) {
            ExternalHuman externalHuman = modelMapper.map(createHuman("external", i), ExternalHuman.class);
            EXTERNAL_HUMANS.add(externalHuman);
            allExternalHumans.add(externalHuman);
        }
        externalHumanRepository.saveAll(allExternalHumans);
    }

    private void createClassrooms(int numberOfClassrooms, int numberOfSections, int numberOfStudents) {
        List<String> sections = List.of("A", "B", "C", "D", "E", "F");
        List<Classroom> classrooms = new ArrayList<>();

        for (int i = 1; i <= numberOfClassrooms; i++) {
            for (int j = 0; j < numberOfSections; j++) {
                Classroom classroom = new Classroom();
                classroom.setClassNumber(String.valueOf(i));
                classroom.setSection(sections.get(j));

                classrooms.add(classroom);
            }
        }
        classroomRepository.saveAll(classrooms);

        // principal + coordinators + teachers + students
        // all have least 1 guardian
        int totalStudents = (numberOfClassrooms * numberOfSections * numberOfStudents);
        int numberOfExternalHumans = 1 + (numberOfClassrooms) + (numberOfClassrooms * numberOfSections)
                + totalStudents;

        createExternalHumans(numberOfExternalHumans);
        createOtherStaffs();
        assignTeachersToClass(classrooms, numberOfClassrooms);
        populateStudentsToClassroom(classrooms, totalStudents);
    }

    private Staff createStaff(String name, List<Role> roles, List<ExternalHuman> externalHumans, int i) {
        Staff staff = modelMapper.map(createHuman(name, i), Staff.class);
        staff.setRole(new ArrayList<>(List.of(roles.get(i % roles.size()))));
        staff.setStaffGuardians(new ArrayList<>(Collections.singleton(externalHumans.get(new Random().nextInt(externalHumans.size())))));

        return staff;
    }

    private void createOtherStaffs() {
        List<ExternalHuman> externalHumans = externalHumanRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        for (int i = 1; i <= 10; i++) {
            Staff staff = createStaff("staff", roles, externalHumans, i);
            staff.setStaffGuardians(new ArrayList<>(Collections.singleton(getAssignedExternalHumans())));
            staffRepository.save(staff);
        }
    }

    /**
     * Populates classrooms with 1 teacher per class per available classroom
     * Also, 1 principal and 1 coordinator per classNumber is also created
     * coordinators are assigned to principal and teachers are assigned to their respective
     * classNumber's coordinators
     * @param classrooms - all classrooms available from the previous method
     * @param numberOfClassrooms - number of classNumbers. eg: 5th, 6th, etc.
     * @return
     */
    private void assignTeachersToClass(List<Classroom> classrooms, int numberOfClassrooms) {
        List<ExternalHuman> externalHumans = externalHumanRepository.findAll();

        Role principalRole = roleRepository.getRoleByName(PRINCIPAL);
        Role coordinatorRole = roleRepository.getRoleByName(CO_ORDINATOR);
        Role teacherRole = roleRepository.getRoleByName(TEACHER);

        Staff principal = modelMapper.map(createHuman(PRINCIPAL, 1), Teacher.class);
        principal.setRole(new ArrayList<>(List.of(principalRole)));
        principal.setStaffGuardians(new ArrayList<>(Collections.singleton(getAssignedExternalHumans())));

        List<Staff> coordinators = new ArrayList<>();
        for (int i = 1; i <= numberOfClassrooms; i++) {
            Teacher coordinator = modelMapper.map(createHuman(CO_ORDINATOR, i), Teacher.class);
            coordinator.setRole(new ArrayList<>(List.of(coordinatorRole)));
            coordinator.setReportsTo(principal);
            coordinators.add(coordinator);
            coordinator.setStaffGuardians(new ArrayList<>(Collections.singleton(getAssignedExternalHumans())));
            coordinator.setSubjects(new ArrayList<>(List.of(allSubjects.get(new Random().nextInt(allSubjects.size())))));
        }

        principal.setSubordinates(coordinators);
        staffRepository.save(principal);
        staffRepository.saveAll(coordinators);

        List<Teacher> teachers = new ArrayList<>();
        List<Classroom> updatedClassrooms = new ArrayList<>();
        List<Staff> updatedCoordinators = new ArrayList<>();

        for (int i = 0; i < classrooms.size(); i++) {
            Teacher teacher = modelMapper.map(
                    createStaff(TEACHER, List.of(teacherRole), externalHumans, i),
                    Teacher.class
            );

            Classroom classroom = classrooms.get(i);
            teacher.setClassroom(classroom);
            classroom.setInchargeStaff(teacher);
            updatedClassrooms.add(classroom);

            int classNumber = Integer.parseInt(classroom.getClassNumber()) - 1;
            Staff coordinator = coordinators.get(classNumber);
            teacher.setReportsTo(coordinator);
            teacher.setStaffGuardians(new ArrayList<>(Collections.singleton(getAssignedExternalHumans())));
            teacher.setSubjects(new ArrayList<>(List.of(allSubjects.get(i % allSubjects.size()))));

            if (coordinator.getSubordinates() != null) {
                List<Staff> subordinates = coordinator.getSubordinates();
                subordinates.add(teacher);
                coordinator.setSubordinates(subordinates);
            } else {
                coordinator.setSubordinates(new ArrayList<>(Collections.singleton(teacher)));
            }

            updatedCoordinators.add(coordinator);
            teachers.add(teacher);
        }

        classroomRepository.saveAll(updatedClassrooms);
        staffRepository.saveAll(updatedCoordinators);
        teacherRepository.saveAll(teachers);
    }

    private void populateStudentsToClassroom(List<Classroom> classrooms, int totalStudents) {
        List<Student> students = new ArrayList<>();

        for (int i = 1; i <= totalStudents; i++) {
            Student student = modelMapper.map(createHuman(STUDENT, i), Student.class);
            student.setAdmissionNumber("ADM" + i);
            student.setAdmissionDate(LocalDate.of(2020, 6,  ((i - 1) % 30) + 1));
            student.setRollNumber(i);
            student.setActivityStatus(ActivityStatus.ACTIVE);
            student.setClassroom(classrooms.get(i % classrooms.size()));
            student.setStudentGuardians(new ArrayList<>(Collections.singleton(getAssignedExternalHumans())));
            students.add(student);
        }
        studentRepository.saveAll(students);
    }
}