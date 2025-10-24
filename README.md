
---

## ⚙️ BACKEND README — `README-backend.md`

```markdown
# Company Management Backend

This is the **Spring Boot backend** for the Company Management application.  
It provides RESTful APIs for managing **companies, employees, customers, and projects**, as well as authentication and data persistence.

---

## 🏗️ Architecture Overview

- **Framework:** Spring Boot (Java)
- **Database:** MariaDB
- **ORM:** Spring Data JPA
- **Authentication:** JWT (JSON Web Token)
- **Build Tool:** Maven

---

## 🗃️ Database Structure

### 1️⃣ Company
| Field | Type | Description |
|--------|------|-------------|
| company_id | Long | Primary key |
| name | String | Company name |
| email | String | Contact email |
| password | String | Hashed admin password |

### 2️⃣ Employee
| Field | Type | Description |
|--------|------|-------------|
| employee_id | Long | Primary key |
| firstname | String | Employee first name |
| lastname | String | Employee last name |
| email | String | Employee email |
| phone | String | Phone number |
| company | Company | Many-to-One relationship |
| projects | List<Project> | Many-to-Many relationship |

### 3️⃣ Customer
| Field | Type | Description |
|--------|------|-------------|
| customer_id | Long | Primary key |
| name | String | Customer name |
| contactPerson | String | Contact person name |
| contactEmail | String | Contact email |
| contactPhone | String | Contact phone |
| customerManager | Employee | Employee managing this customer |
| company | Company | Many-to-One relationship |
| projects | List<Project> | One-to-Many relationship |

### 4️⃣ Project
| Field | Type | Description |
|--------|------|-------------|
| project_id | Long | Primary key |
| name | String | Project name |
| description | String | Project description |
| startDate | LocalDate | Project start date |
| endDate | LocalDate | Project end date |
| active | Boolean | Whether the project is active |
| company | Company | Many-to-One relationship |
| customer | Customer | Linked customer |
| employees | List<Employee> | Assigned employees |

---

## 🔗 REST API Endpoints

### 🔐 Authentication
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `POST` | `/api/auth/register` | Register a new admin/company |
| `POST` | `/api/auth/login` | Authenticate admin and return JWT token |
| `GET`  | `/api/auth/me` | Get logged-in user info (requires token) |

### 🏢 Company
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/api/companies/{id}` | Get company details |
| `PUT` | `/api/companies/{id}` | Update company info |

### 👷 Employees
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/api/employees/company/{companyId}` | Get all employees in a company |
| `POST` | `/api/employees` | Add new employee |
| `PUT` | `/api/employees/{id}` | Update employee |
| `DELETE` | `/api/employees/{id}` | Delete employee |

### 🧍 Customers
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/api/customers/company/{companyId}` | Get all customers in a company |
| `POST` | `/api/customers` | Add new customer |
| `PUT` | `/api/customers/{id}` | Update customer |
| `DELETE` | `/api/customers/{id}` | Delete customer |

### 📁 Projects
| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/api/projects/company/{companyId}` | Get all projects in a company |
| `POST` | `/api/projects` | Create new project |
| `PUT` | `/api/projects/{id}` | Update project details |
| `DELETE` | `/api/projects/{id}` | Delete project |
