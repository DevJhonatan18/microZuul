#!/bin/bash

curl -X POST "localhost:8090/api/payment/v1/paymentEvent" -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODc2MzY0NTMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJqdGkiOiI3NTNkNDQzOS1iM2QwLTRmMWEtOTM4ZC0yYzRlMDA4OWY0YjciLCJjbGllbnRfaWQiOiJ3ZWJBbmd1bGFyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.ZSWhaKI4w-7LOUXfSMnB0h_K0O14XphN42W0mRkHICo'  -H 'Content-Type:application/json'  --data '{"nroprestamo": 1 , "importeCuota": 293.60 , "cuota": 6}' 
