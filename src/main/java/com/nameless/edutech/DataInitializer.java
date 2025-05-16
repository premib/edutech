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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private static final List<String> ROLES = List.of("teacher", "coordinator", "driver", "kitchen");
    private static final List<ExternalHuman> EXTERNAL_HUMANS = new ArrayList<>();
    private final SubjectRepository subjectRepository;


    public DataInitializer(StaffRepository staffRepository,
                           StudentRepository studentRepository,
                           ClassroomRepository classroomRepository,
                           RoleRepository roleRepository,
                           ExternalHumanRepository externalHumanRepository,
                           ModelMapper modelMapper, SubjectRepository subjectRepository) {
        this.staffRepository = staffRepository;
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
        this.roleRepository = roleRepository;
        this.externalHumanRepository = externalHumanRepository;
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        createRoles();
        createSubjects();
        createExternalHumans();
        createClassrooms();
        createStaffs();
        createPupils();
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
            subjectRepository.save(subject);
        }
    }

    private void createRoles() {
        for (String roleName : ROLES) {
            Role role = new Role();
            role.setName(roleName);
            role.setDescription(roleName + " description");
            roleRepository.save(role);
        }
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

    private void createExternalHumans() {
        for (int i = 1; i <= 70; i++) {
            ExternalHuman externalHuman = modelMapper.map(createHuman("external", i), ExternalHuman.class);
            EXTERNAL_HUMANS.add(externalHuman);
            externalHumanRepository.save(externalHuman);
        }
    }

    private void createClassrooms() {
        for (int i = 1; i <= 3; i++) {
            Classroom classroom = new Classroom();
            classroom.setClassNumber("C" + i);
            classroom.setSection("A");
            classroomRepository.save(classroom);
        }
    }

    private void createStaffs() {
        List<Classroom> classrooms = classroomRepository.findAll();
        List<ExternalHuman> externalHumans = externalHumanRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        for (int i = 1; i <= 10; i++) {
            List<Classroom> availableClassrooms = classrooms.stream()
                    .filter(classroom -> classroom.getInchargeStaff() == null).toList();

            availableClassrooms.forEach(classroom -> {System.out.println("Classroom: " + classroom);});

            Staff staff = modelMapper.map(createHuman("staff", i), Staff.class);
            staff.setRole(List.of(roles.get(i % roles.size())));

            if (staff.getRole().stream().anyMatch(role -> "teacher".equals(role.getName())) && !availableClassrooms.isEmpty()) {
                Classroom classroom = availableClassrooms.get(i % availableClassrooms.size());
                System.out.println("Setting classroom: " + classroom.getId() + ": " + staff.getId());
                staff.setClassroom(classroom);
                classroom.setInchargeStaff(staff);
                classroomRepository.save(classroom);
            }
            staff.setStaffGuardians(List.of(externalHumans.get(new Random().nextInt(externalHumans.size()))));
            staffRepository.save(staff);
        }
    }

    private void createPupils() {
        List<Classroom> classrooms = classroomRepository.findAll();
        List<ExternalHuman> externalHumans = externalHumanRepository.findAll();

        for (int i = 1; i <= 30; i++) {
            Student student = modelMapper.map(createHuman("student", i), Student.class);
            student.setAdmissionNumber("ADM" + i);
            student.setAdmissionDate(LocalDate.of(2020, 6, i));
            student.setRollNumber(i);
            student.setActivityStatus(ActivityStatus.ACTIVE);
            student.setClassroom(classrooms.get(i % classrooms.size()));
            student.setStudentGuardians(List.of(
                    externalHumans.get(new Random().nextInt(externalHumans.size())),
                    externalHumans.get(new Random().nextInt(externalHumans.size()))
            ));
            studentRepository.save(student);
        }
    }
}