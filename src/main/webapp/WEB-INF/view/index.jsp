<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Basic Dictionary</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>
<script>
  var confirmDelete = function(e) {
      var wordId = e.target.getAttribute('data-word-id');
      bootbox.confirm({
        message: "Are you sure you want to delete this word?",
        buttons: {
          confirm: {
              label: 'Yes',
              className: 'btn-success'
          },
          cancel: {
              label: 'No',
              className: 'btn-danger'
          }
        },
        callback: function (result) {
          if (result)
            window.location.href=$("#context-path").val() + "/delete?word-id=" + wordId;
        }
      });
    };
</script>
</head>
<body>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <c:set var="role" value="${sessionScope.role}" />
  <c:set var="isAdmin" value="${ not empty role && role=='ADM' }" />

  <input type="hidden" id="context-path" value="${ contextPath }" />
  <div class="container">
    <h2>${ role }</h2>
    <a href="${ contextPath }/logout"><button type="button"
        class="btn btn-link">Sign out</button></a>
    <form class="form-inline" action="${ contextPath }/search">
      <!-- Start of drop down -->
      <div class="form-group">
        <label for="sel-trans-type" class="mb-2 mr-sm-2">Select translation type:</label> <select
          class="form-control mb-2 mr-sm-2" id="sel-trans-type" name="trans-type">
          <c:forEach items="${ transTypes }" var="transType">
            <c:choose>
              <c:when test="${ transType.type == curTransType }">
                <option value="${ transType.type }" selected="selected">${ transType.typeValue }</option>
              </c:when>
              <c:otherwise>
                <option value="${ transType.type }">${ transType.typeValue }</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </div>
      <!-- End of drop down -->
      
      <!-- Start of search box -->
      <div class="input-group">
        <input type="text" class="form-control mb-2" placeholder="Search"
          name="search-word" value="${ searchWord }">
        <div class="input-group-append">
          <button class="btn btn-outline-success mb-2" type="submit">Go</button>
        </div>
      </div>
      <!-- End of search box -->
    </form>

    <c:if test="${ isAdmin }">
      <a href="${ contextPath }/add"><button type="button"
          class="btn btn-primary">Add a Word</button></a>
    </c:if>
    <hr>

    <c:if test="${ not empty words }">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>Word</th>
            <th>Meanings</th>
            <c:if test="${ isAdmin }">
              <th>Operations</th>
            </c:if>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${ words }" var="word">
            <tr>
              <td>${ word.key }</td>
              <td>${ word.meanings }</td>
              <c:if test="${ isAdmin }">
                <c:url value="/edit" var="detailUrl">
                  <c:param name="word-id" value="${ word.id }"></c:param>
                </c:url>
                <c:url value="/delete" var="deleteUrl">
                  <c:param name="word-id" value="${ word.id }"></c:param>
                </c:url>
                <td>
                  <a href="${ detailUrl }"><button
                      type="button" class="btn btn-outline-secondary">Edit</button></a>
                  <button type="button" class="btn text-danger"
                    onclick="confirmDelete(event)" data-word-id="${ word.id }">Delete</button>
                </td>
              </c:if>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <ul class="pagination">
        <c:forEach begin="${ pageStart }" end="${ pageEnd }"
          var="page">
          <c:url value="/search" var="pagingUrl">
            <c:param name="search-word" value="${ searchWord }"></c:param>
            <c:param name="trans-type" value="${ curTransType }"></c:param>
            <c:param name="page" value="${ page }"></c:param>
          </c:url>
          <c:choose>
            <c:when test="${ curPage == page }">
              <li class="page-item active"><a class="page-link"
                href="${ pagingUrl }">${ page }</a></li>
            </c:when>
            <c:otherwise>
              <li class="page-item"><a class="page-link"
                href="${ pagingUrl }">${ page }</a></li>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </ul>
    </c:if>
  </div>
</body>
</html>