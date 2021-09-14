<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>

<%@ include file="jspf/header.jspf"%> 
<c:set var='view' value='/index' scope='session' />


        <div id="indexLeftColumn">
		    <div id="welcomeText">
		        <p>[ welcome text ]</p>
		    </div>
		</div>


        <div id="indexRightColumn">
		    <div class="categoryBox">
		        <a href="#">
		            <span class="categoryLabelText">dairy</span>
		        </a>
		    </div>
		    <div class="categoryBox">
		        <a href="#">
		            <span class="categoryLabelText">meats</span>
		        </a>
		    </div>
		    <div class="categoryBox">
		        <a href="#">
		            <span class="categoryLabelText">bakery</span>
		        </a>
		    </div>
		    <div class="categoryBox">
		        <a href="#">
		            <span class="categoryLabelText">fruit &amp; veg</span>
		        </a>
		    </div>
</div>

<%@ include file="jspf/footer.jspf"%>