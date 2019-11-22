<%@ page import="com.ccsu.cs530.successcentral.model.SessionForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Session Form list"/>
</jsp:include>
<body>
<jsp:include page="includes/navbar.jsp"/>

<!-- Display flash message -->
<% if (request.getSession().getAttribute("message") != null) { %>
<div class="text-center"><%= request.getSession().getAttribute("message") %></div>
<% request.getSession().removeAttribute("message");} %>

<div class="col text-center">
    <h2>Session Forms</h2>
</div>

<!-- Display Session Form table  -->
<div class="container-liquid m-4">
    <div class="m-2 row">
        <div class="col align-items-center">
            <form class="form-inline" action="session_form_list" method="get">
                <div class="form-row align-items-center">
                    <!-- Display Search Bar-->
                    <div class="col-auto">
                        <div class="form-group">
                            <label class="col-form-label text-right">Search for a Session Form by Name</label>
                        </div>
                    </div>
                    <%String search, sortBy;
                        if(request.getAttribute("search") != null){
                            search = (String)request.getAttribute("search");
                        } else search = "";
                        if(request.getAttribute("sortBy")!= null) sortBy = (String)request.getAttribute("sortBy");
                        else sortBy = "";
                    %>
                    <div class="col-auto">
                        <input type="text"
                               autocomplete="off"
                               spellcheck="false"
                               name="search"
                               value="<%=search%>"
                        />
                        <button type="submit" class="btn btn-sm btn-primary"> Search </button>
                    </div>
                </div>
            </form>
        </div>
        <div>
            <!-- Display  Sorting Options -->
            <div class="col" align="right">
                <form class="form-inline" action="session_form_list" method="get">
                    <div class="form-row alight-items-center">
                        <div class="col-auto" >
                            <div class="form-group ">
                                <label class="col-form-label text-right">Sort Session Form by</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-auto">
                        <select name="sortBy" onchange="this.form.submit()">
                            <option value=""  <%if(sortBy.equals("")) {%>selected<%}%>></option>
<%--                            <option value="last_name" <%if(sortBy.equals("last_name")) {%>selected<%}%>>Mentee Last Name</option>--%>
                            <option value="full_name" <%if(sortBy.equals("full_name")) {%>selected<%}%>>Mentee Name</option>
<%--                            <option value="session_number" <%if(sortBy.equals("session_number")) {%>selected<%}%>>Session Number</option>--%>
                            <option value="mentor" <%if(sortBy.equals("mentor")) {%>selected<%}%>>Mentor</option>
                        </select>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Mentee Table -->
    <table style="background-color: white" class="table table-sm table-striped table-bordered table-responsive-sm">
        <thead class="table-secondary">
        <tr class="text-center">
            <th scope="col">Mentor</th>
            <th scope="col">Mentee</th>
            <th scope="col">Session Number</th>
        </tr>
        </thead>
        <form name="session_form" >
            <input type="hidden" name="work" value="">
            <input type="hidden" name="menteeEmail" value="">
            <input type="hidden" name="returnPage" value="mentee_list">
            <tbody>
            <%for (SessionForm sessionform : (List<SessionForm>) request.getAttribute("sessionforms")) {
                String mentorName = "";
                mentorName = sessionform.getMentor();
//                mentorName =  sessionform.getMentor().getFirstName() + " " + sessionform.getMentor().getLastName();
            %>
            <tr class="text-center">
                <td><%= mentorName %></td>
                <td><%= sessionform.getFullName() %></td>
<%--                <td><%= sessionform.getLastName() %></td>--%>
<%--                 todo link for the session number--%>
                <th scope="row"><a
                        href="session_form_info?firstName=<%=sessionform.getFirstName()%>&lastName=<%=sessionform.getLastName()%>&seshNum=<%=sessionform.getSessionNum()%>"><%= sessionform.getSessionNum() %></a></th>
            </tr>
            <% } %>
            </tbody>
        </form>
    </table>

    <!-- Paginator -->
    <div class="ml-2 text-center">
        <%
            int pageCount = (int)request.getAttribute("pageCount");
            int perBlockNum = (int)request.getAttribute("perBlockNum");
            int startPage = (int)request.getAttribute("startPage");
            int endPage = (int)request.getAttribute("endPage");
            if(startPage > perBlockNum){
        %>
        <a href="session_form_list?page=<%= startPage - perBlockNum %>">[Prev]</a>
        <%}
            for (int i = startPage; i <= endPage; i++) {
                if(i == (int)request.getAttribute("page")){
        %>
        [<%=i %>]
        <%
        } else {
        %>
        <a href="session_form_list?page=<%= i %>">[<%= i %>]</a>
        <%
                }
            }

            if(endPage < pageCount){
        %>
        <a href="session_form_list?page=<%= startPage + perBlockNum %>">[Next]</a>
        <%
            }

        %>
    </div>
</div>
<script>
    // function confirm_del(deleteId){
    //     var result = confirm('Do you really want to delete this mentee '+deleteId+'?');
    //     if(result == true) {
    //         document.delete_mentee.elements['menteeEmail'].value = deleteId;
    //         document.delete_mentee.elements['work'].value = 'delete';
    //     } else {
    //         return false;
    //     }
    // }
</script>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>