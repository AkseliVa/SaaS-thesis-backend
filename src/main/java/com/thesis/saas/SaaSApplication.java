package com.thesis.saas;

import com.thesis.saas.admin.Admin;
import com.thesis.saas.admin.AdminRepository;
import com.thesis.saas.company.Company;
import com.thesis.saas.company.CompanyRepository;
import com.thesis.saas.employee.Employee;
import com.thesis.saas.employee.EmployeeRepository;
import com.thesis.saas.project.Project;
import com.thesis.saas.project.ProjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class SaaSApplication {
    private final AdminRepository adminRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public SaaSApplication(AdminRepository aRepository, CompanyRepository cRepository, EmployeeRepository eRepository, ProjectRepository pRepository) {
        adminRepository = aRepository;
        companyRepository = cRepository;
        employeeRepository = eRepository;
        projectRepository = pRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SaaSApplication.class, args);
    }

    public void run (String... args) throws Exception {
        // Add employees
        Employee employee1 = new Employee("Matti", "Meikäläinen", "matti@gmail.com", "0408028052", "IT");
        Employee employee2 = new Employee("Tiina", "Teikäläinen", "tiina@gmail.com", "045125471", "HR");
        Employee employee3 = new Employee("Pete", "Perikkä", "pete@gmail.com", "040289888", "Engineer");
        Employee employee4 = new Employee("Laura", "Larppaaja", "laura@gmail.com", "040556677", "Sales");
        Employee employee5 = new Employee("Sauli", "Savetti", "sauli@gmail.com", "045129866", "IT");

        employeeRepository.saveAll(Arrays.asList(employee1, employee2, employee3, employee4, employee5));

        // Add companies
        Company company1 = new Company("Hykos");
        Company company2 = new Company("Transmer");

        companyRepository.saveAll(Arrays.asList(company1, company2));

        // Add admins
        Admin admin1 = new Admin("testadmin", "testadmin", "Andy", "Admin", company1);
        Admin admin2 = new Admin("secondadmin", "secondadmin", "Sally", "SecondAdmin", company2);

        adminRepository.saveAll(Arrays.asList(admin1, admin2));

        // Add projects
        Project project1 = new Project("testproject", "testprojectdescription", LocalDate.of(2024, 1, 13), LocalDate.of(2024, 2, 13), company1);
        Project project2 = new Project("secondproject", "secondprojectdescription", LocalDate.of(2025, 6, 15), LocalDate.of(2025, 7, 15), company2);

        projectRepository.saveAll(Arrays.asList(project1, project2));
    }
}
