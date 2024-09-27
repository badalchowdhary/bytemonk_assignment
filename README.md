# Setup
- Clone the Repository
git clone https://github.com/badalchowdhary/bytemonk_assignment.git
cd bytemonk_assignment
- Run the DemoApplication file

# API Endpoints
- GET	/incidents	List all incidents
- GET	/incidents/{id}	Retrieve a specific incident by ID
- POST	/incidents	Create a new incident
- PUT	/incidents/{id}	Update an existing incident

### Access the API at: http://localhost:8080

### Request Example (POST /incidents) 
{
  "title": "Network Outage",
  "description": "Complete outage in the main office",
  "severity": "HIGH",
  "incidentDate": "2024-09-25",
  "status": "Open"
}




