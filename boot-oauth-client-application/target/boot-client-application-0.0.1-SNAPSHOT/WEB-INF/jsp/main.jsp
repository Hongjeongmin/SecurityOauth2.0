<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
<title>Show Employees</title>
</head>
<body>	
	
	<a href="${oauthInfo.target_uri}/oauth/authorize?client_id=${oauthInfo.client_id}&redirect_uri=${oauthInfo.redirect_uri}&response_type=code&scope=read write trust">login</a>
</body>
</html>