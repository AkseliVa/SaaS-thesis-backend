package com.thesis.saas;

import com.thesis.saas.admin.Admin;
import com.thesis.saas.admin.AdminRepository;
import com.thesis.saas.company.Company;
import com.thesis.saas.company.CompanyRepository;
import com.thesis.saas.customer.Customer;
import com.thesis.saas.customer.CustomerRepository;
import com.thesis.saas.employee.Employee;
import com.thesis.saas.employee.EmployeeRepository;
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
import java.util.Arrays;

@SpringBootApplication
public class SaaSApplication implements CommandLineRunner {
    private final AdminRepository adminRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final ProjectsEmployeesRepository projectsEmployeesRepository;
    private final CustomerRepository customerRepository;

    public SaaSApplication(AdminRepository aRepository, CompanyRepository cRepository, EmployeeRepository eRepository, ProjectRepository pRepository, ProjectsEmployeesRepository peRepository, CustomerRepository cuRepository) {
        this.adminRepository = aRepository;
        this.companyRepository = cRepository;
        this.employeeRepository = eRepository;
        this.projectRepository = pRepository;
        this.projectsEmployeesRepository = peRepository;
        this.customerRepository = cuRepository;
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

        companyRepository.saveAll(Arrays.asList(company1, company2));

        // Create test employees
        Employee employee1 = new Employee("Matti", "Meikäläinen", "matti@gmail.com", "0408028052", "IT", company1);
        Employee employee2 = new Employee("Tiina", "Teikäläinen", "tiina@gmail.com", "045125471", "HR", company1);
        Employee employee3 = new Employee("Pete", "Perikkä", "pete@gmail.com", "040289888", "Engineer", company1);
        Employee employee4 = new Employee("Laura", "Larppaaja", "laura@gmail.com", "040556677", "Sales", company2);
        Employee employee5 = new Employee("Sauli", "Savetti", "sauli@gmail.com", "045129866", "IT", company2);

        employeeRepository.saveAll(Arrays.asList(employee1, employee2, employee3, employee4, employee5));

        // Create test customer
        Customer customer1 = new Customer("Customer 1", "contact person", "contactPerson@gmail.com", "0405556666", employee1, company1);
        customerRepository.save(customer1);

        // Create test projects
        Project project1 = new Project("testproject", "testprojectdescription",
                LocalDate.of(2024, 1, 13), LocalDate.of(2024, 2, 13), company1, true, customer1);
        Project project2 = new Project("secondproject", "secondprojectdescription",
                LocalDate.of(2025, 6, 15), LocalDate.of(2025, 7, 15), company2, true, customer1);
        Project project3 = new Project("archivedproject", "this project is from the past",
                LocalDate.of(2022, 1, 1), LocalDate.of(2025, 1, 1), company1, false, customer1);

        projectRepository.saveAll(Arrays.asList(project1, project2, project3));

        // Create test project - employee associations
        ProjectsEmployees pe1 = new ProjectsEmployees();

        pe1.setEmployee(employee1);

        pe1.setProject(project1);

        ProjectsEmployees pe2 = new ProjectsEmployees();

        pe2.setEmployee(employee2);

        pe2.setProject(project1);

        projectsEmployeesRepository.save(pe1);
        projectsEmployeesRepository.save(pe2);

        // Create test admins
        Admin admin1 = new Admin("testadmin", "testadmin", "Andy", "Admin", company1);
        Admin admin2 = new Admin("secondadmin", "secondadmin", "Sally", "SecondAdmin", company2);

        adminRepository.saveAll(Arrays.asList(admin1, admin2));
    }
}
