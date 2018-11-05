<html xmlns:jsp="http://java.sun.com/JSP/Page"
      xmlns:form="http://www.springframework.org/tags/form">
<jsp:output omit-xml-declaration="yes"/>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>

<head><title>Spizza</title></head>

<body>
<h2>Customer Registration</h2>
<form:form commandName="customer">
    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>

    <!-- Set the attributes of the object customer -->
    <b>Phone number: </b><form:input path="phoneNumber"/><br/>
    <b>Name: </b><form:input path="name"/><br/>
    <b>Address: </b><form:input path="address"/><br/>
    <b>City: </b><form:input path="city"/><br/>
    <b>State: </b><form:input path="state"/><br/>
    <b>Zip Code: </b><form:input path="zipCode"/><br/>

    <input type="submit" name="_eventId_submit" value="Submit"/>
    <input type="submit" name="_eventId_cancel" value="Cancel"/>
</form:form>
</body>

</html>