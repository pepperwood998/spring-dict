<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="wordOperation" value="${ editMode ? 'Edit Word' : 'Add Word' }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ wordOperation }</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />

  <div class="container">
    <h2>${ wordOperation }</h2>
    <a href="${ contextPath }/"><button type="button"
        class="btn btn-link">Continue search</button></a>

    <c:choose>
      <c:when test="${ editMode }">
        <c:url value="/edit" var="operationUrl" />
      </c:when>
      <c:otherwise>
        <c:url value="/add" var="operationUrl" />
      </c:otherwise>
    </c:choose>

    <form:form action="${ operationUrl }" modelAttribute="word" method="post">
      <div class="form-group">
        <form:hidden cssClass="form-control" path="id" />
      </div>
      <div class="form-group">
        <label for="word">Word:</label>
        <c:choose>
          <c:when test="${ editMode }">
            <form:input cssClass="form-control" path="key"
              placeholder="Enter word" readonly="true" />
          </c:when>
          <c:otherwise>
            <form:input cssClass="form-control" path="key"
              placeholder="Enter word" />
          </c:otherwise>
        </c:choose>
      </div>
      <div class="form-group">
        <label for="meanings">Meanings:</label>
        <form:input cssClass="form-control" id="meanings"
          placeholder="Enter meanings" path="meanings" />
      </div>
      <div class="form-group">
        <label for="sel-trans-type">Select translation type:</label>
        <!-- disable key and translation type when in edit mode -->
        <c:choose>
          <c:when test="${ editMode }">
            <form:select cssClass="form-control" id="sel-trans-type"
              path="type" disabled="true">
              <c:forEach items="${ transTypes }" var="transType">
                <c:choose>
                  <c:when test="${ word.type == transType.type }">
                    <form:option value="${ transType.type }"
                      selected="selected">${ transType.typeValue }</form:option>
                  </c:when>
                  <c:otherwise>
                    <form:option value="${ transType.type }">${ transType.typeValue }</form:option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </form:select>
          </c:when>
          <c:otherwise>
            <form:select cssClass="form-control" id="sel-trans-type"
              path="type">
              <c:forEach items="${ transTypes }" var="transType">
                <c:choose>
                  <c:when test="${ word.type == transType.type }">
                    <form:option value="${ transType.type }"
                      selected="selected">${ transType.typeValue }</form:option>
                  </c:when>
                  <c:otherwise>
                    <form:option value="${ transType.type }">${ transType.typeValue }</form:option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </form:select>
          </c:otherwise>
        </c:choose>
      </div>
      <button type="submit" class="btn btn-primary">Update</button>
    </form:form>
  </div>
</body>
</html>