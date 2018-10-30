<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">

<link rel="stylesheet"
      type="text/css"
      th:href="@{/webapp/static/css/style.css}"/>

<div th:if="${!spittleList.empty}">
    <li th:each="spittle : ${spittleList}">
        <div class="spittleMessage" th:text="${spittle.message}">Message</div>
        <div>
            <span class="spittleTime" th:text="${spittle.time}">Time</span>
            (
            <span class="spittleLocation" th:text="${spittle.latitude}">Latitude</span>
            /
            <span class="spittleLocation" th:text="${spittle.longitude}">Longitude</span>
            )
        </div>
    </li>
</div>

</html>

