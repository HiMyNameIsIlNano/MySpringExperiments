<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">

<body>
<div class="spittleView">
    <h1>This is my profile</h1>
    <%--<div>
        <span class="pull-right">
            <img th:src="*{'data:image/png;base64,'+ ${spitter.image}}" alt="" />
        </span>
    </div>--%>
    <div class="spittleName" th:text="${spitter.username}" />
    <div class="spittleName" th:text="${spitter.firstName}" />
    <div class="spittleName" th:text="${spitter.lastName}" />
</div>
</body>
</html>