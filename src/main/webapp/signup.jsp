<%@ include file="jspf/header.jspf"%> 

<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='signup' scope='session' />

<!-- Sign In Form  -->
<div id="caption"><fmt:message key='label.signUpWithEmail'/></div>
	<form action="signup" method="post">
		<label><fmt:message key='label.yourEmail'/></label>&nbsp;<input name="email" value="<fmt:message key='label.yourEmail'/>"><br>	
		<label><fmt:message key='label.yourPassword'/></label>&nbsp;<input name="password" type="password" value="1"><br>	
		<label><fmt:message key='label.signUpName'/></label>&nbsp;<input name="name" value="<fmt:message key='label.signUpName'/>"><br>	

		<input type="submit" value="<fmt:message key='label.enter'/>">
	</form>

<p>


<hr>

   	<!--Requirement jQuery-->
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<!--Load Script and Stylesheet -->
	<script type="text/javascript" src="jquery.simple-dtpicker.js"></script>
	<link type="text/css" href="jquery.simple-dtpicker.css" rel="stylesheet" />
	<!---->

	<style type="text/css">
		body { background-color: #fefefe; padding-left: 2%; padding-bottom: 100px; color: #101010; }
		footer{ font-size:small;position:fixed;right:5px;bottom:5px; }
		a:link, a:visited  { color: #0000ee; }
		pre{ background-color: #eeeeee; margin-left: 1%; margin-right: 2%; padding: 2% 2% 2% 5%; }
		p { font-size: 0.9rem; }
		ul { font-size: 0.9rem; }
		hr { border: 2px solid #eeeeee; margin: 2% 0% 2% -3%; }
		h3 { border-bottom: 2px solid #eeeeee; margin: 2rem 0 2rem -1%; padding-left: 1%; padding-bottom: 0.1em; }
		h4 { border-bottom: 1px solid #eeeeee; margin-top: 2rem; margin-left: -1%; padding-left: 1%; padding-bottom: 0.1em; }
	</style>
  
	
			<input type="datetime-local" name="date17" value="2021-09-20 09:00">
			<script type="text/javascript">
				$(function(){
					$('*[name=date17]').appendDtpicker({
						"inline": false,
						"futureOnly": true,
						"minTime":"08:00",
						"maxTime":"22:01"

					});
				});
			</script>
	  


<%@ include file="jspf/footer.jspf"%>