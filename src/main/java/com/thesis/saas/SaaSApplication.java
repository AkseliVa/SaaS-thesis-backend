package com.thesis.saas;

import com.thesis.saas.admin.Admin;
import com.thesis.saas.admin.AdminRepository;
import com.thesis.saas.company.Company;
import com.thesis.saas.company.CompanyRepository;
import com.thesis.saas.employee.Employee;
import com.thesis.saas.employee.EmployeeRepository;
import com.thesis.saas.employee.EmployeesProjects;
import com.thesis.saas.employee.EmployeesProjectsRepository;
import com.thesis.saas.project.Project;
import com.thesis.saas.project.ProjectRepository;
import com.thesis.saas.project.ProjectsEmployees;
import com.thesis.saas.project.ProjectsEmployeesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class SaaSApplication implements CommandLineRunner {
    private final AdminRepository adminRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final EmployeesProjectsRepository employeesProjectsRepository;
    private final ProjectsEmployeesRepository projectsEmployeesRepository;

    public SaaSApplication(AdminRepository aRepository, CompanyRepository cRepository, EmployeeRepository eRepository, ProjectRepository pRepository, EmployeesProjectsRepository epRepository, ProjectsEmployeesRepository peRepository) {
        this.adminRepository = aRepository;
        this.companyRepository = cRepository;
        this.employeeRepository = eRepository;
        this.projectRepository = pRepository;
        this.employeesProjectsRepository = epRepository;
        this.projectsEmployeesRepository = peRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SaaSApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all API endpoints
                        .allowedOrigins("http://localhost:5173") // Frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow common HTTP methods
                        .allowedHeaders("*"); // Allow all headers
            }
        };
    }


    public void run(String... args) throws Exception {
        // Create test companies
        Company company1 = new Company("Hykos");
        Company company2 = new Company("Transmer");

        // Save companies first so they get IDs
        companyRepository.saveAll(Arrays.asList(company1, company2));

        // Create test employees
        Employee employee1 = new Employee("Matti", "Meikäläinen", "matti@gmail.com", "0408028052", "IT", company1);
        Employee employee2 = new Employee("Tiina", "Teikäläinen", "tiina@gmail.com", "045125471", "HR", company1);
        Employee employee3 = new Employee("Pete", "Perikkä", "pete@gmail.com", "040289888", "Engineer", company1);
        Employee employee4 = new Employee("Laura", "Larppaaja", "laura@gmail.com", "040556677", "Sales", company2);
        Employee employee5 = new Employee("Sauli", "Savetti", "sauli@gmail.com", "045129866", "IT", company2);

        employeeRepository.saveAll(Arrays.asList(employee1, employee2, employee3, employee4, employee5));

        // Create test projects
        Project project1 = new Project("testproject", "testprojectdescription",
                LocalDate.of(2024, 1, 13), LocalDate.of(2024, 2, 13), company1, true);
        Project project2 = new Project("secondproject", "secondprojectdescription",
                LocalDate.of(2025, 6, 15), LocalDate.of(2025, 7, 15), company2, true);
        Project project3 = new Project("archivedproject", "this project is from the past",
                LocalDate.of(2022, 1, 1), LocalDate.of(2025, 1, 1), company1, false);

        projectRepository.saveAll(Arrays.asList(project1, project2, project3));

        EmployeesProjects ep1 = new EmployeesProjects();
        ProjectsEmployees pe1 = new ProjectsEmployees();

        ep1.setEmployee(employee1);
        pe1.setEmployees(Arrays.asList(employee1));

        ep1.setProjects(Arrays.asList(project1, project2));
        pe1.setProject(project1);

        employeesProjectsRepository.save(ep1);

        projectsEmployeesRepository.save(pe1);

        // Create admins
        Admin admin1 = new Admin("testadmin", "testadmin", "Andy", "Admin", company1);
        Admin admin2 = new Admin("secondadmin", "secondadmin", "Sally", "SecondAdmin", company2);

        adminRepository.saveAll(Arrays.asList(admin1, admin2));
    }
}
