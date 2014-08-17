<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="index.css" rel="stylesheet" />
</head>
<body>
	<div class="page">
		<c:forEach var="result" items="${requestScope.list}"
			varStatus="status">
			<article class="post" itemscope="itemscope"
				itemtype="http://schema.org/BlogPosting">
			<meta content="en" itemprop="inLanguage" />

			<a href="#" class="post-comments" title="comments"><i
				class="icon-comment">10</i></a>
			<h2 itemprop="headline">
				<a href="#">Title</a>
			</h2>
			<div class="abstract" itemprop="description">
				<p><c:out value="${result}" /></p>
			</div>
			<footer class="post-meta">
			<ul>
				<li><i class="icon-time"></i> <time datetime="2013-01-02"
						itemprop="datePublished">2013-01-02</time></li>
				<li><i class="icon-user"></i> <a href="#" itemprop="author">flocko</a>
				</li>
				<li><i class="icon-folder-open"></i> <a href="#"
					itemprop="articleSection">category</a></li>
				<li><i class="icon-tags"></i> <a href="#" itemprop="keywords">HTML</a>,
					<a href="#" itemprop="keywords">SCSS</a>, <a href="#"
					itemprop="keywords">microdata</a></li>
			</ul>
			</footer> </article>
		</c:forEach>
	</div>
</body>
</html>