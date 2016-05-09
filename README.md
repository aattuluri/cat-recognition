#Introduction
**Cat Recognition** is a REST API service to detect cat images in a given image. It uses a simple template matching algorithm with matching threshold support to handle the noise factor. The original cat template image is a constant. The images have to be text matrices and binary, meaning they can only have 2 types of characters (For Ex: + and space)

#Frameworks and Libraries
**Languages:** Java (1.8)  
**Web container:** Spark - A micro framework for creating web applications  
**Build tool:** Maven  
**Libraries:** Jackson, Lompok, JUnit, EasyMock, Guava  

#REST API Documentation

##Request
**URL:** http://107.170.237.114/cat-image-recognition  
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
Base64 encoded text matrix  

*threshold* - Optional - Number  
A value between (25.0 to 100.0), specifies the minimum confidence with which the cat image should be located in the submitted image

##Response
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

**400** - Bad request (one or more mandatory parameters are missing or empty)
  
#Future work
