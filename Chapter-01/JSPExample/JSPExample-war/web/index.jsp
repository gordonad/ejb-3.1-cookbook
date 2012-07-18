<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="packt.ConstantsBeanRemote" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<html>
<head><title>Constants</title></head>
<body>
<%!
ConstantsBeanRemote constantsBean;
%>
<%
Context context = null;
try {
    context = new InitialContext();
         constantsBean = (ConstantsBeanRemote) context.lookup(
                 "java:global/JSPExample/JSPExample-ejb/ConstantsBean");
}
catch(Exception e) {
        e.printStackTrace();
}
%><p>
<h1>Constants</h1>
PI: <%= constantsBean.getPI() %><br>
Golden Rule: <%= constantsBean.getGoldenRatio() %>
</body>
</html>
