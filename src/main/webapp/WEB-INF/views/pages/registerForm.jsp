<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Spittr</title>
    <link rel="stylesheet"
          type="text/css"
          th:href="@{/webapp/static/css/style.css}"/>
</head>
<body>
<form method="POST" th:object="${spitter}" action="/spitter/register">
    <div class="errors" th:if="${#fields.hasErrors('*')}">
        <ul>
            <li th:each="err : ${#fields.errors('*')}"
                th:text="${err}">Input is incorrect
            </li>
        </ul>
    </div>

    <!--/*
        ${} expressions (such as ${spitter}) are SpEL expressions
        *{} expressions, are selection expressions. Selection expressions
        are evaluated on a selected object. In the case of the form, the
        selected object is the one given in the <form> tag’s th:object attribute.
    */-->
    <label th:class="${#fields.hasErrors('firstName')}? 'error'">
        First Name</label>:
    <input type="text" th:field="*{firstName}"
           th:class="${#fields.hasErrors('firstName')}? 'error'"/><br/>

    <!--/*Binding the field to the backing object’s lastName property, you use the th:field attribute,
    referring to the lastName field. By using th:field, one gets both a value attribute set to the
    value of firstName and also a name attribute set to lastName.*/-->
    <label th:class="${#fields.hasErrors('lastName')}? 'error'">
        Last Name</label>:
    <input type="text" th:field="*{lastName}"
           th:class="${#fields.hasErrors('lastName')}? 'error'"/><br/>

    <label th:class="${#fields.hasErrors('email')}? 'error'">
        Email</label>:
    <input type="text" th:field="*{email}"
           th:class="${#fields.hasErrors('email')}? 'error'"/><br/>

    <label th:class="${#fields.hasErrors('username')}? 'error'">
        Username</label>:
    <input type="text" th:field="*{username}"
           th:th:class="${#fields.hasErrors('username')}? 'error'"/><br/>

    <label th:class="${#fields.hasErrors('password')}? 'error'">
        Password</label>:
    <input type="password" th:field="*{password}"
           th:class="${#fields.hasErrors('password')}? 'error'"/><br/>

    <input type="submit" value="Register"/>
</form>
</body>
</html>