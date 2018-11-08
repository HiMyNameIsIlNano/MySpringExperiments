<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Spring JSP Form Library -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ page session="false" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/static/css/style.css" />" >
</head>
<body>
<h1><s:message code="register.header"/></h1>
<%--<form method="POST" action="/spitter/register">
    First Name: <input type="text" firstName="firstName" /><br/>
    Last Name: <input type="text" firstName="lastName" /><br/>
    Email: <input type="text" firstName="email" /><br/>
    Username: <input type="text" firstName="username" /><br/>
    Password: <input type="password" firstName="password" /><br/>
    <input type="submit" value="Register" />
    <!-- This prevents CSRF Attacks -->
    <input type="hidden"name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>--%>

<!-- There must be an object in the model for the method whose key is spitter, or else the form won’t be able to render -->
<sf:form method="POST" commandName="spitter" action="/spitter/register">

    <%-- This one displays all the errors in a tab above the form --%>
    <sf:errors path="*" element="div" cssClass="errors" />

    <s:message code="register.name"/>:
        <sf:input path="firstName" />
            <sf:errors path="firstName" cssClass="error"/><br/>

    <s:message code="register.surname"/>:
        <sf:input path="lastName" />
            <sf:errors path="lastName" cssClass="error"/><br/>

    <s:message code="register.email"/>:
        <sf:input path="email" type="email"/>
            <sf:errors path="email" cssClass="error"/><br/>

    <s:message code="register.username"/>:
        <sf:input path="username" />
            <sf:errors path="username" cssClass="error"/><br/>

    <s:message code="register.password"/>:
        <sf:password path="password" />
            <sf:errors path="password" cssClass="error"/><br/>

    <input type="submit" value=<s:message code="register.submit"/> />
</sf:form>
</body>
</html>