# Setup
Clone the Repository
   ```bash
git clone https://github.com/badalchowdhary/bytemonk_assignment.git
cd bytemonk_assignment
```
- Run the DemoApplication file in demo/src/main/java/com.example.demo

# API Endpoints
- GET	/incidents	List all incidents
- GET	/incidents/{id}	Retrieve a specific incident by ID
- POST	/incidents	Create a new incident
- PUT	/incidents/{id}	Update an existing incident

### Access the API at: http://localhost:8080

### Request Example (POST /incidents) 
```json
{
  "title": "Network Outage",
  "description": "Complete outage in the main office",
  "severity": "HIGH",
  "incidentDate": "2024-09-25",
  "status": "Open"
}
```




