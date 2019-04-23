<header>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">E-Notice Board</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mobile-nav">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/developers"> Developers
              </a>
            </li>
          </ul>
              
              
             
          
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              
              <% if(session.getAttribute("uniqueid")!=null) { %>
                <span class="nav-link">Welcome <%= session.getAttribute("uniqueid") %></span>
            
              <% } else { %>
                <a id="register" class="btnSignUp nav-link" href="#">Sign Up</a>
              <% }%>
              
            </li>
            <li class="nav-item">
              <% if(session.getAttribute("uniqueid")!=null) { %>
                <a id="logout" class="nav-link" href="logout">Logout</a>
              <% } else { %>
              <a id="login" class="btnLogin nav-link" href="#">Login</a>
              <% }%>
            </li>
          </ul>
        </div>
      </div>
    </nav>
</header>