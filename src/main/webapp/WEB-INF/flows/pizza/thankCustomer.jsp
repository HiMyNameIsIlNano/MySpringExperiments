<html xmlns:jsp="http://java.sun.com/JSP/Page">
<jsp:output omit-xml-declaration="true"/>
<jsp:directive.page contentType="text/html; charset=UTF-8"/>
<head>
    <title>Spizza</title>
</head>
<body>
    <h2>Thank you for your order!</h2>
    <!--
        flowExecutionUrl is a variable provided by Spring Web Flow and it contains the URL of the Flow.
        Subsequently the event with id 'finished' will be triggered.
    -->
    <![CDATA[
        <a href='${flowExecutionUrl}&_eventId=finished'>Finish</a>
    ]]>
</body>
</html>

