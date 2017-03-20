<%@page import="com.zhiyou.indemo.bean.Goods"%>
<%@page import="java.util.List"%>
<%@page import="com.zhiyou.indemo.dao.DaoGoods"%>
<%@page import="com.zhiyou.indemo.utils.SqlUtils"%>
<html>
<body>
	<h2>Hello World!</h2>
	<input type="date">
</body>
<%
	DaoGoods daoGoods = new DaoGoods();
	List<Goods> list = daoGoods.selectAll();
	for (Goods e : list) {
%>

<h2 onclick="click(<%=e.getId()%>>)"><%=e.getName()%></h2>
<%
	}
%>
<script type="text/javascript">
	function click(id) {
		window.location.href = "xxx.jsp?id="+id;
	}
</script>
</html>
