<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr bgcolor="#CACACA">
<th>pm_publicFeeNo</th>
<th>pm_clean</th>
<th>pm_maintain</th>
<th>pm_liftMaintain</th>
<th>pm_security</th>
<th>pm_foodWaste</th>
<th>pm_fireInsurance</th>
<th>pm_commission</th>
<th>pm_meeting</th>
<th>pm_publicElectric</th>
<th>pm_liftElectric</th>
<th>pm_TVFee</th>
<th>pm_disinfection</th>
</tr>
<c:forEach items="${list }" var="pm">
<tr>
	<td>${pm.pm_publicFeeNo}</td>
	<td>${pm.pm_clean}</td>
	<td>${pm.pm_maintain}</td>
	<td>${pm.pm_liftMaintain}</td>
	<td>${pm.pm_security}</td>
	<td>${pm.pm_foodWaste}</td>
	<td>${pm.pm_fireInsurance}</td>
	<td>${pm.pm_commission}</td>
	<td>${pm.pm_meeting}</td>
	<td>${pm.pm_publicElectric}</td>
	<td>${pm.pm_liftElectric}</td>
	<td>${pm.pm_TVFee}</td>
	<td>${pm.pm_disinfection}</td>
</tr>
</c:forEach>


</table>
</body>
</html>