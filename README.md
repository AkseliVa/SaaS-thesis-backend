# Company Management Backend

This is the **Spring Boot backend** for the Company Management application.  
It provides RESTful APIs for managing **companies, employees, customers, and projects**, as well as authentication and
data persistence.

---

## Architecture Overview

- **Framework:** Spring Boot (Java)
- **Database:** MariaDB
- **ORM:** Spring Data JPA
- **Authentication:** JWT (JSON Web Token)
- **Build Tool:** Maven

---

## Database Structure

### Company
| Field | Type | Description |
|--------|------|-------------|
| company_id | Long | Primary key |
| name | String | Company name |
| customers | List`<Customer>` | List of customers the company has |
| employees | List`<Employee>` | List of the companys employees |
| projects | List`<Project>` | List of the companys projects |

#### CompanyDTO
| Field | Type | Description |
|--------|------|-------------|
| company_id | Long | Primary key |
| name | String | Company name |
| employees | List`<EmployeeDTO>` | DTO's of the companys employees |
| projects | List`<ProjectDTO>` | DTO's of the companys projects |
| customers | List`<CustomerDTO>` | DTO's of the companys customers

### Employee
| Field | Type | Description |
|--------|------|-------------|
| employee_id | Long | Primary key |
| firstname | String | Employee first name |
| lastname | String | Employee last name |
| email | String | Employee email |
| phone | String | Phone number |
| role | String | Role in the company |
| company | Company | Many-to-One relationship |
| projects | List<ProjectsEmployees> | Intermediate database table to avoid many to many relationships between employees and projects |

#### EmployeeDTO
| Field | Type | Description |
|--------|------|-------------|
| employee_id | Long | Primary key |
| firstname | String | Employee's firstname |
| lastname | String | Employee's lastname |
| email | String | Employee's email |
| phone | String | Employee's phonenumber |
| role | String | Employee's role in the company |
| projects | List`<EmployeesProjectsDTO>` | List of the projects associated with the employee |

#### EmployeesProjectsDTO
| Field | Type | Description |
|--------|------|-------------|
| project_id | Long | Primary key |
| name | String | Projects name |
| description | String | Projects description |
| startDate | LocalDate | Start date of the project |
| endDate | LocalDate | End date of the project |
| active | Boolean | Signals if the project is active or archived |

### Customer
| Field | Type | Description |
|--------|------|-------------|
| customer_id | Long | Primary key |
| name | String | Customer name |
| contactPerson | String | Contact person name |
| contactEmail | String | Contact email |
| contactPhone | String | Contact phone |
| customerManager | Employee | Employee managing this customer |
| company | Company | Many-to-One relationship |
| projects | List<Project> | One-to-Many relationship of the projects that a customer has with the company |

#### CustomerDTO
| Field | Type | Description |
|--------|------|-------------|
| customer_id | Long | Primary key |
| name | String | Customer's company's name |
| contactPerson | String | Customer companys contact person with your company |
| contactEmail | String | Contact persons email |
| contactPhone | String | Contact persons phonenumber |
| projects | List`<ProjectDTO>` | List of projects the customer has |
| customerManager | EmployeeDTO | Your companys customer manager with the customer company |
| company_id | Long | Foreign key for associating with correct company |

#### CustomerSimpleDTO
| Field | Type | Description |
|--------|------|-------------|
| customer_id | Long | Primary key |
| name | String | Name of the customer company |

### Project
| Field | Type | Description |
|--------|------|-------------|
| project_id | Long | Primary key |
| name | String | Project name |
| description | String | Project description |
| startDate | LocalDate | Project start date |
| endDate | LocalDate | Project end date |
| active | Boolean | Whether the project is active or archived |
| company | Company | Many-to-One relationship of the company that is associated with the project |
| customer | Customer | Many-to-One relationship of the customer that has the project |
| projectsEmployees | List<ProjectsEmployees> | Assigned employees |

#### ProjectDTO
| Field | Type | Description |
|--------|------|-------------|
| project_id | Long | Primary key |
| name | String | Projects name |
| description | String | Description of the project |
| startDate | LocalDate | Start date of the project |
| endDate | LocalDate | End date of the project |
| active | Boolean | Tells if the project is active or archived (already done) |
| employees | List`<ProjectsEmployeesDTO>` | Employees associated with the project |
| company_id | Long | Foreign key for the associated company |
| customer | CustomerSimpleDTO | Simplified DTO of the associated customer |

#### ProjectsEmployees
| Field | Type | Description |
|--------|------|-------------|
| pe_id | Long | (projectsEmployees_id) primary key |
| employee | Employee | Many-to-One Employee associated with the projectsEmployees |
| project | Project | Many-to-One project associated with the projectsEmployees |

#### ProjectsEmployeesDTO
| Field | Type | Description |
|--------|------|-------------|
| employee_id | Long | Primary key |
| firstname | String | Employees firstname |
| lastname | String | Employees lastname |

---

## REST API Endpoints

### Authentication
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `POST` | `/api/auth/register` | Register a new admin/company |
| `POST` | `/api/auth/login` | Authenticate admin and return JWT token |
| `GET`  | `/api/auth/me` | Get logged-in user info (requires token) |

### Company
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/api/companies/{id}` | Get company details |
| `POST` | `/api/companies` | Add a new company (done when creating account) |
| `PUT` | `/api/companies/{id}` | Update company info |
| `DELETE` | `/api/companies/{id}` | Delete company (and user in the same time) |

### Employees
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/api/employees/company/{companyId}` | Get all employees in a company |
| `POST` | `/api/employees` | Add new employee |
| `PUT` | `/api/employees/{id}` | Update employee |
| `DELETE` | `/api/employees/{id}` | Delete employee |

### Customers
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/api/customers/company/{companyId}` | Get all customers in a company |
| `POST` | `/api/customers` | Add new customer |
| `PUT` | `/api/customers/{id}` | Update customer |
| `DELETE` | `/api/customers/{id}` | Delete customer |

### Projects
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/api/projects/company/{companyId}` | Get all projects in a company |
| `POST` | `/api/projects` | Create new project |
| `PUT` | `/api/projects/{id}` | Update project details |
| `DELETE` | `/api/projects/{id}` | Delete project |
