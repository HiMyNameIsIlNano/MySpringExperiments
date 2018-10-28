<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Spring Tags Library (Old one not that used anymore) -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%@ page session="false" %>
<html>
<%--Variable definition --%>
<s:url value="/spitter/register" var="registerUrl"/>

<head>
    <title>Spittr</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1><s:message code="home.welcome" /></h1>

<div>
    <s:escapeBody htmlEscape="false">
        <h1><s:message code="home.description"/></h1>
    </s:escapeBody>
</div>

<br/>

<a href="<c:url value="/spittles" />"><s:message code="home.spittles"/> </a>

<%--<s:url value="/spittles">--%>
    <%--<s:param name="max" value="40"/>--%>
    <%--<s:param name="count" value="20"/>--%>
<%--</s:url>--%>
|
<a href="<c:url value="${registerUrl}" />"><s:message code="home.register"/> </a>
</body>
</html>