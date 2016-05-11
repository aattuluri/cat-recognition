#Introduction
**Cat Recognition** is a REST API service to detect cats in a given image. It uses a simple template matching algorithm with matching threshold support to handle the noise factor. The original cat template image is a constant (a text matrix with + and space characters). The submitted images have to be text matrices containing only + and space characters.

#Frameworks and Libraries
**Languages:** Java (1.8)  
**Web container:** Spark - A micro framework for creating web applications  
**Build tool:** Maven  
**Libraries:** Jackson, Lompok, JUnit, EasyMock, Guava  

#REST API Documentation
The URL for the APIs' is http://107.170.237.114. Following is the list of APIs' with Request and Response for each API.
##Cat Image Recognition
###Request
**Path:** /cat-image-recognition  
**Request Type:** POST  
**ContentType:** application/json  
**Payload:**  
```javascript
{
  "image": "KyAgICAgICAgICAgICArCisrKyAgICAgICAgICsrKwogKysrKysrKysrKysrKwogKysgICAgICAgICArKworKyAgKyAgICAgKyAgKysKKysgKysrICAgKysrICsrCisrICAgICAgICAgICArKwogKysgICArKysgICArKwogKysgICAgICAgICArKwogICsrICsgICArICsrCiAgKysgICsrKyAgKysKICAgKysgICAgICsrCiAgICAgKysrKysKCiAgICAgICAgICAgICAgIAo=",              
  "threshold": 75.0
}
```
**Key - Required - Data type**  
_______________________________________   
*image* - Mandatory - String  
Base64 encoded text matrix (containing only the characters + and space). The size of this field should be less than or equals 1 MiB

*threshold* - Optional - Number  
A value between (25.0 to 100.0), specifies the minimum confidence with which the cat image should be located in the submitted image

###Response
**Response code - Description**  
_______________________________   
**200** - The JSON response:  
```javascript
{
  "matches": [
    {
      "x": 0, 
      "y": 0,
      "confidence": 100
    }
  ]
}
```
The response contains an array of 0 or more matches. Each match contains the position (x, y) values of the found cat image and the confidence for that match.

**400** - Bad request  
i) 'image' field is missing or empty or is not Base64 encoded  
ii) 'image' field size exceeds 2Mb  
iii) 'threshold' field doesn't have a valid value between 25.0 and 100.0  
  
#Future work
