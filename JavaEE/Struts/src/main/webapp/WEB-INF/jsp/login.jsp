<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Struts 2 - Login Application | ViralPatel.net</title>
</head>

<body>
<h2>Struts 2 - Login Application</h2>
<s:actionerror/>
<s:form action="login.action" method="post">
    <s:textfield name="username" key="Login" size="20"/>
    <s:password name="password" key="Password" size="20"/>
    <s:submit method="execute" key="Submit" align="center"/>
</s:form>
</body>
</html>