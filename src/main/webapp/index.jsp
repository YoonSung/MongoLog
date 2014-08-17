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
	<div id="form-main">
		<div id="form-div">
			<form class="form" id="form1" action="/" method="post">

				<p class="name">
					<input name="name" type="text"
						class="validate[required,custom[onlyLetter],length[0,100]] feedback-input"
						placeholder="Name" id="name" />
				</p>

				<p class="title">
					<input name="title" type="text"
						class="validate[required,length[2,100]] feedback-input" id="email"
						placeholder="Title" />
				</p>

				<p class="text">
					<textarea name="content"
						class="validate[required,length[6,300]] feedback-input"
						id="comment" placeholder="Comment"></textarea>
				</p>


				<div class="submit">
					<input type="submit" value="SEND" id="button-blue" />
					<div class="ease"></div>
				</div>
			</form>
		</div>

		<div class="page">
			<c:forEach var="result" items="${requestScope.list}"
				varStatus="status">
				<article class="post" itemscope="itemscope"
					itemtype="http://schema.org/BlogPosting">
				<meta content="en" itemprop="inLanguage" />

				<a href="/delete?id=${result._id}" class="post-comments" title="comments">
			<i class="icon-comment">X</i></a>
				<h2 itemprop="headline">
					<a href="#">${result.title}</a>
				</h2>
				<div class="abstract" itemprop="description">
					<p>
						<c:out value="${result.content}" />
					</p>
				</div>
				<footer class="post-meta">
				<ul>
					<li><i class="icon-time"></i> <time datetime="2013-01-02"
							itemprop="datePublished">${result.timestamp}</time></li>
					<li><i class="icon-user"></i> <a href="#" itemprop="author">${result.writer}</a>
					</li>
					<!-- <li><i class="icon-folder-open"></i> <a href="#"
					itemprop="articleSection">category</a></li>
				<li><i class="icon-tags"></i> <a href="#" itemprop="keywords">HTML</a>,
					<a href="#" itemprop="keywords">SCSS</a>, <a href="#"
					itemprop="keywords">microdata</a></li> -->
				</ul>
				</footer> </article>
				<br />
				<br />
			</c:forEach>
		</div>
</body>
</html>