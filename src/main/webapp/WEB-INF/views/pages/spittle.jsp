<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">

<link rel="stylesheet"
      type="text/css"
      th:href="@{/webapp/static/css/style.css}"/>

<body>
    <div class="spittleView">
        <div class="spittleMessage" th:text="${spittle.message}" />
        <div>
            <span class="spittleTime" th:text="${spittle.time}"></span>
        </div>
    </div>
</body>
</html>