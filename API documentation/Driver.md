# API Documentation: Driver Endpoints

## Driver Register
**Endpoint:**  
`POST http://localhost:8080/api/drivers/register`  

**Content-Type:**  
`application/json`  

**Request Body:**  
```json
{
  "name": "John Doe",
  "phone": "1234567890",
  "licenseNumber": "ABC12345",
  "vehicleDetails": "Toyota Prius",
  "password": "password123"
}
```

**Response:**  
**Status Code:** `200`  
**Message:**  
`Driver registered successfully!`

---

## Driver Login
**Endpoint:**  
`POST http://localhost:8080/api/drivers/login`  

**Content-Type:**  
`application/json`  

**Request Body:**  
```json
{
  "phone": "1234567890",
  "password": "password123"
}
```

**Response:**  
**Status Code:** `200`  
**Example Token:**  
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwicm9sZSI6IkRSSVZFUiIsImlhdCI6MTY4NjI0NzIwMCwiZXhwIjoxNjg2MjUwODAwfQ.abc123xyz456
```

---

## Driver Profile
**Endpoint:**  
`GET http://localhost:8080/api/drivers/profile`  

**Headers:**  
`Authorization: Bearer <driver-token>`  

**Response:**  
**Status Code:** `200`  
**Example Response Body:**  
```json
{
  "id": 1,
  "name": "John Doe",
  "phone": "1234567890",
  "licenseNumber": "ABC12345",
  "vehicleDetails": "Toyota Prius",
  "status": "available"
}
```

---

## Driver Logout
**Endpoint:**  
`GET http://localhost:8080/api/drivers/logout`  

**Headers:**  
`Authorization: Bearer <driver-token>`  

**Response:**  
**Status Code:** `200`  
**Message:**  
`Logged out successfully!`