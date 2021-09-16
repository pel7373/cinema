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

<label for="appt">Choose a time for your meeting:</label>

<input type="time" id="appt" name="appt"
       min="09:00" max="18:00" required>

<small>Office hours are 9am to 6pm</small>

<hr>
<label for="start">Start date:</label>

<input type="date" id="start" name="trip-start"
       value="2018-07-22"
       min="2018-01-01" max="2018-12-31">

<hr>
<form>
  <div>
    <label for="bday">Введите дату вашего рождения:</label>
    <input type="date" id="bday" name="bday">
  </div>
</form>

<hr>

<form>
    <div class="nativeDatePicker">
      <label for="bday">Enter your birthday:</label>
      <input type="date" id="bday" name="bday">
      <span class="validity"></span>
    </div>
    <p class="fallbackLabel">Enter your birthday:</p>
    <div class="fallbackDatePicker">
      <span>
        <label for="day">Day:</label>
        <select id="day" name="day" min="1" max="31>
        </select>
      </span>
      <span>
        <label for="month">Month:</label>
        <select id="month" name="month">
          <option selected>January</option>
          <option>February</option>
          <option>March</option>
          <option>April</option>
          <option>May</option>
          <option>June</option>
          <option>July</option>
          <option>August</option>
          <option>September</option>
          <option>October</option>
          <option>November</option>
          <option>December</option>
        </select>
      </span>
      <span>
        <label for="year">Year:</label>
        <select id="year" name="year">
        </select>
      </span>
    </div>
</form>

<hr>
<form>
  <div>
    <label for="appt-time">Choose an appointment time (opening hours 12:00 to 18:00): </label>
    <input id="appt-time" type="time" name="appt-time"
           min="12:00" max="18:00" required
           pattern="[0-9]{2}:[0-9]{2}">
    <span class="validity"></span>
  </div>
  <div>
      <input type="submit" value="Submit form">
  </div>
</form>

<%@ include file="jspf/footer.jspf"%>